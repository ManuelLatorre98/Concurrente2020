package ActividadTeoria;

public class Silvia implements Runnable{
	Parcial parcial;
	public Silvia(Parcial unParcial) {
		this.parcial=unParcial;
	}
	
	public void run() {
		SolucionParcial parcialCorregir;
		System.out.println(ConsoleColors.GREEN+"Silvia entra a pedco para corregir los parciales"+ConsoleColors.RESET);
		while(true) {
			parcialCorregir=this.parcial.recibirParcial();
			System.out.println(ConsoleColors.GREEN+"Silvia agarra el parical de "+parcialCorregir.getNombreAlumn()+" y lo corrige"+ConsoleColors.RESET);
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
			System.out.println(ConsoleColors.GREEN+"Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con promocion"+ConsoleColors.RESET);
		}else {
			if(nota>=6 && nota<8) {
				parcialAlumn.setNota('A');
				System.out.println(ConsoleColors.GREEN+"Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con aprobado"+ConsoleColors.RESET);
			}else {
				parcialAlumn.setNota('D');
				System.out.println(ConsoleColors.GREEN+"Califica el parcial de "+parcialAlumn.getNombreAlumn()+" con desaprobado"+ConsoleColors.RESET);
			}
		}
		
	}
}
