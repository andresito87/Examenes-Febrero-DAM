/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuberias;

// TODO: Implementar la clase Analizador

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Analizador {
    public static void main(String[] args) {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       try{
       String line =br.readLine();
       System.out.println("La longitud de la cadena " +line+" es " + line.length());
       }catch
               (IOException ex)
       {
       ex.getStackTrace();
       }
       
    }
}
