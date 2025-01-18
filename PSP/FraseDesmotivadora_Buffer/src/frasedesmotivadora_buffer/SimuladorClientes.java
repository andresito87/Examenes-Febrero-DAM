package frasedesmotivadora_buffer;

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
            boolean salir = false;
            do {
                System.out.print("Introduce la cantidad de clientes: ");
                try {
                    cantidadClientes = sc.nextInt();
                    if (cantidadClientes > 0) {
                        salir = true;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } while (!salir);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        List<Cliente> listaClientes = new ArrayList<>();
        int indiceCliente = 1;

        for (int i = 0; i < cantidadClientes; i++) {
            Cliente cliente = new Cliente("Cliente" + indiceCliente);
            listaClientes.add(cliente);
            cliente.start();
            indiceCliente++;
        }

        for (Cliente cliente : listaClientes) {
            try {
                cliente.join();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.println("Todos los clientes han acabado");
    }

}
