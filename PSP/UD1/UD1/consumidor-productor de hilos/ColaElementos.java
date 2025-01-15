/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author profe
 */
public class ColaElementos {
    private Queue<Character> colaElementos;
    
    public ColaElementos () {
        this.colaElementos= new LinkedList<>();
    }
    
    public void addElemento (char elemento) {
        this.colaElementos.add (elemento);
    }

    public  char getElemento () {
        if (this.colaElementos.size()>0)
            return colaElementos.poll();
        else
            return 0;        
    }
    
    @Override
    public  String toString() {
        return this.colaElementos.toString();
    }
    
    public  int size() {
        return this.colaElementos.size();
    }


}
