package LocksCondicion;
import java.util.concurrent.locks.*;
public class Biblioteca {
	private Lock lock;
	private Condition esperanAcceso;
	private boolean escribiendo=false;
	private int leyendo=0;
	public Biblioteca() {
		lock= new ReentrantLock(true);
		this.esperanAcceso= this.lock.newCondition();
	}
	
	public void leer() {
		this.lock.lock();
		while(escribiendo) {
			try {
				this.esperanAcceso.await();
			}catch(InterruptedException e) {}
		}
		leyendo++;
		this.lock.unlock();
	}
	
	public void dejarLeer() {
		this.lock.lock();
		leyendo--;
		if(this.leyendo==0) {
			this.esperanAcceso.signal();
		}
		this.lock.unlock();
	}
	
	public void escribir() {
		this.lock.lock();
		while(escribiendo || leyendo>0) {
			try {
				this.esperanAcceso.await();
			}catch(InterruptedException e) {}
		}
		this.escribiendo=true;
		this.lock.unlock();
	}
	
	public void dejarEscribir() {
		this.lock.lock();
		this.escribiendo=false;
		this.esperanAcceso.signal();
		this.lock.unlock();
	}
	

}
