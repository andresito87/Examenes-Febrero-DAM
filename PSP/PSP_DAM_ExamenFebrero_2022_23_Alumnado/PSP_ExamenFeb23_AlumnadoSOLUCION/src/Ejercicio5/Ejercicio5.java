package Ejercicio5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angela Rodriguez Portela
 */
public class Ejercicio5 {

    public static void main(String[] arg) {
        Scanner sc;
        String Host = "localhost";
        int numeroPuerto = 1234;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;
        String respuesta = "";
        Socket cliente = null;

        System.out.println("----------------------------------------\n"
                + "\n"
                + "CLIENTE PARA LA TRADUCCION DE NUMEROS\n"
                + "\n"
                + "-------------------------------------");

        try {
            cliente = new Socket(Host, numeroPuerto);

            //CREO FLUJO DE SALIDA AL SERVIDOR
            flujoSalida = new DataOutputStream(cliente.getOutputStream());

            //CREO FLUJO DE ENTRADA DEL CLIENTE
            flujoEntrada = new DataInputStream(cliente.getInputStream());

            System.out.println("Cliente de la alumna ANGELA RODRIGUEZ PORTELA conectado con localhost e iniciado en el puerto 1234");

        } catch (IOException ex) {
            System.out.println("Error: No se ha podido establecer la conexion con el servidor");
        }
        if (cliente != null && cliente.isConnected()) {
            try {

                System.out.print("Dame un numero positivo:");
                sc = new Scanner(System.in);
                //Enviamos un mensaje
                flujoSalida.writeUTF(sc.nextLine());
                flujoSalida.flush();
                //EL SERVIDOR ME ENVIA UN MENSAJE
                respuesta = flujoEntrada.readUTF();
                System.out.println(respuesta);

            } catch (IOException ex) {
                Logger.getLogger(Ejercicio5.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //CERRAR STREAMS Y SOCKETS
                flujoEntrada.close();
                flujoSalida.close();
                cliente.close();
            } catch (IOException ex) {
                Logger.getLogger(Ejercicio5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
