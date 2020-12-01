package LatorreFAI1931Parcial1;

public class MainCentro {
	public static void main(String[] args) {
		Paciente[]arrayPaciente= new Paciente[10];
		Thread[] arrayThreadPacientes= new Thread[10];
		Centro centro= new Centro();
		ClinicoControl clinicoControl= new ClinicoControl(centro);
		ClinicoExtractor clinicoExtractor= new ClinicoExtractor(centro);
		Recepcionista recepcionista= new Recepcionista(centro);
		
		for (int i = 0; i < arrayThreadPacientes.length; i++) {
			arrayPaciente[i]= new Paciente(centro);
		}
		for (int i = 0; i < arrayThreadPacientes.length; i++) {
			arrayThreadPacientes[i]= new Thread(arrayPaciente[i],"Paciente"+i);
		}
		
		Thread clinicoControlThread= new Thread(clinicoControl, "Clinico de control");
		Thread clinicoExtractorThread= new Thread(clinicoExtractor, "Clinico de extraccion");
		Thread recepcionistaThread= new Thread(recepcionista, "Recepcionista");
		
		for (int i = 0; i < arrayThreadPacientes.length; i++) {
			arrayThreadPacientes[i].start();
		}
		clinicoControlThread.start();
		clinicoExtractorThread.start();
		recepcionistaThread.start();
	}
}
