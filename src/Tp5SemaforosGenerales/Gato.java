package Tp5SemaforosGenerales;

public class Gato implements Runnable{
	private ComedorAnimales comedor;
	private char especie;
	public Gato(ComedorAnimales com,char esp) {
		this.comedor=com;
		this.especie=esp;
	}
	
	public void run() {
		while(true) {
			System.out.println("El"+Thread.currentThread().getName()+" intenta entrar a comer");
			if(this.comedor.intentarComer(especie)) {
				System.out.println("El"+Thread.currentThread().getName()+" entra y come");
				try {
					Thread.sleep(5000);
				}catch(InterruptedException e) {}
				this.comedor.terminarComer();
				System.out.println("El"+Thread.currentThread().getName()+" termino de comer");
			}else {
				System.out.println("El"+Thread.currentThread().getName()+" no pudo entrar, se va a pasear");
			}
			try {
				Thread.sleep(6000);
			}catch(InterruptedException e) {}
		}
	}
}
