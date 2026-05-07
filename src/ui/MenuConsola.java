package ui;

import service.JuegoService;
import model.Jugador;

import java.util.Scanner;

/**
 * Clase que implementa la interfaz de usuario por consola para el Simulador de Traganomedas.
 * Gestiona la interaccion con el usuario, muestra los menus y procesa las opciones.
 * 
 * @author ISC Israel de Jesus Mar Parada
 * @version 1.0
 * @see JuegoService
 * @see Jugador
 */
public class MenuConsola {
    
    // ==================== ATRIBUTOS ====================
    
    private Scanner scanner;
    private JuegoService juegoService;
    private Jugador jugador;
    
    /**
     * Constructor del menu de consola.
     */
    public MenuConsola() {
        this.scanner = new Scanner(System.in);
        inicializarJugador();
        this.juegoService = new JuegoService(jugador);
    }
    
    /**
     * Inicializa el jugador.
     */
    private void inicializarJugador() {
        System.out.println("\n=== BIENVENIDO AL CASINO ===");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Creditos iniciales: $");
        double creditos = Double.parseDouble(scanner.nextLine());
        
        this.jugador = new Jugador(nombre, creditos);
        System.out.println("\n✅ Jugador creado: " + jugador);
    }
    
    /**
     * Inicia el bucle principal del menu.
     */
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1 -> juegoService.seleccionarMaquina(scanner);
                case 2 -> juegoService.jugar(scanner);
                case 3 -> juegoService.recargarCreditos(scanner);
                case 4 -> juegoService.mostrarEstadisticas();
                case 5 -> juegoService.mostrarInfoMaquina();
                case 6 -> {
                    System.out.println("\n🎰 ¡Gracias por jugar en el Casino!");
                    System.out.println("Balance final: $" + jugador.getBalance());
                    System.out.println("¡Hasta pronto!");
                    salir = true;
                }
                default -> System.out.println("Opcion no valida");
            }
            
            if (!salir) {
                pausa();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menu principal del juego.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("     SIMULADOR DE MAQUINA");
        System.out.println("        TRAGANOMEDAS");
        System.out.println("========================================");
        System.out.println("1. Seleccionar maquina");
        System.out.println("2. Jugar (girar)");
        System.out.println("3. Recargar creditos");
        System.out.println("4. Ver estadisticas");
        System.out.println("5. Informacion de la maquina");
        System.out.println("6. Salir");
        System.out.println("========================================");
        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Creditos: $" + jugador.getCreditos());
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }
    
    /**
     * Lee y valida la opcion ingresada por el usuario.
     * 
     * @return Numero entero de la opcion seleccionada, o 0 si no es valida
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Pausa la ejecucion hasta que el usuario presione Enter.
     */
    private void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
    
}//fin de la clase