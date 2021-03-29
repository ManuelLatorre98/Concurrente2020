package TPOFinal;

public class GestorHora implements Runnable{
	private Reloj reloj;
	private Aeropuerto aeropuerto;
	private Terminal[] terminales;
	private static final String RED = "\033[0;31m";     // RED
	private static final String RESET = "\033[0m";  // Text Reset
	public GestorHora(Reloj rel, Aeropuerto aeropuerto, Terminal[] terminales) {
		this.reloj=rel;
		this.aeropuerto=aeropuerto;
		this.terminales=terminales;
	}
	
	public void run() {
	
		while(true) {
			this.pasarTiempo();
			this.reloj.actualizarHora();
			System.out.println(RED+"Actualiza Hora a: "+reloj.getHoraStr()+RESET);
			this.aeropuerto.revicionHora();//Le avisa al aeropuerto que se actualizo la hora del reloj
			for (int i = 0; i < terminales.length; i++) {
				terminales[i].revicionHora();//Hace que los clientes revisen la hora a ver si tienen que embarcar
			}
		}
	}
	
	private void pasarTiempo() {
		try {
			Thread.sleep(5000);//10 segundos por hora
		}catch(InterruptedException e) {}
	}
}
