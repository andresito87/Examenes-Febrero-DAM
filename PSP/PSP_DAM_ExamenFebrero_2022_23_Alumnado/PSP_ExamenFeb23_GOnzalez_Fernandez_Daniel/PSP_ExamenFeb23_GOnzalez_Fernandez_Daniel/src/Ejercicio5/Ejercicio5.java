/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ejercicio5 {

    public static void main(String[] arg) throws IOException {

        int puerto = 1234;
        String host = "localhost";

        Socket sCliente = new Socket(host, puerto);

        String cadenaRecibida = null;

        OutputStream salida = null;
        DataOutputStream flujoSalida = null;
        InputStream entrada = null;
        DataInputStream flujoEntrada = null;

        System.out.println("CLIENTE PARA LA TRADUCCION DE NUMEROS.");
        System.out.println("--------------------------------------");
        if (sCliente.isConnected()) {

            System.out.println("Cliente del alumno Daniel GOnzález Fernandez con localhost e iniciado en el puerto 1234");

            System.out.print("Dame un numero positivo: ");
            String cadena;
            Scanner sc = new Scanner(System.in);
            cadena = sc.nextLine();

            salida = sCliente.getOutputStream();
            flujoSalida = new DataOutputStream(salida);

            entrada = sCliente.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            flujoSalida.writeUTF(cadena);
            cadenaRecibida = flujoEntrada.readUTF();
            System.out.println("Recibiendo del servidor:");
            System.out.println(cadenaRecibida);

        }else{
            System.out.println("Error: No se ha podido establecer conexión con el servidor");
        }
        System.out.println("FIN DE EJECICION DEL CLIENTE");
    }
}
