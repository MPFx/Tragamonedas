package principal;

import ui.MenuConsola;

/**
 * Clase principal que contiene el punto de entrada del Simulador de Maquina Traganomedas.
 * Inicia la aplicacion y muestra el menu de consola al usuario.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see MenuConsola
 */
public class Main {
    
    /**
     * Metodo principal que inicia el simulador.
     * Muestra mensaje de bienvenida y crea una instancia del menu de consola.
     * 
     * @param args Argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   BIENVENIDO AL SIMULADOR DE");
        System.out.println("     MAQUINA TRAGANOMEDAS");
        System.out.println("========================================");
        System.out.println("Elige tu maquina, apuesta y gira");
        System.out.println("para ganar grandes premios!");
        System.out.println("========================================\n");
        
        MenuConsola menu = new MenuConsola();
        menu.iniciar();
    }
    
}//fin de la clase