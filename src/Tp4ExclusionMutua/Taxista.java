package Tp4ExclusionMutua;

public class Taxista implements Runnable {
	private Taxi taxi;

	public Taxista(Taxi unTaxi) {
		this.taxi = unTaxi;
	}

	public void run() {
		while(true) {
			taxi.trabajar();
		}
	}
}
