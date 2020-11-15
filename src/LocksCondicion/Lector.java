package LocksCondicion;

public class Lector implements Runnable{
	Biblioteca biblioteca;
	public Lector(Biblioteca biblio) {
		this.biblioteca= biblio;
	}
	
	public void run() {
		this.biblioteca.leer();
		System.out.println("El "+Thread.currentThread().getName()+" entra a leer");
		try {
			Thread.sleep(4000);
		}catch(InterruptedException e) {}
		this.biblioteca.dejarLeer();
		System.out.println("El "+Thread.currentThread().getName()+" deja de leer");
	}
}
