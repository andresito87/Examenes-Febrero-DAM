package pspejercicioheladeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Main {

    private static int contadorClientes = 1;
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Thread> listaClientes = new ArrayList();
    private static final Mostrador mostrador = new Mostrador();
    private static final Heladero heladero = new Heladero(mostrador);
    private static final Thread heladeroHilo = new Thread(new Heladero(mostrador));
    private static int posicionUltimoHiloLanzado = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Heladeria MiraFlores acaba de abrir ...");
        heladeroHilo.start();
        try {
            int opcion;
            do {
                System.out.println("1.-Registrar conjunto de clientes");
                System.out.println("2.-Ver estado del mostrador");
                System.out.println("3.-Cerrar heladería");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        entranClientes();
                        break;
                    case 2:
                        consultarEstadoMostrador();
                        break;
                    case 3:
                        cerrarHeladeria();
                        break;
                    default:
                        System.out.println("Opción inválida. Vuelve a intentarlo.");
                }

            } while (opcion != 3);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void entranClientes() {
        System.out.print("¿Cuantos clientes llegan de golpe a la heladería? ");
        int numeroClientes = scanner.nextInt();

        for (int i = 0; i < numeroClientes; i++) {
            int cantidadBolas;
            do {
                System.out.print("¿Cuantas bolas para el cliente " + contadorClientes + "?(1-5): ");
                cantidadBolas = scanner.nextInt();
            } while (!(cantidadBolas >= 1 && cantidadBolas <= 5));

            Thread cliente = new Thread(new Cliente("Cliente " + contadorClientes, cantidadBolas, mostrador));

            listaClientes.add(cliente);
            contadorClientes++;
        }

        for (int i = posicionUltimoHiloLanzado; i < listaClientes.size(); i++) {
            listaClientes.get(i).start();
        }
        posicionUltimoHiloLanzado = listaClientes.size();

    }

    private static void consultarEstadoMostrador() {
        mostrador.consultarEstado();
    }

    private static void cerrarHeladeria() {
        heladero.cierraHeladeria();
        for (Thread cliente : listaClientes) {
            try {
                cliente.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        try {
            heladeroHilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
