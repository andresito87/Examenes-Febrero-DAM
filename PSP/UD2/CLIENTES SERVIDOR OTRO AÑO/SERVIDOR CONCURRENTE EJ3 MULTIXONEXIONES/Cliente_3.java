package Ejercicio3;

import java.io.*;
import java.net.Socket;


public class Cliente_3 extends Thread {

    public static void main(String[] args) {

        Socket cliente = null;
        int puerto = 6000;
//        boolean conseguido = false;
//        boolean finalizado = false;
        
        int numeroQueMando = 1;//

        //Defino los Stream para el flujo de entrada del Cliente al Servidor
        InputStream entrada = null;
        DataInputStream flujoEntrada = null;

        String mensajeRecibido = "";
        String mensajeSalida = "";

        //Defino los Stream para el flujo de salida del Cliente al Servidor
        OutputStream salida = null;
        DataOutputStream flujoSalida = null;

        System.out.println("PROGRAMA CLIENTE INICIADO ...");
        try {
            cliente = new Socket("localhost", puerto);

            entrada = cliente.getInputStream();
            flujoEntrada = new DataInputStream(entrada);

            salida = cliente.getOutputStream();
            flujoSalida = new DataOutputStream(salida);

            /*
            CUIDADO!!! Este while tiene que controlar bien para que las salidas 
            y entradas de mensajes entre el servidor y el cliente esten sincronizados
            */
            while (mensajeRecibido.startsWith("Clave CORRECTA")==false &&
                    mensajeRecibido.contains("Game")==false){
                
                mensajeSalida = (""+numeroQueMando);
                flujoSalida.writeUTF(mensajeSalida);//defino el flujo de salida y envio numeros para probar
                
                mensajeRecibido = flujoEntrada.readUTF();
                /*
                Si recibo del servidor el mensaje que empieza por "Clave incorrecta...."
                entonces intento con el siguiente numero
                */
                if (mensajeRecibido.startsWith("Clave incorrecta") && (mensajeRecibido.contains("Game")==false)) {
                    System.out.println("He intentado como clave el valor "
                            + numeroQueMando + " y me dice: " + mensajeRecibido);
                    numeroQueMando++;
                }
                /*
                Si recibo del servidor el mensaje que empieza por "Clave CORRECTA...." 
                entonces he conseguido acertar el número
                */
                if (mensajeRecibido.startsWith("Clave CORRECTA")) {
                    System.out.println("He intentado como clave el valor "
                            + numeroQueMando + " y me dice: " + mensajeRecibido);
//                    conseguido = true;

                }
                /*
                Si recibo del servidro el mensaje que empieza por "Game..." entonces han terminado los intentos, no
                he conseguido adivinar la clave y finalizo la busqueda
                */
                if (mensajeRecibido.contains("Game")) {
                    System.out.println(mensajeRecibido);
//                  finalizado = true;

                }
            }
             
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Cierro los Streams y los Sockets
        try {
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            cliente.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
