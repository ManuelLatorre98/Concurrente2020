package Tp2ThreadYRunnable;

public class testMiEjecucion {
	public static void main(String[] args) {
		Thread miHilo= new MiEjecucion();
		miHilo.start();
		/*try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
		}*/
		System.out.println("En el main");
		
	}
}
