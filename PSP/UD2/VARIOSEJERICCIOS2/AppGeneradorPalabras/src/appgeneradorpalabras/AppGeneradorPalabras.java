package appgeneradorpalabras;

import java.util.Random;

/**
 *
 * @author Roi Iglesias
 */
public class AppGeneradorPalabras {

    public static void main(String[] args) {

        String palabras[] = {"orejero", "radar", "oso", "camioneta", "reconocer",
            "sobreverbos", "colchoneta", "sometemos", "sotos", "personaje"};

        // System.out.println(""); Debug para probar a mandar línea vacía
        
        System.out.println(generaPalabraAlea(palabras));
    }

    public static String generaPalabraAlea(String[] palabras) {
        String palabraAlAzar;
        Random rnd = new Random();
        palabraAlAzar = palabras[rnd.nextInt(palabras.length)];

        return palabraAlAzar;

    }

}
