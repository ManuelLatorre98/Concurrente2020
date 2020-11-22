package LocksCondicion;

import java.util.concurrent.locks.*;

public class Biblioteca {
	private Lock lock;
	private Condition esperanLeer;
	private Condition esperanEscribir;
	private int cantLectoresParaCamb=10;//entran hasta 10 lectores consecutivos y hace cambio si hay escritores esperando
	private int cantEscritoresParaCamb=2;//entran hasta 2 escritores consecutivos y si hay lectores esperando hace cambio
	private int cantLectoresEsp=0;
	private int cantEscritoresEsp=0;
	private int cantLectores=0;
	private boolean escribiendo=false;

	public Biblioteca() {
		lock = new ReentrantLock(true);
		this.esperanLeer = this.lock.newCondition();
		this.esperanEscribir = this.lock.newCondition();
	}

	public void leer() {
		this.lock.lock();
		this.cantLectoresEsp++;
		while(this.escribiendo || cantLectoresParaCamb==0 ) {
			try {
				this.esperanLeer.await();
			}catch (InterruptedException e){}
		}
		this.cantLectoresEsp--;
		this.cantLectoresParaCamb--;
		this.cantLectores++;
		this.lock.unlock();
	}

	public void dejarLeer() {
		this.lock.lock();
		this.cantLectores--;
		if((this.cantLectoresParaCamb==0 && this.cantLectores==0 && this.cantEscritoresEsp>0) || (this.cantLectores==0 && this.cantEscritoresEsp>0)) {//El or es por si entraban <10 nomas no habia mas lectores
			this.cantEscritoresParaCamb=2;
			this.esperanEscribir.signal();//si ya leyeron 10, terminaron todos y hay minimo un escritor esperando le avisa
		}else {
			if(this.cantEscritoresEsp==0 && cantLectores==0 && this.cantLectoresEsp>0) {//Si terminaron de leer 10 y no hay escritores pueden entrar n mas a leer
				this.cantLectoresParaCamb=10;//Si no, pueden entrar otros n lectores si los hay
				this.esperanLeer.signalAll();//Avisa que pueden entrar otros n
			}
		}
		lock.unlock();
	}

	public void escribir() {
		this.lock.lock();
		this.cantEscritoresEsp++;
		while(escribiendo || this.cantLectores>0 || this.cantEscritoresParaCamb==0) {
			try {
				this.esperanEscribir.await();
			}catch(InterruptedException e) {}
		}
		this.cantEscritoresParaCamb--;
		this.cantEscritoresEsp--;
		this.escribiendo=true;
		this.lock.unlock();
	}

	public void dejarEscribir() {
		this.lock.lock();
		this.escribiendo=false;
		if((this.cantEscritoresParaCamb==0 && this.cantLectoresEsp>0)|| this.cantEscritoresEsp==0) {//si escribieron los 2 escritores o no hay escritores esperando
			this.cantLectoresParaCamb=10;
			this.esperanLeer.signalAll();;//Si escribieron n escritores, notifica a los lectores que pueden leer
		}else {
			if((this.cantEscritoresParaCamb>0 && this.cantEscritoresEsp>0)) {
				this.esperanEscribir.signal();;//Si no escribieron n escritores y hay esperando, libera a uno
			}else {
				if(this.cantEscritoresParaCamb==0 && this.cantLectoresEsp==0) {
					this.cantEscritoresParaCamb=2; //Si ya escribieron todos, pero no hay lectores pueden entrar otros n escritores
					this.esperanEscribir.signal(); //le aviso a uno
				}
			}
		}
		this.lock.unlock();
	}

}
