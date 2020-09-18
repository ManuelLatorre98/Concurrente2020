package Tp3Synchronized;


public class mainAutoSurtidor {	
	public static void main(String[]args) {
		Surtidor surtidor= new Surtidor();
		Auto[]arrayAutos=new Auto[5];
		Thread[]threadAutos=new Thread[5];
		for (int i = 0; i < arrayAutos.length; i++) {
			arrayAutos[i]=new Auto("120"+i,"Modelo","Marca",10000,300,surtidor);
			threadAutos[i]= new Thread(arrayAutos[i],("Auto"+i));
		}
		for (int i = 0; i < threadAutos.length; i++) {
			threadAutos[i].start();
		}
	}
}
