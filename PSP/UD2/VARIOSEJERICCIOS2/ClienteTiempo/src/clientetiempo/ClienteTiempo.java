
package clientetiempo;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class ClienteTiempo {

   
    public static void main(String[] args) throws IOException {

        String host = "localhost";
        int puerto = 6000;
        Socket socketCliente;
        String[] palabras = {"Tiempo","Ciudad","Coche","TIME","guardia","perro"};
        String palabraAlAzar = "TIME";
        socketCliente = new Socket(host,puerto);
        OutputStream salida = socketCliente.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream()));
        bfw.write("TIME");
        /*do {
            palabraAlAzar = palabras[(int)(Math.random()*palabras.length)];
            System.out.println(palabraAlAzar);
            //flujoSalida.writeUTF(palabraAlAzar);
             System.out.println("Enviando palabra al azar : " + palabraAlAzar);
        } while(!palabraAlAzar.equals("TIME"));*/
        
        System.out.println("Palabra TIME enviada al servidor");
        
    }
    
}
