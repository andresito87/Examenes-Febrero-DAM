package pruebagetoutputstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class PruebaGetOutPutStream {

   
    public static void main(String[] args) throws IOException {
       //esta variable me vale para tuberia y teclado
       //texto = tuberia.readLine() y texto = teclado.nextLine();
        String texto = "";
        
       //para leer desde tubería
       InputStreamReader streamTuberia = null;
       BufferedReader tuberia = null;
       InputStream streamTeclado = null;    
       streamTuberia = new InputStreamReader(System.in);
       tuberia = new BufferedReader(streamTuberia);
       
       //para leer desde teclado
       Scanner teclado = null;
       streamTeclado = System.in;
       teclado = new Scanner(streamTeclado);
       
       //está pendiente a cuando llega flujo de información
       boolean preparado = tuberia.ready();
       
       
       //si le llega por tubería o sino lo pide por teclado
       //piensa que ésto sería si se ejecuta individualmente
       //si lo lanzas dede el otro siempre se lo va a pasar
       
       if(preparado){
           
           texto = tuberia.readLine();
           System.out.println(" El texto es: " + texto);
           
       } else {
             System.out.println("Introduce una cadena:.. ");
             texto = teclado.nextLine();
             System.out.println("Cadena escrita: " + texto);
       }
       
       
    }
    
}