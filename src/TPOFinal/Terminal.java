package TPOFinal;

import java.util.concurrent.Semaphore;

public class Terminal {
	private char letraTerminal;
	private Reloj reloj;
	private PuestoEmbarque [] puestosEmbarque;//Estan representados por numeros ya que no da datos el enunciado, y con numeros me alcanza para simularlos
	private Semaphore semCapacidadShop=new Semaphore(5,true);
	private Semaphore semCajasShop=new Semaphore(2,true);//Hay 2 cajas
	public Terminal(char letraTerm,PuestoEmbarque[]puestosEmbarque,Reloj reloj) {
		this.letraTerminal=letraTerm;
		this.puestosEmbarque=puestosEmbarque;
		this.reloj=reloj;
	
	}
	
	public char getLetraTerminal() {
		return this.letraTerminal;
	}
	
	public PuestoEmbarque getPuestoEmbarque(int nroPuesto) {
		PuestoEmbarque puesto=null;
		boolean encontrado=false;
		int i=0;
		while(i<puestosEmbarque.length && !encontrado) {
			if(puestosEmbarque[i].nroPuesto==nroPuesto) {
				puesto=puestosEmbarque[i];
				encontrado=true;
			}else {
				i++;
			}
		
		}
		return puesto;
	}
	
	public int getCantidadPuestosEmbarque() {
		return this.puestosEmbarque.length;
	}
	
	public boolean intentarEntrarShop(Pasajero pasajero) {//Si ya esta lleno desiste de querer entrar y va a esperar el arribo;
		boolean entro;
		entro=this.semCapacidadShop.tryAcquire();
		return entro;
	}
	
	public void comprarEnShop(Pasajero pasajero) {
		try {
			this.semCajasShop.acquire();
			pasajero.compraEnShop();
			this.semCajasShop.release();
		}catch(InterruptedException e) {}
	}
	
	
	public void salirShop() {
		this.semCapacidadShop.release();
	}
	
	public synchronized void esperarEmbarque(Reserva reserva) {
		while(!reserva.getHoraArribo().equals(reloj.getHoraStr())) {//Mientras no sea su hora de arribo espera
			try {	
				this.wait();
			}catch(InterruptedException e) {}
		}
	}
	
	public synchronized void revicionHora() {
		this.notifyAll();
	}
	
	
	
}
