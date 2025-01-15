/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TitularThread extends Thread{
    private String nombreTitular;
    private static CuentaCorriente cuenta;
    private ArrayList<Integer>movimientos;
    private int tiempoEspera;
    
    public TitularThread(String nombreTitular, CuentaCorriente cuenta, ArrayList<Integer>movimientos, int tiempoEspera){
        this.nombreTitular=nombreTitular;
        this.cuenta=cuenta;
        this.movimientos=(ArrayList<Integer>) movimientos.clone();
        this.tiempoEspera = tiempoEspera;
    }
    
    public void run(){
        int dineroMovimiento;
        for(int i=0; i<movimientos.size();i++){
            dineroMovimiento = movimientos.get(i).intValue();
            if(dineroMovimiento > 0){
                cuenta.ingresar(nombreTitular,dineroMovimiento);
            }else if(dineroMovimiento < 0){
                dineroMovimiento = dineroMovimiento * (-1);
                cuenta.retirar(nombreTitular,dineroMovimiento);
            }
            try {
                Thread.sleep(tiempoEspera*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TitularThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
