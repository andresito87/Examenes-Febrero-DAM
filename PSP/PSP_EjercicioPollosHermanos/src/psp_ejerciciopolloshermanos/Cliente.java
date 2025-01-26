package psp_ejerciciopolloshermanos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Cliente implements Runnable {

    private final int PUERTO = 55555;
    private final String URL = "localhost";
    private String nombreCliente;

    public Cliente() {
    }

    @Override
    public void run() {
        Socket skCliente = null;
        BufferedReader flujoEntrada = null;
        PrintWriter flujoSalida = null;

        try {
            skCliente = new Socket(URL, PUERTO);
            flujoEntrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            flujoSalida = new PrintWriter(skCliente.getOutputStream(), true);

            // Recogo el numero del cliente
            int numeroCliente = Integer.parseInt(flujoEntrada.readLine());
            this.nombreCliente = "Cliente " + numeroCliente;

            // Genero la cantidad de pollos, de patatas y las envio
            int cantidadPollos = (int) (Math.random() * 3) + 1;
            int cantidadPatatas = (int) (Math.random() * 5);
            System.out.println(this.nombreCliente
                    + " solicita encargo de: "
                    + cantidadPollos + " pollos con "
                    + cantidadPatatas + " patatas.");
            flujoSalida.println(cantidadPollos);
            flujoSalida.println(cantidadPatatas);

            // Recibo la contestacion del servidor
            String resultado = flujoEntrada.readLine();

            if (resultado.contains("Pedido procesado")) {
                flujoSalida.println("adios");
            }

        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (skCliente != null) {
                    skCliente.close();
                }
                if (flujoEntrada != null) {
                    flujoEntrada.close();
                }
                if (flujoSalida != null) {
                    flujoSalida.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
