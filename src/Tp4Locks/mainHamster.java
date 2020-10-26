package Tp4Locks;

public class mainHamster {
	public static void main(String[] args) {
		Jaula jaula= new Jaula();
		Hamster [] arrayHamster= new Hamster[5];
		Thread[] arrayThread= new Thread[5];
		for (int i = 0; i < arrayHamster.length; i++) {
			arrayHamster[i]=new Hamster(jaula);
		}
		
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i]= new Thread(arrayHamster[i], ("hamster"+i));
		}
		
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i].start();
		}
	}
}
