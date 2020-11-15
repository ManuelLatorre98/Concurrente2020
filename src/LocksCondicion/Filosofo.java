package LocksCondicion;

public class Filosofo implements Runnable{
	private Mesa mesa;
	private int TenedorIzq;
	private int TenedorDer;
	
	public Filosofo(Mesa me, int tenIzq, int tenDer) {
		this.mesa=me;
		this.TenedorIzq=tenIzq;
		this.TenedorDer= tenDer;
	}
	
	public void run() {
		
	}
}
