package Parcial;

public class BlancaNieves implements Runnable{
	private Casa casa;
	
	public BlancaNieves(Casa unaCasa) {
		this.casa=unaCasa;
	}
	
	public void run() {
		while(true) {
			casa.trabajar();
		}
	}
}
