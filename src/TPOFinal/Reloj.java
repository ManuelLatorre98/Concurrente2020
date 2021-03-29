package TPOFinal;

import java.util.concurrent.Semaphore;

public class Reloj {
	private String horaStr;
	private int horaInt;
	private Semaphore mutex=new Semaphore(1,true);
	public Reloj(String hora) {
		this.horaStr=hora;
		this.horaInt=this.traducirHora(hora);
	}
	
	public int traducirHora(String hora) {//Retorna la hora en minutos 
		int horaT=Integer.parseInt(hora.substring(0,hora.indexOf(":")));
		int minT=Integer.parseInt(hora.substring(hora.indexOf(":")+1,5));
		int salida=horaT*60+minT;
		
		return salida;
	}
	
	public String getHoraStr() {
		String hora="";
		try {
			this.mutex.acquire();
			hora=this.horaStr;
			this.mutex.release();
		}catch(InterruptedException e) {}
		return hora;
	}
	
	public int getHoraInt() {
		int hora=0;
		try {
			this.mutex.acquire();
			hora=this.horaInt;
			this.mutex.release();
		}catch(InterruptedException e) {}
		return hora;
	}
	
	public int horaStringToHoraInt(String hora) {//Traduce una hora en formato String a formato int (minutos)
		int horaInt=Integer.parseInt(hora.substring(0, hora.indexOf(":")));
		int min=Integer.parseInt(hora.substring(hora.indexOf(":")+1, 5));
		int salida= (horaInt*60+min);
		return salida;
	}
	
	public String horaIntToHoraString(int horaMin) {//Recibe una hora en minutos y la transforma a un string formato hh:mm
		int horas=horaMin/60;
		int minutos=horaMin%60;
		String salida="";
		if(minutos<10) {
			 salida=horas+":0"+minutos;
		}else {
			salida=horas+":"+minutos;
		}
		return salida;
	}
	
	public String getMinutosStr(String hora) {//retorna los minutos formato mm
		return hora.substring(hora.indexOf(":")+1, 5);
	}
	public void actualizarHora() {//Se actualiza de a 30 minutos
		String horaVieja="";
		String minViejo="";
		String horaNueva="";
		String minNuevo="";
		int horaInt;
		try {
			this.mutex.acquire();
			horaVieja=horaStr.substring(0,horaStr.indexOf(":"));
			minViejo=horaStr.substring(horaStr.indexOf(":")+1,5);
			if(minViejo.equals("00")) {//Si los min estan en 00 solo agrego 30 minutos
				horaNueva=horaVieja;
				minNuevo="30";
			}else {
				if(minViejo.equals("30")) {//Si los min estan en 30 tengo que cambiar la hora y los min a 00
					if(horaVieja.equals("23")) {//23:30 paso a 00:00
						horaNueva="00";
						minNuevo="00";
					}else {//Si no son las 23:30
						horaInt=Integer.parseInt(horaVieja);//Paso la hora a entero
						horaNueva=Integer.toString(horaInt+1);//Si es menor que 9 solo tiene un caracter
						if(horaInt<9){
							horaNueva="0"+horaNueva;//Agrego el 0 restante
						}
						minNuevo="00";
					}
				}
			}
			
			this.horaStr=horaNueva+":"+minNuevo;
			this.horaInt=this.traducirHora(horaStr);
		
			this.mutex.release();
		}catch(InterruptedException e) {}
	}
}
