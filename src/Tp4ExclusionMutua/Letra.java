package Tp4ExclusionMutua;

public class Letra implements Runnable{
	private char letra;
	private int cantImpLet;
	private Turno adminTurno;
	private int repeticiones;
	
	public Letra(char let, int cantImp, Turno adT , int repe) {
		this.letra=let;
		this.cantImpLet=cantImp;
		this.adminTurno=adT;
		this.repeticiones=repe;
	}
	
	public void run() {
		while(this.repeticiones>0) {
			adminTurno.imprimir(letra, cantImpLet);
			this.terminarRepe();
		}
	}
	private void terminarRepe() {
		if(repeticiones>0) {
			repeticiones--;
		}
	}
}
