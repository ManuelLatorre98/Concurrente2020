package TPOFinal;

public class Guardia implements Runnable{
	private HallCentral hallCentral;
	private int nroPuestoAsignado;
	public Guardia(HallCentral hallCentral, int nroPuestoAsignado ) {
		this.hallCentral=hallCentral;
		this.nroPuestoAsignado = nroPuestoAsignado;
	}
	
	public void run() {
		while(true) {
			hallCentral.llamarPasajero(nroPuestoAsignado);

		}
	}
}
