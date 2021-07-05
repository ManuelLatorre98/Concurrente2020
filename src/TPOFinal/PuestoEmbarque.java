package TPOFinal;


public class PuestoEmbarque {
	private static final String CYAN = "\033[0;36m";    // CYAN
	private static final String RESET = "\033[0m";  // Text Reset
	int nroPuesto;
	private Reloj reloj;
	private int cantHorarios=6;
	private String [] horariosEmbarque=new String[cantHorarios];//11:00 primer embarque, 21:00 ultimo embarque, //De aca se saca uno random y se lo entrega al cliente
	//Para los horarios podria haber usado una lista para que sea mas reutilizable, pero con un array me alcanza para testear
	//En vez de creacion automatica de horarios podria poner un setHorarios para poner los horarios deseados en la lista
	private int horaUltimoEmbarque;
	public PuestoEmbarque(int nro,Reloj reloj) {
		this.nroPuesto=nro;
		this.reloj=reloj;
		this.crearHorariosEmbarque();
		
	}
	
	public void crearHorariosEmbarque() {//Genera automaticamente, para facilitar todos los puertos tienen los mismos horarios de embarque
		int horaPrimerEmbarque=11;//Solo poner la hora. Ejemplo: para 17:00hs hay que poner 17, hay que tener cuidado con array
		int horaMin;
		int diferenciaEntreEmbarque=2;//Cantidad de horas entre embarques
		String horaArribo;
		for (int i = 0; i < horariosEmbarque.length; i++) {
			horaMin=((60)*(horaPrimerEmbarque+(i*diferenciaEntreEmbarque)));//Siempre va a ser hora exacta,horarios entre 11 y 21 hs con diferencia de 2hs entre embarques
			horaArribo=reloj.horaIntToHoraString(horaMin);
			this.horariosEmbarque[i]=horaArribo;
		}
	}
	
	public String getHorarioRandom() {
		return this.horariosEmbarque[((int)(Math.random()*cantHorarios))];//Retorna un horario random para asignarselo al cliente
	}
	
	public int getNroPuesto() {
		return this.nroPuesto;
	}
	
	private boolean esHorarioEmbarque(String hora) {
		boolean encontrado=false;
		int i=0;
		while(i<this.horariosEmbarque.length && !encontrado) {
			if(this.horariosEmbarque[i].equals(hora)) {
				encontrado=true;
			}else {
				i++;
			}
		}
		return encontrado;
	}
	
	public void revisionHora() {
		/*String horaActual=reloj.getHoraStr();
	
		if(this.esHorarioEmbarque(reloj.getHoraStr())) {//Si la hora actual es la hora de un arribo del puesto de embarque
			System.out.println(CYAN+"El puesto de embarque "+this.nroPuesto+" abre sus puertas de embarque"+RESET);
			this.horaUltimoEmbarque=reloj.horaStringToHoraInt(reloj.getHoraStr());
		}else {
			if(reloj.getHoraInt()==this.horaUltimoEmbarque+30) {//Habra 30 minutos para que los pasajeros puedan embarcar luego se cierran las puertas
				System.out.println(CYAN+"El puesto de embarque "+this.nroPuesto+" cierra sus puertas de embarque"+RESET);
			}
		}*///Descomentar para ver que abren y cierran embarques correctamente
	}

}
