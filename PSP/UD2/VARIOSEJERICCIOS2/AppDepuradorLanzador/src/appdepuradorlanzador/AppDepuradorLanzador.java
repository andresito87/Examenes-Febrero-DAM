package appdepuradorlanzador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Roi Iglesias
 */
public class AppDepuradorLanzador {

    
    public static void main(String[] args) throws IOException, InterruptedException {
      
        Process pbEjecutaJar;

        
        /*Compilación..
        //Generamos la clase .class
        Process pbCreaJar;
        pbCreaJar = Runtime.getRuntime().exec("javac "
                + "C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\AppGeneradorPalabras\\src\\appgeneradorpalabras\\AppGeneradorPalabras.java");
        pbCreaJar.waitFor();

        //Para este paso hemos creado un archivo con nombre temp.mf
        //Ese archivo, creado con el bloc de notas le especifica la clase principal
        //Main-Class: AppGeneradorPalabras
        //Sealed: true
        pbCreaJar = Runtime.getRuntime().exec("jar -cf "
                + "C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\AppGeneradorPalabras\\src\\appgeneradorpalabras\\AppGeneradorPalabras.jar AppGeneradorPalabras.class");

        pbCreaJar.waitFor();
        pbCreaJar = Runtime.getRuntime().exec("jar cmf " +
                "C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\AppGeneradorPalabras\\src\\appgeneradorpalabras\\MANIFEST.mf AppGeneradorPalabras.jar AppGeneradorPalabras.class");
        pbCreaJar.waitFor();*/
        
        
        //Lanzamos la aplicación Generadora de palabras
        pbEjecutaJar = Runtime.getRuntime().exec("java -jar " +
                "C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\AppGeneradorPalabras\\dist\\AppGeneradorPalabras.jar");
        
        pbEjecutaJar.waitFor();
        
        //recogemos el resultado...
        String linea = null;
        InputStream is = pbEjecutaJar.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        linea = br.readLine();
        
        //mostramos por pantalla la palabra a analizar
        System.out.println("La palabra a analizar es.. " + linea);
        
        //Lanzamos aplicación de comprobación de Palíndromos pasándole como argumento la palabra..
        pbEjecutaJar = Runtime.getRuntime().exec("java -jar " +
                "C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\AppComprobadorPalindromos\\dist\\AppComprobadorPalindromos.jar " + linea );
        
        pbEjecutaJar.waitFor();
         
        //recogemos el resultado...
        is = pbEjecutaJar.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        linea = br.readLine();
        System.out.println("El comprobador me ha dicho : " + linea);
        /* bucle por si fueran varias lineas
        while((linea = br.readLine())!= null)
            System.out.println(linea);*/
        
        

    }

}
