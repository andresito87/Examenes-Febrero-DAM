/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuberias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Implementar la clase Lanzador
public class Lanzador {
    public static void main(String[] args) throws IOException {

        Process p = new ProcessBuilder ("java", "Generador").start();
        BufferedReader br = new BufferedReader (new InputStreamReader(p.getInputStream()));
        System.out.println(br.readLine());
        
    }
}
