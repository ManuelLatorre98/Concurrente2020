package LatorreFAI1931Parcial2;

public class MainMontania {
	public static void main(String[] args) {
		MontaniaRusa montania= new MontaniaRusa(10,4);//capCarro=10, cantVueltas=4
		Pasajero [] pasajeros= new Pasajero[50];
		Thread[]threadPasajeros= new Thread[50];
		
		for (int i = 0; i < pasajeros.length; i++) {
			pasajeros[i]= new Pasajero(montania);
		}
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i]= new Thread(pasajeros[i],"Pasajero"+i);
		}
		
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i].start();
		}
		
	}
}
