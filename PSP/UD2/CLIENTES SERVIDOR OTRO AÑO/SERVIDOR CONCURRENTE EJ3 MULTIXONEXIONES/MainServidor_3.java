/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class MainServidor_3 {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        int puerto=6000;
        ArrayList<Thread> hilosServidor = new ArrayList();
        //ServerSocket sServidor=null;
        int numClientesConectados = 0;
        
        ServerSocket servidor = null;
        //Socket clienteConectado = null;

        try {
            // Punto de conexión del servidor (socket en el puerto indicado en la máquina donde se ejecute el proceso)
            servidor = new ServerSocket(puerto);

            // Mensaje de arranque de la aplicación
            System.out.println("SERVIDOR CONCURRENTE");
            System.out.println("--------------------");
            System.out.println("Servidor iniciado.");
            System.out.println("Escuchando por el puerto: "+ puerto);
            System.out.println("Esperando conexión con cliente.");

            
            while (numClientesConectados < 100) {

                // Quedamos a la espera ("escuchando") de que se realice una conexión con el socket de servidor.
                // En el momento en que eso suceda, se aceptará. Mientras tanto, la ejecución queda aquíe 
                // bloqueada en espera a que se reciba esa petición por parte de un cliente.
                Socket clienteConectado = servidor.accept();

                //Interacción del servidor con un cliente
                System.out.println("Conexión establecida con cliente.");

                // Creamos un nuevo hilo de ejecución para servir a este nuevo cliente conectado
                Servidor_3 hiloServidor = new Servidor_3(clienteConectado, numClientesConectados + 1);
                hilosServidor.add(hiloServidor);
                numClientesConectados++;

                // Lanzamos la ejecución de ese nuevo hilo
                hiloServidor.start();
            } // y seguimos "escuchando" a otras posibles peticiones de cliente
            
            //Cuando finalice cierro el socket del servidor
            servidor.close();

        } catch (SocketException ex) {
            System.out.printf("Error de socket: %s\n", ex.getMessage());
        } catch (IOException ex) {
            System.out.printf("Error de E/S: %s\n", ex.getMessage());
        }

        System.out.println("Fin de ejecución del servidor.");
    }

}
