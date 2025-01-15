
package tema3cliente2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Tema3Cliente2 {

    
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6000;
        String mensajeServidor = "";
        
        Socket socketCliente = new Socket(host,puerto);
        
        DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
        DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
        
        
        
        flujoSalida.writeUTF("Hola soy el cliente 2");
        
        mensajeServidor = flujoEntrada.readUTF();
        
        System.out.println("el servidor me ha dicho : " +  mensajeServidor);
        
    }
    
}
