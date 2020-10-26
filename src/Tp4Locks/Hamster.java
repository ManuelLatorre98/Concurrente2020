package Tp4Locks;

public class Hamster implements Runnable{
	Jaula jaula;
	private boolean comio=false;
	private boolean corrio=false;
	private boolean seHamaco=false;
	public Hamster(Jaula unaJaula) {
		this.jaula=unaJaula;
	}
	
	public void run() {
		int rnd;
		while(!comio || !corrio || !seHamaco) {
			rnd=(int)(Math.random()*3 +1);
			if(!comio && rnd==1) {
				comio=jaula.comer();
			}
			if(!corrio && rnd==2) {
				corrio=jaula.correr();
			}
			if(!seHamaco && rnd==3) {
				seHamaco=jaula.hamacarse();
			}
		}
	}
}
