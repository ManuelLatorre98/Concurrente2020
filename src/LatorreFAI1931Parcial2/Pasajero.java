package LatorreFAI1931Parcial2;

public class Pasajero implements Runnable{
	private MontaniaRusa montania;
	private boolean subio;
	private static boolean fin=false;
	public Pasajero(MontaniaRusa mR) {
		this.montania=mR;
	}
	
	public void run() {
		while(!fin) {//Cuando cierra la montania termina la ejecucion
			System.out.println("El "+Thread.currentThread().getName()+" intenta subir a la montania");
			subio=this.montania.intentarSubirMontania();
			if(subio) {
				System.out.println("El "+Thread.currentThread().getName()+" sube al carro y espera a que arranque");
				this.montania.empezar();
				System.out.println("Arranca el carro y el "+Thread.currentThread().getName()+" da una vuelta en la montania");
				this.vueltaMontania();
				this.montania.bajarCarro();
				System.out.println("El "+Thread.currentThread().getName()+" baja del carro y se va a dar una vuelta al parque");
				this.vueltaParque();
			}else {
				System.out.println("El "+Thread.currentThread().getName()+" se va a su casa porque cerro la montania");
				fin=true;
			}
		}
	}
	
	public void vueltaMontania() {
		try {
			Thread.sleep(5000);//Los pasajeros pasan 5 segundos en la montania rusa
		}catch(InterruptedException e) {}
	}
	
	public void vueltaParque() {
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {}
	}
}
