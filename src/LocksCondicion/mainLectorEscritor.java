package LocksCondicion;

public class mainLectorEscritor {
	public static void main(String[] args) {
		Biblioteca biblioteca= new Biblioteca();
		Lector []lector= new Lector[15];
		Escritor []escritor= new Escritor[3];
		Thread[]threadLector= new Thread[15];
		Thread[]threadEscritor= new Thread[3];
		
		for (int i = 0; i < lector.length; i++) {
			lector[i]= new Lector(biblioteca);
		}
		
		for (int i = 0; i < escritor.length; i++) {
			escritor[i]= new Escritor(biblioteca);
		}
		
		for (int i = 0; i < threadLector.length; i++) {
			threadLector[i]= new Thread(lector[i], "Lector"+i);
		}
		
		for (int i = 0; i < threadEscritor.length; i++) {
			threadEscritor[i]= new Thread(escritor[i], "Escritor"+i);
		}
		
		for (int i = 0; i < threadLector.length; i++) {
			threadLector[i].start();
		}
		
		for (int i = 0; i < threadEscritor.length; i++) {
			threadEscritor[i].start();
		}
		
	}
}
