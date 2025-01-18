package frasedesmotivadora_buffer;

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
public class Servidor extends Thread {

    private static final int PUERTO = 55555;

    private final Socket skCliente;

    public Servidor(Socket skCliente) {
        this.skCliente = skCliente;
    }

    public static void main(String[] args) {
        try {

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

        try {

            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(skCliente.getInputStream()));
            PrintWriter flujoSalida = new PrintWriter(skCliente.getOutputStream(), true);

            String nombreCliente = "";
            String linea;
            while ((linea = flujoEntrada.readLine()) != null && !linea.equalsIgnoreCase("fin")) {

                nombreCliente = linea;
                System.out.println(nombreCliente + " conectado");

                String tipoFrase = flujoEntrada.readLine();
                int numAleatorio = (int) (Math.random() * 5);
                String fraseADevolver;

                if (tipoFrase.equals("positiva")) {
                    fraseADevolver = frasesPositivas[numAleatorio];
                } else {
                    fraseADevolver = frasesNegativas[numAleatorio];
                }

                flujoSalida.println(fraseADevolver);
            }

            skCliente.close();
            flujoEntrada.close();
            flujoSalida.close();

            System.out.println(nombreCliente + " Desconectado");

        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
