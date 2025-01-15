package comprobadorprimosmain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ComprobadorPrimosMain {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String nombreArchivo = args[0];
        String linea;
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        //int listaNumeros[] = new int[20];
        ArrayList<HiloComprobadorPrimos> listaHilos = new ArrayList<HiloComprobadorPrimos>();
        File f = new File(nombreArchivo);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        System.out.println("El archivo contiene...");
        while ((linea = br.readLine()) != null) {
            listaNumeros.add(Integer.parseInt(linea));
        }

        System.out.println("Se ha guardado la lista de numeros...");
        for (int i = 0; i < listaNumeros.size(); i++) {
            System.out.println("El numero es: " + listaNumeros.get(i));
            listaHilos.add(new HiloComprobadorPrimos(listaNumeros.get(i)));
        }

        for (int i = 0; i < listaHilos.size(); i++) {
            listaHilos.get(i).start();
        }
    }

}
