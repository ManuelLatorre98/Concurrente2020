package Tp3Synchronized;

public class MainCuentaBanco {
	public static void main(String[] args) {
		VerificarCuenta vc= new VerificarCuenta();
		
		Thread Luis= new Thread(vc,"Luis");
		Thread Manuel= new Thread(vc,"Manuel");
		Luis.start();
		Manuel.start();
	}
}

