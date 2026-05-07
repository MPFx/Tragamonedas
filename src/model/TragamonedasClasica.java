package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una máquina tragamonedas clasica.
 * Tiene 3 tambores con simbolos tradicionales: Cereza, Siete, Campana.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Tragamonedas
 */
public class TragamonedasClasica extends Tragamonedas {
    
    // ==================== ATRIBUTOS ====================
    
    /** Lista de simbolos clasicos. */
    private List<Simbolo> simbolosClasicos;
    
    /**
     * Constructor para crear una máquina clasica.
     * 
     * @param nombre Nombre de la máquina
     */
    public TragamonedasClasica(String nombre) {
        super(nombre, 3, 100, 10000);
        inicializarSimbolos();
        inicializarTambores();
    }
    
    /**
     * Inicializa los simbolos clasicos.
     */
    @Override
    public void inicializarSimbolos() {
        simbolosClasicos = new ArrayList<>();
        simbolosClasicos.add(new Simbolo("Siete", 100));
        simbolosClasicos.add(new Simbolo("Campana", 50));
        simbolosClasicos.add(new Simbolo("Cereza", 20));
        simbolosClasicos.add(new Simbolo("Naranja", 10));
        simbolosClasicos.add(new Simbolo("Limon", 5));
    }
    
    /**
     * Obtiene los simbolos disponibles.
     * 
     * @return Lista de simbolos clasicos
     */
    @Override
    protected List<Simbolo> getSimbolosDisponibles() {
        return simbolosClasicos;
    }
    
    /**
     * Obtiene el multiplicador especial.
     * 
     * @param simbolos Lista de simbolos
     * @return Multiplicador especial
     */
    @Override
    public double getMultiplicadorEspecial(List<Simbolo> simbolos) {
        // Combinacion especial: Siete + Campana + Cereza
        boolean tieneSiete = simbolos.stream().anyMatch(s -> s.getNombre().equals("Siete"));
        boolean tieneCampana = simbolos.stream().anyMatch(s -> s.getNombre().equals("Campana"));
        boolean tieneCereza = simbolos.stream().anyMatch(s -> s.getNombre().equals("Cereza"));
        
        if (tieneSiete && tieneCampana && tieneCereza) {
            return 25.0;
        }
        return 0;
    }
    
    /**
     * Obtiene el tipo de máquina.
     * 
     * @return "Clasica"
     */
    @Override
    public String getTipoMaquina() {
        return "Clasica";
    }

    @Override
    public String getNombreCombinacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}//fin de la clase