package service;

import model.Jugador;

import java.util.Scanner;
import model.Tragamonedas;
import model.TragamonedasClasica;
import model.TragamonedasProgresiva;
import model.TragamonedasTematica;

/**
 * Clase de servicio que gestiona la logica del juego de tragamonedas.
 * Permite seleccionar maquina, girar, apostar y gestionar creditos.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see Traganomedas
 * @see Jugador
 */
public class JuegoService {
    
    // ==================== ATRIBUTOS ====================
    
    private Tragamonedas maquinaActual;
    private Jugador jugador;
    private EstadisticasService estadisticasService;
    
    /**
     * Constructor del servicio de juego.
     * 
     * @param jugador Jugador que juega
     */
    public JuegoService(Jugador jugador) {
        this.jugador = jugador;
        this.estadisticasService = new EstadisticasService();
    }
    
    /**
     * Selecciona una máquina tragamonedas.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void seleccionarMaquina(Scanner scanner) {
        System.out.println("\n=== SELECCIONAR MAQUINA ===");
        System.out.println("1. Clasica (3 tambores, simbolos tradicionales)");
        System.out.println("2. Tematica (5 tambores, temas especiales)");
        System.out.println("3. Progresiva (5 tambores, bote progresivo)");
        System.out.print("Seleccione una maquina: ");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion) {
            case 1 -> maquinaActual = new TragamonedasClasica("Jackpot Clasico");
            case 2 -> {
                System.out.print("Tema (Piratas/Egipto): ");
                String tema = scanner.nextLine();
                maquinaActual = new TragamonedasTematica("Aventura " + tema, tema);
            }
            case 3 -> maquinaActual = new TragamonedasProgresiva("Super Jackpot");
            default -> {
                System.out.println("Opcion invalida, seleccionando Clasica");
                maquinaActual = new TragamonedasClasica("Jackpot Clasico");
            }
        }
        
        System.out.println("\n✅ Maquina seleccionada: " + maquinaActual);
        System.out.println("Apuesta minima: $" + maquinaActual.getApuestaMinima());
        System.out.println("Apuesta maxima: $" + maquinaActual.getApuestaMaxima());
    }
    
    /**
     * Realiza una jugada.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void jugar(Scanner scanner) {
        if (maquinaActual == null) {
            System.out.println("Primero seleccione una maquina");
            return;
        }
        
        System.out.println("\n=== JUGAR ===");
        System.out.println("Creditos disponibles: $" + jugador.getCreditos());
        System.out.print("Monto a apostar (min $" + maquinaActual.getApuestaMinima() + 
                        " max $" + maquinaActual.getApuestaMaxima() + "): $");
        
        double apuesta;
        try {
            apuesta = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto invalido");
            return;
        }
        
        if (!jugador.apostar(apuesta)) {
            System.out.println("Creditos insuficientes");
            return;
        }
        
        System.out.println("Apostaste: $" + apuesta);
        System.out.println("Creditos restantes: $" + jugador.getCreditos());
        
        double premio = maquinaActual.girar(apuesta);
        
        if (premio > 0) {
            jugador.ganar(premio);
            System.out.println("✨ Ganaste: $" + premio + " ✨");
        } else {
            jugador.perder(apuesta);
        }
        
        System.out.println("Creditos actuales: $" + jugador.getCreditos());
    }
    
    /**
     * Recarga creditos al jugador.
     * 
     * @param scanner Scanner para entrada de datos
     */
    public void recargarCreditos(Scanner scanner) {
        System.out.println("\n=== RECARGAR CREDITOS ===");
        System.out.println("Creditos actuales: $" + jugador.getCreditos());
        System.out.print("Monto a recargar: $");
        
        double monto;
        try {
            monto = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto invalido");
            return;
        }
        
        jugador.ganar(monto); // Reutilizamos ganar para agregar creditos
        System.out.println("✅ Creditos recargados! Nuevo saldo: $" + jugador.getCreditos());
    }
    
    /**
     * Muestra las estadisticas del jugador.
     */
    public void mostrarEstadisticas() {
        estadisticasService.mostrarEstadisticas(jugador);
    }
    
    /**
     * Muestra la informacion de la maquina actual.
     */
    public void mostrarInfoMaquina() {
        if (maquinaActual == null) {
            System.out.println("No hay maquina seleccionada");
            return;
        }
        
        System.out.println("\n=== INFORMACION DE LA MAQUINA ===");
        System.out.println("Nombre: " + maquinaActual.getNombre());
        System.out.println("Tipo: " + maquinaActual.getTipoMaquina());
        System.out.println("Carretes: " + maquinaActual.getCarretes());
        System.out.println("Apuesta minima: $" + maquinaActual.getApuestaMinima());
        System.out.println("Apuesta maxima: $" + maquinaActual.getApuestaMaxima());
        
        if (maquinaActual instanceof TragamonedasProgresiva) {
            TragamonedasProgresiva prog = (TragamonedasProgresiva) maquinaActual;
            System.out.println("Bote progresivo: $" + prog.getBoteProgresivo());
        }
    }
    
    /**
     * Verifica si el jugador tiene creditos.
     * 
     * @return true si tiene creditos
     */
    public boolean tieneCreditos() {
        return jugador.getCreditos() > 0;
    }
    
}//fin de la clase