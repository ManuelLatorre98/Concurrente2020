package Tp5SemaforosGenerales;

public class Barbero implements Runnable {
	Barberia barberia;
	public Barbero(Barberia barb) {
		this.barberia=barb;
	}
	
	public void run() {
		while(true) {
			this.barberia.trabajar();
			System.out.println("El barbero atiende un cliente");
			try {
			Thread.sleep(5000);
			}catch(InterruptedException e) {}
			this.barberia.cortarPelo();
		}
	}
}
