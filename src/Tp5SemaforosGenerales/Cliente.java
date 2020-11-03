package Tp5SemaforosGenerales;

public class Cliente implements Runnable{
	Barberia barberia;
	public Cliente(Barberia barb) {
		this.barberia=barb;
	}
	
	public void run() {
		boolean entro;
		try {
			Thread.sleep(((long)Math.random()*4+5)*1000);
		}catch(InterruptedException e) {}
		entro=this.barberia.entrarABarberia();
		if(entro) {
		this.barberia.cortarsePelo();
		}
	}
}
