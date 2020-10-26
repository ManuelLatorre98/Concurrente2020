package Tp4ExclusionMutua;

import java.util.concurrent.Semaphore;

public class Taxi {
	private Semaphore semSiesta = new Semaphore(0);
	private Semaphore semViaje = new Semaphore(0);
	private Semaphore mutex = new Semaphore(1);

	public void tomarTaxi() { // Solo lo hace cliente
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
		}
			System.out.println("El cliente toma el taxi y despierta al taxista");
			semSiesta.release();// despierta al taxista
			try {
				semViaje.acquire(); // Espera a que el taxista le avise que llegaron
			} catch (InterruptedException e) {
			}
			System.out.println("El pasajero baja del taxi");
		mutex.release();
	}

	public void trabajar() { // Solo lo hace taxista
			System.out.println("El taxista duerme mientras espera un cliente");
			try {
				semSiesta.acquire(); // Se queda esperando hasta que un cliente le avise, cuando se le hace release
										// deja pasar a taxista y vuelve a 0
			} catch (InterruptedException e) {
			}
			try {
				System.out.println("El taxista esta manejando");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			System.out.println("LLegaron a destino. El taxista avisa al pasajero");
			semViaje.release(); // Avisa al cliente que llegaron

		}
}
