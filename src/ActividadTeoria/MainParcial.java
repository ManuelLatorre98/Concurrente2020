package ActividadTeoria;

public class MainParcial {
	public static void main(String[] args) {
		Parcial parcial= new Parcial();
		Alumno [] alumnos= new Alumno[44];
		Thread [] threadAlumnos= new Thread[44];
		Silvia silvia= new Silvia(parcial);
		Thread threadSilvia= new Thread(silvia,"Silvia");
		for (int i = 0; i < alumnos.length; i++) {
			alumnos[i]= new Alumno(parcial);
		}
		
		for (int i = 0; i < threadAlumnos.length; i++) {
			threadAlumnos[i]= new Thread(alumnos[i],"Alumno"+i);
		}
		threadSilvia.start();
		for (int i = 0; i < threadAlumnos.length; i++) {
			threadAlumnos[i].start();
		}
		
	}
}
