package Parcial;

public class mainBlancaNieves {
	public static void main(String[] args) {
		Casa casa= new Casa();
		BlancaNieves blanca= new BlancaNieves(casa);
		Enano [] arrayEnano= new Enano[7];
		Thread [] arrayThread= new Thread[7];
		
		for (int i = 0; i < arrayEnano.length; i++) {
			arrayEnano[i]= new Enano(casa);
		}
		
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i]= new Thread(arrayEnano[i], "Enano"+i);
		}
		Thread threadBlanca= new Thread(blanca);
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i].start();
		}
		threadBlanca.start();
		
	}
}
