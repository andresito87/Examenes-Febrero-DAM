/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumadigitosp1;

/**
 *
 * @author pc
 */
public class SumaDigitosP1 {

    
    public static void main(String[] args) {
        int numero;
        numero = Integer.parseInt(args[0]);

        int sum = 0;
        while (numero > 0) {
            sum += numero % 10;
            numero = numero / 10;
        }
        
        System.out.println(sum);
    }
    
}
