/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

public class Principal {

	public static void main(String args[]) {
	
		Ring ring = new Ring();
		Boxeador boxeador1 = new Boxeador("Tyson", ring);
		Boxeador boxeador2 = new Boxeador("Rocky", ring);
		
		boxeador1.start();
		boxeador2.start();
		
		try {
			boxeador1.join();
			boxeador2.join();
		} catch (InterruptedException ie) {}
		
		System.out.println("Fin del combate");
		if (boxeador1.getNGolpesDados() > boxeador2.getNGolpesDados())
			System.out.println("Ha ganado " + boxeador1.getNombre());
		else if (boxeador1.getNGolpesDados() < boxeador2.getNGolpesDados())
			System.out.println("Ha ganado " + boxeador2.getNombre());
		else
			System.out.println("Empate");
	}
}
