package LatorreFAI1931Parcial1;

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
