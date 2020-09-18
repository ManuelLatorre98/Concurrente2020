package Tp3Synchronized;

public class Vehiculo {
    private String patente;
    private String modelo;
    private String marca;
    private int kmFaltantesService;
    
    public Vehiculo(String unaPatente, String unModelo, String unaMarca, int cantKmFaltantesService){
        this.patente=unaPatente;
        this.modelo= unModelo;
        this.marca= unaMarca;
        this.kmFaltantesService= cantKmFaltantesService;
    }
    
    public String getPatente(){
        return this.patente;
    }
    
    public String getModelo(){
        return this.modelo;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public int getKmFaltantesService(){
        return this.kmFaltantesService;
    }
}
