package Tp3Synchronized;

public class Turno {
	private int contTurno=0;
	
	public Turno() {}
	
	public synchronized void incrementarTurno() {
		contTurno++;
		if(contTurno>5) {
			contTurno=0; 
		}
	}
	
	public synchronized boolean esTurno(char letra) {
		boolean esTurno=false;
		if(letra=='A' && contTurno==0 ) {
			esTurno=true;
		}else {
			if(letra=='B' && (contTurno==1 || contTurno==2)) {
				esTurno=true;
			}else {
				if(letra=='C'&& (contTurno==3 || contTurno==4 || contTurno==5)) {
					esTurno=true;
				}
			}
		}
		return esTurno;
	}
}
