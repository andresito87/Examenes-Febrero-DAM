/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Profe
 */
public class Analizador {
     public static void main(String[] args) {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String line = br.readLine();
            System.out.println("La longitud de la cadena  '"+line+"' es "+line.length());
        }catch (IOException ex){
            ex.getStackTrace();
        }
    }
}
