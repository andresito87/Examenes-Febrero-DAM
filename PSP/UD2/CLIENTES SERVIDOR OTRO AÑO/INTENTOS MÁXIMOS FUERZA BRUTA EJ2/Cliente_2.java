/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import Ejercicio1.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author Usuario
 */
public class Cliente_2 {

    static final int puerto = 6000;
    static final String host = "localhost";
    int numero = 1;
    boolean conseguido, finalizado = false;
    String mensajeRecibido = "";

    /*
    Constructor del Cliente_2
     */
    public Cliente_2() {
        try {
            Socket sCliente = new Socket(host, puerto);// El cliente se conecta al servidor por el puerto
            /*
            Una vez establecida la conexion entre el cliente y el servidor, hay que enviar y recibir
            datos a través del socket, para lo que se usa un STREAM
             */

            //Defino los Stream para el flujo de salida del CLiente al Servidor
            OutputStream salida = sCliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            //Defino los stream para el flujo de salida desde el Servidor al Cliente_2
            InputStream entrada = sCliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            System.out.println("PROGRAMA CLIENTE INICIADO....");

            while (!conseguido && !finalizado) {
                //System.out.println("envio el numero "+numero);
                flujoSalida.writeUTF("" + numero);//defino el flujo de salida y envio numeros para probar
                mensajeRecibido = flujoEntrada.readUTF();
                if (mensajeRecibido.startsWith("Clave incorrecta")) {
                    System.out.println("He intentado como clave el valor "
                            + numero + " y me dice: " + mensajeRecibido);
                    numero++;
                }
                if (mensajeRecibido.startsWith("Clave CORRECTA")) {
                    System.out.println("He intentado como clave el valor "
                            + numero + " y me dice: " + mensajeRecibido);
                    conseguido = true;

                }
                if (mensajeRecibido.startsWith("Game")) {
                    System.out.println(mensajeRecibido);
                    finalizado = true;

                }
            }
            //CERRAR STREAMS Y SOCKETS
            salida.close();
            flujoSalida.close();
            entrada.close();
            flujoEntrada.close();
            sCliente.close();// Cierro el socket

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] arg) {
        new Cliente_2();

    }
}
