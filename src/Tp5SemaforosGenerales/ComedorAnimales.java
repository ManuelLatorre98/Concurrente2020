package Tp5SemaforosGenerales;

import java.util.concurrent.Semaphore;

public class ComedorAnimales {
	private char turnoEsp = 'P'; // P los perros, G los gatos primero entran perros
	private int cantAnimTurno=5; //Indico cuantos animales de una especie pueden comer hasta cambiar el turno
	private int animParaCambioTurno=cantAnimTurno;
	private int cantComiendo=0;
	private Semaphore semCapacidad = new Semaphore(5, true);
	private Semaphore mutexEspecie = new Semaphore(1, true);

	public ComedorAnimales() {}
	
	public boolean intentarComer(char especie) { // El animal indica su especie
		boolean entro = false;
		try {
			this.mutexEspecie.acquire();
			if (turnoEsp == especie && animParaCambioTurno>0 && this.semCapacidad.tryAcquire() ) {
				entro = true;
				this.animParaCambioTurno--;//Si entra un animal lo indico para saber cuando cambiar turno
				this.cantComiendo++;
			}
			this.mutexEspecie.release();
		} catch (InterruptedException e) {
		}
		return entro;
	}

	public void terminarComer() {
		try {
			this.mutexEspecie.acquire();
			this.semCapacidad.release(); //Libero un espacio de capacidad
			this.cantComiendo--;
			if (this.animParaCambioTurno == 0 || cantComiendo==0) {
				this.cambiarTurno();//Si ya comieron todos lo animales indicados o no hay animales comiendo cambio el turno de la especie
				
			}
			this.mutexEspecie.release();
		} catch (InterruptedException e) {
		}
	}

	private void cambiarTurno() {
		
			if (this.turnoEsp == 'P') {
				this.turnoEsp = 'G';
			} else {
				this.turnoEsp = 'P';
			}
			this.animParaCambioTurno=this.cantAnimTurno;//Reseteo la cantidad que pueden entrar hasta cambiar turno
			System.out.println("CAMBIO");

		
	}
}
