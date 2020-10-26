package Tp5SemaforosGenerales;

public class MainBarbero {
	public static void main(String[] args) {
		Barberia barberia= new Barberia();
		Cliente []cliente= new Cliente[7];
		Thread[] threadCliente= new Thread[7];
		Barbero barbero= new Barbero(barberia);
		
		for (int i = 0; i < cliente.length; i++) {
			cliente[i]= new Cliente(barberia);
		}
		
		for (int i = 0; i < threadCliente.length; i++) {
			threadCliente[i]= new Thread(cliente[i], "cliente"+i);
		}
		Thread threadBarbero= new Thread(barbero);
		
		for (int i = 0; i < threadCliente.length; i++) {
			threadCliente[i].start();
		}
		threadBarbero.start();
	}
}
