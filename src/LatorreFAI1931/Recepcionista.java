package LatorreFAI1931;

public class Recepcionista implements Runnable{
	private Centro centro;
	public Recepcionista(Centro unCentro) {
		this.centro=unCentro;
	}
	
	public void run() {
		while(true) {
			this.centro.atenderRecepcion();
		}
	}
	
	
}
