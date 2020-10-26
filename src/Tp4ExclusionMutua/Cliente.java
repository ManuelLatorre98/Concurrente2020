package Tp4ExclusionMutua;

public class Cliente implements Runnable {
	private Taxi taxi;

	public Cliente(Taxi unTaxi) {
		this.taxi = unTaxi;
	}

	public void run() {
			this.caminar();
			taxi.tomarTaxi();
	}
	
	private void caminar() {
		try {
			System.out.println("Cliente caminando");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}

}
