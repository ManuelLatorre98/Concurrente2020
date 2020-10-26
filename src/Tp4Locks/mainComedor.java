package Tp4Locks;

public class mainComedor {
	public static void main(String[] args) {
		Empleado [] arrayEmpleado= new Empleado[6];
		Thread [] arrayThreadEmpleado= new Thread[6];
		Comedor comedor= new Comedor();
		Cocinero cocinero= new Cocinero(comedor);
		Mozo mozo= new Mozo(comedor);
		
		for (int i = 0; i < arrayEmpleado.length; i++) {
			arrayEmpleado[i]= new Empleado (comedor);
		}
		
		for (int i = 0; i < arrayThreadEmpleado.length; i++) {
			arrayThreadEmpleado[i]= new Thread(arrayEmpleado[i], "Empleado"+i);
		}
		
		Thread threadCocinero= new Thread(cocinero);
		Thread threadMozo= new Thread(mozo);
		
		for (int i = 0; i < arrayThreadEmpleado.length; i++) {
			arrayThreadEmpleado[i].start();
		}
		
		threadCocinero.start();
		threadMozo.start();
		
	
	}
}
