package Tp4ExclusionMutua;

public class Atleta implements Runnable{
	private Carrera carrera;
	private int nroCorredor;
	
	public Atleta(Carrera carr, int nro) {
		this.carrera=carr;
		this.nroCorredor=nro;
	}
	
	public void run() {
		while(true) {
		carrera.empezarAcorrer(nroCorredor);
		}
	}
}
