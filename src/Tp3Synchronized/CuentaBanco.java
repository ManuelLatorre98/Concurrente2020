package Tp3Synchronized;

public class CuentaBanco {
	private int balance=50;
	
	
	public CuentaBanco() {
		
	}
	
	public int getBalance() {
		return balance;
	}
	
	private void retiroBancario(int retiro) {
		balance= balance-retiro;
	}
	
	public synchronized void hacerRetiro(int cantidad) {
		if(this.getBalance()>=cantidad) {
			System.out.println(Thread.currentThread().getName()+ " esta realizando un retiro de: "+cantidad+".");
			try {
				Thread.sleep(1000);
				
			}catch(InterruptedException e) {}
			this.retiroBancario(cantidad);
			System.out.println(Thread.currentThread().getName()+": Retiro realizado");
			System.out.println(Thread.currentThread().getName()+": Los fondos son de: "+this.getBalance());
		}else {
			System.out.println("No hay suficiente dinero en la cuenta para realizar el retiro Sr."+Thread.currentThread().getName());
			System.out.println("Su saldo actual es de: "+this.getBalance());
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
		}
	}
	
}
