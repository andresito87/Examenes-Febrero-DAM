package Ejercicio03;

import java.io.*;

/**
 *
 * @author Usuario
 */
public class Ejercicio3 {

    public static void main(String[] args) throws IOException {
        //Process p1 = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java LeerNombre");
        //Process p1 = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java LeerNombre"+" Fran");
        Process p1 = Runtime.getRuntime().exec("java LeerNombre" + " Fran");
        //Process p1 = Runtime.getRuntime().exec("java LeerNombre");
        int exitVal;
        try {
            //El proceso actual espera hasta que el subproceso Process finalice
            exitVal = p1.waitFor(); //Recoge la salida de System.exit()
            System.out.println("Valor de salida " + exitVal);

            InputStream is = p1.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
