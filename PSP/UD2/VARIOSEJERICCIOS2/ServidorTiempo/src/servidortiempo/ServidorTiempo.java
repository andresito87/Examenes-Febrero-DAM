
package servidortiempo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Roi Iglesias Costas
 */
public class ServidorTiempo {

    
    public static void main(String[] args) throws IOException {
        
        int puerto = 6000;
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        
        
        DataInputStream flujoEntrada = null;
        
        OutputStream salida = null;
        DataOutputStream flujoSalida = null;
        
        socketServidor = new ServerSocket(puerto);
        
        System.out.println("Esperando conexión del cliente...");
         socketCliente = socketServidor.accept();
        
        System.out.println("Conexión iniciada");
        
        //entrada = socketCliente.getInputStream();
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        
        String mensajeCliente;
        do {
        
           mensajeCliente = entrada.readLine();
           
           System.out.println("Iniciando proceso TIME...");
            
            if (mensajeCliente.equals("TIME")) {
                
                ProcessBuilder nuevoProceso = new ProcessBuilder("CMD","C","TIME");
               nuevoProceso.start();
               System.out.println("Iniciando proceso TIME...");
               
            }
            
        } while (mensajeCliente != "TIME");
        
        salida = socketCliente.getOutputStream();
        flujoSalida = new DataOutputStream(salida);
        
        
        
        //mensajeCliente = flujoEntrada.readUTF();
        /*while(!mensajeCliente.equals("TIME")){
            System.out.println("Comando no reconocido. La palabra que has enviado es: " + mensajeCliente);
            mensajeCliente = flujoEntrada.readUTF();
        }*/
        
        /*System.out.println("Comando recibido: ");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH:mm:ss");
        Date fecha = new Date();
        System.out.println("Hora actual" + formatoFecha.format(fecha));
        
        flujoEntrada.close();
        flujoSalida.close();
        socketCliente.close();
        socketServidor.close();*/
        
        
        
        
        
    }
    
}
