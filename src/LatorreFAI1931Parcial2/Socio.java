package LatorreFAI1931Parcial2;

public class Socio implements Runnable{
	private Serie serie;
	private char idioma;
	private int nroCapitulo=1; //El nro de capitulo que quiere ver, van a ir por orden de salida, si todavia no sale espera
	
	public Socio(Serie unaSerie, char leng) {
		this.serie=unaSerie;
		this.idioma=leng;
	}
	
	public void run() {
		while(true) {
			System.out.println("El "+Thread.currentThread().getName()+" intenta ver el capitulo nro: "+this.nroCapitulo);
			this.serie.empezarVerCapitulo(idioma, nroCapitulo);
			System.out.println("El "+Thread.currentThread().getName()+" mira el episodio capitulo: "+this.nroCapitulo);
			this.verCapitulo();
			System.out.println("El "+Thread.currentThread().getName()+" termina de ver el capitulo nro: "+this.nroCapitulo);
			this.nroCapitulo++;//paso a siguiente capitulo
		}
	}
	
	public void verCapitulo() {
		try {
			Thread.sleep((long)(Math.random()*10+5)*1000); //Tarda entre 5 y 10 segundos en ver un capiutlo
		}catch(InterruptedException e) {}
	}
}
