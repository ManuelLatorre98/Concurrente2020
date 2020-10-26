package Tp4Locks;

public class Mozo implements Runnable{
	private Comedor comedor;
	public Mozo(Comedor comed) {
		this.comedor=comed;
	}
	
	public void run() {
		while(true) {
			this.comedor.trabajaMozo();
		}
		
	}
}
