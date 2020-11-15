package LocksCondicion;
import java.util.concurrent.locks.*;
public class Mesa {
	private Lock lock;
	private Condition esperaTenedor;
	private boolean [] tenedores=new boolean[5];
	
	public Mesa() {
		lock= new ReentrantLock(true);
		for (int i = 0; i < tenedores.length; i++) {
			tenedores[i]=true;//True disponible, False ocupado
		}
	}
	
	public void tomarTenedores(int izq, int der) {
		while()
	}
}
