/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductorRecolectorCompila;

import java.util.Random;

/**
 *
 * @author Fran
 */
public class Main {
    public static void main(String[] args) {
        Random v = new Random();

        for (int i = 0; i < 40; i++) {
            System.out.println(v.nextInt(101)); // valores enteros entre 0 y (101-1)
        }

    }
}
