package LatorreFAI1931Parcial2;

public class Serie {
	private Lista capitulos = new Lista();
	private int nroCapituloFilma = 1;
	private int nroCapituloATraducir = 1;
	private int capitulosSinTraduccion = 0;

	public Serie() {
	}

	public synchronized void terminarFilmarCapitulo() {// Es un solo hilo pero sincronizo por reutilizacion a futuro
		Capitulo capitulo = new Capitulo(this.nroCapituloFilma);
		this.capitulos.insertar(capitulo, this.nroCapituloFilma);
		this.nroCapituloFilma++;
		this.capitulosSinTraduccion++;
		//colaTraduccion ++
		this.notifyAll();// Avisa que hay un capitulo nuevo y que hay que traducirlo
	}

	public synchronized Capitulo traducirCapitulo() {
		Capitulo capitulo;
		//colaTraduccion--;
		while (this.capitulosSinTraduccion == 0) {// Si no hay capitulos que traducir espera
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.capitulosSinTraduccion--;// Como ya esta en proceso de traduccion indico que hay uno menos por traducir
		capitulo = (Capitulo) capitulos.recuperar(nroCapituloATraducir);// Retornaba un object por eso (Capitulo)
		this.nroCapituloATraducir++;//Indico que se puede traducir el siguiente capitulo
		return capitulo;// Retorna el capitulo para que el traductor lo traduzca
	}
	
	public synchronized void terminarTaduccion() {
		this.notifyAll();//Notifica que hay un nuevo capitulo traducido
	}

	public synchronized void empezarVerCapitulo(char idiomaSocio, int nroCapitulo) {
		while(nroCapitulo>=this.nroCapituloFilma) {//Si el capitulo todavia no se esta filmando o esta en proceso espera
			try {
				this.wait();
			}catch(InterruptedException e) {}
			
		}
		Capitulo capitulo = (Capitulo) this.capitulos.recuperar(nroCapitulo);//Obtengo el capitulo que quiere ver el socio
		
		while((idiomaSocio == 'I' && !capitulo.getIng())) {//Si no esta disponible espera (como en español esta de salida no esperaria nunca)
			try {
				this.wait();
			}catch(InterruptedException e) {}
		}
		//Socio empieza a ver capitulo en su clase
	}
}
