package TPOFinal;


public class Main {
	public static void main(String[] args) {
		int cantTerminales=3;
		int cantPuestosEmbarque=7;//Por terminal todas las terminales tienen la misma cantidad para simplificar creacion
		Reloj reloj=new Reloj("05:00");
		PuestoEmbarque []puestosEmbarque=new PuestoEmbarque[cantPuestosEmbarque*cantTerminales];
		Terminal[]terminales=new Terminal[cantTerminales];
		PuestoEmbarque[]puestosTerminal=null;
		
		for (int i = 0; i < puestosEmbarque.length; i++) {
			puestosEmbarque[i]=new PuestoEmbarque(i+1,reloj);
		}
		
		for (int i = 0; i < cantTerminales; i++) {
			puestosTerminal=new PuestoEmbarque[cantPuestosEmbarque];
			for (int j = 0; j < cantPuestosEmbarque; j++) {
				puestosTerminal[j]=puestosEmbarque[(((j)+1)+(cantPuestosEmbarque*(i)))-1];
				
			}
			
			terminales[i]=new Terminal(Character.toChars(65+i)[0],puestosTerminal,reloj);//Uso ascii para obtener la letra de la terminal, supongo que puede haber temrinales hasta la Z
			//En pos 0 de terminales esta la terminal con letra A
			//Lo hice con letras para no confundirlo con los puertos de embarque
			
			
			}
		
		
		//System.out.println("TERMINAL: "+terminales[0].getLetraTerminal()+ "   "+terminales[0].getPuestoEmbarque(1).getNroPuesto());
		Aeropuerto aeropuerto=new Aeropuerto("06:00","22:00",reloj,terminales);
		
	
		
		//Hora
		GestorHora gestorHora= new GestorHora(reloj,aeropuerto,terminales);
		Thread threadGestorHora=new Thread(gestorHora,"GestorHora");
		
		//Pasajeros
		Pasajero[]arrayPasajeros=new Pasajero[48];
		Thread [] threadPasajeros= new Thread[48];
		
		//CentroInformes
		CentroInformes centroInformes= new CentroInformes(aeropuerto);
		Thread threadCentroInf= new Thread(centroInformes);
		
		//Empleado puesto atencion
		EmpleadoPuestoAtencion []arrayEmpPuestosAten=new EmpleadoPuestoAtencion[cantTerminales];
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
			arrayGuardias[i]=new Guardia(aeropuerto.getListaPuestosAtencion()[i]);
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
