package LatorreFAI1931Parcial1;

import java.util.concurrent.Semaphore;

public class Centro {
	// VARIABLES Y SEMAFOROS LLAMDA DEL CLIENTE Y ATENCION DEL RECEPCIONISTA
	private Semaphore mutexLlamada = new Semaphore(1, true);
	private Semaphore semLlamada = new Semaphore(0, true);
	private Semaphore semRecepcionista = new Semaphore(0, true);
	private int llamadasEspera = 0;

	// VARIABLES Y SEMAFOROS SALA DE ESPERA Y ATENCION CONTROL
	private Semaphore semAtencionCtrl = new Semaphore(0, true);
	private Semaphore semClinicoCtrl = new Semaphore(0, true);
	private Semaphore mutexCtrl = new Semaphore(1, true);
	private Semaphore semEstudios = new Semaphore(0, true);
	private int esperaPacienteCtrl = 0;
	
	
	//VARIABLES Y SEMAFOROS ATENCION EXTRACCION
	private Semaphore semAtencionExtr = new Semaphore(0, true);
	private Semaphore mutexExtr= new Semaphore(1, true);
	private Semaphore semExtraccion= new Semaphore(0,true);
	private int esperaPacienteExtr=0;
	
	public Centro() {}

	// LLAMADA DEL CLIENTE Y ATENCION DEL RECEPCIONISTA
	public void llamar() {
		System.out.println("El " + Thread.currentThread().getName()+ " llama al centro para donar sangre y espera que lo atiendan");
		try {
			mutexLlamada.acquire();
			llamadasEspera++; // Sumo uno al contador en espera
			
			if(this.llamadasEspera==1) { //Para no hacer releases de mas
			semRecepcionista.release();//Llama a la recepcionista (le avisa)
			}
			mutexLlamada.release();
			

			semLlamada.acquire();// Se queda esperando a que lo atiendan
			System.out.println("El cliente fue atendido y corta");
		} catch (InterruptedException e) {
		}
	}

	public void atenderRecepcion() {

		System.out.println("No hay llamadas, el recepcionista procesa y almacena sangre en heladeras");
		try {
			semRecepcionista.acquire(); // Se queda esperando a que una llamada de un cliente lo libere
			this.atenderLlamadas(); // Cuando recibe una llamada atiende

		} catch (InterruptedException e) {
		}
	}

	private void atenderLlamadas() {
		boolean sinAtender = false;
	
		try {
			this.mutexLlamada.acquire();
			if (this.llamadasEspera > 0) { // Verifico si hay alguna llamada
				sinAtender = true;
			}
			this.mutexLlamada.release();
			
			while (sinAtender) { // Si tengo muchas llamadas en espera las atiende todas antes de irse
				System.out.println("El recepcionista atiende el telefono a una persona");
				Thread.sleep(3000); //Simulo la atencion
				semLlamada.release(); // Atiende a un cliente (le avisa)
				this.mutexLlamada.acquire();
				this.llamadasEspera--;// Saco una llamada de las de espera
				if (this.llamadasEspera <= 0) { // Verifico si quedan llamadas sin atender
					sinAtender = false;
				}
				this.mutexLlamada.release();
				
			}
		} catch (InterruptedException e) {
		}
	}

	//SALA DE ESPERA Y ATENCION CONTROL

	public void esperaAtencionCtrl() {
		System.out.println(
				"El " + Thread.currentThread().getName() + " asiste al centro y espera que lo atiendan sentado");
		try {
			this.mutexCtrl.acquire();
			this.esperaPacienteCtrl++; // Sumo uno a la cola de espera de ser atendidos
			this.mutexCtrl.release();

			this.semAtencionCtrl.acquire();// Espera a que lo llame un medico para realizar el control
			System.out.println("El " + Thread.currentThread().getName() + " pasa a hacerse los estudios");
			this.semEstudios.acquire();// Espera hasta que el medico termine de hacerle los estudios clinicos
			System.out.println("El " + Thread.currentThread().getName()+ " termino con los estudios, ahora espera a ser atendido para la extraccion");

		} catch (InterruptedException e) {
		}
	}

