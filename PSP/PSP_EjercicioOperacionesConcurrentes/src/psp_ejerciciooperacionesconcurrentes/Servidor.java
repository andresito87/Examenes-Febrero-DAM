package psp_ejerciciooperacionesconcurrentes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andres
 */
public class Servidor implements Runnable {

    private static final int PUERTO = 6000;
    private static int contadorClientes = 1;

    private final int numCliente;
    private final Socket socket;

    public static void main(String[] args) {
        try {

            ServerSocket ss = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO + "...");

            while (true) {
                Socket socket = ss.accept();
                System.out.println("Cliente " + contadorClientes + " conectado...");
                Thread hiloServidor = new Thread(new Servidor(socket, contadorClientes++));
                hiloServidor.start();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Servidor(Socket socket, int numCliente) {
        this.socket = socket;
        this.numCliente = numCliente;
    }

    @Override
    public void run() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(this.numCliente);
            String operacion = br.readLine();
            String resultado = this.calcularResultado(operacion);
            pw.println(resultado);

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String calcularResultado(String operacion) {
        String resultado;
        
        if (operacion.contains("+") || operacion.contains("-")) {
            int indiceOperacion = operacion.indexOf("+");

            if (indiceOperacion == -1) {
                indiceOperacion = operacion.indexOf("-"); // Buscar el operador de resta si no se encontró "+"
            }

            try {
                // Extraer los dos operandos correctamente
                int primerOperador = Integer.parseInt(operacion.substring(0, indiceOperacion));
                int segundoOperador = Integer.parseInt(operacion.substring(indiceOperacion + 1)); // Ignorar el operador

                // Verificar qué operación se realizó
                if (operacion.charAt(indiceOperacion) == '+') {
                    resultado = String.valueOf(primerOperador + segundoOperador);
                } else {
                    resultado = String.valueOf(primerOperador - segundoOperador);
                }
            } catch (NumberFormatException ex) {
                resultado = "Operación matemática inválida";
            }
        } else {
            resultado = "Operación no soportada";
        }

        return resultado;
    }
}
