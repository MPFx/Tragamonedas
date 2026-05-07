package service;

import model.Jugador;
import model.Tragamonedas;

import java.util.List;

/**
 * Clase de servicio que gestiona las estadisticas del juego.
 * Permite mostrar estadisticas del jugador y generar reportes.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Jugador
 */
public class EstadisticasService {
    
    /**
     * Muestra las estadisticas del jugador.
     * 
     * @param jugador Jugador a analizar
     */
    public void mostrarEstadisticas(Jugador jugador) {
        System.out.println("\n=== ESTADISTICAS DEL JUGADOR ===");
        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Creditos actuales: $" + jugador.getCreditos());
        System.out.println("Total apostado: $" + jugador.getTotalApostado());
        System.out.println("Total ganado: $" + jugador.getTotalGanado());
        System.out.println("Balance neto: $" + jugador.getBalance());
        
        List<Double> historial = jugador.getHistorialGiros();
        if (!historial.isEmpty()) {
            double mayorGanancia = historial.stream().max(Double::compare).orElse(0.0);
            double mayorPerdida = historial.stream().min(Double::compare).orElse(0.0);
            double promedio = historial.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            
            long ganados = historial.stream().filter(v -> v > 0).count();
            long perdidos = historial.stream().filter(v -> v < 0).count();
            long empatados = historial.stream().filter(v -> v == 0).count();
            int totalGiros = historial.size();
            
            System.out.println("\n--- ESTADISTICAS DE GIROS ---");
            System.out.println("Total de giros: " + totalGiros);
            System.out.println("Giros ganados: " + ganados + " (" + (ganados * 100.0 / totalGiros) + "%)");
            System.out.println("Giros perdidos: " + perdidos + " (" + (perdidos * 100.0 / totalGiros) + "%)");
            System.out.println("Giros empatados: " + empatados);
            System.out.println("Mayor ganancia: $" + mayorGanancia);
            System.out.println("Mayor perdida: $" + Math.abs(mayorPerdida));
            System.out.println("Promedio por giro: $" + String.format("%.2f", promedio));
        }
        
        if (jugador.getBalance() > 0) {
            System.out.println("\n✅ ¡Vas ganando! Sigue asi.");
        } else if (jugador.getBalance() < 0) {
            System.out.println("\n⚠️ Vas perdiendo. Juega con responsabilidad.");
        } else {
            System.out.println("\n📊 Estas empatado.");
        }
    }
    
    /**
     * Genera un reporte completo del juego.
     * 
     * @param jugador Jugador
     * @param maquina Maquina utilizada
     * @return Reporte formateado
     */
    public String generarReporte(Jugador jugador, Tragamonedas maquina) {
        StringBuilder reporte = new StringBuilder();
        
        reporte.append("\n").append("=".repeat(50)).append("\n");
        reporte.append("REPORTE DE JUEGO\n");
        reporte.append("=".repeat(50)).append("\n");
        reporte.append("Jugador: ").append(jugador.getNombre()).append("\n");
        reporte.append("Maquina: ").append(maquina != null ? maquina.getNombre() : "Ninguna").append("\n");
        reporte.append("Tipo: ").append(maquina != null ? maquina.getTipoMaquina() : "N/A").append("\n");
        reporte.append("-".repeat(50)).append("\n");
        reporte.append("Creditos actuales: $").append(jugador.getCreditos()).append("\n");
        reporte.append("Total apostado: $").append(jugador.getTotalApostado()).append("\n");
        reporte.append("Total ganado: $").append(jugador.getTotalGanado()).append("\n");
        reporte.append("Balance neto: $").append(jugador.getBalance()).append("\n");
        reporte.append("-".repeat(50)).append("\n");
        reporte.append("Total giros: ").append(jugador.getHistorialGiros().size()).append("\n");
        reporte.append("=".repeat(50)).append("\n");
        
        return reporte.toString();
    }
    
}//fin de la clase