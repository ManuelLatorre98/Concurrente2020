package TPO1;

public class Orco implements Runnable{
	private int daño=3;
	private Vida vida;
	public Orco(Vida unaVida) {
		vida=unaVida;
		
	}
	
	public void run() {
		vida.quitarVida(daño);
		
		
	}
}
