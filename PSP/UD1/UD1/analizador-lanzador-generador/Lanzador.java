/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejercicio_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Profe
 */
public class Lanzador {

    
    public static void main(String[] args) throws IOException {
      
            Process p = new ProcessBuilder("java", "Generador").start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

  
            System.out.println(br.readLine());

        }

    }

