package frasedesmotivadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
            DataInputStream flujoEntrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(skCliente.getOutputStream());

            flujoSalida.writeUTF(this.nombreCliente);

            int numeroAleatorio = (int) (Math.random() * 2) + 1;
            String tipoFrase;

            if (numeroAleatorio == 1) {
                tipoFrase = "positiva";
            } else {
                tipoFrase = "negativa";
            }

            flujoSalida.writeUTF(tipoFrase);

            String fraseRecibida = flujoEntrada.readUTF();

            System.out.println(this.nombreCliente + " recibe la frase: " + fraseRecibida);

            Thread.sleep(1000);
            skCliente.close();
            flujoEntrada.close();
            flujoSalida.close();

        } catch (SocketException ex) {
            System.out.println("Error Socket: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error IO: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("Error de Interrupcion" + ex.getMessage());
        }
    }

}
