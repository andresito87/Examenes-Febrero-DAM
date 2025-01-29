package ejerciciofloristeria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Main {

    private static final JardinAutomatico jardin = new JardinAutomatico();
    private static final Florista florista1 = new Florista("Florista 1",jardin);
    private static final Florista florista2 = new Florista("Florista 2",jardin);
    private static final Florista florista3 = new Florista("Florista 3",jardin);
    private static Florista florista4 = null;
    private static Florista florista5 = null;
    private static final Scanner scanner = new Scanner(System.in);
    private static int contadorClientes = 1;
    private static final List<Thread> listaClientes = new ArrayList();
    private static int posicionUltimoHiloLanzado = 0;
    private static final List<Thread> listaFloristasHilos = new ArrayList();
    private static final List<Florista> listaFloristas = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Floristería con Servicio Automatizado iniciando... ");
        activarFloristas();

        int opcion;
        do {
            System.out.println("----- Menú opciones ----");
            System.out.println("1.- Llegan un grupo de clientes(Deberá ingresar cuantas rosas,claveles y margaritas quiere cada uno)");
            System.out.println("2.- Ver estado de la floristería");
            System.out.println("3.- Realizar mantenimiento a la floristería");
            System.out.println("4.- Cerrar floristería");

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("¿Cuántos clientes acaban de llegar?");
                    int cantidadClientes = scanner.nextInt();
                    lanzarClientes(cantidadClientes);
                    break;
                case 2:
                    verEstado();
                    break;
                case 3:
                    realizarMantenimiento();
                    break;
                case 4:
                    cerrarFloristeria();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 4);

    }

    private static void lanzarClientes(int cantidad) {

        for (int i = 0; i < cantidad; i++) {
            System.out.println("¿Cuántas rosas quiere el cliente " + contadorClientes + "?");
            int cantidadRosas = scanner.nextInt();
            Cliente cliente = new Cliente(cantidadRosas, 0, 0,listaFloristas);

            Thread clienteHilo = new Thread(cliente);

            listaClientes.add(clienteHilo);
            contadorClientes++;
        }

        for (int i = posicionUltimoHiloLanzado; i < listaClientes.size(); i++) {
            listaClientes.get(i).start();
        }
        posicionUltimoHiloLanzado = listaClientes.size();
    }

    private static void verEstado() {
        jardin.verEstado();

    }

    private static void realizarMantenimiento() {
        if (listaFloristasHilos.size() == 3) {
            florista4 = new Florista("Florista 4",jardin);
            Thread floristaHilo4 = new Thread(florista4);
            listaFloristasHilos.add(floristaHilo4);
            floristaHilo4.start();
            listaFloristas.add(florista4);
        } else if (listaFloristasHilos.size() == 4) {
            florista5 = new Florista("Florista 5",jardin);
            Thread floristaHilo5 = new Thread(florista5);
            listaFloristasHilos.add(floristaHilo5);
            floristaHilo5.start();
            listaFloristas.add(florista5);
        }
    }

    private static void cerrarFloristeria() {

        System.out.println("Cerrando floristeria...");

        try {
            for (Thread florista : listaFloristasHilos) {
                florista.join();
            }

            for (Thread cliente : listaClientes) {
                cliente.join();
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void activarFloristas() {
        Thread floristaHilo1 = new Thread(florista1);
        Thread floristaHilo2 = new Thread(florista2);
        Thread floristaHilo3 = new Thread(florista3);

        listaFloristasHilos.add(floristaHilo1);
        listaFloristasHilos.add(floristaHilo2);
        listaFloristasHilos.add(floristaHilo3);
        
        listaFloristas.add(florista1);
        listaFloristas.add(florista2);
        listaFloristas.add(florista3);

        for (Thread florista : listaFloristasHilos) {
            florista.start();
        }

    }

}
