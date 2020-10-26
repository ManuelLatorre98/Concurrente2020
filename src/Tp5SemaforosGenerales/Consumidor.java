package Tp5SemaforosGenerales;

public class Consumidor implements Runnable{
	private Buffer buffer;
	private BufferLimit bufferLimit;
	public Consumidor(Buffer buff, BufferLimit buffLim) {
		this.buffer=buff;
		this.bufferLimit= buffLim;
	}
	
	public void run() {
		try {
			//Thread.sleep((long)((Math.random()*4+1)*1000));
			Thread.sleep(5000);
		}catch(InterruptedException e) {}
		System.out.println("El consumidor entra a tomar un producto");
		//buffer.consumir(); //Con limite
		this.bufferLimit.consumir(); //Sin limite
		System.out.println("El consumidor toma un producto");
	}
	
}
