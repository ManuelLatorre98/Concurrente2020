package Tp6Mixaso;

public class SalaMuseo {
	private int personasMax = 50;
	private int maxPersonasUmbral = 30;
	private int personasPermit=personasMax;
	private int personasActual = 0;
	private int tUmbral = 30;
	private int jubiladosEsp = 0;

	public SalaMuseo() {
	}

	public synchronized void entrarSala() {

		while (personasActual == personasPermit && jubiladosEsp > 0) {
			try {// espera no jubilados
				this.wait();
			} catch (InterruptedException e) {}
		}
		this.personasActual++;
	}

	public synchronized void entrarSalaJubilado() {
		try {// Espera jubilados
			while (personasActual == personasPermit) {
				jubiladosEsp++;
				this.wait();
				jubiladosEsp--;
			}
			personasActual++;
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void salirSala() {
		personasActual--;
		this.notifyAll();
	}
	
	public synchronized void notificarTemperatura(int temperatura) {
		if(this.tUmbral<=temperatura && this.personasPermit!= this.maxPersonasUmbral) {
			this.personasPermit=this.maxPersonasUmbral;//No notifico que se cambio ya que no permite que entren mas personas, al contrario
		}else {
			if(this.tUmbral>temperatura && this.personasPermit!=this.personasMax) {
				this.personasActual= this.personasMax;
				this.notifyAll();//Notifico que pueden entrar mas personas
			}
		}
		
	}
}
