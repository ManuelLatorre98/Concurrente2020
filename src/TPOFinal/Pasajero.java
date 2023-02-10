package TPOFinal;

public class Pasajero implements Runnable{
	private Aeropuerto aeropuerto;
	private Reserva reserva;
	private String nombre;
	private PuestoAtencion puestoAtenAsignado;
	private Terminal terminalAsignada;
	private Reloj reloj;
	//private PuestoEmbarque puestoEmbarqueAsignado;
	private boolean entraShop;
	private boolean compraShop;
	
	private static final String BLACK = "\u001B[30m";
	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String BLUE = "\u001B[34m";
	private static final String PURPLE = "\u001B[35m";
	private static final String CYAN = "\u001B[36m";
	private static final String WHITE = "\u001B[37m";
	private static final String RESET = "\033[0m";  // Text Reset
	private static final String ROJOFONDO = "\u001b[41;1m";  
	private static final String AZULFONDO = "\u001b[44m"; 
	
	public Pasajero(Aeropuerto aero,Reloj reloj) {
		this.aeropuerto=aero;
		this.reloj=reloj;
	}
	
	public void run() {
		//Entrada aeropuerto
		//while(true) {//Dificulta testeo
			System.out.println("El "+Thread.currentThread().getName()+" intenta entrar al aeropuerto");
			this.aeropuerto.intentoEntradaAeropuerto();
			System.out.println("El "+Thread.currentThread().getName()+" entra al aeropuerto");
			this.aeropuerto.entrarAeropuerto(this);
			System.out.println("El "+Thread.currentThread().getName()+" entro al aeropuerto y se le asigno la "+this.reserva.toString());
		
		//Centro informes
			System.out.println("El "+Thread.currentThread().getName()+" hace cola para entrar al centro de informes");
			this.aeropuerto.esperaAtencionCentroInf(this);
			System.out.println(CYAN+"AL "+Thread.currentThread().getName()+" lo llaman y pasa al centro de informes"+RESET);
			this.aeropuerto.pasarCentroInf();
			System.out.println(CYAN+"El "+Thread.currentThread().getName()+" termina de ser atendido y es derivado al centro de atencion nro "+this.puestoAtenAsignado.getNro()+" para realizar el checkin y espera a ser atendido"+RESET);

		//Puesto atencion
			//System.out.println(GREEN+"El "+Thread.currentThread().getName()+" es entra al hall central y espera  a ser llamado a un centro de atencion"+RESET);
			aeropuerto.getHallCentral().entrarHallCentral(this.puestoAtenAsignado.getNro()); //entra al hall con su numero de puesto
			System.out.println(GREEN+"El "+Thread.currentThread().getName()+" es llamado por el guardia y hace cola en el puesto de atencion nro: "+this.puestoAtenAsignado.getNro()+RESET);
			this.puestoAtenAsignado.esperarEnPuesto(this);
			System.out.println(GREEN+"El "+Thread.currentThread().getName()+" es atendido en el puesto de atencion nro: "+this.puestoAtenAsignado.getNro()+RESET);
			this.puestoAtenAsignado.esperaFinAtencion();
			aeropuerto.getHallCentral().salirDelHall(this.puestoAtenAsignado.getNro());
			System.out.println(GREEN+"El "+Thread.currentThread().getName()+" termino de ser atendido en el centro de atencion nro: "+this.puestoAtenAsignado.getNro()+" y se le indico que vaya a la terminal: "+this.terminalAsignada.getLetraTerminal()+". Va a tomar el tren"+RESET);

		//Tren
			this.aeropuerto.esperarTren();
			this.aeropuerto.indicarTerminalParada(terminalAsignada);
			this.aeropuerto.tomarTren();
			System.out.println(YELLOW+"El "+Thread.currentThread().getName()+" y viaja a su terminal asignada"+RESET);
			this.aeropuerto.viajarEnTren(terminalAsignada);
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {}//Para que el mensaje salga en orden justo despues del mensaje de que frena el tren
			System.out.println(YELLOW+"El "+Thread.currentThread().getName()+" baja del tren y entra a la terminal: "+this.terminalAsignada.getLetraTerminal()+RESET);
	
		//Terminal
			this.entrarShop();//El pasajero decide si va a entrar al shop de la terminal o no
			if(this.entraShop) {//Si el pasajero quiere entrar al shop
				if(this.tiempoFreeShop()) {//Si tiene tiempo para ir al shop va
					if(this.terminalAsignada.intentarEntrarShop(this)) {//Intenta entrar al shop, si lo logra
						if(this.compraShop) {//Si tiene pensado comprar algo en el shop
							System.out.println("El pasajero "+Thread.currentThread().getName()+" entra en el shop y compra");
							this.terminalAsignada.comprarEnShop(this);
						}else {
							System.out.println("El pasajero "+Thread.currentThread().getName()+" entra en el shop y mira");
							this.mirarEnShop();
						}
						System.out.println("El pasajero "+Thread.currentThread().getName()+" sale del shop");
						this.terminalAsignada.salirShop();
					}
				}
			}
			if(this.reloj.getHoraInt()<=this.reloj.horaStringToHoraInt(this.reserva.getHoraArribo())) {
				System.out.println("El pasajero "+Thread.currentThread().getName()+" espera sentado el embarque a las "+this.reserva.getHoraArribo()+"hs");
				this.terminalAsignada.esperarEmbarque(this.reserva);
				System.out.println(AZULFONDO+"El pasajero "+Thread.currentThread().getName()+" es llamado para embarque en puesto de embarque nro "+this.reserva.getNroPuerto()+" y toma su vuelo a las "+this.reserva.getHoraArribo()+RESET);
			}else {
				System.out.println(ROJOFONDO+"El pasajero "+Thread.currentThread().getName()+" perdio su vuelo de las "+reserva.getHoraArribo()+"hs. Se va del aeropuerto"+RESET);
			}
		}
	
	private void entrarShop() {
		int valor;
		valor=(int)(Math.random()*2);
		if(valor==0) {
			this.entraShop=false;
			
		}else {
			this.entraShop=true;
			this.compraShop=(Math.random() < 0.5);//Si entra puede comprar o no
		}
	}
	
	private boolean tiempoFreeShop() {
		int horaArriboPasajeroMin=reloj.horaStringToHoraInt(this.reserva.getHoraArribo());//Obtengo la hora a la que tiene que arribar el pasajero
		boolean tieneTiempo=false;
		if(horaArriboPasajeroMin - reloj.getHoraInt() >= 60) {//Si el arribo es dentro de una hora o mas puede entrar al shop
			tieneTiempo=true;
		}
		return tieneTiempo;
	}
	
	public void compraEnShop() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
	}
	
	public void mirarEnShop() {
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {}
	}
	
	public void setReserva(Reserva res) {
		this.reserva=res;
	}
	
	public void setPuestoAtenAsig(PuestoAtencion centro) {
		this.puestoAtenAsignado=centro;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setTerminalAsignada(Terminal terminal) {
		this.terminalAsignada=terminal;
	}
	
	public String getNombre() {//TESTEO BORRAR
		return this.nombre;
	}
	
	
	
	public Reserva getReserva() {
		return this.reserva;
	}
}
