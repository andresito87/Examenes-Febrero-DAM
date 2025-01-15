package Ejercicio3;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio3 {
    public static void main(String argv[]){
        CuentaCorriente cuenta = new CuentaCorriente(100);
        
        ArrayList<TitularThread> titulares = new ArrayList<TitularThread>();
        
        ArrayList<Integer> movimientos1 = new ArrayList<Integer>();
        movimientos1.add(-50);
        movimientos1.add(25);
        movimientos1.add(-10);
        titulares.add(new TitularThread("Titular 1", cuenta,movimientos1,2));
        
        ArrayList<Integer> movimientos2 = new ArrayList<Integer>();
        movimientos2.add(-10);
        movimientos2.add(-6);
        movimientos2.add(-34);
        titulares.add(new TitularThread("Titular 2", cuenta,movimientos2,3));
        
        ArrayList<Integer> movimientos3 = new ArrayList<Integer>();
        movimientos3.add(50);
        movimientos3.add(-25);
        movimientos3.add(-41);
        titulares.add(new TitularThread("Titular 3", cuenta,movimientos3,3));
        
        //...
        
        for (int i = 0; i < titulares.size(); i++) {
            titulares.get(i).start();//lanzo los hilos que estan guardados en le arraylist
            
        }
        
        for (int i = 0; i < titulares.size(); i++) {
            try {
                titulares.get(i).join();//cuando todosl os hilos acaben.....
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        System.out.println("Resumen de operaciones realizadas: ");
        
        System.out.println(cuenta.getMovimientos());
//...
    }
}
