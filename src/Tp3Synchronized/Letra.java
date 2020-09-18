package Tp3Synchronized;

public class Letra implements Runnable {
	private char letra;
	private Turno turno;

	public Letra(char let, Turno tur) {
		this.letra = let;
		this.turno = tur;
	}

	public void run() {
	
		while (true) {
			while (!turno.esTurno(this.letra)) {
				try {// Por poner algo en el while
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}

			while (turno.esTurno(letra)) {
				System.out.print(letra);
				turno.incrementarTurno();
			}
		
		}
	}
}
