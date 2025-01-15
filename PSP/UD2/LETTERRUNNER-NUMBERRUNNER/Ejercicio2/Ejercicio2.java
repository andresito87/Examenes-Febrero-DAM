 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;


/**
 *
 * @author Usuario
 */
public class Ejercicio2 {
    public static void main(String[] args){
    
        RecursoCompartido res = new RecursoCompartido();
 
        Thread letterThread = new Thread(new LetterRunner(res));
        Thread numberThread = new Thread(new NumberRunner(res));
 
        letterThread.start();
        numberThread.start();
 
    }
}
