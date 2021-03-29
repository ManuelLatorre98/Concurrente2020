package TPOFinal;

public class EmpleadoPuestoAtencion implements Runnable{
	private PuestoAtencion puestoAtencion;
	public EmpleadoPuestoAtencion(PuestoAtencion puestoAtencion) {
		this.puestoAtencion=puestoAtencion;
	}
	
	public void run() {
		while(true) {
			this.puestoAtencion.atenderCliente(this);
		}
	}
	
	public void atenderCliente(Pasajero pasajero,Aeropuerto aeropuerto) {
		Terminal terminalPasajero;
		try {
			terminalPasajero=aeropuerto.getTerminal(pasajero.getReserva().getNroReserva()-1);
			pasajero.setTerminalAsignada(terminalPasajero);//Las terminales estan asociadas al nro de reserva
			//pasajero.setPuestoEmbarqueAsignado(terminalPasajero.getPuestoEmbarque(pasajero.getReserva().getNroPuerto()));
			Thread.sleep(3000);//Con 30000 se ve claro para test
		}catch(InterruptedException e) {}
	}
}
