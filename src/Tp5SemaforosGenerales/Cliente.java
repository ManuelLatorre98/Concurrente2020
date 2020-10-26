package Tp5SemaforosGenerales;

public class Cliente implements Runnable{
	Barberia barberia;
	public Cliente(Barberia barb) {
		this.barberia=barb;
	}
	
	public void run() {
		try {
			Thread.sleep(((long)Math.random()*4+5)*1000);
		}catch(InterruptedException e) {}
		this.barberia.entrarABarberia();
		this.barberia.cortarsePelo();
	}
}
