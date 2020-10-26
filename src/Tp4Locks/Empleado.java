package Tp4Locks;

public class Empleado implements Runnable{
	Comedor comedor;
	public Empleado(Comedor comed) {
		this.comedor=comed;
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("El "+Thread.currentThread().getName()+" esta trabajando");
				Thread.sleep(30000);
				System.out.println("El "+Thread.currentThread().getName()+" se acerca a la cafeteria");
				this.comedor.acercarse();
			}catch(InterruptedException e) {}
		}
	}
}
