/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1bis;

import java.util.*;

public class Principal {

	public static void main(String args[]) {
	
		Ring ring = new Ring();
		/**Boxeador boxeador1 = new Boxeador("Tyson", ring);
		Boxeador boxeador2 = new Boxeador("Rocky", ring);
		
		boxeador1.start();
		boxeador2.start();**/
                
                ArrayList<Boxeador> boxeadores = new ArrayList<Boxeador>();
                for(int i=0; i<2; i++){
                    boxeadores.add(new Boxeador("Boxeador "+i,ring));
                }
		
                for(int i=0; i<boxeadores.size();i++){
                    boxeadores.get(i).start();
                }
                
		try {
			for(int i=0; i<boxeadores.size();i++){
                            boxeadores.get(i).join();
                        }
		} catch (InterruptedException ie) {}
		
		/**System.out.println("Fin del combate");
		if (boxeadores.get(0).getNGolpesDados() > boxeadores.get(1).getNGolpesDados())
			System.out.println("Ha ganado " + boxeador1.getNombre());
		else if (boxeador1.getNGolpesDados() < boxeador2.getNGolpesDados())
			System.out.println("Ha ganado " + boxeador2.getNombre());
		else
			System.out.println("Empate");**/
	}
}
