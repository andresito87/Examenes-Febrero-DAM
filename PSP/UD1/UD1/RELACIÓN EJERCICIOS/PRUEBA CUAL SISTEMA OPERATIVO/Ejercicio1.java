/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio01;

/**
 *
 * @author Usuario
 */
public class Ejercicio1 {
    public static void main(String args[]) {
        String osName = System.getProperty("os.name").toLowerCase();
        Process nuevoProceso1, nuevoProceso2; //Definimos una variable de tipo Process
        try {
            if (osName.contains("win")) { //Windows
                nuevoProceso1 = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java -jar C:\\Relacion1\\PruebaProceso1.jar");
                nuevoProceso2 = Runtime.getRuntime().exec("cmd /c start cmd.exe /k java -jar C:\\Relacion1\\PruebaProceso3.jar");
            } else if (osName.contains("mac")) {
                System.out.println("This is Mac");
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                System.out.println("This is Unix or Linux");
            } else if (osName.contains("sunos")) {
                System.out.println("This is Solaris");
            } else {
                System.out.println("Your OS is not support!!");
            }
        } catch (SecurityException ex) {
            System.out.println("Ha ocurrido un error de Seguridad."
                    + "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error, descripciÃ³n: "
                    + ex.toString());
        }
    }
}
