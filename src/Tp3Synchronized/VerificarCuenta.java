package Tp3Synchronized;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificarCuenta implements Runnable{
	private CuentaBanco cb= new CuentaBanco();
	
	
	private void hacerRetiro(int cantidad) {
		if(cb.getBalance()>=cantidad) {
			System.out.println(Thread.currentThread().getName()+ " esta realizando un retiro de: "+cantidad+".");
			try {
				Thread.sleep(1000);
				
			}catch(InterruptedException e) {}
			cb.retiroBancario(cantidad);
			System.out.println(Thread.currentThread().getName()+": Retiro realizado");
			System.out.println(Thread.currentThread().getName()+": Los fondos son de: "+cb.getBalance());
		}else {
			System.out.println("No hay suficiente dinero en la cuenta para realizar el retiro Sr."+Thread.currentThread().getName());
			System.out.println("Su saldo actual es de: "+cb.getBalance());
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
		}
	}
	
	public void run() {
		for (int i = 0; i <=3; i++) {
			try {
				this.hacerRetiro(10);
				if(cb.getBalance()<0) {
					System.out.println("La cuenta esta sobregirada");
				}
			}catch(Exception ex) {
				Logger.getLogger(VerificarCuenta.class.getName()).log(Level.SEVERE,null,ex);
			}
		}
	}
}
