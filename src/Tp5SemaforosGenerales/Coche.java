package Tp5SemaforosGenerales;

public class Coche implements Runnable{
	private Puente puente;
	private char direccion;
	private int turno;
	public Coche(Puente unPuente, char unaDir) {
		this.puente=unPuente;
		this.direccion=unaDir;
	}
	
	public void run() {
		
		if(this.direccion=='N') {
			turno=this.puente.obtieneTurnoNorte();
			//System.out.println("El "+Thread.currentThread().getName()+" saco el turno: "+this.turno);
			this.puente.entraCocheNorte(this.turno);
			System.out.println("El "+Thread.currentThread().getName()+" entro al puente por el norte. Turno: "+this.turno);
			this.cruzarPuente();
			this.puente.salirCocheNorte();
			System.out.println("El "+Thread.currentThread().getName()+" salio del puente");
		}else {
			turno=this.puente.obtieneTurnoSur();
			this.puente.entraCocheSur(this.turno);
			//System.out.println("El "+Thread.currentThread().getName()+" entro al puente por el sur. Turno: "+this.turno);
			this.cruzarPuente();
			this.puente.salirCocheSur();
			System.out.println("El "+Thread.currentThread().getName()+" salio del puente");
		}
	}
	
	public void cruzarPuente() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
	}
}
