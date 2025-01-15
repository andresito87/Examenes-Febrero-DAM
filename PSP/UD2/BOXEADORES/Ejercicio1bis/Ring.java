/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1bis;

import Ejercicio1.*;
import java.util.Random;

public class Ring {

	private int totalGolpes;
	
	public Ring() {
		totalGolpes = 0;
	}
	
	public int getTotalGolpes() {
		return totalGolpes;
	}
	
	public synchronized boolean pegar(Boxeador boxeador) {
            if(this.totalGolpes>= 20){
                return true;
            }else{
                        
                try {
                    Thread.sleep(new Random().nextInt(500));
                } catch (InterruptedException ie) {
                }
                boxeador.pegar();
		totalGolpes++;
		System.out.println("Pegada de " + boxeador.getNombre() + " (" + boxeador.getNGolpesDados()+"/"+ this.getTotalGolpes()+ ")");
            }
            return false;
	}
}