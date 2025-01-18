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
 * @author Profe
 */
public class MainServidor3 {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        int puertoServidor=6000;
        int clave = generarClave();
        ArrayList<Thread> hilosServidor = new ArrayList();
        ServerSocket socketServidor;
        int numCliente = 0;

        try {
            // Punto de conexión del servidor (socket en el puerto indicado en la máquina donde se ejecute el proceso)
            socketServidor = new ServerSocket(puertoServidor);

            // Mensaje de arranque de la aplicación
            System.out.println("SERVIDOR CONCURRENTE");
            System.out.println("--------------------");
            System.out.println("Servidor iniciado.");
            System.out.println("");
            System.out.printf("Escuchando por el puerto %d.\n", socketServidor.getLocalPort());
            System.out.println("Esperando conexión con cliente.");

            
            while (numCliente < 100) {

                // Quedamos a la espera ("escuchando") de que se realice una conexión con el socket de servidor.
                // En el momento en que eso suceda, se aceptará. Mientras tanto, la ejecución queda aquí 
                // bloqueada en espera a que se reciba esa petición por parte de un cliente.
                Socket clientSocket = socketServidor.accept();

                //Interacción del servidor con un cliente
                System.out.println("Conexión establecida con cliente."); // Debug

                // Creamos un nuevo hilo de ejecución para servir a este nuevo cliente conectado
                Servidor3 hiloServidor = new Servidor3(clientSocket, numCliente + 1,clave);
                hilosServidor.add(hiloServidor);
                numCliente++;

                // Lanzamos la ejecución de ese nuevo hilo
                hiloServidor.start();
            } // y seguimos "escuchando" a otras posibles peticiones de cliente
            
            //Cuando finalice cierro el socket del servidor
            socketServidor.close();

        } catch (SocketException ex) {
            System.out.printf("Error de socket: %s\n", ex.getMessage());
        } catch (IOException ex) {
            System.out.printf("Error de E/S: %s\n", ex.getMessage());
        }

        // Esperamos a que todos los hilos finalicen su ejecución
        for (Thread hilo : hilosServidor) {
            hilo.join();
        }

        System.out.println("Fin de ejecución del servidor.");
    }

     public static int generarClave(){
        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
    }
}
