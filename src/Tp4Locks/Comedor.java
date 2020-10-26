package Tp4Locks;
import java.util.concurrent.Semaphore;
public class Comedor {
	private Semaphore semCafeteria= new Semaphore(1,true);
	private Semaphore semMozo= new Semaphore(0,true);
	private Semaphore semCocinero= new Semaphore(0,true);
	private Semaphore semServicio= new Semaphore(0,true);
	private Semaphore semEsperaCocina= new Semaphore(0,true);
	
	public Comedor() {
	}
	
	public void acercarse() {
		if(semCafeteria.tryAcquire()) {
			System.out.println("La cafeteria esta libre asi que "+Thread.currentThread().getName()+" entra");
			semMozo.release();
			try {
				semServicio.acquire();//Espera a que el mozo le sirva la comida
				System.out.println("El empleado esta comiendo");
				Thread.sleep(15000);
				System.out.println("El "+Thread.currentThread().getName()+" termino de comer y deja la cafeteria");
				semCafeteria.release();//Libera la cafeteria para que pueda entrar otro empleado
			}catch(InterruptedException e) {}
		}else {
			System.out.println("La cafeteria esta ocupada asi que vuelve al trabajo");
		}
	}
	
	public void trabajaMozo() {
		System.out.println("El mozo espera a que llegue alguien inventando nuevas recetas");
		try {
		semMozo.acquire();//Espera a que lo libere un empleado 
		System.out.println("Llego un cliente a la cafeteria el mozo lo atiende y le indica al cocinero el pedido");
		semCocinero.release(); //Libera al cocinero para que cocine
		semEsperaCocina.acquire(); //Espera a que el cocinero le entregue la comida
		System.out.println("El mozo le sirve la comida al empleado");
		semServicio.release();//Le sirve la comida al empleado
		}catch(InterruptedException e) {}
	}
	
	public void trabajaCocinero() {
		System.out.println("El cocinero espera a que el mozo le indique que cocine");
		try {
		semCocinero.acquire(); //Espera a que el mozo le indique que debe cocinar
		System.out.println("El cocinero esta cocinando");
		Thread.sleep(10000);
		System.out.println("El cocinero termino de cocinar");
		semEsperaCocina.release();//Le avisa y entrega la comida al mozo para que la sirva
		}catch(InterruptedException e) {}
	}
}
