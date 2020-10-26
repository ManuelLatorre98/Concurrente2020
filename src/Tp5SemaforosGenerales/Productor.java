package Tp5SemaforosGenerales;

public class Productor implements Runnable{
	private Buffer buffer;
	private BufferLimit bufferLimit;
	public Productor(Buffer buff, BufferLimit buffLim) {
		this.buffer=buff;
		this.bufferLimit= buffLim;
	}
	
	public void run() {
		while(true) {
			//this.buffer.producir(); //Sin limite
			this.bufferLimit.producir(); //Con limite
			System.out.println("El productor genera un producto");
		}
	}
}
