package TPOFinal;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HallCentral {
    private Lock accesoPuesto;
    private Condition[] arrayCondPuestos;
    private int [] lugaresLibres;
    private int pasajerosEnEspera=0;
    private int capMaxPuestoAten;
    public HallCentral(int cantPuestosAten, int capPuestosAten){
        capMaxPuestoAten = capPuestosAten;
        accesoPuesto = new ReentrantLock(true);
        arrayCondPuestos= new Condition[cantPuestosAten];
        lugaresLibres = new int [cantPuestosAten];
        for (int i = 0; i < arrayCondPuestos.length; i++) {
            arrayCondPuestos[i] = accesoPuesto.newCondition();
        };

        for (int i = 0; i < lugaresLibres.length; i++) {
            lugaresLibres[i]=capPuestosAten;
        }
    }

    public void entrarHallCentral(int nroPuesto){

        try{
            accesoPuesto.lock();
            pasajerosEnEspera++;
            this.notifyAll();//Le dice a los guardias que entro al hall
            while(lugaresLibres[nroPuesto-1] == 0){
                arrayCondPuestos[nroPuesto-1].await(); //Si no hay lugar en su puesto espera
            }

            lugaresLibres[nroPuesto-1]--; //Resta un lugar
            pasajerosEnEspera--;
            accesoPuesto.unlock();
         }catch(InterruptedException e){}
    }

    public synchronized void llamarPasajero(int nroPuestoAsignado){
        try{
            while(lugaresLibres[nroPuestoAsignado-1] == capMaxPuestoAten || pasajerosEnEspera==0){
                System.out.println("No hay lugar en el puesto "+nroPuestoAsignado+" el guardia espera");
                this.wait();
            }
            System.out.println("El guardia llama a un pasajero para que pase al puesto de atencion "+nroPuestoAsignado);
            arrayCondPuestos[nroPuestoAsignado-1].signal(); //Desbloquea a un pasajero del hall

        }catch(InterruptedException e){}
    }

    public void salirDelHall(int nroPuesto){
        accesoPuesto.lock();
        lugaresLibres[nroPuesto-1]++;
        this.notifyAll();
        accesoPuesto.unlock();
    }
}
