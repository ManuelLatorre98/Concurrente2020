package Tp3Synchronized;

public class Auto extends Vehiculo implements Runnable{
	private int kmHastaReserva;
	private Surtidor surtidor;
	
	public Auto(String unaPatente, String unModelo, String unaMarca, int cantKmFaltantesService, int cantKmReservaCombustible,Surtidor unSurtidor) {
        super(unaPatente, unModelo, unaMarca, cantKmFaltantesService);
        this.kmHastaReserva= cantKmReservaCombustible;
        this.surtidor=unSurtidor;
    }
	
	
	public void setKmHastaReserva(int cantCarga) {
		this.kmHastaReserva=cantCarga;
	}
	
	private void recorrerKm(int kmARecorrer) {
		try {
			Thread.sleep(2000);
			this.kmHastaReserva= kmHastaReserva-kmARecorrer;
			System.out.println("El auto: "+Thread.currentThread().getName()+" recorrio "+kmARecorrer+"Km le quedan "+this.kmHastaReserva+" hasta la reserva");
		}catch(InterruptedException e) {
		}
		
	}
	
	public void run() {
		while(true) {
			while(this.kmHastaReserva>0) {
				recorrerKm(50);
			}
			//Se quedo sin combustible y carga
			System.out.println("El auto: "+Thread.currentThread().getName()+" se quedo sin combustible, va a cargar");
			surtidor.cargar(this);
		}
	}
}
