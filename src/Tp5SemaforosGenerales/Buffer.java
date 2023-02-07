package Tp5SemaforosGenerales;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Buffer {
	// private Semaphore semBuffer= new Semaphore(0,true);
	private BlockingQueue<String> cola;
	private int nroProducto;
	private Semaphore mutex = new Semaphore(1, true);

	public Buffer() {
		cola = new LinkedBlockingQueue<String>();
	}

	public void consumir() {
		try {
			
			cola.take();
			//System.out.println("Se consume el "+objeto);
		} catch (InterruptedException e) {
		}
	}

	public void producir() {
		String objeto = "Elemento";
		try {
			mutex.acquire();
			objeto += this.nroProducto;
			this.nroProducto++;
			mutex.release();
			Thread.sleep(1000);
			//System.out.println("Se produjo el "+objeto);
			cola.put(objeto);
		} catch (InterruptedException e) {
		}
	}
}
