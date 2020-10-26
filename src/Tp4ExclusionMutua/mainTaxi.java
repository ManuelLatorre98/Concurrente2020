package Tp4ExclusionMutua;

public class mainTaxi {
	public static void main(String[] args) {
		Taxi taxi=new Taxi();
		Cliente cliente1= new Cliente(taxi);
		Cliente cliente2= new Cliente(taxi);
		Cliente cliente3= new Cliente(taxi);
		Taxista taxista= new Taxista(taxi);
		Thread threadCliente1= new Thread(cliente1);
		Thread threadCliente2= new Thread(cliente2);
		Thread threadCliente3= new Thread(cliente3);
		Thread threadTaxista= new Thread(taxista);
		threadCliente1.start();
		threadCliente2.start();
		threadCliente3.start();
		threadTaxista.start();
	}
}
