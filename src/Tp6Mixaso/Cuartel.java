package Tp6Mixaso;

import java.util.concurrent.Semaphore;

public class Cuartel {
	private Semaphore semCapacidad = new Semaphore(100, true);
	private Semaphore semBandeja = new Semaphore(5, true);
	private Semaphore semAbridores = new Semaphore(10, true);
	private Semaphore semPostre = new Semaphore(3, true);

	public void entrarCuartel() {
		try {
		this.semCapacidad.acquire(); //entra al cuartel
		}catch(InterruptedException e) {}
	}
	
	public void agarrarBandeja() {
		try {
			this.semBandeja.acquire();//Pasa a un mostrador
		}catch(InterruptedException e) {}
	}
	
	public void salirMostradorBandeja() {
		this.semBandeja.release();//Libera un mostrador
	}
	
	public void usarAbridor() {
		try {
			this.semAbridores.acquire();//Ocupa un abridor
		}catch(InterruptedException e) {}
	}
	
	public void soltarAbridor() {
			this.semAbridores.release();//Suelta un abridor		
	}
	
	public void agarrarPostre() {
		try {
			this.semPostre.acquire();//Pasa a un mostrador
		}catch(InterruptedException e) {}
	}
	
	public void salirMostradorPostre() {
		this.semPostre.release();//Libera un mostrador
	}
	
	public void salirCuartel() {
		this.semCapacidad.release();
	}

}
