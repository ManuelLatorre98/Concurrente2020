package LocksCondicion;

public class Persona implements Runnable {
	private CentroHemoterapia centro;
	private boolean sentado;
	private int nroTurno;

	public Persona(CentroHemoterapia cent) {
		this.centro = cent;
	}
	
	public void run() {
		this.nroTurno=this.centro.sacarNumero();//Saca numero y lo guarda
		System.out.println("La "+Thread.currentThread().getName()+" saca numero"+this.nroTurno);
			this.sentado= this.centro.intentarSentarse();//Indica si se sento o no
			if(this.sentado) {
				System.out.println("La "+Thread.currentThread().getName()+" se sienta");
			}else {
				System.out.println("La "+Thread.currentThread().getName()+" espera parada");
			}
			
			this.centro.tomarRevista(nroTurno);
			this.centro.esperarLeyendo(nroTurno);
			this.centro.pararse(sentado);
			this.atencion();
		
	}
	
	public void atencion() {
		this.centro.atenderse();
		System.out.println("La "+Thread.currentThread().getName()+" se esta extrayendo sangre"+this.nroTurno);
		try {
			Thread.sleep((long)(Math.random()*10+5)*1000);
		}catch(InterruptedException e) {}
		this.centro.salir();
		System.out.println("La persona termino de extraerse sangre se va del centro");
	}
}
