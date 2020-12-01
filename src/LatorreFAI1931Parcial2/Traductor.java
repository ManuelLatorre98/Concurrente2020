package LatorreFAI1931Parcial2;

public class Traductor implements Runnable{
	private Serie serie;
	
	public Traductor(Serie unaSerie) {
		this.serie=unaSerie;
	}
	
	public void run() {
		Capitulo capATraducir;
		while(true) {
			capATraducir=this.serie.traducirCapitulo();
			System.out.println("El "+Thread.currentThread().getName()+" empieza a traducir un capitulo");
			this.traducir(capATraducir);
			this.serie.terminarTaduccion();
			System.out.println("El "+Thread.currentThread().getName()+" termina traducir un capitulo");
		}
	}
	
	private void traducir(Capitulo capitulo) {
		try {
			Thread.sleep(10000);//La traduccion tarda el doble que el rodaje
		}catch(InterruptedException e) {}
		capitulo.traduccion();//Indica que el capitulo ya esta disponible en ingles
	}
}
