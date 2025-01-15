/*
 * El cliente se debe conectar al servidor y enviar numeros del 1 hasta el 10000
 * para encontrar la clave aleatoria que el servidor ha generado. Lo intenta
 * hasta que el acierte y entonces recibe del servidor un mensaje concreto. En
 * cuando llegue ese mensaje en concreto, entonces el cliente para de enviar numeros
 */
package Ejercicio1;

import java.net.*;
import java.io.*;

/**
 *
 * @author Usuario
 */
public class Cliente_1 {

    static final int puerto = 6000;
    static final String host = "localhost";
    int numero = 1;
    boolean conseguido = false;

    /*
    Constructor del Cliente_1
     */
    public Cliente_1() {
        try {
            Socket sCliente = new Socket(host, puerto);// El cliente se conecta al servidor por el puerto
            /*
            Una vez establecida la conexion entre el cliente y el servidor, hay que enviar y recibir
            datos a través del socket, para lo que se usa un STREAM
             */

            //Defino los Stream para el flujo de salida del CLiente al Servidor
            OutputStream auxSalida = sCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(auxSalida);

            //Defino los stream para el flujo de entrada desde el Servidor al Cliente_1
            InputStream auxEntrada = sCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(auxEntrada);

            System.out.println("PROGRAMA CLIENTE INICIADO....");
            while (!conseguido) {
                flujoSalida.writeUTF("" + numero);//defino el flujo de salida y envio numeros para probar
//                System.out.println("numero enviado");
                if (flujoEntrada.readUTF().toString().startsWith("Clave incorrecta")) {
                    System.out.println("He intentado como clave el valor "+
                            numero+ " y me dice: Clave Incorrecta. Llevas "+
                            numero+" intentos");
                    numero++;
//                    System.out.println(numero);

                } else {
                    conseguido = true;
                    System.out.println("He intentado como clave el valor "+
                            numero+ " y me dice: Has acertado la clave en  "+
                            numero+" intentos");
                    
                }

            }
            
            /*
            Cierre de flujos y conexiones
            */
            flujoEntrada.close();
            flujoSalida.close();

            sCliente.close();// Cierro el socket

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] arg) {
        new Cliente_1();

    }
}
