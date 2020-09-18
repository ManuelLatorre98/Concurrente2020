package Tp3Synchronized;

public class Orco implements Runnable{
	private int daño=3;
	private Vida vida;
	public Orco(Vida unaVida) {
		vida=unaVida;
		
	}
	
	public void run() {
		System.out.println("(Accion orco)Vida actual: "+vida.getVida());
		vida.quitarVida(daño);
		System.out.println("El Orco causo "+daño+" de daño, vida restante: "+vida.getVida() );
		
	}
}
