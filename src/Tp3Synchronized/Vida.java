package Tp3Synchronized;

public class Vida {
	private int vida;
	
	public Vida() {
		this.vida=10;
	}
	
	public synchronized void curarVida(int valorCura) {
		this.vida+=valorCura;
	}
	
	public synchronized void quitarVida(int valorDaño) {
		this.vida-=valorDaño;	
	}
	public int getVida() {
		return vida;
	}

}
