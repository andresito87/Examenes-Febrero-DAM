/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilodormilonexc;

import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class HiloDormilonExc {

   
    public static void main(String[] args) {
        
        int totalHilos = 10;
        ArrayList<HiloDormilon> listaHilos = new ArrayList<>();
        
        for (int i = 0; i <totalHilos; i++) {
            listaHilos.add(new HiloDormilon("Hilo" + 1));
        }
        
        for (int i = 0; i < listaHilos.size(); i++) {
            listaHilos.get(i).start();
        }
    }
    
}
