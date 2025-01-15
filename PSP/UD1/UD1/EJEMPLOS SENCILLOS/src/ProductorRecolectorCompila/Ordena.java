/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductorRecolectorCompila;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Collections;

public class Ordena {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        String lineaTeclado = null;
        LinkedList<Integer> lista = new LinkedList<>();

        try {
            System.out.println("Proceso ordenaNúmeros");
            while ((lineaTeclado = bf.readLine()) != null) {
                if (isNumeric(lineaTeclado)) {
                    int valor = Integer.parseInt(lineaTeclado);
                    lista.add(valor);
                }
            }

            ordena(lista);

            System.out.println("Los enteros ordenados son: ");
            for (int elemento : lista) {
                System.out.println(elemento);
            }
            System.out.println("Número de elementos leídos: " + lista.size());

        } catch (IOException ex) {
            System.err.println("Se ha producido un error de E/S. Su descripción es: ");
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println("Se ha producido un error. Su descripción es: ");
            System.err.println(ex.toString());
        }
    }

    private static boolean isNumeric(String dato) {
        try {
            Integer.parseInt(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void ordena(LinkedList<Integer> lista) {
        Collections.sort(lista);
    }
}
