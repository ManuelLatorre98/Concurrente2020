package Tp3Synchronized;

public class Orco implements Runnable{
	private int da�o=3;
	private Vida vida;
	public Orco(Vida unaVida) {
		vida=unaVida;
		
	}
	
	public void run() {
		System.out.println("(Accion orco)Vida actual: "+vida.getVida());
		vida.quitarVida(da�o);
		System.out.println("El Orco causo "+da�o+" de da�o, vida restante: "+vida.getVida() );
		
	}
}
