package TPOFinal;

public class GestorHora implements Runnable{
	private Reloj reloj;
	private Aeropuerto aeropuerto;
	private Terminal[] terminales;
	private PuestoEmbarque[]puestosEmbarque;
	private static final String RED = "\033[0;31m";     // RED
	private static final String RESET = "\033[0m";  // Text Reset
	public GestorHora(Reloj rel, Aeropuerto aeropuerto, Terminal[] terminales, PuestoEmbarque[]puestosEmbarque) {
		this.reloj=rel;
		this.aeropuerto=aeropuerto;
		this.terminales=terminales;
		this.puestosEmbarque=puestosEmbarque;
	}
	
	public void run() {
	
		while(true) {
			this.pasarTiempo();
			this.reloj.actualizarHora();
			System.out.println(RED+"Actualiza Hora a: "+reloj.getHoraStr()+RESET);
			this.aeropuerto.revicionHora();//Le avisa al aeropuerto que se actualizo la hora del reloj
			
			/*for (int j = 0; j < puestosEmbarque.length; j++) {//PARA TESTEO
				puestosEmbarque[j].revisionHora();//Cada embarque imprimira que abrio o cerro las puertas de embarque
			}*/////Descomentar para ver que abren y cierran embarques correctamente
			
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
