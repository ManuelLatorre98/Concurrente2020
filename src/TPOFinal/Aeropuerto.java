package TPOFinal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Aeropuerto {
	private static final String ROJOFONDO = "\u001b[41;1m";
	private static final String RESET = "\033[0m";  // Text Reset
	private Terminal[]terminales;//Recibe por parametro, Las terminales estan asociadas con el nro de reserva
	private Reloj reloj;//Dato compartido
	private String horaApertura;
	private String horaCierre;
	private int horaAperturaInt;//En minutos
	private int horaCierreInt;//En minuto
	
	boolean abierto=false;

	private int capPuestosAten=5;
	private PuestoAtencion[]puestosAten;
	
	//CentroInformes
	private Semaphore semCentroInf=new Semaphore (0,true);
	private Semaphore semAtencionCentroInf=new Semaphore(0,true);
	private Semaphore mutexCentroInf=new Semaphore(1,true);
	private Semaphore mutexCentroInfDatos=new Semaphore(1,true);
	private BlockingQueue <Pasajero> colaCentroInf = new LinkedBlockingQueue<Pasajero>();//Cola ilimitada

	//Hall Puestos de atencion
	private HallCentral hallCentral = new HallCentral(puestosAten.length,capPuestosAten);
	//Tren
	private int capacidadTren=10;//La responsabilidad de setear esto creo que deberia ser del thread tren o directamente el tren deberia dar dicha info, lo seteo asi para no enrredar tanto
	private int tiempoEsperaMs=15000;
	private Lock lock;
	private Condition esperaPasajeros;
	private Condition esperaTren;
	private Condition esperaArranque;
	private boolean enMarcha=false;
	private CyclicBarrier barrierStartTren=new CyclicBarrier(capacidadTren);
	private int[]cantPasajerosTerminal;
	private Condition viajando;
	private int cantPasajerosEsp=0;
	private char letraTerminalActual='0';//De origen
	private int cantPasajerosEnTren=0;
	
	
	public Aeropuerto(String horaApertura, String horaCierre,Reloj reloj, Terminal []terminales) {
		this.terminales=terminales;
		this.horaApertura=horaApertura;
		this.horaCierre=horaCierre;
		this.reloj=reloj;
		this.horaAperturaInt=reloj.traducirHora(horaApertura);
		this.horaCierreInt=reloj.traducirHora(horaCierre);
		
		this.puestosAten=new PuestoAtencion[this.terminales.length];//Voy a tener 1 puesto de atencion por terminal/aerolinea
		this.crearCentrosAtencion();
		
		this.lock=new ReentrantLock(true);
		this.esperaPasajeros=this.lock.newCondition();
		this.esperaTren=this.lock.newCondition();
		this.esperaArranque=this.lock.newCondition();
		this.viajando=this.lock.newCondition();
		this.cantPasajerosTerminal=new int[this.terminales.length];
		
	}
	
	//Operaciones Aeropuerto
	
	private void crearCentrosAtencion() {//Un centro por aerolinea
		for (int i = 0; i < this.terminales.length; i++) {
			this.puestosAten[i]=new PuestoAtencion((i+1),this.capPuestosAten,this);//Los centros de atencion se identifican por su plataforma/aerolinea
		}
	}

	public PuestoAtencion[]getListaPuestosAtencion(){
		return this.puestosAten;
	}
	public PuestoAtencion getPuestoAtencion(int nroPuesto) {
		PuestoAtencion puestoAten=null;
		try {
			this.mutexCentroInfDatos.acquire();
			puestoAten=this.puestosAten[nroPuesto];
			this.mutexCentroInfDatos.release();
		}catch(InterruptedException e) {}
		return puestoAten;
	}
	
	public Terminal getTerminal(int nro) {
		return this.terminales[nro];
	}
	
	public int ubicarTerminal(Terminal terminal) {
		boolean encontrado=false;
		int pos=-1;
		int i=0;
		while(!encontrado && i<this.terminales.length) {
			if(this.terminales[i].equals(terminal)) {
				pos=i;
				encontrado=true;
			}
			i++;
		}
		return pos;
	}

	public HallCentral getHallCentral(){
		return hallCentral;
	}
	
	//OPERACIONES PASAJERO
	public synchronized void intentoEntradaAeropuerto() {
		try {
			while(!abierto) {
				this.wait();
			}
		}catch(InterruptedException e) {}
	}
	
	public void entrarAeropuerto(Pasajero pasajero) {//La reserva se le asigna cuando llego al aeropuerto por eso lo hago aca
		int nroReserva=(int)(Math.random()*this.terminales.length)+1;//El nro de reserva va por aerolinea/terminal
		int puestoTerminal=0;//Define el puesto de embarque, 1 puesto de embarque por vuelo, 7 vuelos por aerolinea/terminal,
		Reserva reserva;
		String horaArribo;
		PuestoEmbarque puestoEmbarquePasajero;
		puestoTerminal=((int)(Math.random()*this.terminales[nroReserva-1].getCantidadPuestosEmbarque())+1)+(this.terminales[nroReserva-1].getCantidadPuestosEmbarque()*(nroReserva-1));//Esto me da un puesto de embarque en funcion de la terminal/aerolinea
		puestoEmbarquePasajero=this.terminales[nroReserva-1].getPuestoEmbarque(puestoTerminal);//Obtengo el puesto asignado
		horaArribo=puestoEmbarquePasajero.getHorarioRandom();//Me entrega uno de sus horarios de embarque
		reserva=new Reserva(nroReserva,puestoEmbarquePasajero.getNroPuesto(),horaArribo);
		pasajero.setReserva(reserva);
	}
	
	public void esperaAtencionCentroInf(Pasajero pasajero) {
		try {
			this.mutexCentroInf.acquire();//El lock de la blocking queue va tan rapido que los hilos llegan al semaforo en orden distinto al que entran en cola
			Thread.sleep(1);//Con esto hago que entren con una diferencia de 1ms a la cola y por lo tanto al semaforo lleguan en el orden que entraron a la cola
			this.mutexCentroInf.release();
			this.colaCentroInf.put(pasajero);//Pongo una referencia a si mismo en la cola
			
			this.semCentroInf.acquire();//Queda loqueado hasta que le digan que puede pasar

		
		}catch(InterruptedException e) {}
	}
	
	public void pasarCentroInf() {
		try {
			this.semAtencionCentroInf.acquire();//Queda lockeado hasta que lo liberan
		}catch(InterruptedException e) {}
	}
	
	public void esperarTren() {
		this.lock.lock();
		while(enMarcha || this.cantPasajerosEnTren==this.capacidadTren) {//Mientras el tren esta en marcha o esta lleno los pasajeros esperan
			try {
				this.esperaTren.await();
			}catch(InterruptedException e) {};
		}
		this.cantPasajerosEsp++;
		if(this.cantPasajerosEsp==1) {//El primero indica que hay pasajeros esperando
			this.esperaPasajeros.signal();
		}
		this.cantPasajerosEnTren++;//Se va a subir al tren asi que suma uno
		this.lock.unlock();
	}
	
	public void indicarTerminalParada(Terminal terminalAsignada){
		int posTerminal;
		this.lock.lock();
		posTerminal=this.ubicarTerminal(terminalAsignada);//La terminal y por lo tanto su letra corresponde a una posicion del arreglo de terminales del aeropuerto
		this.cantPasajerosTerminal[posTerminal]=this.cantPasajerosTerminal[posTerminal]+1;//pos 0=terminalA, pos1=terminalB, etc.
		//System.out.println("PASAJEROS DENTRO DEL TREN "+this.barrierStartTren.getNumberWaiting());//Lo pongo aca para aprovechar el lock
		this.lock.unlock(); 
	}
	
	
	public void tomarTren() {
		try {	
			this.barrierStartTren.await(this.tiempoEsperaMs,TimeUnit.MILLISECONDS);//Espera a que el tren este lleno o pase el tiempo
		}catch(InterruptedException e) {
		}catch(BrokenBarrierException e) {
		}catch(TimeoutException e) {//Suponogo que viene para aca con el timeout
		}
	}
	
	public void viajarEnTren(Terminal terminalAsignada) {
		this.lock.lock();
		this.cantPasajerosEsp--;//Una vez en el tren el pasajero indica que ya no espera
		if(!this.enMarcha && cantPasajerosEsp==0) {//Si el tren sigue parado y ya no quedan pasajeros
			this.enMarcha=true;//El ultimo indica que se puede poner en marcha
			this.esperaArranque.signal();//Le avisa al tren que puede arrancar ya sea porque se libero la barrier por capacidad o tiempo
		}
		while(this.letraTerminalActual!=terminalAsignada.getLetraTerminal()) {//Mientras el tren no haya parado en su terminal espera
			try {
				this.viajando.await();
			}catch(InterruptedException e) {}
		}
		this.cantPasajerosEnTren--;//Se baja del tren asi que hay uno menos
		this.lock.unlock();
		
		
	}
	

	
	//Operaciones centroInformes
		public void atiendeCentro(CentroInformes centro) {
			Pasajero pasajero=null;
			
			try {
				Thread.sleep(10);//Para que salgan mensajes en orden y quede claro en testeo
				pasajero=(Pasajero)this.colaCentroInf.take();//Saca la referencia del cliente de la cola, si esta vacia queda bloqueado
			}catch(InterruptedException e) {}

			//System.out.println("PASAJEROOOOOOOOOOOOOO"+pasajero.getNombre());//Prueba que atiende al que saca de la lista junto con mensaje en centroInforems.atenderCliente(). con un sleep de 500 antes del take se ven mensajes en orden
			this.semCentroInf.release();//Avisa a un pasajero que puede pasar

			//El setPuestoAten tiene mas sentido que se haga en centroInformes o aca?

			centro.atenderCliente(pasajero);//Atiende al pasajero y se lleva la referencia de los centros de atencion para poder trabajar
			this.semAtencionCentroInf.release();
		}
	
	//Operaciones del tren
	public void recogerPasajeros() {
		lock.lock();
		while(this.cantPasajerosEsp==0) {//Mientras no haya pasajeros espera, el primer pasajero que llegue le avisa
			try {
				this.esperaPasajeros.await();
			}catch(InterruptedException e) {}
		}
		
		while(!enMarcha) {//Mientras esta parado recogiendo pasajeros espera
			try {
				this.esperaArranque.await();
			}catch(InterruptedException e) {}
		}

		lock.unlock();
	}
	
	public void transportar(Tren tren) {
		boolean hayPasajeros;
		//El for no lo protejo porque me retiene el lock y cuando hago signalAll solo le llega para los pasajeros de la ultima estacion
		//de todas maneras la cantidad de terminales no se tendria que ver modificada en ningun momento por lo que no se seria necesario 
		//Asegurar exclusion mutua ya que los hilos solo acceden a esta y siempre tiene un estado fijo
		for (int i = 0; i < this.terminales.length; i++) {//Recorre terminal por terminal
			lock.lock();
			tren.viajar(this.terminales[i].getLetraTerminal());//Viaja
			hayPasajeros=this.cantPasajerosTerminal[i]>0;//tomo y retomo el lock luego para que los mensajes salgan en orden
			lock.unlock();
			
			if(hayPasajeros) {//Si hay pasajeros que quieren bajar en esa terminal el tren frena y les avisa
				lock.lock();
				this.letraTerminalActual=this.terminales[i].getLetraTerminal();//Indico en que terminal esta parado el tren
				this.viajando.signalAll();//Avisa a todos los pasajeros que bajo en una terminal, ellos decidiran si les corresponde o no
				lock.unlock();
				
				//Antes era todo un solo lock y salia a destiempo el mensaje de parada del tren con la bajada de los pasajeros
				lock.lock();
				tren.frenarTerminal(this.terminales[i].getLetraTerminal(), this.cantPasajerosTerminal[i]);//se liberaban antes 1,5seg despues de este mensaje porque el lock sigue siendo del tren 
				this.cantPasajerosTerminal[i]=0;//inidico que no quedan pasajeros para bajar en esa terminal para la vuelta
				lock.unlock();
			}
			
		}	
	}
	
	public void volverOrigen(Tren tren) {
		lock.lock();
		tren.viajar('0');//Viaja de vuelta al origen
		this.letraTerminalActual='0';
		this.enMarcha=false;//Cuando termina de recorrer todas las terminales indica que no estan en marcha
		this.esperaTren.signalAll();//Le avisa a todos los pasajeros que ya llego
		lock.unlock();
	}

	
	
	//Operaciones GestorHora
	public synchronized void revisionHora() {//Luego de actualizarse la hora se notifica por si estaba cerrado y pasa a estar abierto
		if(!abierto && this.reloj.getHoraInt()>=this.horaAperturaInt && this.reloj.getHoraInt()<=this.horaCierreInt) {//Si estaba cerrado y es hora de abrir notifica
			this.abierto=true;
			System.out.println(ROJOFONDO+"ABRE EL AEROPUERTO"+RESET);
			this.notifyAll();
		}else
			if(abierto && this.reloj.getHoraInt()==this.horaCierreInt) {
				this.abierto=false;
				System.out.println(ROJOFONDO+"CIERRA EL AEROPUERTO"+RESET);
				
			}
	}
	
	
	
}
