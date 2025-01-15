/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgReto11;

/**
 *
 * @author Usuario
 */
public class PruebaReto11 {
    public static void main(String[] args){
        Molecula m1=null;
        try{
            m1 = new Molecula("H2O");
        }catch(Exception e){
            System.out.println("Error al instanciar el objeto");
        }
        System.out.println("El número de átomos distintos es "+m1.getNumAtomosDistintos());
        System.out.println("La cantidad de átomos en la molécula es de "+m1.getNumAtomosTotales());
        System.out.println(m1.toString());
        
        
        Molecula m2=null;
        try{
            m2 = new Molecula("H02O");
        }catch(Exception e){
            System.out.println("Error al instanciar el objeto");
        }
        
        Molecula m3=null;
        try{
            m3 = new Molecula("2O");
        }catch(Exception e){
            System.out.println("Error al instanciar el objeto");
        }
    }
}
