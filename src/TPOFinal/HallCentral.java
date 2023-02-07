package TPOFinal;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HallCentral {
    private Lock accesoPuesto;
    private Condition[] arrayCondPasajeros;

    private Condition condGuardias;
    private int [] lugaresLibres;
    private int []pasajerosEnEspera;
    private int capMaxPuestoAten;
    public HallCentral(int cantPuestosAten, int capPuestosAten){
        capMaxPuestoAten = capPuestosAten;
        accesoPuesto = new ReentrantLock(true);
        arrayCondPasajeros= new Condition[cantPuestosAten];
        condGuardias= accesoPuesto.newCondition();
        lugaresLibres = new int [cantPuestosAten];
        pasajerosEnEspera = new int[cantPuestosAten];
        for (int i = 0; i < cantPuestosAten; i++) {
            arrayCondPasajeros[i] = accesoPuesto.newCondition();
            lugaresLibres[i]=capPuestosAten;
            pasajerosEnEspera[i]=0;
        };

    }

    public void entrarHallCentral(int nroPuesto){
        try{
            accesoPuesto.lock();
            pasajerosEnEspera[nroPuesto-1]++;
            condGuardias.signalAll();//Le dice a los guardias que entro al hall
            while(lugaresLibres[nroPuesto-1] == 0){
                arrayCondPasajeros[nroPuesto-1].await(); //Si no hay lugar en su puesto espera
            }
            lugaresLibres[nroPuesto-1]--; //Resta un lugar en el puesto
            pasajerosEnEspera[nroPuesto-1]--;//Hay un pasajero menos esperando
            accesoPuesto.unlock();
         }catch(InterruptedException e){}
    }

    public void llamarPasajero(int nroPuestoAsignado){
        accesoPuesto.lock();
         try {
             while (lugaresLibres[nroPuestoAsignado - 1] == 0 || pasajerosEnEspera[nroPuestoAsignado-1] == 0) {
                 if (pasajerosEnEspera[nroPuestoAsignado-1] == 0) {
                     //System.out.println("No hay pasajeros en el hall el guardia del puesto " + nroPuestoAsignado + " duerme");
                 } else{
                     //System.out.println("No hay lugar en el puesto " + nroPuestoAsignado + " el guardia duerme");
                 }
                 condGuardias.await();
             }

             //System.out.println("El guardia llama a un pasajero para que pase al puesto de atencion " + nroPuestoAsignado);
             arrayCondPasajeros[nroPuestoAsignado-1].signal(); //Desbloquea a un pasajero del hall
             accesoPuesto.unlock();
         } catch (InterruptedException e) {}
    }

    public void salirDelHall(int nroPuesto){
        accesoPuesto.lock();
        lugaresLibres[nroPuesto-1]++;
        condGuardias.signalAll();
        accesoPuesto.unlock();
    }
}
