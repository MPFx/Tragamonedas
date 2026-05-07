package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa una máquina tragamonedas progresiva.
 * Tiene un bote que aumenta con cada jugada y puede ofrecer premios mayores.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Tragamonedas
 */
public class TragamonedasTematica extends Tragamonedas {
    
    // ==================== ATRIBUTOS ====================
    private String tema;
    /** Bote progresivo acumulado. */
    private double boteProgresivo;
    
    /** Porcentaje de la apuesta que va al bote. */
    private double incrementoPorcentaje;
    
    /** Lista de simbolos. */
    private List<Simbolo> simbolos;
    
    /**
     * Constructor para crear una máquina progresiva.
     * 
     * @param nombre Nombre de la máquina
     */
    public TragamonedasTematica(String nombre, String tema) {
        super(nombre, 5, 200, 20000);
        this.tema = tema;
        inicializarSimbolos();
        inicializarTambores();
    }
    
    /**
     * Inicializa los simbolos.
     */
    @Override
    public void inicializarSimbolos() {
        simbolos = new ArrayList<>();
        simbolos.add(new Simbolo("Jackpot", 1000));
        simbolos.add(new Simbolo("Diamante", 500));
        simbolos.add(new Simbolo("Oro", 200));
        simbolos.add(new Simbolo("Plata", 100));
        simbolos.add(new Simbolo("Bronce", 50));
    }
    
    /**
     * Obtiene los simbolos disponibles.
     * 
     * @return Lista de simbolos
     */
    @Override
    protected List<Simbolo> getSimbolosDisponibles() {
        return simbolos;
    }
    
    /**
     * Gira la máquina y actualiza el bote progresivo.
     * 
     * @param apuesta Monto apostado
     * @return Premio obtenido
     */
    @Override
    public double girar(double apuesta) {
        // Actualizar bote progresivo
        double incremento = apuesta * incrementoPorcentaje;
        boteProgresivo += incremento;
        
        System.out.println("💰 Bote progresivo: $" + boteProgresivo);
        
        // Realizar giro normal
        double premio = super.girar(apuesta);
        
        // Verificar si se gano el bote
        List<Simbolo> resultados = new ArrayList<>();
        for (Tambor t : tambores) {
            resultados.add(t.getSimboloActual());
        }
        
        boolean todosJackpot = true;
        for (Simbolo s : resultados) {
            if (!s.getNombre().equals("Jackpot")) {
                todosJackpot = false;
                break;
            }
        }
        
        if (todosJackpot) {
            System.out.println("🎉🎉🎉 ¡JACKPOT! Ganaste el bote de $" + boteProgresivo + " 🎉🎉🎉");
            double jackpot = boteProgresivo;
            reiniciarBote();
            return premio + jackpot;
        }
        
        return premio;
    }
    
    /**
     * Obtiene el multiplicador especial.
     * 
     * @param simbolos Lista de simbolos
     * @return Multiplicador especial
     */
    @Override
    public double getMultiplicadorEspecial(List<Simbolo> simbolos) {
        // Contar simbolos iguales
        Map<String, Long> conteo = new HashMap<>();
        for (Simbolo s : simbolos) {
            conteo.put(s.getNombre(), conteo.getOrDefault(s.getNombre(), 0L) + 1);
        }
        
        for (Map.Entry<String, Long> entry : conteo.entrySet()) {
            if (entry.getValue() >= 4) {
                String nombre = entry.getKey();
                if (nombre.equals("Diamante")) return 50.0;
                if (nombre.equals("Oro")) return 30.0;
                return 10.0;
            }
        }
        
        return 0;
    }
    
    /**
     * Obtiene el tipo de máquina.
     * 
     * @return "Progresiva"
     */
    @Override
    public String getTipoMaquina() {
        return "Progresiva";
    }
    
    /**
     * Obtiene el bote progresivo actual.
     * 
     * @return Bote progresivo
     */
    public double getBoteProgresivo() {
        return boteProgresivo;
    }
    
    /**
     * Reinicia el bote progresivo a su valor base.
     */
    public void reiniciarBote() {
        this.boteProgresivo = 50000;
        System.out.println("Bote reiniciado a $" + boteProgresivo);
    }

    @Override
    public String getNombreCombinacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}//fin de la clase