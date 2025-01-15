package tema3clienteuno;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Tema3ClienteUno {

    public static void main(String[] args) throws IOException {
        int numIntentos = 20;
        int contadorIntentos = 0;
        String mensajeServidor = "";

        int puerto = 6000;
        String Host = "localhost";
        

        System.out.println("Soy el cliente y voy a empezar a probar números");

        while (contadorIntentos <= numIntentos && !(mensajeServidor.contains("OK"))) {
            Socket socketCliente = new Socket(Host, puerto);
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            
            System.out.println("Probando el número " + contadorIntentos );
            flujoSalida.writeUTF("" + contadorIntentos);
            mensajeServidor = flujoEntrada.readUTF();
            
            System.out.println("El servidor me ha dicho..." + mensajeServidor);
            contadorIntentos++;
        }
    }

}
