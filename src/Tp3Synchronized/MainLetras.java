package Tp3Synchronized;

public class MainLetras {
	public static void main(String[] args) {
		Turno turno=new Turno();
		Letra letraA=new Letra('A',turno);
		Letra letraB=new Letra('B',turno);
		Letra letraC=new Letra('C',turno);
		
		Thread threadA=new Thread(letraA);
		Thread threadB=new Thread(letraB);
		Thread threadC=new Thread(letraC);
		
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
