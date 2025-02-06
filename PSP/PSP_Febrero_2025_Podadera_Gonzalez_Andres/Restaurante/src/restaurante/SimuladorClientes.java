package restaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlumnoFPD Andres Samuel Podadera Gonzalez
 */
public class SimuladorClientes {
    
    private static List<Thread> listaClientes;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1.- Simular peticiones clientes");
            System.out.println("2.- Terminar programa");
            System.out.print("Seleccione una opción:");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("¿Cuantos clientes desea simular?: ");
                    int cantidadClientes = scanner.nextInt();
                    simularClientes(cantidadClientes);
                    break;
                case 2:
                    System.out.println("Programa terminado");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 2);

    }

    public static void simularClientes(int cantidadClientes) {
        listaClientes= new ArrayList();
        for (int i = 0; i < cantidadClientes; i++) {
            Cliente cliente = new Cliente("Cliente " + i + 1);
            Thread hiloCliente= new Thread(cliente);
            hiloCliente.start();
            listaClientes.add(hiloCliente);
        }
        
        for (Thread hiloCliente : listaClientes) {
            try {
                hiloCliente.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SimuladorClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
