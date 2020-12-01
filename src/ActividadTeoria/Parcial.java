package ActividadTeoria;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Parcial { 
	private BlockingQueue<SolucionParcial> cola;
	public Parcial() {
		cola = new LinkedBlockingQueue<SolucionParcial>();
	}
	
	public void entregaParcial(SolucionParcial parcialAlumn) {
		try {
			cola.put(parcialAlumn);//El alumno envia su parcial
		}catch(InterruptedException e) {}
	}
	
	public SolucionParcial recibirParcial() {
		SolucionParcial solucion=null;
		try {
			solucion=cola.take();//Silvia espera hasta que haya parciales o los agarra si los hay
		}catch(InterruptedException e) {}
		return solucion;
	}
}
