package ActividadTeoria;

public class Alumno implements Runnable{
	Parcial parcial;
	SolucionParcial solucion;
	public Alumno() {
		
	}
	
	public void run() {
		System.out.println("El "+Thread.currentThread().getName()+" resuelve su parcial");
		this.resolverParcial();
		System.out.println("El "+Thread.currentThread().getName()+" termina de resolver su parcial y lo sube a Pedco");
		System.out.println("Pedco esta caido...");
		this.esperarPedco();
		System.out.println("Revivio Pedco, ahora si el "+Thread.currentThread().getName()+" sube su parcial");
		this.parcial.entregaParcial(this.solucion);
		System.out.println("El "+Thread.currentThread().getName()+" termino de subir el parcial. Se va  a llorar a la cama");
	}
	
	private void resolverParcial() {
		try {
			Thread.sleep((long)(Math.random()*10+5)*1000);
		}catch(InterruptedException e) {}
		this.solucion= new SolucionParcial(Thread.currentThread().getName());
	}
	
	private void esperarPedco() {
		try {
			Thread.sleep((long)(Math.random()*2+1)*1000);
		}catch(InterruptedException e) {}
	}
}
