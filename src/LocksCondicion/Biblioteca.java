package LocksCondicion;

import java.util.concurrent.locks.*;

public class Biblioteca {
	private Lock lock;
	private Condition esperanLeer;
	private Condition esperanEscribir;
	private boolean escribiendo = false;
	private int leyendo = 0;
	private int cantEspLeer = 0;
	private int cantEspEsc = 0;

	public Biblioteca() {
		lock = new ReentrantLock(true);
		this.esperanLeer = this.lock.newCondition();
		this.esperanEscribir = this.lock.newCondition();
	}

	public void leer() {
		this.lock.lock();
		while (escribiendo) {
			try {
				cantEspLeer++;
				this.esperanLeer.await();
			} catch (InterruptedException e) {
			}
		}
		cantEspLeer--;
		leyendo++;
		this.lock.unlock();
	}

	public void dejarLeer() {
		this.lock.lock();
		leyendo--;
		if (this.leyendo == 0 && this.cantEspEsc > 0) {// Si no hay lectores y hay escritores esperando les avisa
			this.esperanEscribir.signal();// Libero a uno solo
		}
		this.lock.unlock();
	}

	public void escribir() {
		this.lock.lock();
		while (escribiendo || leyendo > 0) {
			try {
				this.cantEspEsc++;
				this.esperanEscribir.await();
			} catch (InterruptedException e) {
			}
		}
		cantEspEsc--;
		this.escribiendo = true;
		this.lock.unlock();
	}

	public void dejarEscribir() {
		this.lock.lock();
		this.escribiendo = false;
		if (this.cantEspLeer > 0) {
			this.esperanLeer.signalAll();// Si hay lectores esperando los libero a todos para que lean
		} else {
			if (this.cantEspEsc > 0) {
				this.esperanEscribir.signal();// Si no hay lectores libero a un escritor esperando
			}
		}
		this.lock.unlock();
	}

}
