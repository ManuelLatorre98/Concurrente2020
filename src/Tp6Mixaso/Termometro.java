package Tp6Mixaso;

public class Termometro implements Runnable{
	SalaMuseo sala;
	
	public Termometro(SalaMuseo salaM) {
		this.sala= salaM;
	}
	
	public void run() {
		int temp;
		while(true) {
			temp=generarTemp();
			this.sala.notificarTemperatura(temp);
			try {
				Thread.sleep(5000);
			}catch(InterruptedException e) {}
		}
	}
	
	public int generarTemp() {
		int temp= (int)(Math.random()*10 + 25);
		return temp;
	}
}
