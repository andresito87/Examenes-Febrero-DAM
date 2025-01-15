
package tema3servidorunico;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Tema3ServidorUnico {

    
    public static void main(String[] args) throws IOException {
      int numSecreto = (int)(Math.random()*10+1);
      System.out.println("Numero Secreto: " + numSecreto);
      int maxIntentos = 20;
      int numIntentos = 0;
      
      ArrayList<HiloServidor> listaHilos = new ArrayList<>();
      
      int puerto = 6000;
      ServerSocket socketServidor = new ServerSocket(puerto);
      Socket socketCliente;
      System.out.println("Iniciando hilo servidor...");
      
      while (numIntentos < maxIntentos){
          System.out.println("Esperando conexión número " + numIntentos);
          socketCliente = socketServidor.accept();
          HiloServidor hiloServidor = new HiloServidor(numSecreto,socketCliente);
          
          hiloServidor.start();
          numIntentos++;
          
      }
      
      
      
      
    }
    
}
