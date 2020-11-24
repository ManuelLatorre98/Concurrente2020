package LocksCondicion;

public class MainHemoterapia {
	public static void main(String[] args) {
		Persona [] personas= new Persona[10];
		Thread[] threadPersonas= new Thread[10];
		CentroHemoterapia centro= new CentroHemoterapia();
		
		for (int i = 0; i < personas.length; i++) {
			personas[i]= new Persona(centro);
		}
		for (int i = 0; i < threadPersonas.length; i++) {
			threadPersonas[i]= new Thread(personas[i],"Persona"+i);
		}
		
		for (int i = 0; i < threadPersonas.length; i++) {
			threadPersonas[i].start();
		}
	}
}
