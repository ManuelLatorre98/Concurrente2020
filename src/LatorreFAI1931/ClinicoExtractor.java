package LatorreFAI1931;

public class ClinicoExtractor implements Runnable{
private Centro centro;
	
	public ClinicoExtractor(Centro unCentro) {
		this.centro=unCentro;
	}
	
	public void run() {
		while(true) {
			this.centro.realizarExtracciones();;
		}
	}
}
