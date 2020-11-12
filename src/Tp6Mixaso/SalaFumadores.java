package Tp6Mixaso;

public class SalaFumadores {
	private int idIngredienteMesa=0;//0 significa que no hay ingredientes
	
	public SalaFumadores() {
	}
	
	public synchronized void entraFumar(int id) {
		try {
		while(this.idIngredienteMesa!=id) {
			this.wait();//Si no hay de su ingrediente espera
		}
		this.idIngredienteMesa=0;//indico que tomo el ingrediente asi que no quedan en mesa
		}catch(InterruptedException e) {}
	}
	
	public synchronized void terminaFumar() {
		this.notifyAll();//aviso que no quedan ingredientes
	}
	
	public synchronized void colocar(int idIngrediente) {
		try {
		while(this.idIngredienteMesa!=0) {
			this.wait();//Mientras haya ingredientes espera
		}
		this.idIngredienteMesa=idIngrediente;
		this.notifyAll();//aviso 
		}catch(InterruptedException e) {}
	}
	
	
}
