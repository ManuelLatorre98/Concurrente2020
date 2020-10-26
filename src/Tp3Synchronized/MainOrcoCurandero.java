package Tp3Synchronized;

public class MainOrcoCurandero {
	public static void main(String[] args) {
		Vida vida= new Vida();
		Orco orco= new Orco(vida);
		Curandero curandero= new Curandero(vida);
		
		Thread threadOrco= new Thread(orco, "Orco");
		Thread threadCurandero= new Thread(curandero, "Curandero");
		
		threadOrco.start();
		threadCurandero.start();
		
		
	}
}
