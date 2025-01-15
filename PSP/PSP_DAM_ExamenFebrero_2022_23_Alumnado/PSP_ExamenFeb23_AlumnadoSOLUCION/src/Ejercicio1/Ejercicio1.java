
package Ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angela Rodriguez Portela
 */
public class Ejercicio1 {

    public static void main(String[] args) throws IOException {

        //DECLARACION DE VARIABLES
        String programa = "";

        File file = null;
        FileWriter fw = null;

        if (args.length != 1) {
            System.err.println("Debe introducir un parámetro");

        }

        try {

            //Se almacena el parametro obtenido en una variable
            String comando = args[0];

            Process process = Runtime.getRuntime().exec("cmd.exe /c" + comando);
            int estado = process.waitFor();

            if (estado == 0) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {//leemos cada linea
                    System.out.println(line); // y la pintamos
                }
            } else {
                try {
                    file = new File("errores.txt");
                    fw = new FileWriter(file);
                    fw.append("El comando " + comando + " no es valido.");
                    fw.close();
                    System.exit(-1);
                } catch (IOException ex) {
                    Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

        
    
   
