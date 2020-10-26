package Tp5SemaforosGenerales;

public class mainProductConsum {
	public static void main(String[] args) {
		Buffer buffer= new Buffer();
		BufferLimit bufferLimit= new BufferLimit();
		Productor productor= new Productor(buffer, bufferLimit);
		Consumidor [] consumidores= new Consumidor[5];
		Thread threadProdcut= new Thread(productor);
		Thread [] threadConsum= new Thread[5];
		for (int i = 0; i < consumidores.length; i++) {
			consumidores[i]= new Consumidor(buffer, bufferLimit);
		}
		for (int i = 0; i < threadConsum.length; i++) {
			threadConsum[i]= new Thread(consumidores[i]);
		}
		
		
		threadProdcut.start();
		for (int i = 0; i < threadConsum.length; i++) {
			threadConsum[i].start();
		}
	}
}
