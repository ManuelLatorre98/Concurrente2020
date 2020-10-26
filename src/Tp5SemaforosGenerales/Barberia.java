package Tp5SemaforosGenerales;

public class Barberia {
	private int cantSillas = 5;
	private int cantClientesEsp = 0;
	private int sillon = 1;
	private int cantClientesAten = 0;

	public synchronized void entrarABarberia() {
		System.out.println("El " + Thread.currentThread().getName() + " entra a la barberia");
		if (cantSillas > cantClientesEsp) {
			cantClientesEsp++;
			this.notify();
			try {
				while (cantClientesAten == 1) {
					System.out.println("Ya hay alguien en el sillon asi que el "+ Thread.currentThread().getName() +" espera");
					this.wait();// Espera a que le avise el barbero
				}

			} catch (InterruptedException e) {
			}
		} else {
			System.out.println("No hay lugar el " + Thread.currentThread().getName() + " se va");
		}
	}

	public synchronized void cortarsePelo() {
		cantClientesAten++; // ACA

		try {
			while (cantClientesAten == 1) {
				System.out.println("El " + Thread.currentThread().getName() + " esta siendo atendido");
				this.wait();
			}
			System.out.println("El " + Thread.currentThread().getName() + " termino de cortarse el pelo");
		} catch (InterruptedException e) {
		}
	}

	public synchronized void trabajar() {

		try {
			while (cantClientesEsp == 0) {
				System.out.println("El barbero duerme hasta que llega un cliente");
				this.wait();
			}
			cantClientesEsp--;
			System.out.println("El barbero atiende un cliente");
			Thread.sleep(5000);
			cantClientesAten--;
			System.out.println("el barbero termina de atender a un cliente");
			this.notify();

		} catch (InterruptedException e) {
		}
	}

}
