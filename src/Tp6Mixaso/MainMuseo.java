package Tp6Mixaso;

public class MainMuseo {
	public static void main(String[] args) {
		Persona[]arrayPersonas= new Persona[150];
		Persona[]arrayJubilados= new Persona[40];
		Thread[]threadPersonas= new Thread[150];
		Thread[]threadJubilados= new Thread[40];
		SalaMuseo sala= new SalaMuseo();
		Termometro termometro= new Termometro(sala);
		Thread threadTermometro= new Thread(termometro, "Termometro");
		
		for (int i = 0; i < arrayPersonas.length; i++) {
			arrayPersonas[i]= new Persona(false,sala);
		}
		
		for (int i = 0; i < arrayJubilados.length; i++) {
			arrayJubilados[i]= new Persona(true,sala);
		}
		
		for (int i = 0; i < threadPersonas.length; i++) {
			threadPersonas[i]= new Thread(arrayPersonas[i],"Persona"+i);
		}
		
		for (int i = 0; i < threadJubilados.length; i++) {
			threadJubilados[i]= new Thread(arrayJubilados[i],"Jubilado"+i);
		}
		
		
		threadTermometro.start();
		for (int i = 0; i < threadPersonas.length; i++) {
			threadPersonas[i].start();
		}
		
		for (int i = 0; i < threadJubilados.length; i++) {
			threadJubilados[i].start();
		}
		
		
	}
}
