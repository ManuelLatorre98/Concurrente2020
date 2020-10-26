package Tp5SemaforosGenerales;

public class Barbero implements Runnable {
	Barberia barberia;
	public Barbero(Barberia barb) {
		this.barberia=barb;
	}
	
	public void run() {
		while(true) {
			this.barberia.trabajar();
		}
	}
}
