package Tp4Locks;

import java.util.concurrent.locks.*;

public class Jaula {
	private Lock lockPlato;
	private Lock lockRueda;
	private Lock lockHamaca;
	

	public Jaula() {
		lockPlato= new ReentrantLock(true);
		lockRueda= new ReentrantLock(true);
		lockHamaca= new ReentrantLock(true);
	}

	public boolean comer() {
		System.out.println("El hamster " + Thread.currentThread().getName() + " va a comer");
		lockPlato.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " esta comiendo");
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " termino de comer");
		} catch (InterruptedException e) {
		} finally {
			lockPlato.unlock();
		}
		return true;
	}

	public boolean correr() {
		System.out.println("El hamster " + Thread.currentThread().getName() + " va a correr");
		lockRueda.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " esta corriendo");
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName() + " termino de correr");
		} catch (InterruptedException e) {
		} finally {
			lockRueda.unlock();
		}
		return true;
	}

	public boolean hamacarse() {
		System.out.println("El hamster " + Thread.currentThread().getName() + " va a hamacarse");
		lockHamaca.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " esta hamacandose");
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName() + " termino de hamacarse");
		} catch (InterruptedException e) {
		} finally {
			lockHamaca.unlock();
		}
		return true;
	}
}
