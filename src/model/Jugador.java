package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un jugador de tragamonedas.
 * Gestiona creditos, apuestas y estadisticas de juego.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Traganomedas
 */
public class Jugador {
    
    // ==================== ATRIBUTOS ====================
    
    /** Identificador unico del jugador. */
    private int idJugador;
    
    /** Nombre del jugador. */
    private String nombre;
    
    /** Creditos disponibles del jugador. */
    private double creditos;
    
    /** Historial de resultados de giros. */
    private List<Double> historialGiros;
    
    /** Total apostado. */
    private double totalApostado;
    
    /** Total ganado. */
    private double totalGanado;
    
    /** Contador estatico para generar IDs. */
    private static int contadorIds = 1;
    
    /**
     * Constructor para crear un jugador.
     * 
     * @param nombre Nombre del jugador
     * @param creditosIniciales Creditos iniciales
     */
    public Jugador(String nombre, double creditosIniciales) {
        this.idJugador = contadorIds++;
        this.nombre = nombre;
        this.creditos = creditosIniciales;
        this.historialGiros = new ArrayList<>();
        this.totalApostado = 0;
        this.totalGanado = 0;
    }
    
    /**
     * Realiza una apuesta.
     * 
     * @param monto Monto a apostar
     * @return true si tiene suficientes creditos
     */
    public boolean apostar(double monto) {
        if (creditos >= monto) {
            creditos -= monto;
            totalApostado += monto;
            return true;
        }
        return false;
    }
    
    /**
     * Registra una ganancia.
     * 
     * @param monto Monto ganado
     */
    public void ganar(double monto) {
        creditos += monto;
        totalGanado += monto;
        historialGiros.add(monto);
    }
    
    /**
     * Registra una perdida.
     */
    public void perder(double apuesta) {
        historialGiros.add(-apuesta);
    }
    
    /**
     * Obtiene el balance neto (ganado - apostado).
     * 
     * @return Balance neto
     */
    public double getBalance() {
        return totalGanado - totalApostado;
    }
    
    // ==================== GETTERS ====================
    
    /** @return Identificador del jugador */
    public int getIdJugador() {
        return idJugador;
    }
    
    /** @return Nombre del jugador */
    public String getNombre() {
        return nombre;
    }
    
    /** @return Creditos actuales */
    public double getCreditos() {
        return creditos;
    }
    
    /** @return Historial de giros */
    public List<Double> getHistorialGiros() {
        return historialGiros;
    }
    
    /** @return Total apostado */
    public double getTotalApostado() {
        return totalApostado;
    }
    
    /** @return Total ganado */
    public double getTotalGanado() {
        return totalGanado;
    }
    
    /**
     * Devuelve una representacion textual del jugador.
     * 
     * @return Cadena con informacion
     */
    @Override
    public String toString() {
        return nombre + " - Creditos: $" + creditos + " | Balance: $" + getBalance();
    }
    
}//fin de la clase