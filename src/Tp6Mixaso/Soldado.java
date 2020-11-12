package Tp6Mixaso;

public class Soldado implements Runnable{
	private Cuartel cuartel;
	
	public Soldado(Cuartel cuart) {
		this.cuartel=cuart;
	}
	
	public void run() {
		this.cuartel.entrarCuartel();
		this.cuartel.agarrarBandeja();
		System.out.println("El "+Thread.currentThread().getName()+" entra al cuartel y toma una bandeja");
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {}
		this.cuartel.salirMostradorBandeja();
		this.bebida();
		this.postre();
		System.out.println("El "+Thread.currentThread().getName()+" va a comer");
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		this.cuartel.salirCuartel();
		System.out.println("El "+Thread.currentThread().getName()+" termino de comer, deja la bandeja y sale del cuartel");
	}
	
	private void bebida() {
		if(this.quiere()) {
			this.cuartel.usarAbridor();
			System.out.println("El "+Thread.currentThread().getName()+" quiere gaseosa asi que usa un abridor");
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			this.cuartel.soltarAbridor();
		}
	}
	
	private void postre() {
		if(this.quiere()) {
			this.cuartel.agarrarPostre();
			System.out.println("El "+Thread.currentThread().getName()+" quiere postre, pasa a un mostrador y le sirven");
			try {
				Thread.sleep(3000);
			}catch(InterruptedException e) {}
			this.cuartel.salirMostradorPostre();
		}
	}
	
	private boolean quiere() {//tanto postre como gaseosa
		int bebida=(int) (Math.random()*2 + 1);
		boolean quiere=false;
		if(bebida==2) {
			quiere=true;
		}
		return quiere;
	}
	
	
}
