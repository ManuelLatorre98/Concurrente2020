package Tp5SemaforosGenerales;

public class Persona implements Runnable {
	private CentroHemoterapia centro;
	private boolean sentado;
	private boolean lee;

	public Persona(CentroHemoterapia cent) {
		this.centro = cent;
	}

	public void run() {
		System.out.println("La" + Thread.currentThread().getName() + " entra al centro de hemoterapia");
		if (centro.intentaAtenderse()) {
			this.atencion();
		} else {

			if (this.centro.tomarSilla()) {
				System.out.println("La " + Thread.currentThread().getName() + " se sienta en una silla y espera");
				sentado = true;
			} else {
				sentado = false;
				System.out.println("La " + Thread.currentThread().getName() + " espera parada");
			}

			if (this.centro.tomarRevista()) {
				lee = true;
				System.out.println("La " + Thread.currentThread().getName() + " agarra una revista y lee");
			} else {
				lee = false;
				System.out.println("Como no hay revistas la " + Thread.currentThread().getName() + " mira la tele");
			}
			
			System.out.println("La " + Thread.currentThread().getName() +" espera a que la atiendan");
			
			centro.esperarAtencion();
			
			if(sentado) {//Una vez que lo atienden
				System.out.println("La " + Thread.currentThread().getName() +" se levanta y deja la silla");
				centro.soltarSilla();//suelta silla
			}
		
			if(lee) {
				System.out.println("La " + Thread.currentThread().getName() +" deja la revista");
				centro.soltarRevista();//sueltaRevista
			}
			
			this.atencion();
		}

	}
	
	public void atencion() {
		System.out.println("La " + Thread.currentThread().getName() +" es atentida y se esta extrayendo sangre");
		try {
			Thread.sleep((long)(Math.random()*10+5)*1000);
		}catch(InterruptedException e) {}
		this.centro.soltarCamilla();
		System.out.println("La " + Thread.currentThread().getName() +" termino de hacerse la extraccion");
	}

}
