package sumadigitosp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SumaDigitosP2 {

    public static void main(String[] args) throws IOException {
        int numero;
       InputStreamReader isr = new InputStreamReader(System.in);
       BufferedReader bf = new BufferedReader(isr);
       numero = Integer.parseInt(bf.readLine());
       System.out.println("El resultado es..." + Calculo(numero));
    }
    
    public static int Calculo(int numero){
        
        int dobleNumero = numero * 2;
        return dobleNumero;
    }

}
