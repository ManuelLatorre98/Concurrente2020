package LatorreFAI1931Parcial2;

public class Capitulo {
	private boolean español;
	private boolean ingles;
	private int nroCapitulo;
	public Capitulo(int  nro) {
		this.español=true;
		this.ingles=false;
		this.nroCapitulo=nro;
	}
	
	public void traduccion() {
		this.ingles=true;
	}
	
	public boolean getEsp() {
		return español;
	}
	public boolean getIng() {
		return this.ingles;
	}
}
