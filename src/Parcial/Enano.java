package Parcial;

public class Enano implements Runnable{
	private Casa casa;
	
	public Enano(Casa unaCasa) {
		this.casa=unaCasa;
	}
	
	public void run() {
		while(true) {
		this.trabajar();
		this.casa.comer();
		}
		
	}
	
	private void trabajar() {
		int rnd= (int)Math.random()*3+1;
		System.out.println("El "+Thread.currentThread().getName()+" esta trabajando");
		try {
		Thread.sleep((7000*rnd));
		}catch(InterruptedException e) {}
	}
}
