package model;

import java.util.List;

/**
 * Interfaz que define el comportamiento para verificar combinaciones ganadoras
 * en una máquina tragamonedas.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Traganomedas
 */
public interface CombinacionGanadora {
    
    /**
     * Verifica si una combinacion de simbolos es ganadora y calcula el premio.
     * 
     * @param simbolos Lista de simbolos obtenidos en los tambores
     * @return Multiplicador del premio (0 si no es ganadora)
     */
    double verificarCombinacion(List<Simbolo> simbolos);
    
    /**
     * Obtiene el nombre de la combinacion ganadora.
     * 
     * @return Nombre de la combinacion
     */
    String getNombreCombinacion();
    
}//fin de la interfaz