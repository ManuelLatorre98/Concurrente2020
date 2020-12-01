package LatorreFAI1931Parcial1;

public class ClinicoControl implements Runnable{
	private Centro centro;
	
	public ClinicoControl(Centro unCentro) {
		this.centro=unCentro;
	}
	
	public void run() {
		while(true) {
			this.centro.realizarControlClinico();
		}
	}
}
