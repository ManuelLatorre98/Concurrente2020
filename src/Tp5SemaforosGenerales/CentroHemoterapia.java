package Tp5SemaforosGenerales;

import java.util.concurrent.Semaphore;

public class CentroHemoterapia {
	private Semaphore semSillas = new Semaphore(12, true);
	private Semaphore semRevistas = new Semaphore(9, true);
	private Semaphore semCamillas = new Semaphore(4, true);

	public boolean intentaAtenderse() {
		return semCamillas.tryAcquire();
	}
	
	public boolean tomarSilla() {
		boolean seSienta = false;
		if (semSillas.tryAcquire()) {
			seSienta = true;
		}
		return seSienta;
	}

	public boolean tomarRevista() {
		boolean agarraRevista = false;
		if (semRevistas.tryAcquire()) {
			agarraRevista = true;
		}
		return agarraRevista;
	}
	
	public void esperarAtencion() {
		try {
			this.semCamillas.acquire();// Si no hay camillas libres queda esperando
		} catch (InterruptedException e) {
		}
	}
	
	public void soltarSilla() {
		this.semSillas.release();
	}
	
	public void soltarRevista() {
		this.semSillas.release();
	}
	
	public void soltarCamilla() {
		this.semCamillas.release();
	}
}
