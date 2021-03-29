package TPOFinal;

import java.util.concurrent.BlockingQueue;

public class CentroInformes implements Runnable{
	private Aeropuerto aeropuerto;
	private static final String GREEN = "\u001B[32m";
	private static final String RESET = "\033[0m";  // Text Reset
	public CentroInformes(Aeropuerto aeropuerto) {
		this.aeropuerto=aeropuerto;
	}
	public void run() {
		while(true) {
			aeropuerto.atiendeCentro(this);
		}
	}
	
	public void atenderCliente(Pasajero pasajero) {
		PuestoAtencion puesto;
		try {
			//System.out.println(GREEN+"Atiende al "+pasajero.getNombre()+RESET);
			puesto=aeropuerto.getPuestoAtencion(pasajero.getReserva().getNroReserva()-1);
			pasajero.setPuestoAtenAsig(puesto);//Le asigna al pasajero el centro de atencion al que es derivado
			Thread.sleep(1000);
			
			
		}catch(InterruptedException e) {}
	}
}
