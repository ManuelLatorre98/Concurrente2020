package TPOFinal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class PuestoAtencion {
	private Aeropuerto aeropuerto;//El aeropuerto tiene al centro de atencion y el centro de atencion conoce a que aeropuerto pertenece
	private int nro;//Depende de la plataforma/Aerolinea
	private Semaphore semHall=new Semaphore (0,true);
	private Semaphore semEsperaAtencion=new Semaphore(0,true);
	private BlockingQueue <Pasajero> colaPuestoAten = new LinkedBlockingQueue<Pasajero>(5);//Cola con limite 5
	private BlockingQueue <Pasajero> colaHall = new LinkedBlockingQueue<Pasajero>();//Ilimitada
	
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore semAtencion=new Semaphore(0,true);
	public PuestoAtencion(int nro, Aeropuerto aeropuerto) {
		this.nro=nro;
		this.aeropuerto=aeropuerto;
	}
	
	//Operaciones pasajero
	public void esperarEnHall(Pasajero pasajero) {//Entra al hall y espera a que lo llame el guardia
		try {
			mutex.acquire();
			Thread.sleep(1);//Para que lleguen al semaforo en orden
			mutex.release();
			this.colaHall.put(pasajero);
			this.semHall.acquire();//Queda lockeado hasta que lo llaman
		}catch(InterruptedException e) {}
	}
	
	public void esperaAtencionEnCola() {//Espera "dentro de la cola" a que lo atiendan
		try {
		
			this.semEsperaAtencion.acquire();
		}catch(InterruptedException e) {}
	}
	
	public void esperaFinAtencion() {//Espera a que terminen de atenderlo
		try {
			this.semAtencion.acquire();//queda lockeado hasta que terminan de atenderlo
		}catch(InterruptedException e) {}
	}
	
	
	//Operaciones guardia
	public void llamarCliente() {
		Pasajero pasajero;
		try {

			this.colaPuestoAten.put(this.colaHall.take());//Saca un hilo del hall y lo mete al puesto, si el hall queda vacio queda bloqueado, si el puestoAtencion esta lleno queda bloqueado
			this.semHall.release();//Le indica al hilo del hall que pase a la cola del puesto
			
			
			
		}catch(InterruptedException e) {}
	}
	
	//Operaciones empleadoPuestoAtencion
	public void atenderCliente(EmpleadoPuestoAtencion empleado) {
		Pasajero pasajero=null;
		try {
			Thread.sleep(100);//Para que salgan mensajes en orden y quede claro en testeo
			pasajero=(Pasajero)this.colaPuestoAten.take();//Saca la referencia del cliente de la cola, si esta vacia queda bloqueado
		
		}catch(InterruptedException e) {}
			this.semEsperaAtencion.release();//Avisa a un hilo que puede pasar
			empleado.atenderCliente(pasajero,aeropuerto);
			this.semAtencion.release();//Indica al pasajero que termino de ser atendido
	}
	public int getNro() {
		return this.nro;
	}
}
