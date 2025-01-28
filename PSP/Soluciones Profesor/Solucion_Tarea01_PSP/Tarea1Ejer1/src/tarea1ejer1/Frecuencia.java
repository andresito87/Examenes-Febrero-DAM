package tarea1ejer1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Frecuencia {
    public static void main(String[] args) {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String linea; 
            
            while((linea = br.readLine()) != null){
                if(linea.contains("Indica el número")){
                    System.out.print(linea);
                } else {
                    int[] vocales = new int[5]; 
                
                    for(int i=0; i < linea.length(); i++){
                        char letra = linea.charAt(i); 

                        switch (letra) {
                            case 'a':
                                vocales[0]++;
                                break;
                            case 'e':
                                vocales[1]++;
                                break;
                            case 'i':
                                vocales[2]++;
                                break;
                            case 'o':
                                vocales[3]++;
                                break;
                            case 'u':
                                vocales[4]++;
                                break;
                            default:
                                break;
                        }
                     }

                    System.out.print("\nCadena: " + linea + " --> ");
                    for (int i = 0; i < vocales.length; i++) {
                        System.out.print(vocales[i] + " ");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error");
        }

    }
}
