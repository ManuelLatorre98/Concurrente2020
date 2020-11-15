package LocksCondicion;

public class Escritor implements Runnable{
	Biblioteca biblioteca;
	public Escritor(Biblioteca biblio) {
		this.biblioteca= biblio;
	}
	
	public void run() {		
		this.biblioteca.escribir();
		System.out.println("El "+Thread.currentThread().getName()+" entra a escribir");
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		
		this.biblioteca.dejarEscribir();
		System.out.println("El "+Thread.currentThread().getName()+" deja de escribir");
	}
}
