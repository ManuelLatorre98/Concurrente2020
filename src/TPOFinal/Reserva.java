package TPOFinal;

public class Reserva {
	private int nroReserva;
	private int nroPuerto;
	private String horaArribo="";
	
	public Reserva(int nroRes, int nroPuerto, String horaStr) {
		this.nroReserva=nroRes;
		this.nroPuerto=nroPuerto;
		this.horaArribo=horaStr;
	}
	
	public int getNroReserva() {
		return this.nroReserva;
	}
	
	public int getNroPuerto() {
		return this.nroPuerto;
	}
	
	public String getHoraArribo() {
		return this.horaArribo;
	}
	
	public void setHoraArribo(String horaStr) {
		this.horaArribo=horaStr;
	}
	public String toString() {
		return "Reserva nro:"+this.nroReserva+" con vuelo nro: "+this.nroPuerto; 
	}
}
