package LatorreFAI1931Parcial2;
import java.util.concurrent.Semaphore;
public class MontañaRusa {
	private Semaphore semCarro;
	private int capacidad;
	private int pasajerosEsp=0;
	private boolean abierto=true;
	private Semaphore mutex= new Semaphore(1,true);
	private Semaphore semArranque= new Semaphore(0,true);
	private int contPasajCarro=0;
	private int cantVueltas;
	private Semaphore semBajada= new Semaphore(0,true);
	private int esperandoArranque=0;
	private int esperandoBajar=0;

	
	public MontañaRusa(int capCarro, int cantV) {
		this.capacidad=capCarro;
		this.cantVueltas=cantV;
		this.semCarro= new Semaphore(capCarro,true);
	}
	
	public boolean intentarSubirMontaña() {
		boolean subio=false;
		try {
		mutex.acquire();
		pasajerosEsp++;
		mutex.release();
		
		semCarro.acquire();//Entran hasta la capacidad del carro el resto espera
		
		mutex.acquire();

		if(abierto) {
			subio=true;//retorno si el pasajero paso porque subio o porque cerro la montaña
			this.contPasajCarro++;//Indico que hay un pasajero mas en el carro
		}
		pasajerosEsp--;//Hay un pasajero menos esperando
		mutex.release();
		}catch(InterruptedException e) {}
		return subio;
	}
	
	public void empezar() {
		boolean lleno= false;
		try {
		mutex.acquire();
				if(this.contPasajCarro==this.capacidad) {
					lleno=true;
				}
		mutex.release();
		
			if(!lleno) {//mientras no este lleno el carro esperan, entrarian todos excepto el ultimo
				mutex.acquire();
				esperandoArranque++;//Uno mas esperando arrancar
				mutex.release();
				semArranque.acquire();
			}else {//El ultimo pasajero liberaria los otros, se supone que aca entra uno solo
					mutex.acquire();
					semArranque.release(this.esperandoArranque);//Libero los que estaban esperando arrancar
					esperandoArranque=0;//indico que no queda nadie esperando arrancar
					mutex.release();
					
					
					mutex.acquire();
					this.cantVueltas--;//El carro da una vuelta mas
					if(this.cantVueltas==0) {//Si es la ultima vuelta que da
						this.abierto=false;//Indico que cerro
						this.semCarro.release(this.pasajerosEsp);//Libero a todos los pasajeros que esperaban para que se vayan a su casa
					}
					mutex.release();
				}
		}catch(InterruptedException e) {}
		
	}
	
	public void bajarCarro() {
		boolean bajanTodos=false;
		try {
			mutex.acquire();
			this.contPasajCarro--;//hay un pasajero que quiere bajar
			if(this.contPasajCarro==0) {
				bajanTodos=true;
			}
			mutex.release();
			
			if(!bajanTodos) {//Si no quieren bajar todos los pasajeros quedan esperando
				mutex.acquire();
				esperandoBajar++;
				mutex.release();
				this.semBajada.acquire();//Con esto aseguro que baje todos a la vez
			}else {
				mutex.acquire();
				this.semBajada.release(esperandoBajar);//El ultimo libera a los otros que lo estaban esperando para que poder bajar
				this.esperandoBajar=0;
				this.semCarro.release(capacidad);//Cuando bajan todos del carro libero todos los permisos de una asi no entran mientras salen
				mutex.release();
			}
			
			
			
		}catch(InterruptedException e) {}
	}
}
