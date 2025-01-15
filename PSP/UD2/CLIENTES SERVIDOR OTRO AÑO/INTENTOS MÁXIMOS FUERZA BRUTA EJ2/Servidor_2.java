/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import Ejercicio1.*;
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
public class Servidor_2 {

    static final int intentosMax = 1000;//numero de intentos máximos de conexiones

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numeroPuerto = 6000;
        int clave = generarClave();
        int probarNum = 0;
        boolean descifrada = false;
        int numIntentos = 0;

        /*
        Declaracion de servidor y socket
         */
        ServerSocket servidor = null;
        Socket clienteConectado = null;

        /*
        Declaracion de flujos de entrada
         */
        InputStream entrada = null;
        DataInputStream flujoEntrada = null;

        /*
        Declaracion de flujos de salida
         */
        OutputStream salida = null;
        DataOutputStream flujoSalida = null;
        String mensaje = "";

        try {
            servidor = new ServerSocket(numeroPuerto);//El servidor escucha el puerto

            System.out.println("Esperando al cliente...");

            clienteConectado = servidor.accept();//acepto la conexión del cliente

            //CREO FLUJO DE ENTRADA DEL CLIENTE
            entrada = clienteConectado.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            //CREO FLUJO DE SALIDA AL CLIENTE
            salida = clienteConectado.getOutputStream();
            flujoSalida = new DataOutputStream(salida);
        } catch (IOException ex) {
            Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Se conecta un cliente que intenta descifrar la clave.");
        System.out.println("Clave: " + clave);

        /*
        Mientras la clave no este descubierta y no se hayan superado el numero 
        de intentos, se sigue probando
         */
        do {
            try {
                //RECIBO EL NUMERO QUE EL CLIENTE ME ENVÍA
                probarNum = Integer.parseInt(flujoEntrada.readUTF());

                numIntentos++;
                //ENVÍO LA RESPUESTA ANTE EL INTENTO

                if (clave == probarNum) {
                    flujoSalida.writeUTF("Clave CORRECTA. Llevas " + numIntentos + " intentos.");
                    descifrada = true;
                } else {//Si no ha encontrado el numero en el intento, mando este mensaje
                    flujoSalida.writeUTF("Clave incorrecta. Llevas " + numIntentos + " intentos");
                }

            } catch (IOException ex) {
                Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (descifrada == false && (numIntentos < intentosMax));
        if (!descifrada) {
            try {
                flujoSalida.writeUTF("Game Over. No has conseguido descrifrar la clave en " + intentosMax + " intentos");

            } catch (IOException ex) {
                Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //CERRAR STREAMS Y SOCKETS
                entrada.close();
                flujoEntrada.close();
                salida.close();
                flujoSalida.close();
                clienteConectado.close();
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Servidor_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int generarClave() {
        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
    }
}
