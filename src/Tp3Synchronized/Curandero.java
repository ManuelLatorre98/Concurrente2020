package Tp3Synchronized;

public class Curandero implements Runnable{
	private int cura=3;
	private Vida vida;
	
	public Curandero(Vida unaVida) {
		vida=unaVida;
	}
	
	public void run() {
		System.out.println("(Accion curandero)Vida actual: "+vida.getVida());
		vida.curarVida(cura);
		System.out.println("El Curandero curo "+cura+" de vida, vida actual: "+vida.getVida() );
	}
}
