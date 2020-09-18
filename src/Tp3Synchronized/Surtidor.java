package Tp3Synchronized;

public class Surtidor {
	public Surtidor() {}
	
	public synchronized void cargar(Auto unAuto) {
		System.out.println("El auto: "+Thread.currentThread().getName()+" Esta cargando combustible");
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		unAuto.setKmHastaReserva(500);
		System.out.println("El auto: "+Thread.currentThread().getName()+" termino de cargar combustible");
	}
}
