/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;


import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Servidor_3 extends Thread {

    static final int intentosMax = 1000;
    static final int puerto = 6000;
    static final String host = "localhost";

    Socket sCliente;
    int numCliente = 1;

    InputStream entrada = null;
    DataInputStream flujoEntrada = null;

    OutputStream salida = null;
    DataOutputStream flujoSalida = null;

//    String mensajeRecibido = "";

    /*
    Constructor
     */
    public Servidor_3(Socket sCliente, int numCliente) {
        this.sCliente = sCliente;
        this.numCliente = numCliente;

    }

    @Override
    public void run() {

        int numeroRecibido = 1;
        int numIntentos = 0;
        int clave = generarClave();
        boolean descifrada = false;

//        boolean conseguido, finalizado = false;

        System.out.println("Iniciado el hilo del servidor: " + this.numCliente);
        System.out.println("La clave es: " + clave);
        do {
            try {
                //Defino los Stream para el flujo de salida del Cliente al Servidor_3
                this.salida = sCliente.getOutputStream();
                this.flujoSalida = new DataOutputStream(salida);

                // Flujo de entrada a través del cual recibiremos información desde el proceso cliente
                this.entrada = sCliente.getInputStream();
                this.flujoEntrada = new DataInputStream(entrada);

            } catch (IOException ex) {
                Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //RECIBO EL NUMERO QUE EL CLIENTE ME ENVÍA
                numeroRecibido = Integer.parseInt(this.flujoEntrada.readUTF());

                numIntentos++;
                //ENVÍO LA RESPUESTA ANTE EL INTENTO

                if (clave == numeroRecibido) {//si el cliente ha descifrado la clave
                    this.flujoSalida.writeUTF("Clave CORRECTA. Llevas " + numIntentos + " intentos.");
                    descifrada = true;
                    System.out.println(
                            "El cliente " + this.numCliente + " ha descifrado la clave en " + numIntentos + " intentos");

                    System.out.println(
                            "Hilo servidor:" + this.numCliente + " Fin de la conexión con el cliente");
                    this.sCliente.close();
                }
                if (descifrada==false && numIntentos==intentosMax){
                    System.out.println("El cliente " + this.numCliente + " ha agotado el numero de intentos sin descifrar la clave");
                this.flujoSalida.writeUTF("Clave incorrecta. Llevas " + numIntentos + " intentos\n"+"Game Over. No has conseguido descrifrar la clave en " + intentosMax + " intentos");
                }
                
                if (numIntentos<intentosMax && descifrada==false){//Si no ha encontrado el numero en el intento, mando este mensaje
                    this.flujoSalida.writeUTF("Clave incorrecta. Llevas " + numIntentos + " intentos");
                }
                

            } catch (IOException ex) {
                Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (descifrada == false && (numIntentos < intentosMax));
        
//        if (!descifrada) {
//            try {
//            System.out.println("El cliente " + this.numCliente + " ha agotado el numero de intentos sin descifrar la clave");
//            this.flujoSalida.writeUTF("Game Over. No has conseguido descrifrar la clave en " + intentosMax + " intentos");
//
//            }catch (IOException ex) {
//                Logger.getLogger(Servidor_3.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {

                // Cerramos la comunicación con el cliente
                this.flujoSalida.close();
                this.flujoEntrada.close();
                this.sCliente.close();

            } catch (SocketException ex) {
                System.out.printf("Error de socket: %s\n", ex.getMessage());
            } catch (IOException ex) {
                System.out.printf("Error de E/S: %s\n", ex.getMessage());
            }

            System.out.println(
                    "Hilo servidor:" + this.numCliente + " Fin de la conexión con el cliente");

//        }

    }

    public static int generarClave() {
        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
    }
}
