package Tp5SemaforosGenerales;
import java.util.concurrent.Semaphore;

public class Buffer {
	private Semaphore semBuffer= new Semaphore(0,true);
	
	public void consumir() {
		try {
		semBuffer.acquire(); //Si hay producto lo toma si no espera hasta que se genere uno
		}catch(InterruptedException e) {}
	}
	
	public void producir() {
		try {
			Thread.sleep(1000);
			semBuffer.release();
		}catch(InterruptedException e) {}
	}
}
