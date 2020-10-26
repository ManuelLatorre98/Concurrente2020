package Tp3Synchronized;

public class Curandero implements Runnable{
	private int cura=3;
	private Vida vida;
	
	public Curandero(Vida unaVida) {
		vida=unaVida;
	}
	
	public void run() {
		vida.curarVida(cura);
		
	}
}
