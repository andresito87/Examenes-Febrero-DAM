/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package generador;

/**
 *
 * @author Daniel González Fernández (33.374.9888-W)
 * Programa que elige aleatoriamente un elemento de un array 
 * Ese elemento se imprime con la intención de dejarlo en un Buffer
 * para que otra aplicación a través del sistema de tuberías
 * lo recoja .
 */
public class Generador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String aplic[]={"CALC","NOTEPAD","EXPLORER","WORDPAD"};// Array con las aplicaciones que quiero que lanzador.java abra
        int numero =(int)(Math.random()*aplic.length);//Aqui elijo aleatoriamente un elemento del array entre 1 y tamaño del array
        
        System.out.println(aplic[numero]);// imprimo la aplicación a abrir, pero lo que hago es dejar el dato (nombre de la aplicación) en el buffer
    }
}
