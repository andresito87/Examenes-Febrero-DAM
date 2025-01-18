package frasedesmotivadora_buffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author andres
 */
public class Cliente extends Thread {

    private final int PUERTO = 55555;
    private final String URL = "localhost";
    private String nombreCliente;

    public Cliente(String nombre) {
        this.nombreCliente = nombre;
    }

    @Override
    public void run() {

        try {

            Socket skCliente = new Socket(URL, PUERTO);
            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            PrintWriter flujoSalida = new PrintWriter(skCliente.getOutputStream(), true);

            flujoSalida.println(this.nombreCliente);

            int numeroAleatorio = (int) (Math.random() * 2) + 1;

            String tipoFrase;

            if (numeroAleatorio == 1) {
                tipoFrase = "positiva";
            } else {
                tipoFrase = "negativa";
            }

            flujoSalida.println(tipoFrase);

            String fraseRecibida = flujoEntrada.readLine();

            flujoSalida.println("fin");

            System.out.println(this.nombreCliente + " ha recibido la frase: " + fraseRecibida);
            
            skCliente.close();
            flujoEntrada.close();
            flujoSalida.close();

        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
