package Tp4ExclusionMutua;

import java.util.concurrent.Semaphore;

public class Carrera {
	private Semaphore semCorredor1 = new Semaphore(1);
	private Semaphore semCorredor2 = new Semaphore(0);
	private Semaphore semCorredor3 = new Semaphore(0);
	private Semaphore semCorredor4 = new Semaphore(0);
	private long tiempoTotal=0;

	public void empezarAcorrer(int nroCorredor) {
		if (nroCorredor == 1) {
			try {
				semCorredor1.acquire();
				correr(nroCorredor);
				semCorredor2.release();
			} catch (InterruptedException e) {
			}
		}
		
		if (nroCorredor == 2) {
			try {
				semCorredor2.acquire();
				correr(nroCorredor);
				semCorredor3.release();
			} catch (InterruptedException e) {
			}
		}
		
		if (nroCorredor == 3) {
			try {
				semCorredor3.acquire();
				correr(nroCorredor);
				semCorredor4.release();
			} catch (InterruptedException e) {
			}
		}
		
		if (nroCorredor == 4) {
			try {
				semCorredor4.acquire();
				correr(nroCorredor);
				semCorredor1.release();
			} catch (InterruptedException e) {
			}
		}
	}
	
	private void correr(int nroCorredor) {
		long tiempoIni=System.currentTimeMillis();
		long tiempoFin;
		int rangoTiempo=(11000-9000)+1;//(max-min)+1
		System.out.println("Corredor n°"+nroCorredor+" empieza a correr");
		try {
			Thread.sleep((long)((Math.random()*rangoTiempo)+9000));
		}catch(InterruptedException e) {}
		tiempoFin=(System.currentTimeMillis()-tiempoIni)/1000;//Quiero verlo en segundos
		this.tiempoTotal+=tiempoFin;//Sumo el tiempo total siempre en segundos
		System.out.println("Tiempo corredor n°"+nroCorredor+": "+tiempoFin+". El corredor entrega el testigo");
	}
	
	public long getTiempoTotal() {
		return this.tiempoTotal;
	}

}
