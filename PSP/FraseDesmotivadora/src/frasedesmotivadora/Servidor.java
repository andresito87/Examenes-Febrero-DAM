package frasedesmotivadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author andres
 */
public class Servidor extends Thread {

    private final Socket skCliente;

    public Servidor(Socket skCliente) {
        this.skCliente = skCliente;
    }

    public static void main(String[] args) {
        try {
            final int PUERTO = 55555;
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            while (true) {
                Socket skCliente = skServidor.accept();
                new Servidor(skCliente).start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {

        String[] frasesPositivas = {"Vamos que esto está 'chupao'",
            "Vas genial, no le hagas caso al Profe",
            "Seguro que la próxima vez lo haces mejor",
            "No necesitas ChatGPT, tu puedes con esto y más",
            "PSP es tan sólo una piedrecita más en el camino"};

        String[] frasesNegativas = {"Esto es fácil ¿verdad?, solo necesitas práctica y un milagro para aprobar",
            "A quien vas a engañar, copy/paste no es programar",
            "Esto es muy complicado para tí, mejor dedicate a cazar gamusinos",
            "Procura no romper el PC, con tanto enrededo de hilos",
            "¿Estás seguro de lo que estás haciendo? Porque parece que programas como pollo sin cabeza"};

        System.out.println("Cliente conectado");

        DataInputStream flujoEntrada;
        DataOutputStream flujoSalida;

        try {

            flujoEntrada = new DataInputStream(skCliente.getInputStream());
            flujoSalida = new DataOutputStream(skCliente.getOutputStream());

            String nombreCliente = flujoEntrada.readUTF();
            System.out.println(nombreCliente + " conectado");

            String tipoFrase = flujoEntrada.readUTF();

            int numAleatorio = (int) (Math.random() * 5);
            String fraseADevolver;

            if (tipoFrase.equals("positiva")) {
                fraseADevolver = frasesPositivas[numAleatorio];
            } else {
                fraseADevolver = frasesNegativas[numAleatorio];
            }

            flujoSalida.writeUTF(fraseADevolver);

            skCliente.close();
            flujoEntrada.close();
            flujoSalida.close();

            System.out.println(nombreCliente + " desconectado");

        } catch (SocketException ex) {
            System.out.println("Error Socket " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error IO " + ex.getMessage());
        }

    }
}
