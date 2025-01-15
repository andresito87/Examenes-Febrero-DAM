/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaclientehttp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class Pruebaclientehttp {

    /**
     * @param args the command line arguments
     */
    
        public static void main(String[] args) throws MalformedURLException, IOException {
        
        try {

            //********************************
            //  Declaracion de Variables
            //********************************
            //variable que contiene URL
            URL url;
            HttpURLConnection connection;

            //variable con URL de prueba
            String urlDebug = "http://www.iesaguadulce.es/centro/index.php/oferta-formativa/formacion-profesional-a-distancia";

            

            //variable para flujo de datos de entrada
            BufferedReader in;

            //variable para crear archivo y escribir
            File f = new File("./salida.html");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            //controlamos el número de argumentos introducidos como parámetro
            if (args.length != 1) {
            System.err.println("Error : debe introducir una URL...");
            System.exit(1);
             }
            
            //si se introduce un argumento lo guardamos como URL.
            //controlamos su formato mediante la excepción MalformedURLException
            url = new URL(args[0]);
            //url = new URL(urlDebug); //debug,oculta esta línea y activa la siguiente cuando termines

            connection = (HttpURLConnection) url.openConnection();

            System.out.println("CLIENTE HTTP");
            System.out.println("------------");
            System.out.println("CLIENTE HTTP de Roi Iglesias Costas");
            System.out.println("Conéctandose a la URL " + url);
            System.out.println();

            System.out.println("Respuesta: " + connection.getResponseMessage());

            Map<String, List<String>> mapaCabecera = connection.getHeaderFields();

            for (String clave : mapaCabecera.keySet()) {
                List<String> valores = mapaCabecera.get(clave);
                System.out.printf(clave + ":");
                for (String valor : valores) {
                    System.out.println(valor);
                }
            }

            //********************************
            //Guardamos a archivo si es html..
            //********************************
            System.out.println("Contenido HTML guardado en salida.html");
            if (connection.getContentType().equals("text/html; charset=utf-8")) {
            

                //variable para guardar datos
                String linea = "";

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while ((linea = in.readLine()) != null) {
                    bw.write(linea);
                    //System.out.println(linea); Debug

                }

                //cerramos flujos
                bw.close();
                in.close();
                fw.close();
            }

            //Desconectamos conexión con Servidor
            connection.disconnect();

            //controlamos el error en la URL aportada
        } catch (MalformedURLException e) {
            System.err.println("URL no válida");
        } finally {
            //termina la aplicación
            System.exit(0);
        }

    }
    }
    

