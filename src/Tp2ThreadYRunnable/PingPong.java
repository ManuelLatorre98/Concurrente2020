package Tp2ThreadYRunnable;

public class PingPong extends Thread{
	private int delay;
	private DatoPingPong miD;
	private int miCta=0;
	
	public PingPong(String cartel, int cantMs) {
		super(cartel);
		this.delay=cantMs;
	}
	
	public PingPong(String cartel, int cantMs, DatoPingPong dd) {
		this(cartel, cantMs);
		this.miD=dd;
	}
	
	public void run() {
		for (int i = 1; i < delay*2; i++) {
			miCta++;
			this.miD.contar();
		}
		System.out.println(miCta+"veces"+this.getName());
	}
}
