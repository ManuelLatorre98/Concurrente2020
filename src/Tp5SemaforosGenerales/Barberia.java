package Tp5SemaforosGenerales;

public class Barberia {
	private int cantSillas = 5;
	private int cantClientesEsp = 0;
	private int sillon = 1;
	private int cantClientesAten = 0;

	public synchronized boolean entrarABarberia() {
		boolean entro=true;
		if (cantSillas > cantClientesEsp) {
			System.out.println("El " + Thread.currentThread().getName() + " entra a la barberia");
			cantClientesEsp++;
			if(cantClientesEsp==1) {
			this.notify(); //cuando llega un cliente despierto al barbero
			}
			try {
				while (cantClientesAten == 1) { //Si ya hay clientes atentidos espera, si no pasa directo al sillon
					System.out.println("Ya hay alguien en el sillon asi que el "+ Thread.currentThread().getName() +" espera");
					this.wait();// Espera a que le avise el barbero
				}

			} catch (InterruptedException e) {
			}
		} else {
			entro=false;
			System.out.println("No hay lugar el " + Thread.currentThread().getName() + " se va");
		}
		return entro;
	}

	public synchronized void cortarsePelo() {
		cantClientesAten++;//Se suma aca para no tener problemas con el while
		try {
			while (cantClientesAten == 1) {
				System.out.println("El " + Thread.currentThread().getName() + " esta siendo atendido");
				this.wait();//Espera a que el barbero le indique que termino
			}
			System.out.println("El " + Thread.currentThread().getName() + " termino de cortarse el pelo");
		} catch (InterruptedException e) {
		}
	}

	public synchronized void trabajar() {

		try {
			while (cantClientesEsp == 0) { //Si no hay clientes duerme
				System.out.println("El barbero duerme hasta que llega un cliente");
				this.wait();//Espera hasta que el primer cliente le avisa
			}
			cantClientesEsp--;//atiende un cliente, el cliente suelta la silla
			System.out.println("El barbero atiende un cliente");
			Thread.sleep(5000);
			cantClientesAten--;//Indica que no esta atendiendo
			System.out.println("el barbero termina de atender a un cliente");
			this.notify();//Avisa en la sala de espera

		} catch (InterruptedException e) {
		}
	}

}
