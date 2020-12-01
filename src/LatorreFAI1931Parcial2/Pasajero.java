package LatorreFAI1931Parcial2;

public class Pasajero implements Runnable{
	private Monta�aRusa monta�a;
	private boolean subio;
	private static boolean fin=false;
	public Pasajero(Monta�aRusa mR) {
		this.monta�a=mR;
	}
	
	public void run() {
		while(!fin) {//Cuando cierra la monta�a termina la ejecucion
			System.out.println("El "+Thread.currentThread().getName()+" intenta subir a la monta�a");
			subio=this.monta�a.intentarSubirMonta�a();
			if(subio) {
				System.out.println("El "+Thread.currentThread().getName()+" sube al carro y espera a que arranque");
				this.monta�a.empezar();
				System.out.println("Arranca el carro y el "+Thread.currentThread().getName()+" da una vuelta en la monta�a");
				this.vueltaMonta�a();
				this.monta�a.bajarCarro();
				System.out.println("El "+Thread.currentThread().getName()+" baja del carro y se va a dar una vuelta al parque");
				this.vueltaParque();
			}else {
				System.out.println("El "+Thread.currentThread().getName()+" se va a su casa porque cerro la monta�a");
				fin=true;
			}
		}
	}
	
	public void vueltaMonta�a() {
		try {
			Thread.sleep(5000);//Los pasajeros pasan 5 segundos en la monta�a rusa
		}catch(InterruptedException e) {}
	}
	
	public void vueltaParque() {
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {}
	}
}
