package Tp2ThreadYRunnable;

public class Contador {
	public static void main(String[] args) {
		final DatoPingPong unContador=new DatoPingPong();
		RunnableCdor unRunCdor= new RunnableCdor(unContador);
		
		Thread h1= new Thread(unRunCdor);
		Thread h2= new Thread(unRunCdor);
		h1.start();
		h2.start();
		try {
			h1.join();
			h2.join();
		}catch(InterruptedException e) {}
		System.out.println("en main-"+unContador.obtenerValor());
	}
}
