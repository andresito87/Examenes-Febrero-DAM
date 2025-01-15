/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanzador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Daniel González Fernández (33.374.9888-W)
 * Esta aplicacióm recoge un dato que haya en el buffer
 * para abrirla (ejecutarla)
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Inicio el lanzador");// Sé que he entrado en el lanzador
        InputStreamReader isr = new InputStreamReader(System.in);//Crep un objeto para el Stream
        BufferedReader bf = new BufferedReader(isr);//Creo un objeto para leer el buffer. 
        String linea= null;//Aqui voy a meter la cadena que este en el buffer.Estas tres lineas siempre juntas
        
        try{
            linea=bf.readLine();//Meto lo que haya en el buffer dentro de la cadena
            System.out.println("Iniciamos:" +linea);
            Process pb = new ProcessBuilder(linea).start();//Paso la cadena como parametro, creo un proceso y lo inicio
           
        }catch(IOException ex){
            System.err.println("Se ha producido un error en E/S");//Si hubiera algun problema con la entrada salida, salta la excepción (salta con WORDPAD)
        }
    }
}
