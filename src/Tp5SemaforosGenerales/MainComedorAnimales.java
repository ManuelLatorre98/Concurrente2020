package Tp5SemaforosGenerales;

public class MainComedorAnimales {
	public static void main(String[] args) {
		ComedorAnimales comedor= new ComedorAnimales();
		Perro []arrayPerros= new Perro[5];
		Gato []arrayGatos= new Gato[10];
		Thread[]threadPerros= new Thread[5];
		Thread[]threadGatos= new Thread[10];
		for (int i = 0; i < arrayPerros.length; i++) {
			arrayPerros[i]= new Perro(comedor, 'P');
		}
		
		for (int i = 0; i < arrayGatos.length; i++) {
			arrayGatos[i]= new Gato(comedor, 'G');
		}
		
		for (int i = 0; i < threadPerros.length; i++) {
			threadPerros[i]= new Thread(arrayPerros[i],"Perro"+i);
		}
		
		for (int i = 0; i < threadGatos.length; i++) {
			threadGatos[i]= new Thread(arrayGatos[i],"Gato"+i);
		}
		
		for (int i = 0; i < threadPerros.length; i++) {
			threadPerros[i].start();
		}
		
		for (int i = 0; i < threadGatos.length; i++) {
			threadGatos[i].start();
		}
	}
}
