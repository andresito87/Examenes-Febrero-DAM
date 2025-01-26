package psp_ejerciciopolloshermanos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author andres
 */
public class Servidor implements Runnable {

    private static final int PUERTO = 55555;
    private static int contadorClientes = 0;
    private static int cantidadPollosDiaria = 100;
    private static int cantidadPatatasDiaria = 100;
    private static Object candado;
    private final int numeroCliente;
    private final Socket skCliente;

    public Servidor(Socket skCliente, int numero) {
        contadorClientes++;
        this.skCliente = skCliente;
        this.numeroCliente = numero;
    }

    public static void main(String[] args) {
        try {

            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            while (true) {
                Socket skCliente = skServidor.accept();
                new Thread(new Servidor(skCliente, contadorClientes)).start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        BufferedReader flujoEntrada = null;
        PrintWriter flujoSalida = null;
        try {
            flujoEntrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            flujoSalida = new PrintWriter(skCliente.getOutputStream(), true);

            // Envio el numero del cliente
            flujoSalida.println(this.numeroCliente);

            String nombreCliente = "Cliente " + this.numeroCliente;
            String linea;
            while ((linea = flujoEntrada.readLine()) != null && !linea.equalsIgnoreCase("adios")) {

                //Recojo la cantidad de pollos que quiere el cliente
                int cantidadPollosSolicitados = Integer.parseInt(linea);

                //Recojo la cantidad de pollos que quiere el cliente
                int cantidadPatatasSolicitadas = Integer.parseInt(flujoEntrada.readLine());

                //Realizo pedido de pollos y patatas
                this.procesarPedido(nombreCliente, cantidadPollosSolicitados, cantidadPatatasSolicitadas);

                // Envio respuesta al servidor
                flujoSalida.println("Pedido procesado");

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
                System.out.println(ex.getMessage());
            }

        }
    }

    private void procesarPedido(String nombreCliente, int cantidadPollosSolicitados, int cantidadPatatasSolicitadas) {

        synchronized (System.out) {
            if (Servidor.cantidadPollosDiaria > 0) {
                System.out.println("Encargo " + nombreCliente
                        + ": "
                        + cantidadPollosSolicitados + " pollos y "
                        + cantidadPatatasSolicitadas + " patatas.");

                if (Servidor.cantidadPollosDiaria >= cantidadPollosSolicitados) {
                    Servidor.cantidadPollosDiaria -= cantidadPollosSolicitados;
                }

                if (Servidor.cantidadPatatasDiaria >= cantidadPatatasSolicitadas) {
                    Servidor.cantidadPatatasDiaria -= cantidadPatatasSolicitadas;
                } else {
                    Servidor.cantidadPatatasDiaria = 0;
                }
                System.out.println("Tras el encargo nos quedan "
                        + Servidor.cantidadPollosDiaria + " pollos y "
                        + Servidor.cantidadPatatasDiaria + " patatas.");
            } else {
                System.out.println("Encargo " + nombreCliente
                        + ": "
                        + cantidadPollosSolicitados + " pollos y "
                        + cantidadPatatasSolicitadas + " patatas.");
                System.out.println("No quedan pollos, pedido rechazado");
            }
            System.out.flush();
        }
    }
}
