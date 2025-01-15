/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1;

public class Ring {

	private int totalGolpes;
	
	public Ring() {
		totalGolpes = 0;
	}
	
	public int getTotalGolpes() {
		return totalGolpes;
	}
	
	public void pegar(Boxeador boxeador) {
		boxeador.pegar();
		totalGolpes++;
		System.out.println("Pegada de " + boxeador.getNombre() + " (" + boxeador.getNGolpesDados()+"/"+ this.getTotalGolpes()+ ")");
	}
}