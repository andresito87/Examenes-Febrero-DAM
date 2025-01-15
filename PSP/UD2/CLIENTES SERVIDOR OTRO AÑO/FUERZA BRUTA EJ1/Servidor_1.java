 /*
El servidor genera un numero aleatorio entre 1 y 10000 y atiende la peticion de
1 cliente (solo 1) que le envia 1000 intentos para descrifrar el numero.
Si lo consigue manda un mensaje y cierra la conexion. Si no lo consigue manda 
otro mensaje y espera hasta que se llegue a los 1000 numeros. Si a los 1000 
numeros no se ha conseguido cierra la conexion y el cliente deja de enviar numeros
*/
package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Servidor_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numeroPuerto=6000;
        int clave = generarClave();
        int intento=0;
        boolean descifrada = false;
        int numIntentos=0;
        ServerSocket servidor=null;
        Socket clienteConectado = null;
        InputStream entrada = null;
        OutputStream salida = null;
        DataInputStream flujoEntrada=null;
        DataOutputStream flujoSalida = null;
        String mensaje="";
        int intentosMax = 1000;//numero de intentos máximos de conexiones
        
        try {
            servidor = new ServerSocket(numeroPuerto);

            System.out.println("Esperando al cliente...");

            clienteConectado = servidor.accept();

            //CREO FLUJO DE ENTRADA DEL CLIENTE
            entrada = clienteConectado.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            //CREO FLUJO DE SALIDA AL CLIENTE
            salida = clienteConectado.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
        } catch (IOException ex) {
            Logger.getLogger(Servidor_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Se conecta un cliente que intenta descifrar la clave.");
        System.out.println("Clave: "+clave);
        while(!descifrada){

            try {
                //EL CLIENTE ME ENVÍA UNA PRUEBA DE CLAVE
                intento = Integer.parseInt(flujoEntrada.readUTF());
//                System.out.println("Clave recibida. Valor de la clave: "+intento);

                numIntentos++;
                //ENVÍO LA RESPUESTA ANTE EL INTENTO
                if(clave == intento){
                    flujoSalida.writeUTF("Has acertado la clave en "+numIntentos+" intentos.");
                    descifrada=true;
                }else{
                    flujoSalida.writeUTF("Clave incorrecta. Llevas "+numIntentos+" intentos.");
                }

            } catch (IOException ex) {
                    Logger.getLogger(Servidor_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
            System.out.println("El cliente ha descifrado la clave en "+numIntentos+" intentos.");
        
        
        try {
            //CERRAR STREAMS Y SOCKETS
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            clienteConectado.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int generarClave(){
        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
    }
    
}
