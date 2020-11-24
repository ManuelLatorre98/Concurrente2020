package LocksCondicion;
import java.util.concurrent.locks.*;
public class CentroHemoterapia {
	private Lock lock;
	private Condition esperaLeyendo;
	private Condition esperaViendoTele;
	private int nroTurno=1;
	private int nroTurnoEntrega=1;
	private int nroSillas=12;
	private int nroSillasOcup=0;
	private int nroCamillas=4;
	private int nroCamillasOcup=0;
	private int nroRevistas=9;
	private int nroRevistasOcup=0;
	
	public int sacarNumero() {
		int nro;
		lock.lock();
		nro= this.nroTurnoEntrega;//Le da un numero de turno al usuario
		this.nroTurnoEntrega++; //Incrementa en uno para el proximo
		lock.unlock();
		return nro;
	}
	
	public boolean intentarAtenderse(int nroTurPersona) {
		boolean atendido=false;
		lock.lock();
		if(nroTurPersona== nroTurno && nroCamillasOcup<nroCamillas) {//Si coincide el nro de turno y hay camillas
			atendido=true;
		}
		lock.unlock();
		return atendido;
	}
	
	public boolean intentarSentarse() {
		boolean sentado=false;
		lock.lock();
		if(this.nroSillasOcup<this.nroSillas) {
			sentado=true;
			this.nroSillasOcup++;
		}
		lock.unlock();
		return sentado;
	}
	
	public void tomarRevista(int nroTurPersona) {
		lock.lock();
		while((nroTurPersona!= nroTurno && nroRevistasOcup==nroRevistas) || this.nroCamillasOcup==this.nroCamillas) {//Si no es su turno y no hay revistas espera poder agarrar una
			try {
				System.out.println("La "+Thread.currentThread()+" no puede tomar revista, mira la tele");
				esperaViendoTele.await();//espera viendo tele si no hay revistas
			}catch(InterruptedException e) {}
		}
		lock.unlock();
	}
	
	public void esperarLeyendo(int nroTurPersona) {
		boolean leia=false;
		lock.lock();
		while(nroTurPersona!= nroTurno || this.nroCamillasOcup==this.nroCamillas) {//Mientras no sea su turno y no haya camillas espera
			try {
				System.out.println("La "+Thread.currentThread()+" toma una revista y espera leyendo a ser atentida");
				leia=true;
				this.esperaLeyendo.await();//Espera a que sea su turno
			}catch(InterruptedException e) {}
		}
		if(leia) {
			this.nroRevistasOcup--; //Si estaba leyendo suelta la revista
			this.esperaViendoTele.signal();//Avisa que hay una revista disponible a los que miran la tele
		}
		lock.unlock();
	}
	
	public void pararse(boolean sentado) {
		lock.lock();
		if(sentado) {//Si estaba sentado libera la silla
			this.nroSillasOcup--;
		}
		lock.unlock();
	}
	
	public void atenderse() {
		lock.lock();
			this.nroCamillasOcup++;
			this.nroTurno++;//indico quien es el siguiente que podria entrar, si hay camillas pasaria,si no , no.
		lock.unlock();
	}
	
	public void salir() {
		lock.lock();
		this.nroCamillasOcup--;//hay una camilla mas disponible
		this.esperaViendoTele.signalAll();//Avisa a todos los que mira tele y a todos los que leen
		this.esperaLeyendo.signalAll();//Si coincide su numero de turno y como hay camillas pasan
		lock.unlock();
	}
}
