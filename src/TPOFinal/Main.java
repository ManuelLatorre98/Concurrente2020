package TPOFinal;


public class Main {
	public static void main(String[] args) {
		int cantTerminales=3;
		int cantPuestosEmbarque=7;//Todas las terminales tienen la misma cantidad para simplificar creacion
		int cantPasajeros=48;
		Reloj reloj=new Reloj("05:00");
		PuestoEmbarque []puestosEmbarque=new PuestoEmbarque[cantPuestosEmbarque*cantTerminales];
		Terminal[]terminales=new Terminal[cantTerminales];
		PuestoEmbarque[]puestosTerminal=null;

		//inicializa los puestos de embarque (los de todas las terminales)
		for (int i = 0; i < puestosEmbarque.length; i++) {
			puestosEmbarque[i]=new PuestoEmbarque(i+1,reloj);
		}

		//Crea las terminales
		for (int i = 0; i < cantTerminales; i++) {
			int indicePuesto= cantPuestosEmbarque*i;//Terminal 1 su primer puesto es 0, terminal 2 el primer puesto el 7 etc.
			puestosTerminal=new PuestoEmbarque[cantPuestosEmbarque]; //array de puestos de embarque nuevo
			for (int j = 0; j < cantPuestosEmbarque; j++) { //Asigna los puestos antes creados a cada terminal
				puestosTerminal[j]=puestosEmbarque[(j+indicePuesto)];
			}
			
			terminales[i]=new Terminal(Character.toChars(65+i)[0],puestosTerminal,reloj);//Uso ascii para obtener la letra de la terminal, supongo que puede haber terminales hasta la Z
			//En pos 0 de terminales esta la terminal con letra A
			//Lo hice con letras para no confundirlo con los puertos de embarque
		}

		Aeropuerto aeropuerto=new Aeropuerto("06:00","22:00",reloj,terminales);
		
		//Hora
		GestorHora gestorHora= new GestorHora(reloj,aeropuerto,terminales,puestosEmbarque);
		Thread threadGestorHora=new Thread(gestorHora,"GestorHora");
		
		//Pasajeros
		Pasajero[]arrayPasajeros=new Pasajero[cantPasajeros];
		Thread [] threadPasajeros= new Thread[arrayPasajeros.length];
		
		//CentroInformes
		CentroInformes centroInformes= new CentroInformes(aeropuerto);
		Thread threadCentroInf= new Thread(centroInformes);
		
		//Empleado puesto atencion
		EmpleadoPuestoAtencion []arrayEmpPuestosAten=new EmpleadoPuestoAtencion[aeropuerto.getListaPuestosAtencion().length];
		Thread[]threadEmpPuestosAten=new Thread[cantTerminales];
		
		//Guardia
		Guardia []arrayGuardias=new Guardia[cantTerminales];
		Thread[]threadGuardias= new Thread[cantTerminales];
		
		//Tren
		Tren tren= new Tren(aeropuerto);
		Thread threadTren=new Thread(tren,"Tren");
		
		//instanciacion pasajeros
		for (int i = 0; i < arrayPasajeros.length; i++) {
			arrayPasajeros[i]=new Pasajero(aeropuerto,reloj);
		}
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i]=new Thread(arrayPasajeros[i],"Pasajero"+(i+1));
		}
		
		//instanciacion empleados de puestos de atencion
		for (int i = 0; i < arrayEmpPuestosAten.length; i++) {
			arrayEmpPuestosAten[i]=new EmpleadoPuestoAtencion(aeropuerto.getListaPuestosAtencion()[i]);
		}
		for (int i = 0; i < threadEmpPuestosAten.length; i++) {
			threadEmpPuestosAten[i]=new Thread(arrayEmpPuestosAten[i],"EmpleadoAten"+(i+1));
		}
		
		//instanciacion de guardias
		for (int i = 0; i < arrayGuardias.length; i++) {
			arrayGuardias[i]=new Guardia(aeropuerto.getHallCentral(),i+1);
		}
		for (int i = 0; i < threadGuardias.length; i++) {
			threadGuardias[i]=new Thread(arrayGuardias[i],"Guardia"+(i+1));
		}
		
		
		
		//Inicio de threads
		threadGestorHora.start();
		threadCentroInf.start();
		threadTren.start();
		for (int i = 0; i < threadEmpPuestosAten.length; i++) {
			threadEmpPuestosAten[i].start();
		}
		
		for (int i = 0; i < threadPasajeros.length; i++) {
			threadPasajeros[i].start();
		}
		
		for (int i = 0; i < threadGuardias.length; i++) {
			threadGuardias[i].start();
		}
	}
		
}
