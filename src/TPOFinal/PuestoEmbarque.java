package TPOFinal;

import java.util.concurrent.Semaphore;

public class PuestoEmbarque {
	int nroPuesto;
	private Reloj reloj;
	private String [] horariosEmbarque=new String[5];//07:00 primer embarque, 21:00 ultimo embarque, //De aca se saca uno random y se lo entrega al cliente
	
	public PuestoEmbarque(int nro,Reloj reloj) {
		this.nroPuesto=nro;
		this.reloj=reloj;
		this.setearHorariosEmbarque();
		
	}
	
	public void crearHorariosEmbarque() {//Genera automaticamente, para facilitar todos los puertos tienen los mismos horarios de embarque
		int multiplicadorHoras;
		int horaMin;
		String horaArribo;
		for (int i = 0; i < horariosEmbarque.length; i++) {
			horaMin=(60*(17+i));//Siempre va a ser hora exacta,horarios entre 17 y 21 hs para que puedan entrar la mayor cantidad de hilos
			horaArribo=reloj.horaIntToHoraString(horaMin);
			this.horariosEmbarque[i]=horaArribo;
		}
	}
	
	public String getHorarioRandom() {
		return this.horariosEmbarque[((int)Math.random()*4)];//Retorna un horario random para asignarselo al cliente
	}
	
	public int getNroPuesto() {
		return this.nroPuesto;
	}

	
	private void setearHorariosEmbarque() {//Un embarque cada una hora
		int hora=7;
		for (int i = 0; i < horariosEmbarque.length; i++) {
			if(hora<10) {
				this.horariosEmbarque[i]="0"+hora+":00";
			}else {
				this.horariosEmbarque[i]=hora+":00";
			}
			hora++;
		}
	}
	
	public synchronized void revicionHora() {
		int tiempoProximoEmbarque=60;//Define en minutos en cuanto tiempo es el proximo embarque
		if(reloj.getHoraStr().equals(this.horaProximoEmbarque) && reloj.getHoraInt()>=reloj.horaStringToHoraInt("06:00") && reloj.getHoraInt()<=reloj.horaStringToHoraInt("20:00")) {//Si no esta abierto no lo actualiza
			this.horaProximoEmbarque= reloj.horaIntToHoraString((reloj.getHoraInt()+60));//El proximo embarque va a ser 60 una hora despues de la hora actual
			this.horaEmbarqueActual=reloj.getHoraStr();
			
		}
	}
}
