package LatorreFAI1931Parcial1;

public class Paciente implements Runnable{
	private Centro centro;
	private Boolean certificadoDonador=false;
	public Paciente(Centro unCentro) {
		this.centro=unCentro;
	}
	
	public void run() {
		this.centro.llamar();
		this.centro.esperaAtencionCtrl();
		this.certificadoDonador= this.centro.hacerseExtraccion(); //Indica que tiene su certificado de extraccion
		this.centro.desayunar();
	}
}
