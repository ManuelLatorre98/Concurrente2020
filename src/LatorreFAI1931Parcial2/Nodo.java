package LatorreFAI1931Parcial2;

public class Nodo {
	private Object elem;
	private Nodo enlace;
	
	public Nodo(Object e, Nodo en) {
		this.elem=e;
		this.enlace=en;
	}
	
	public void setElem(Object e) {
		this.elem=e;
	}
	
	public void setEnlace(Nodo en) {
		this.enlace=en;
	}
	
	public Object getElem() {
		return this.elem;
	}
	
	public Nodo getEnlace() {
		return this.enlace;
	}
}
