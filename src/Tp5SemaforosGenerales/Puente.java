package Tp5SemaforosGenerales;

import java.util.concurrent.Semaphore;

public class Puente {
	private int contNorteCambio = 10;
	private int contSurCambio = 10;
	private int contCochesEspNorte = 0;
	private int contCochesEspSur = 0;
	private boolean pasaNorte = false;
	private boolean pasaSur = false;
	private int cochesEnPuente=0;
	private int turnoEntregaNorte=1;
	private int turnoEntregaSur=1;
	private int turnoActualNorte=1;
	private int turnoActualSur=1;
	
	private int turnoSalidaNorte=1;
	private int turnoSalidaSur=1;
	
	public synchronized int obtieneTurnoNorte() {
		int entrega= turnoEntregaNorte;
		this.turnoEntregaNorte++;
		return entrega;
	}
	
	public synchronized int obtieneTurnoSur() {
		int entrega= turnoEntregaSur;
		this.turnoEntregaSur++;
		return entrega;
	}
	
	public synchronized void entraCocheNorte(int turno) {

		this.contCochesEspNorte++;
		while (pasaSur || this.cochesEnPuente==10 || contNorteCambio==0 || turno!=this.turnoActualNorte) {
			try {
				this.wait();// Mientras haya pasando autos del sur, 10 autos en puente, tenga que cambiar o no sea su turno
			} catch (InterruptedException e) {
			}
		}
		if (!this.pasaNorte) {// indico que estan pasando del norte
			this.pasaNorte = true;
		}
		this.contCochesEspNorte--;
		this.cochesEnPuente++;
		this.turnoActualNorte++;//Indica que paso y que podria pasar el siguiente
		this.contNorteCambio--;
		
		this.notifyAll();
	}

	public synchronized void salirCocheNorte(int turno) {
		while(turnoSalidaNorte!=turno) {
			try {
				this.wait();
			}catch(InterruptedException e) {}
		}
		this.turnoSalidaNorte++;
		
		this.cochesEnPuente--;
		if (this.contCochesEspSur > 0 && (this.contNorteCambio == 0 || this.contCochesEspNorte == 0) && this.cochesEnPuente==0) {// Si pasaron 10 o no hay  esperando avisa a los
			// del sur
			this.pasaNorte = false;
			this.pasaSur=true;
			this.contSurCambio = 10;
			this.contNorteCambio=0;
		} else {
			if (this.contNorteCambio == 0 && this.contCochesEspSur == 0 && this.contCochesEspNorte > 0 && this.cochesEnPuente==0) {// Si no hay coches esperando del norte pero si del sur  deja  pasar del norte
				this.contNorteCambio = 10;
			}
		}
		
		
		this.notifyAll();
		//verifico turno salida
		//cuando salgo incremento turno salida
		//notifico turno de la salida

	}

	public synchronized void entraCocheSur(int turno) {

		this.contCochesEspSur++;
		while (pasaNorte || this.cochesEnPuente==10 || contSurCambio==0 || turno!= this.turnoActualSur) {
			try {
				this.wait();// Mientras haya pasando autos del sur espera
			} catch (InterruptedException e) {
			}
		}
		if (!this.pasaSur) {// indico que estan pasando del norte
			this.pasaSur = true;
		}

		this.contCochesEspSur--;
		this.cochesEnPuente++;
		this.turnoActualSur++;
		this.contSurCambio--;
		this.notifyAll();
	}

	public synchronized void salirCocheSur(int turno) {	
		while(turnoSalidaSur!=turno) {
			try {
				this.wait();
			}catch(InterruptedException e) {}
		}
		this.turnoSalidaSur++;
		this.cochesEnPuente--;
		
		if (this.contCochesEspNorte > 0 && (this.contSurCambio == 0 || this.contCochesEspSur == 0) && this.cochesEnPuente==0) {// Si pasaron 10 o no hay esperando avisa a los
			// del norte
			this.pasaSur = false;
			this.pasaNorte=true;
			this.contNorteCambio= 10;
			this.contSurCambio=0;
		} else {
			if (this.contSurCambio == 0 && this.contCochesEspNorte == 0 && this.contCochesEspSur > 0 && this.cochesEnPuente==0) {// Si no hay coches esperando del norte pero si del sur deja pasar del norte
				this.contSurCambio = 10;
				
			}
		}
		this.notifyAll();
	}
	
	
	
}
