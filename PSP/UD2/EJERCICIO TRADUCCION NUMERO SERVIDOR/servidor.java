/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

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
 * @author Estudiante
 */
public class servidor {

    public static void main(String[] args) {

        int puerto = 1234;
        ServerSocket servidor = null;
        Socket clienteCOnectado = null;

        InputStream entrada = null;
        OutputStream salida = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream flujoSalida = null;

        String mensajeRecibido = "";
        String mensajeEnviado = "";
        int numero = 0;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("SERVIDOR NO CONCURRENTE DE ESCRITURA DE NÚMEROS");
            System.out.println("-----------------------------------------------");
            System.out.println("servidor del alunmo Daniel González Fernández en el puerto " + puerto);
            System.out.println("Esperando al cliente....");
            clienteCOnectado = servidor.accept();
            System.out.println("Reciebiendo del cliente:");

            entrada = clienteCOnectado.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            salida = clienteCOnectado.getOutputStream();
            flujoSalida = new DataOutputStream(salida);

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("RECIBIENDO DEL CLENTE");
            mensajeRecibido = flujoEntrada.readUTF();
            System.out.println(mensajeRecibido);
            if (mensajeRecibido.matches("[0-9]{1,2}")) {
                numero = Integer.parseInt(mensajeRecibido);
                mensajeEnviado = Ejercicio4.traducir(numero);
                System.out.print("Respuesta al cliente: El numero es: ");
                flujoSalida.writeUTF(mensajeEnviado);
                System.out.println(mensajeEnviado);

            } else {
                mensajeEnviado = "El valor introducido no es numérico o no cumple con las caracteristicas requeridas";
                flujoSalida.writeUTF(mensajeEnviado);

            }

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("FIN DE EJECICIÓN DEL SERVIDOR");
        try {
            salida.close();
            entrada.close();
            flujoEntrada.close();
            flujoSalida.close();
            clienteCOnectado.close();
            servidor.close();

        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
