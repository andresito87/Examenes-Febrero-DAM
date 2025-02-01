package psp_ejerciciooperacionesconcurrentes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class SimuladorClientes {

    private static final Scanner scanner = new Scanner(System.in);
    private static int contadorClientes=1;
    private static List<Thread> hilosClientes;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("----- MENÚ OPERACIONES CONCURRENTES -----");
            System.out.println("1.- Modo manual - Introduce una operación(solo sumas y restas). Ej:2+5.");
            System.out.println("2.- Modo automático - Introduce el número de clientes a simular.");
            System.out.println("3.- Cerrar el programa.");
            System.out.print("Escoge una de las opciones: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer de entrada
            switch (opcion) {
                case 1:
                    activarModoManual(scanner);
                    break;
                case 2:
                    activarModoAutomatico();
                    break;
                case 3:
                    cerrarPrograma();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    public static void activarModoManual(Scanner scanner) {
        System.out.print("Cliente " + (contadorClientes++) + " introduce una operación: ");

        String operacion;

        // Asegurar que el scanner tenga una línea disponible
        if (scanner.hasNextLine()) {
            operacion = scanner.nextLine().trim();
        } else {
            System.out.println("Error: No se pudo leer la operación.");
            return;
        }

        Thread clienteManual = new Thread(new Cliente("Cliente " + contadorClientes, "manual", operacion));
        clienteManual.start();

        try {
            clienteManual.join();
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void activarModoAutomatico() {
        System.out.print("¿Cuántos clientes quieres lanzar? ");
        int numClientes = scanner.nextInt();
        hilosClientes = new ArrayList();

        for (int i = 0; i < numClientes; i++) {
            hilosClientes.add(new Thread(new Cliente("Cliente " + contadorClientes, "auto", null)));
        }

        for (Thread hiloCliente : hilosClientes) {
            hiloCliente.start();
        }

        for (Thread hiloCliente : hilosClientes) {
            try {
                hiloCliente.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SimuladorClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void cerrarPrograma() {
        System.out.println("Cerrando el programa...");
        if (hilosClientes != null) {
            for (Thread hilo : hilosClientes) {
                try {
                    hilo.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimuladorClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
