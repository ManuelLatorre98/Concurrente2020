package LatorreFAI1931Parcial2;

public class Lista {
	private Nodo cabecera;

	public Lista() {
		cabecera = null;
	}

	public boolean insertar(Object elem, int pos) {
		boolean exito = false;
		int posicion = 1;
		Nodo flagNodo = this.cabecera;

		if (pos >= 1) {
			if (pos == 1) {
				this.cabecera = new Nodo(elem, this.cabecera);
				exito = true;
			} else {
				while (posicion != (pos - 1) && flagNodo.getEnlace() != null) {
					flagNodo = flagNodo.getEnlace();
					posicion++;
				}

				if (posicion == (pos - 1)) {
					flagNodo.setEnlace(new Nodo(elem, flagNodo.getEnlace()));
					exito = true;
				}
			}
		}
		return exito;
	}

	public boolean eliminar(int pos) {
		boolean exito = false;
		Nodo flagNodo = this.cabecera;
		int posicion = 1;
		if (pos >= 1 && this.cabecera != null) {
			if (pos == 1) {
				this.cabecera = this.cabecera.getEnlace();// Si no tiene enlace la cabecera sera null por lo tanto la
															// lista quedara vacia
				exito = true;
			} else {
				while (posicion != (pos - 1) && flagNodo.getEnlace() != null) {
					flagNodo = flagNodo.getEnlace();
					posicion++;
				}
				if (flagNodo.getEnlace() != null) { // Si paro porque flagNodo==null no hace
													// esto ya que la posicion ingresada
					// supera al tamaño de la lista
					flagNodo.setEnlace(flagNodo.getEnlace().getEnlace());
					exito = true;
				}
			}
		}
		return exito;
	}

	public Object recuperar(int pos) {
		Object elem = null;
		Nodo flagNodo = this.cabecera;
		int posicion = 1;
		if (pos >= 1 && this.cabecera != null) {
			while (posicion != pos && flagNodo.getEnlace() != null) {
				flagNodo = flagNodo.getEnlace();
				posicion++;
			}
			if (posicion == pos) {
				elem = flagNodo.getElem();
			}
		}
		return elem;
	}

	public int localizar(Object elem) {
		int posicion = 0;
		Nodo flagNodo = this.cabecera;
		while (flagNodo != null && !flagNodo.getElem().equals(elem)) {
			flagNodo = flagNodo.getEnlace();
			posicion++;
		}
		if (flagNodo != null) { // El while corta una posicion antes si encuentra al elemento
			posicion++;
		} else { // Si corto el while con flagNodo==null significa que no econtro el elem
			posicion = -1;
		}
		return posicion;
	}

	public int longitud() {
		int longitud = 0;
		Nodo flagNodo = this.cabecera;
		while (flagNodo != null) {
			longitud++;
			flagNodo = flagNodo.getEnlace();
		}
		return longitud;
	}

	public boolean esVacia() {
		return this.cabecera == null;
	}

	public void vaciar() {
		this.cabecera = null;
	}

	public Lista clone() {
		Lista clon = new Lista();
		Nodo flagLista = this.cabecera;
		Nodo flagClon;
		if (this.cabecera != null) {
			clon.cabecera = new Nodo(this.cabecera.getElem(), null);
			flagClon = clon.cabecera;
			flagLista = flagLista.getEnlace();
			while (flagLista != null) {
				flagClon.setEnlace(new Nodo(flagLista.getElem(), null));
				flagClon = flagClon.getEnlace();
				flagLista = flagLista.getEnlace();
			}
		}
		return clon;
	}

	public String toString() {
		String cadena = "| ";
		Nodo flagNodo = this.cabecera;
		if (this.cabecera != null) {
			while (flagNodo != null) {
				cadena += flagNodo.getElem();
				flagNodo = flagNodo.getEnlace();
				if (flagNodo != null) {
					cadena += ", ";
				}
			}
		}
		cadena += " |";
		return cadena;
	}

	public Lista invertir() {
		Nodo flagLista = this.cabecera;
		Lista invertida = new Lista();
		if (this.cabecera != null) {
			auxInvertir(flagLista, invertida);
		}
		return invertida;
	}

	private Nodo auxInvertir(Nodo flagLista, Lista invertida) {
		Nodo flagInvertida;
		if (flagLista.getEnlace() == null) {
			invertida.cabecera = new Nodo(flagLista.getElem(), null);
			flagInvertida = invertida.cabecera;
		} else {
			flagInvertida = auxInvertir(flagLista.getEnlace(), invertida);
			flagInvertida.setEnlace(new Nodo(flagLista.getElem(), null));
			flagInvertida = flagInvertida.getEnlace();
		}
		return flagInvertida;
	}

	public void eliminarApariciones(Object elem) {
		Nodo flagLista = this.cabecera;
		if (this.cabecera != null) {
			while (this.cabecera != null && this.cabecera.getElem().equals(elem)) {
				this.cabecera = this.cabecera.getEnlace();
				flagLista = this.cabecera;
			}
			while (this.cabecera != null && flagLista.getEnlace() != null) {
				if (flagLista.getEnlace().getElem().equals(elem)) {
					flagLista.setEnlace(flagLista.getEnlace().getEnlace());
				} else {
					flagLista = flagLista.getEnlace();
				}

			}
		}
	}

	public void intercalarElem(Object elem, int k) {
		Nodo flagNodo;
		int cont = 0;
		this.cabecera = new Nodo(elem, this.cabecera);
		flagNodo = this.cabecera;
		while (flagNodo != null) {
			if (cont == k) {
				flagNodo.setEnlace(new Nodo(elem, flagNodo.getEnlace()));
				flagNodo = flagNodo.getEnlace();
				cont = 0;
			} else {
				flagNodo = flagNodo.getEnlace();
				cont++;
			}
		}
	}

	public void agregarProducto(int x) {
		int cont = 0;
		int producto = 1;
		Nodo flagNodo = this.cabecera;
		while (flagNodo != null) {
			if (cont < x) {
				cont++;
				producto = producto * (int) flagNodo.getElem();
				if (cont < x) {
					flagNodo = flagNodo.getEnlace();
				}
			}
			
			if(flagNodo.getEnlace()==null) {
				producto=producto = producto * (int) flagNodo.getElem();
			}
			
			if ((cont == x) || (flagNodo.getEnlace() == null)) {
					flagNodo.setEnlace(new Nodo(producto, flagNodo.getEnlace()));
					cont = 0;
					producto = 1;
					flagNodo = flagNodo.getEnlace().getEnlace();
				}
			
		}
	}
}
