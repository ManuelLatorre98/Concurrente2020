package Tp4ExclusionMutua;

import java.util.concurrent.Semaphore;

public class Turno {
	private Semaphore semA = new Semaphore(1);
	private Semaphore semB = new Semaphore(0);
	private Semaphore semC= new Semaphore(0);

	public Turno() {
	}

	public void imprimir(char letra, int cantImpLet) {
		if(letra=='A') {
			try {
			semA.acquire();
			this.escribir(letra, cantImpLet);
			semB.release();
			}catch(InterruptedException e) {}
		}
		
		if(letra=='B') {
			try {
			semB.acquire();
			this.escribir(letra, cantImpLet);
			semC.release();
			}catch(InterruptedException e) {}
		}
		
		if(letra=='C') {
			try {
			semC.acquire();
			this.escribir(letra, cantImpLet);
			semA.release();
			}catch(InterruptedException e) {}
		}
	}
	
	private void escribir(char letra, int cantImpLet) {
		for (int i = 0; i < cantImpLet; i++) {
			System.out.print(letra);
		}
	}
	
	
}
