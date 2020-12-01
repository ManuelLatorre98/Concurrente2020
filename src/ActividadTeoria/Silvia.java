package ActividadTeoria;

public class Silvia implements Runnable{
	Parcial parcial;
	public Silvia(Parcial unParcial) {
		this.parcial=unParcial;
	}
	
	public void run() {
		SolucionParcial parcialCorregir;
		System.err.println("Silvia entra a pedco para corregir los parciales");
		while(true) {
			parcialCorregir=this.parcial.recibirParcial();
			System.err.println("Silvia agarra el parical de "+parcialCorregir.getNombreAlumn()+" y lo corrige");
			this.corregir(parcialCorregir);
		}
	}
	
	private void corregir(SolucionParcial parcialAlumn) {
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {}
		
		int nota=(int)((Math.random()*10)+1);
		if(nota>=8) {
			parcialAlumn.setNota('P');
			System.err.println("Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con promocion");
		}else {
			if(nota>=6 && nota<8) {
				parcialAlumn.setNota('A');
				System.err.println("Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con aprobado");
			}else {
				parcialAlumn.setNota('D');
				System.err.println("Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con desaprobado");
			}
		}
		
	}
}
