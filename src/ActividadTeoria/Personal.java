package ActividadTeoria;

public class Personal implements Runnable{
	private String nombre;
	private Saludo saludo;
	private boolean esJefe;
	static int llegaron=0;
	private int numEmp;
	
	Personal(Saludo s, String n){
		esJefe= false;
		nombre= n;
		saludo= s;
	}
	
	Personal(Saludo s, String n, int x){
		esJefe=true;
		nombre=n;
		saludo=s;
		numEmp=x;
	}
	
	public void run() {
	System.out.println("("+nombre+" llega)");
	if(esJefe) {
		while(llegaron<numEmp) {
			System.out.println("(Esperando...)"+Thread.currentThread().getName());
		}//ESPERA ACTIVA
		saludo.saludoJefe();
	}else {
		/*synchronized(this) {*/
			llegaron++;
		/*}*/
		
		saludo.esperarJefe(nombre);
	}
}
}
