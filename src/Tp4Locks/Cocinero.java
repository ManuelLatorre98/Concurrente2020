package Tp4Locks;

public class Cocinero implements Runnable{
	Comedor comedor;
	
	public Cocinero(Comedor comed) {
		this.comedor=comed;
	}
	
	public void run() {
		this.comedor.trabajaCocinero();
	}
	
}
