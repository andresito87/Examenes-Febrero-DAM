package tarea1ejer1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author LuisRosillo <>
 */
public class Cadenas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Indica el número de palabras que deseas imprimir:");
        Scanner entrada = new Scanner(System.in);    
        int numPalabras = entrada.nextInt(); 
        
        String[] silabas = {
            "ba", "be", "bi", "bo", "bu", "ca", "ce", "ci", "co", "cu",
            "da", "de", "di", "do", "du", "fa", "fe", "fi", "fo", "fu",
            "ga", "ge", "gi", "go", "gu", "ha", "he", "hi", "ho", "hu",
            "ja", "je", "ji", "jo", "ju", "ka", "ke", "ki", "ko", "ku",
            "la", "le", "li", "lo", "lu", "ma", "me", "mi", "mo", "mu",
            "na", "ne", "ni", "no", "nu", "pa", "pe", "pi", "po", "pu",
            "ra", "re", "ri", "ro", "ru", "sa", "se", "si", "so", "su",
            "ta", "te", "ti", "to", "tu", "va", "ve", "vi", "vo", "vu",
            "wa", "we", "wi", "wo", "wu", "xa", "xe", "xi", "xo", "xu",
            "ya", "ye", "yi", "yo", "yu", "za", "ze", "zi", "zo", "zu"
        };

        
        
        try{
           
            Random r = new Random();
            String palabra = "";


            //Rellenamos con el numero de palabras que tenemos que realizar             
            for(int i = 0; i < numPalabras; i++){                
                int numSilabas = r.nextInt(5) + 1; // Numero de silabas para esa palabra
                
                //Rellenamos cada palabra con el número de sílabas
                for(int j = 0; j < numSilabas; j++){ 
                    palabra=palabra.concat(silabas[r.nextInt(silabas.length)]); //Añadimos la silaba a la palabra
                }
                
                System.out.println(palabra);
                palabra="";
            }                    

        }catch(NumberFormatException e){ 
            System.out.println("Ha ocurrido un error");
        }
    }     
} 
