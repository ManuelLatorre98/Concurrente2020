package ActividadTeoria;

public class SolucionParcial {
	private char nota;
	private String nombreAlumn;
	public SolucionParcial(String nombre) {
		this.nombreAlumn=nombre;
	}
	
	public void setNota(char unaNota) {
		this.nota=unaNota;
	}
	
	public String getNombreAlumn() {
		return this.nombreAlumn;
	}
	
}
