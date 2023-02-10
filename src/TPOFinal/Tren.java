package TPOFinal;

public class Tren implements Runnable{
	private int capacidadTren;
	private Aeropuerto aeropuerto;
	
	public Tren(Aeropuerto aero) {
		this.aeropuerto=aero;
	}
	
	public void run() {
		while(true) {
			this.aeropuerto.recogerPasajeros();
			this.aeropuerto.transportar(this);
			this.aeropuerto.volverOrigen(this);
			System.out.println("El tren volvio a la terminal de origen para recoger pasajeros");
		}
	}
	
	//Estos metodos son llamados desde aeropuerto para tener la info de las terminales y cant pasajeros
	public void viajar(char letraTerminalViaje) {
		try {
			System.out.println("Tren viaja a terminal "+letraTerminalViaje);
			Thread.sleep(7000);
		}catch(InterruptedException e) {}
	}
	
	public void frenarTerminal(char letraTerminal,int cantPasajeros) {
		System.out.println("El tren frena en la terminal: "+letraTerminal+" para que bajen "+cantPasajeros+" pasajeros");
		try {
			Thread.sleep(1500);
		}catch(InterruptedException e) {}
		
	}
}
