package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase abstracta que representa una máquina tragamonedas.
 * Contiene los atributos y comportamientos comunes a todos los tipos de máquinas
 * (Clasica, Tematica, Progresiva). Implementa la interfaz CombinacionGanadora.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see TragamonedasClasica
 * @see TragamonedasTematica
 * @see TragamonedasProgresiva
 * @see CombinacionGanadora
 */
public abstract class Tragamonedas implements CombinacionGanadora {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico de la máquina. */
    protected int idMaquina;
    
    /** Nombre de la máquina. */
    protected String nombre;
    
    /** Numero de carretes (tambores). */
    protected int carretes;
    
    /** Lista de tambores. */
    protected List<Tambor> tambores;
    
    /** Apuesta minima permitida. */
    protected double apuestaMinima;
    
    /** Apuesta maxima permitida. */
    protected double apuestaMaxima;
    
    /** Mapa de multiplicadores por combinacion. */
    protected Map<String, Double> multiplicadores;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear una máquina tragamonedas.
     * 
     * @param nombre Nombre de la máquina
     * @param carretes Numero de carretes
     * @param apuestaMinima Apuesta minima
     * @param apuestaMaxima Apuesta maxima
     */
    public Tragamonedas(String nombre, int carretes, double apuestaMinima, double apuestaMaxima) {
        this.idMaquina = contadorIds++;
        this.nombre = nombre;
        this.carretes = carretes;
        this.apuestaMinima = apuestaMinima;
        this.apuestaMaxima = apuestaMaxima;
        this.tambores = new ArrayList<>();
        this.multiplicadores = new HashMap<>();
        inicializarMultiplicadores();
    }
    
    /**
     * Inicializa los multiplicadores basicos.
     */
    protected void inicializarMultiplicadores() {
        multiplicadores.put("TRES_SIETES", 100.0);
        multiplicadores.put("TRES_CAMPANAS", 50.0);
        multiplicadores.put("TRES_CEREZAS", 20.0);
        multiplicadores.put("DOS_SIETES", 10.0);
        multiplicadores.put("DOS_CAMPANAS", 5.0);
        multiplicadores.put("DOS_CEREZAS", 2.0);
    }
    
    /**
     * Inicializa los simbolos de la máquina.
     * Metodo abstracto implementado por las subclases.
     */
    public abstract void inicializarSimbolos();
    
    /**
     * Inicializa los tambores con los simbolos.
     */
    public void inicializarTambores() {
        List<Simbolo> simbolosDisponibles = getSimbolosDisponibles();
        for (int i = 0; i < carretes; i++) {
            tambores.add(new Tambor(simbolosDisponibles));
        }
    }
    
    /**
     * Obtiene los simbolos disponibles.
     * 
     * @return Lista de simbolos
     */
    protected abstract List<Simbolo> getSimbolosDisponibles();
    
    /**
     * Gira la máquina y calcula el premio.
     * 
     * @param apuesta Monto apostado
     * @return Premio obtenido (0 si perdio)
     */
    public double girar(double apuesta) {
        if (apuesta < apuestaMinima || apuesta > apuestaMaxima) {
            System.out.println("Apuesta fuera de rango. Min: $" + apuestaMinima + " Max: $" + apuestaMaxima);
            return 0;
        }
        
        // Girar tambores
        List<Simbolo> resultados = new ArrayList<>();
        System.out.print("🎰 Girando... ");
        for (Tambor t : tambores) {
            Simbolo s = t.girar();
            resultados.add(s);
            System.out.print("[" + s.getNombre() + "] ");
        }
        System.out.println();
        
        // Verificar combinacion ganadora
        double multiplicador = verificarCombinacion(resultados);
        
        if (multiplicador > 0) {
            double premio = apuesta * multiplicador;
            System.out.println("✨ ¡" + getNombreCombinacion(resultados) + "! Ganaste $" + premio + " ✨");
            return premio;
        } else {
            System.out.println("😞 Perdiste. ¡Sigue intentando!");
            return 0;
        }
    }
    
    /**
     * Verifica la combinacion ganadora.
     * 
     * @param simbolos Lista de simbolos obtenidos
     * @return Multiplicador del premio
     */
    @Override
    public double verificarCombinacion(List<Simbolo> simbolos) {
        // Verificar si todos los simbolos son iguales
        boolean todosIguales = simbolos.stream().distinct().count() == 1;
        if (todosIguales) {
            String nombre = simbolos.get(0).getNombre();
            if (nombre.equals("Siete")) return 100.0;
            if (nombre.equals("Campana")) return 50.0;
            if (nombre.equals("Cereza")) return 20.0;
            return 10.0;
        }
        
        // Verificar pares
        Map<String, Long> conteo = new HashMap<>();
        for (Simbolo s : simbolos) {
            conteo.put(s.getNombre(), conteo.getOrDefault(s.getNombre(), 0L) + 1);
        }
        
        for (Map.Entry<String, Long> entry : conteo.entrySet()) {
            if (entry.getValue() >= 2) {
                String nombre = entry.getKey();
                if (nombre.equals("Siete")) return 10.0;
                if (nombre.equals("Campana")) return 5.0;
                if (nombre.equals("Cereza")) return 2.0;
                return 1.0;
            }
        }
        
        // Verificar multiplicador especial de la subclase
        return getMultiplicadorEspecial(simbolos);
    }
    
    /**
     * Obtiene el nombre de la combinacion ganadora.
     * 
     * @param simbolos Lista de simbolos
     * @return Nombre de la combinacion
     */
    public String getNombreCombinacion(List<Simbolo> simbolos) {
        boolean todosIguales = simbolos.stream().distinct().count() == 1;
        if (todosIguales) {
            return "¡" + simbolos.get(0).getNombre() + " x " + carretes + "!";
        }
        
        Map<String, Long> conteo = new HashMap<>();
        for (Simbolo s : simbolos) {
            conteo.put(s.getNombre(), conteo.getOrDefault(s.getNombre(), 0L) + 1);
        }
        
        for (Map.Entry<String, Long> entry : conteo.entrySet()) {
            if (entry.getValue() >= 2) {
                return "Par de " + entry.getKey() + "s";
            }
        }
        
        return "Sin suerte";
    }
    
    /**
     * Obtiene el multiplicador especial de la subclase.
     * 
     * @param simbolos Lista de simbolos
     * @return Multiplicador especial
     */
    public abstract double getMultiplicadorEspecial(List<Simbolo> simbolos);
    
    /**
     * Obtiene el tipo de máquina.
     * 
     * @return Tipo de máquina
     */
    public abstract String getTipoMaquina();
    
    // ==================== GETTERS ====================
    
    /** @return Identificador de la máquina */
    public int getIdMaquina() {
        return idMaquina;
    }
    
    /** @return Nombre de la máquina */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Numero de carretes */
    public int getCarretes() {
        return carretes;
    }
    
    /** @return Apuesta minima */
    public double getApuestaMinima() {
        return apuestaMinima;
    }
    
    /** @return Apuesta maxima */
    public double getApuestaMaxima() {
        return apuestaMaxima;
    }
    
    /**
     * Devuelve una representacion textual de la máquina.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return nombre + " (" + getTipoMaquina() + ") - " + carretes + " carretes";
    }
    
}//fin de la clase