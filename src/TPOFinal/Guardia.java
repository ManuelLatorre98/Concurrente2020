package TPOFinal;

public class Guardia implements Runnable{
	private PuestoAtencion puestoAtencion;
	public Guardia(PuestoAtencion puestoAtencion) {
		this.puestoAtencion=puestoAtencion;
	}
	
	public void run() {
		while(true) {
			puestoAtencion.llamarCliente();
		}
	}
}
