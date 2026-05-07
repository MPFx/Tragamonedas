package model;

/**
 * Clase que representa un premio en la máquina tragamonedas.
 * Asocia una combinacion ganadora con un multiplicador.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see CombinacionGanadora
 */
public class Premio {
    
    // ==================== ATRIBUTOS ====================
    
    /** Nombre de la combinacion ganadora. */
    private String combinacion;
    
    /** Multiplicador del premio. */
    private double multiplicador;
    
    /**
     * Constructor para crear un premio.
     * 
     * @param combinacion Nombre de la combinacion
     * @param multiplicador Multiplicador del premio
     */
    public Premio(String combinacion, double multiplicador) {
        this.combinacion = combinacion;
        this.multiplicador = multiplicador;
    }
    
    /**
     * Calcula el premio basado en la apuesta.
     * 
     * @param apuesta Monto apostado
     * @return Monto del premio
     */
    public double calcularPremio(double apuesta) {
        return apuesta * multiplicador;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Nombre de la combinacion */
    public String getCombinacion() {
        return combinacion;
    }
    
    /** @return Multiplicador */
    public double getMultiplicador() {
        return multiplicador;
    }
    
    /**
     * Devuelve una representacion textual del premio.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return combinacion + " x" + multiplicador;
    }
    
}//fin de la clase