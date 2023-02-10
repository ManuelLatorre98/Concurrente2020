package TPOFinal;


public class PuestoEmbarque {
	private static final String CYAN = "\033[0;36m";    // CYAN
	private static final String RESET = "\033[0m";  // Text Reset
	int nroPuesto;
	private Reloj reloj;
	private int cantHorarios=6;
	private String [] horariosEmbarque=new String[cantHorarios];//11:00 primer embarque, 21:00 ultimo embarque
	public PuestoEmbarque(int nro,Reloj reloj) {
		this.nroPuesto=nro;
		this.reloj=reloj;
		this.crearHorariosEmbarque();
		
	}
	
	public void crearHorariosEmbarque() {//Genera automaticamente, para facilitar todos los puertos tienen los mismos horarios de embarque
		int horaPrimerEmbarque=11;//Solo poner la hora. Ejemplo: para 17:00hs hay que poner 17
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

}
