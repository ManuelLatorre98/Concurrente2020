package Tp6Mixaso;

public class mainCuartel {
	public static void main(String[] args) {
		Soldado []arraySoldados= new Soldado[150];
		Cuartel cuartel= new Cuartel();
		Thread[]threadSoldados= new Thread[150];
		for (int i = 0; i < arraySoldados.length; i++) {
			arraySoldados[i]= new Soldado(cuartel);
		}
		
		for (int i = 0; i < threadSoldados.length; i++) {
			threadSoldados[i]= new Thread(arraySoldados[i],"Soldado"+i);
		}
		for (int i = 0; i < threadSoldados.length; i++) {
			threadSoldados[i].start();
		}
		
	}
}
