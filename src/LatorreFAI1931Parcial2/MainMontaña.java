package LatorreFAI1931Parcial2;

public class MainMonta�a {
	public static void main(String[] args) {
		Monta�aRusa monta�a= new Monta�aRusa(10,4);//capCarro=10, cantVueltas=4
		Pasajero [] pasajeros= new Pasajero[50];
		Thread[]threadPasajeros= new Thread[50];
		
		for (int i = 0; i < pasajeros.length; i++) {
			pasajeros[i]= new Pasajero(monta�a);
		}
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i]= new Thread(pasajeros[i],"Pasajero"+i);
		}
		
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i].start();
		}
		
	}
}
