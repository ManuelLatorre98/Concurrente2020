package LatorreFAI1931Parcial2;

public class Capitulo {
	private boolean espa�ol;
	private boolean ingles;
	private int nroCapitulo;
	public Capitulo(int  nro) {
		this.espa�ol=true;
		this.ingles=false;
		this.nroCapitulo=nro;
	}
	
	public void traduccion() {
		this.ingles=true;
	}
	
	public boolean getEsp() {
		return espa�ol;
	}
	public boolean getIng() {
		return this.ingles;
	}
}
