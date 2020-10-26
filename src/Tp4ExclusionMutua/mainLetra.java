package Tp4ExclusionMutua;
public class mainLetra {
	public static void main(String[] args) {
		Turno adminTurno=new Turno();
		int repeticiones=3;
		Letra a= new Letra('A',1,adminTurno,repeticiones);
		Letra b=new Letra('B',2,adminTurno,repeticiones);
		Letra c=new Letra('C',3,adminTurno,repeticiones);
		
		Thread threadA=new Thread(a);
		Thread threadB=new Thread(b);
		Thread threadC=new Thread(c);
		threadA.start();
		threadB.start();
		threadC.start();
	}
}
