package Tp4ExclusionMutua;

public class mainCarrera {
	public static void main(String[] args) {
		Atleta [] arrayCorredor= new Atleta[4];
		Thread [] arrayThread= new Thread[4];
		Carrera carrera= new Carrera();
		
		for (int i = 0; i < arrayCorredor.length; i++) {
			arrayCorredor[i]= new Atleta(carrera,(i+1));
		}
		
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i]= new Thread(arrayCorredor[i]);
		}
		
		for (int i = 0; i < arrayThread.length; i++) {
			arrayThread[i].start();
		}
		
		try {
			for (int i = 0; i < arrayThread.length; i++) {
				arrayThread[i].join();
			}
		}catch(InterruptedException e) {}
		
		System.out.println("Fin de la carrera. Tiempo total de los corredores: "+carrera.getTiempoTotal()+" segundos");
	}
}