	public void realizarControlClinico() {
		boolean sinAtender = false;
		System.out.println("No hay pacientes para realizar controles, espera a que llegue uno");
		while (!sinAtender) { // Se queda esperando hasta que haya como minimo un paciente que atender
			try {
				this.mutexCtrl.acquire();
				if (this.esperaPacienteCtrl > 0) {
					sinAtender = true;
				}
				this.mutexCtrl.release();
			} catch (InterruptedException e) {
			}
		}
		while (sinAtender) { // Mientras haya pacientes que atender los atiendo
			this.atenderPacienteControl();

			try {
				this.mutexCtrl.acquire();
				this.esperaPacienteCtrl--; // como termine de atender a uno lo saco de los que esperan
				if (this.esperaPacienteCtrl == 0) { // Si no quedan pacientes lo indico
					sinAtender = false;
				}
				this.mutexCtrl.release();
			} catch (InterruptedException e) {
			}

		}

	}

	private void atenderPacienteControl() {
		System.out.println("El " + Thread.currentThread().getName() + " llama a un paciente para realizarle los estudios");
		this.semAtencionCtrl.release(); // Le dice al siguiente paciente que puede pasar
		try {
			Thread.sleep(10000); // Simulacion de los estudios, no pongo cuales son para no sobrecargar la
									// consola de info
		} catch (InterruptedException e) {
		}
		System.out.println("El " + Thread.currentThread().getName() + " termino de hacerle los estudios al paciente");
		this.semEstudios.release();// Le dice al paciente que ya terminaron que puede retirarse
	}
	
	//ESPERA Y ATENCION EXTRACCION
	
	public boolean hacerseExtraccion() {
		System.out.println("El " + Thread.currentThread().getName()+" espera a que lo llamen para hacerse la extraccion");
		try {
			this.mutexExtr.acquire();
			this.esperaPacienteExtr++; // Sumo uno a la cola de espera de ser atendidos
			this.mutexExtr.release();
			this.semAtencionExtr.acquire();//Se queda esperando hasta que lo llame el medico de extracciones
			System.out.println("El " + Thread.currentThread().getName() + " pasa a hacerse la extraccion");
			this.semExtraccion.acquire();// Espera hasta que el medico termine de hacerle la extraccion
			System.out.println("El " + Thread.currentThread().getName()+ " termino de hacerse la extraccion, recibe el diploma");
			

		}catch(InterruptedException e) {}
		return true; //Representa que recibio el diploma
	}
	
		
	public void realizarExtracciones() {
		boolean sinAtender=false;
		System.out.println("No hay pacientes para realizarse extracciones " + Thread.currentThread().getName()+" espera a que llegue alguno");
		while (!sinAtender) { // Se queda esperando hasta que haya como minimo un paciente que atender
			try {
				this.mutexExtr.acquire();
				if (this.esperaPacienteExtr > 0) {
					sinAtender = true;
				}
				this.mutexExtr.release();
			} catch (InterruptedException e) {
			}
		}
		
		while (sinAtender) { // Mientras haya pacientes que atender los atiendo
			this.atenderPacienteExtraccion();

			try {
				this.mutexExtr.acquire();
				this.esperaPacienteExtr--; // como termine de atender a uno lo saco de los que esperan
				if (this.esperaPacienteExtr == 0) { // Si no quedan pacientes lo indico
					sinAtender = false;
				}
				this.mutexExtr.release();
			} catch (InterruptedException e) {
			}

		}
		
	}
	
	private void atenderPacienteExtraccion() {
		System.out.println("El " + Thread.currentThread().getName() + " llama a un paciente para realizarle los estudios");
		this.semAtencionExtr.release(); // Le dice al siguiente paciente que puede pasar
		try {
			Thread.sleep(40000); // Simulacion de extraccion de sangre
		} catch (InterruptedException e) {
		}
		System.out.println("El " + Thread.currentThread().getName() + " termino de hacerle los estudios al paciente");
		this.semExtraccion.release();// Le dice al paciente que ya terminaron que puede retirarse
	}
	
	//DESAYUNO
	public void desayunar() {
		System.out.println("El " + Thread.currentThread().getName() +" desayuna");
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e) {}
		System.out.println("El " + Thread.currentThread().getName() +" termino de comer se levanta y se va del centro");
	}
}
