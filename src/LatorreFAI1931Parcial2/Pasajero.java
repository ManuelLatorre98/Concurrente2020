package LatorreFAI1931Parcial2;

public class Pasajero implements Runnable{
	private MontañaRusa montaña;
	private boolean subio;
	private static boolean fin=false;
	public Pasajero(MontañaRusa mR) {
		this.montaña=mR;
	}
	
	public void run() {
		while(!fin) {//Cuando cierra la montaña termina la ejecucion
			System.out.println("El "+Thread.currentThread().getName()+" intenta subir a la montaña");
			subio=this.montaña.intentarSubirMontaña();
			if(subio) {
				System.out.println("El "+Thread.currentThread().getName()+" sube al carro y espera a que arranque");
				this.montaña.empezar();
				System.out.println("Arranca el carro y el "+Thread.currentThread().getName()+" da una vuelta en la montaña");
				this.vueltaMontaña();
				this.montaña.bajarCarro();
				System.out.println("El "+Thread.currentThread().getName()+" baja del carro y se va a dar una vuelta al parque");
				this.vueltaParque();
			}else {
				System.out.println("El "+Thread.currentThread().getName()+" se va a su casa porque cerro la montaña");
				fin=true;
			}
		}
	}
	
	public void vueltaMontaña() {
		try {
			Thread.sleep(5000);//Los pasajeros pasan 5 segundos en la montaña rusa
		}catch(InterruptedException e) {}
	}
	
	public void vueltaParque() {
		try {
			Thread.sleep(10000);
		}catch(InterruptedException e) {}
	}
}
