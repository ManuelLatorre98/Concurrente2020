package Parcial;

import java.util.concurrent.Semaphore;
import 
public class Casa {
	private Semaphore semSillas = new Semaphore(4, true);
	private Semaphore semPasear = new Semaphore(0, true);
	private Semaphore semComida = new Semaphore(0, true);
	private Semaphore mutex = new Semaphore(1, true);
	private int sinServir = 0;

	public Casa() {
	}

	public void comer() {
		System.out.println("El " + Thread.currentThread().getName() + " mira si hay una silla libre");
		if (semSillas.tryAcquire()) {
			System.out.println("El " + Thread.currentThread().getName() + " se sienta y pide la comida a blanca nieves");
			try {
				mutex.acquire();
				sinServir++; // Para que blanca no se vaya a pasear si hay mas de 1 enano
				if (sinServir == 1) { // Si no habia ya ningun enano sentado le avisa a blanca nieves que deje de pasear
					semPasear.release();
				}
				mutex.release();

				semComida.acquire();// Espera hasta que lo libere Blanca
				System.out.println("El " + Thread.currentThread().getName() + " esta comiendo");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				System.out.println("El " + Thread.currentThread().getName() + " termino de comer, se levanta y se va");
				semSillas.release();
			} catch (InterruptedException e) {
			}
		} else {
			System.out.println("Todas las sillas estan ocupadas el " + Thread.currentThread().getName() + " vuelve al trabajo");
		}
	}

	public void trabajar() {
		boolean sinAtender;
		System.out.println("Blanca nieves pasea con el principe");
		try {
			semPasear.acquire();// Espera a hasta que un enanito le pide que sirva
			sinAtender = true; 
			while (sinAtender) {
				this.servir();
				try {
					mutex.acquire();
					if (this.sinServir == 0) {
						sinAtender = false;
					}
					mutex.release();
				} catch (InterruptedException e) {
				}
			}
		} catch (InterruptedException e) {
		}
	}

	private void servir() {
		System.out.println("Blanca nieves le sirve la comida a un enanito");
		semComida.release(); // Le indica a un enano que puede comer
		try {
			mutex.acquire();
			sinServir--;
			mutex.release();
		} catch (InterruptedException e) {
		}
	}

}
