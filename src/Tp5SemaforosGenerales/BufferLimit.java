package Tp5SemaforosGenerales;
import java.util.concurrent.Semaphore;
public class BufferLimit {
	private Semaphore semBuffer= new Semaphore(0,true);
	private Semaphore semProduccion= new Semaphore(3,true);
	public BufferLimit() {
	}
	
	public void consumir() {
		try {
			semBuffer.acquire(); //Si hay producto lo toma si no espera hasta que se genere uno
			semProduccion.release(); //Indico que consumio un producto y que puede generar otro
		}catch(InterruptedException e) {}
	}
	
	public void producir() {
		try {
			semProduccion.acquire();//puede entrar 3 veces (genera 3 productos), puede volver a generar cuando se consume uno de los productos
			Thread.sleep(1000);
			semBuffer.release();
		}catch(InterruptedException e) {}
	}
}
