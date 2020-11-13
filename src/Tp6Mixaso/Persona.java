package Tp6Mixaso;

public class Persona implements Runnable {
	boolean jubilado;
	SalaMuseo sala;
	public Persona(boolean jubi, SalaMuseo salaM) {
		this.jubilado=jubi;
		this.sala= salaM;
	}
	
	public void run() {
		if(jubilado) {
			this.sala.entrarSalaJubilado();
			System.out.println(Thread.currentThread().getName()+" entro en la sala");
		}else {
			this.sala.entrarSala();
			System.out.println(Thread.currentThread().getName()+" entro en la sala");
		}
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {}
		this.sala.salirSala();
		System.out.println(Thread.currentThread().getName()+" salio de la sala");
	}
	
}
