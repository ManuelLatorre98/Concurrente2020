package Tp2ThreadYRunnable;

public class RunnableCdor implements Runnable {
	DatoPingPong unContador;
	public RunnableCdor(DatoPingPong elCdor) {
		unContador=elCdor;
	}
	
	public void run() {
		for (int i = 0; i < 10000; i++) {
			unContador.contar();
		}
	}
	
}
