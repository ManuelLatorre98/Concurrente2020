package Tp5SemaforosGenerales;

public class MainPuente {
	public static void main(String[] args) {
		Coche[]coches= new Coche[30];
		Thread[]threadCoche= new Thread[30];
		Puente puente= new Puente();
		
		for (int i = 0; i < 15; i++) {
			coches[i]= new Coche(puente, 'N');
		}
		for (int i = 15; i < coches.length; i++) {
			coches[i]= new Coche(puente, 'S');
		}
		
		for (int i = 0; i < threadCoche.length; i++) {
			threadCoche[i]=new Thread(coches[i],"Coche"+i);
		}
		
		for (int i = 0; i < threadCoche.length; i++) {
			threadCoche[i].start();
		}
	}
}
