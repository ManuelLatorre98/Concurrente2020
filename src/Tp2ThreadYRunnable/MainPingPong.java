package Tp2ThreadYRunnable;

public class MainPingPong {
	public static void main(String[] args) {
		DatoPingPong cuenta= new DatoPingPong();
		PingPong t1= new PingPong("PING",(int)(Math.random()*2300),cuenta);
		PingPong t2= new PingPong("PONG",(int)(Math.random()*2000),cuenta);
		
		t1.start();
		t2.start();
		try {
		t1.join();
		t2.join();
		}catch(InterruptedException e) {
		}
		
		System.out.println(Thread.currentThread()+"Ta luego");
		System.out.println("dato"+ cuenta.obtenerValor());
	}
	
}
