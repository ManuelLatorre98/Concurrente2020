package Tp3Synchronized;

public class CuentaBanco {
	private int balance=50;
	
	
	public CuentaBanco() {
		
	}
	
	public int getBalance() {
		return balance;
	}
	
	public synchronized void  retiroBancario(int retiro) {
		balance= balance-retiro;
	}
}
