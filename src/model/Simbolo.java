package model;

/**
 * Clase que representa un simbolo en la máquina tragamonedas.
 * Cada simbolo tiene un nombre, un valor y una imagen asociada.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Tambor
 */
public class Simbolo {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del simbolo. */
    private int idSimbolo;
    
    /** Nombre del simbolo (Cereza, Siete, Campana, etc.). */
    private String nombre;
    
    /** Valor del simbolo para calcular premios. */
    private double valor;
    
    /** Representacion grafica del simbolo. */
    private String imagen;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un simbolo.
     * 
     * @param nombre Nombre del simbolo
     * @param valor Valor del simbolo
     */
    public Simbolo(String nombre, double valor) {
        this.idSimbolo = contadorIds++;
        this.nombre = nombre;
        this.valor = valor;
        this.imagen = "";
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del simbolo */
    public int getIdSimbolo() {
        return idSimbolo;
    }
    
    /** @return Nombre del simbolo */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Valor del simbolo */
    public double getValor() {
        return valor;
    }
    
    /** @return Imagen del simbolo */
    public String getImagen() {
        return imagen;
    }
    
    // ==================== SETTERS ====================
    
    /** @param imagen Nueva imagen del simbolo */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    /**
     * Devuelve una representacion textual del simbolo.
     * 
     * @return Nombre del simbolo
     */
    @Override
    public String toString() {
        return nombre;
    }
    
}//fin de la clase