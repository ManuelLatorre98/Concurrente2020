package Tp6Mixaso;

public class Fumador implements Runnable {
	private int id;
	private SalaFumadores sala;

	public Fumador(int id, SalaFumadores sala) {
		this.id = id;
		this.sala = sala;
	}// constructor

	public void run() {
		while (true) {
			try {
				sala.entraFumar(id);
				System.out.println("Fumador" + id + " está fumando");

				Thread.sleep(1000);
				sala.terminaFumar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		} 
	}
}
