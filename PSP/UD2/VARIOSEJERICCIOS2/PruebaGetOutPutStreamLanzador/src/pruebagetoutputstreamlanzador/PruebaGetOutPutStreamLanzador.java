
package pruebagetoutputstreamlanzador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PruebaGetOutPutStreamLanzador {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb;
        Process proceso;

        String textoEnviado = "Hola Manuel";

        //modo 1 por línea de comando
        pb = new ProcessBuilder("CMD", "/C",
                " ECHO Hola Manuel | java -jar C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\PruebaGetOutPutStream\\dist\\PruebaGetOutPutStream.jar");

        proceso = pb.start();

        /*modo 2 usando getOutputStream
        
        pb = new ProcessBuilder("CMD", "/C",
                " java -jar C:\\Users\\pc\\Desktop\\PSP_ejercicios_preparacion_febrero\\PruebaGetOutPutStream\\dist\\PruebaGetOutPutStream.jar");
        
        proceso = pb.start();
        
        OutputStream os = proceso.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(textoEnviado);
        bw.flush();
        
        //os.write("Hola Manuel".getBytes());
        //os.flush();*/
        
        //Leemos lo que nos llega desde el otro programa
        InputStream is;
        String textoRecibido = "";
        is = proceso.getInputStream();
        int c;
      
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        System.out.println("El texto recibido por el lanzador ha sido : " + textoRecibido);
        while ((c = is.read()) != -1) {

            System.out.print((char) c);

        }

        is.close();

    }

}
