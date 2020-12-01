package LatorreFAI1931Parcial2;

public class MainSerie {
	public static void main(String[] args) {
		Serie serie=new Serie();
		Filmador filmador= new Filmador(serie);
		Thread threadFilmador= new Thread(filmador);
		Traductor[]traductores= new Traductor[2];
		Thread[]threadTraductores= new Thread[2];
		Socio[]socios= new Socio[10];
		Thread[]threadSocios= new Thread[10];
		
		for (int i = 0; i < traductores.length; i++) {
			traductores[i]= new Traductor(serie);
		}
		
		for (int i = 0; i < 5; i++) {
			socios[i]= new Socio(serie,'I');
		}
		
		for (int i = 5; i < socios.length; i++) {
			socios[i]= new Socio(serie,'E');
		}
		
		for (int i = 0; i < threadTraductores.length; i++) {
			threadTraductores[i]= new Thread(traductores[i],"Traducotor"+i);
		}
		
		for (int i = 0; i < threadSocios.length; i++) {
			threadSocios[i]= new Thread(socios[i],"Socio"+i);
		}
		
		threadFilmador.start();
		
		for (int i = 0; i < threadTraductores.length; i++) {
			threadTraductores[i].start();
		}
		
		for (int i = 0; i < threadSocios.length; i++) {
			threadSocios[i].start();
		}
	}
}
