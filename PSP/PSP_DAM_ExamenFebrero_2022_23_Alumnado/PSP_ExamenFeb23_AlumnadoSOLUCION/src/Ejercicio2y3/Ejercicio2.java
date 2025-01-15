package Ejercicio2y3;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angela Rodriguez Portela
 */
public class Ejercicio2 {

    public static void main(String argv[]) {

        CuentaCorriente cuenta = new CuentaCorriente(100);

        ArrayList<TitularThread> titulares = new ArrayList<TitularThread>();

        ArrayList<Integer> movimientos1 = new ArrayList<Integer>();
        movimientos1.add(-50);
        movimientos1.add(25);
        movimientos1.add(-10);
        titulares.add(new TitularThread("Titular 1", cuenta, movimientos1, 2));

        ArrayList<Integer> movimientos2 = new ArrayList<Integer>();
        movimientos2.add(-10);
        movimientos2.add(-6);
        movimientos2.add(-34);
        titulares.add(new TitularThread("Titular 2", cuenta, movimientos2, 3));

        ArrayList<Integer> movimientos3 = new ArrayList<Integer>();
        movimientos3.add(50);
        movimientos3.add(-25);
        movimientos3.add(-41);
        titulares.add(new TitularThread("Titular 3", cuenta, movimientos3, 3));

        
        System.out.println("RESUMEN DE OPERACIONES REALIZADAS:");
        System.out.println("----------------------------------");

        //Recorremos el Arraylist de los hilos y los lanzamos
        
         String nombreTitular;

        for (TitularThread titular : titulares) {
            titular.start();            

        }

        for (TitularThread titular : titulares) {
            
            try {

                nombreTitular = titular.getName();
                titular.join();

            } catch (InterruptedException ex) {

                Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

        String movs = cuenta.getMovimientos();

        System.out.println(movs);

        
    }
}
