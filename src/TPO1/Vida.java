package TPO1;

public class Vida {
	private int vida;
	
	public Vida() {
		this.vida=10;
	}
	
	public synchronized void curarVida(int valorCura) {
		this.vida+=valorCura;
		System.out.println("El Curandero curo "+valorCura+" de vida, vida actual: "+this.getVida() );
	}
	
	public synchronized void quitarVida(int valorDanio) {
		this.vida-=valorDanio;
		System.out.println("El Orco causo "+valorDanio+" de da�o, vida restante: "+this.getVida() );
	}
	
	public synchronized int getVida() {
		return vida;
	}

}
