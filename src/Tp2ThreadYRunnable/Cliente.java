package Tp2ThreadYRunnable;

public class Cliente extends Thread{
	public void run() {
		System.out.println("soy"+Thread.currentThread().getName());
		Recurso.uso();
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
		}
	}
}
