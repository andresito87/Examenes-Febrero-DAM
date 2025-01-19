package psp_ejerciciopanaderia;

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

    public static Mostrador mostrador = new Mostrador();
    public static Panadero panadero = new Panadero(mostrador);
    public static List<Cliente> clientes = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        abrirPanaderia();

        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\u001B[36mElige una de las siguientes opciones: ");
            System.out.println("\u001B[36m1.- Llegada grupo clientes(indicar cuantas barras desea cada uno).");
            System.out.println("\u001B[36m2.- Ver estado del mostrador");
            System.out.println("\u001B[36m3.- Cerrar la panadería\u001B[0m");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1 -> {
                        int numClientes = 0;

                        while (numClientes <= 0) {
                            try {
                                System.out.print("Dime la cantidad de clientes que llegan juntos: ");
                                numClientes = sc.nextInt();
                                sc.nextLine();
                            } catch (Exception ex) {
                            }
                        }
                        for (int i = 0; i < numClientes; i++) {
                            try {
                                System.out.print("¿Cuantas barras quiere el cliente " + (i + 1) + "(1-10) ?: ");
                                int cantidadBarras = sc.nextInt();
                                if (cantidadBarras < 0 || cantidadBarras > 10) {
                                    throw new Exception();
                                }
                                lanzarCliente("Cliente " + i, cantidadBarras);
                            } catch (Exception ex) {
                                i--;
                                sc.nextLine();
                            }
                        }
                        sc.nextLine();
                    }
                    case 2 -> {
                        verEstadoMostrador();
                    }
                    case 3 -> {
                        cerrarPanaderia();
                    }
                    default ->
                        System.out.println("Opción inválida");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            }
        } while (opcion != 3);
        sc.close();
    }

    private static void abrirPanaderia() {
        System.out.println("\u001B[33mPanadería Hilos y Harinas acaba de abrir ...\u001B[0m");
        panadero.start();
    }

    private static void lanzarCliente(String nombre, int cantidadBarras) {
        Cliente cliente = new Cliente(nombre, mostrador, cantidadBarras);
        clientes.add(cliente);
        cliente.start();
    }

    private static void verEstadoMostrador() {
        mostrador.verEstado();
    }

    private static void cerrarPanaderia() {
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        panadero.interrupt();
        mostrador.limpiandoParaCerrar();
        System.out.println("Cerrando panadería ...");
        mostrador.mostrarResumenFinal();
    }

}
