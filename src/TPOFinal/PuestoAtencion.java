package TPOFinal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class PuestoAtencion {
	private Aeropuerto aeropuerto;//El aeropuerto tiene al centro de atencion y el centro de atencion conoce a que aeropuerto pertenece
	private int nro;//Depende de la plataforma/Aerolinea
	private Semaphore semEsperaAtencion=new Semaphore(0,true);
	private BlockingQueue <Pasajero> colaPuestoAten;

	private int capacidad;
	private Semaphore mutex=new Semaphore(1,true);
	private Semaphore semAtencion=new Semaphore(0,true);
	public PuestoAtencion(int nro, int capacidad, Aeropuerto aeropuerto) {
		this.nro=nro;
		this.aeropuerto=aeropuerto;
		this.capacidad = capacidad;
		colaPuestoAten = new LinkedBlockingQueue<Pasajero>(this.capacidad);
	}
	
	//Operaciones pasajero
	public void esperarEnPuesto(Pasajero pasajero) {
		try {
			/*mutex.acquire();
			Thread.sleep(1);//Para que lleguen al semaforo en orden
			mutex.release();*/
			this.colaPuestoAten.put(pasajero);//Se mete en la cola de espera, siempre hay lugar porque se controla en hall
			this.semEsperaAtencion.acquire();
		}catch(InterruptedException e) {}
	}
	
	public void esperaFinAtencion() {//Espera a que terminen de atenderlo
		try {
			this.semAtencion.acquire();//queda lockeado hasta que terminan de atenderlo
		}catch(InterruptedException e) {}
	}
	
	//Operaciones empleadoPuestoAtencion
	public void atenderCliente(EmpleadoPuestoAtencion empleado) {
		Pasajero pasajero=null;
		try {
			Thread.sleep(100);//Para que salgan mensajes en orden y quede claro en testeo
			pasajero=(Pasajero)this.colaPuestoAten.take();//Saca la referencia del cliente de la cola, si esta vacia queda bloqueado
			this.semEsperaAtencion.release();//Avisa a un hilo que puede pasar
			empleado.atenderCliente(pasajero,aeropuerto);
			this.semAtencion.release();//Indica al pasajero que termino de ser atendido
		}catch(InterruptedException e) {}


	}
	public int getNro() {
		return this.nro;
	}
}
