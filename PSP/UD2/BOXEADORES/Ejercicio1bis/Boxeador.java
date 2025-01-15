/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1bis;

import Ejercicio1.*;
import java.util.Random;

/**
 * @author Santiago Faci
 * @version curso 2014-2015
 */
public class Boxeador extends Thread {

    private String nombre;
    private Ring ring;
    private int NGolpesDados;

    public Boxeador(String nombre, Ring ring) {
        this.nombre = nombre;
        this.ring = ring;
        NGolpesDados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNGolpesDados() {
        return NGolpesDados;
    }

    public void pegar() {
        NGolpesDados++;
    }

    @Override
    public void run() {
        //synchronized(ring){
            boolean salir = false;
            while (!salir) {
                //synchronized(ring){
                    salir = ring.pegar(this);
                //}
            }
        //}
    }
}
