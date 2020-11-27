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
			//System.out.println("El "+Thread.currentThread().getName()+" saco el turno NORTE: "+this.turno);
			this.puente.entraCocheNorte(this.turno);
			System.out.println("El "+Thread.currentThread().getName()+" entro al puente por el norte. Turno: "+this.turno);
			this.cruzarPuente();
			this.puente.salirCocheNorte(this.turno);
			System.out.println("El "+Thread.currentThread().getName()+" salio del puente NORTE"+this.turno);
		}else {
			turno=this.puente.obtieneTurnoSur();
			//System.out.println("El "+Thread.currentThread().getName()+" saco el turno SUR: "+this.turno);
			this.puente.entraCocheSur(this.turno);
			System.out.println("El "+Thread.currentThread().getName()+" entro al puente por el sur. Turno: "+this.turno);
			this.cruzarPuente();
			this.puente.salirCocheSur(this.turno);
			System.out.println("El "+Thread.currentThread().getName()+" salio del puente SUR"+this.turno);
		}
	}
	
	public void cruzarPuente() {
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
	}
}
