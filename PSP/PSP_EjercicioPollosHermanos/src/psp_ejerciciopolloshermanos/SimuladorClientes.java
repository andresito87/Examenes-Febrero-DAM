package psp_ejerciciopolloshermanos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class SimuladorClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantidadClientes = 0;

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            do {
                opcion = 0;
                System.out.println("1.- Simular peticiones clientes");
                System.out.println("2.- Terminar programa");
                System.out.print("Seleccione una opción: ");
                try {
                    opcion = sc.nextInt();
                    if (opcion == 1) {
                        System.out.print("¿Cuántos clientes deseas simular? ");
                        cantidadClientes = sc.nextInt();
                        List<Thread> listaClientes = new ArrayList<>();
                        int indiceCliente = 1;

                        for (int i = 0; i < cantidadClientes; i++) {
                            Thread cliente = new Thread(new Cliente());
                            listaClientes.add(cliente);
                            cliente.start();
                            indiceCliente++;
                        }

                        for (Thread cliente : listaClientes) {
                            try {
                                cliente.join();
                            } catch (InterruptedException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }

                        System.out.println("Todos los clientes han acabado");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } while (opcion != 2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
