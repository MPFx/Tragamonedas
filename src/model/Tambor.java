package model;

import java.util.List;
import java.util.Random;

/**
 * Clase que representa un tambor de la máquina tragamonedas.
 * Cada tambor contiene una lista de simbolos y puede girar aleatoriamente.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Simbolo
 * @see Traganomedas
 */
public class Tambor {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del tambor. */
    private int idTambor;
    
    /** Lista de simbolos disponibles en el tambor. */
    private List<Simbolo> simbolos;
    
    /** Simbolo actual mostrado en el tambor. */
    private Simbolo simboloActual;
    
    /** Generador de numeros aleatorios. */
    private Random random;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un tambor.
     * 
     * @param simbolos Lista de simbolos del tambor
     */
    public Tambor(List<Simbolo> simbolos) {
        this.idTambor = contadorIds++;
        this.simbolos = simbolos;
        this.random = new Random();
        this.simboloActual = simbolos.get(0);
    }
    
    /**
     * Gira el tambor y selecciona un simbolo aleatorio.
     * 
     * @return Simbolo seleccionado
     */
    public Simbolo girar() {
        int index = random.nextInt(simbolos.size());
        this.simboloActual = simbolos.get(index);
        return simboloActual;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del tambor */
    public int getIdTambor() {
        return idTambor;
    }
    
    /** @return Simbolo actual */
    public Simbolo getSimboloActual() {
        return simboloActual;
    }
    
    /**
     * Devuelve una representacion textual del tambor.
     * 
     * @return Simbolo actual como String
     */
    @Override
    public String toString() {
        return simboloActual != null ? simboloActual.getNombre() : "?";
    }
    
}//fin de la clase