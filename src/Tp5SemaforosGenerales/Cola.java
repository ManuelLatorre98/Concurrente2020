package Tp5SemaforosGenerales;

public class Cola {
	private Nodo frente;
	private Nodo fin;

	public Cola() {
		frente = null;
		fin = null;
	}

	public boolean poner(Object elem) {
		boolean exito = true;

		Nodo nodo = new Nodo(elem, null);
		if (this.frente == null && this.fin == null) {
			this.frente = nodo;
			this.fin = nodo;
		} else {
			this.fin.setEnlace(nodo);
			this.fin = nodo;
		}

		return exito;
	}

	public boolean sacar() {
		boolean exito = false;
		if (this.frente != null && this.fin != null) {
			this.frente = this.frente.getEnlace();
			if (this.frente == null) {
				this.fin = null;
			}
			exito = true;
		}
		return exito;
	}

	public Object obtenerFrente() {
		Object elem;
		if(this.frente==null) {
			elem=null;
		}else {
			elem=this.frente.getElem();
		}
		return elem;
	}

	public boolean esVacia() {
		return this.frente == null; 
	}

	public void vaciar() {
		this.frente = null;
		this.fin = null;
	}

	public Cola clone() {
		Cola clon = new Cola();
		Nodo flagClon;
		Nodo flagCola;

		if (this.frente != null) {
			clon.frente = new Nodo(this.frente.getElem(), null); 
			flagClon = clon.frente;
			flagCola = this.frente.getEnlace();

			while (flagCola != null) {
				Nodo nodoClon=new Nodo(flagCola.getElem(),null);
				flagClon.setEnlace(nodoClon);	
				flagClon = flagClon.getEnlace();
				flagCola = flagCola.getEnlace();
				if(flagCola==null) { 
					clon.fin=nodoClon;
				}
			}
		}
		return clon;
	}
	
	public String toString() {
		String cadena="FRENTE<--- ";
		Nodo flagCola=this.frente;
		while(flagCola!=null) {
			cadena+=flagCola.getElem();
			flagCola=flagCola.getEnlace();
			if(flagCola!=null) {
				cadena+=", ";
			}
		}
		cadena+=" <---FIN";
		return cadena;
	}
}
