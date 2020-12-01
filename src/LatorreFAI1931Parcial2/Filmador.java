package LatorreFAI1931Parcial2;

public class Filmador implements Runnable{
	private Serie serie;
	
	public Filmador(Serie unaSerie) {
		this.serie=unaSerie;
	}
	
	public void run() {
		while(true) {
			System.out.println("Se empieza a filmar un capitulo de la serie");
			this.filmar();
			System.out.println("Termino la filmacion de un episodio");
			this.serie.terminarFilmarCapitulo();
		}
	}
	
	public void filmar() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
	}
}
