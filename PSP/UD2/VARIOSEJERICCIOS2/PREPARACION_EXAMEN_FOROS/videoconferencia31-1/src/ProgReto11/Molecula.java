/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgReto11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Molecula {
    private String[] atomos;
    private int[]cantidades;
    private int numElementos;
    
    public Molecula(String formula){
        this.atomos = new String[10];
        this.cantidades =  new int[10];
        this.numElementos=0;
        
        //Generar patrón
        String expresion = "([A-Z][a-z]?([2-9]|[1-9][0-9])*)+";
        Pattern patron = Pattern.compile(expresion); 
        Matcher acoplamiento = patron.matcher(formula);
        String aux;
        boolean patronEncontrado; // Si el patron ha sido encontrado
        int inicio,fin;
        int numVeces= 0; // Contador para saber cuántas veces aparece el patrón
        while(acoplamiento.matches()) {
                atomos[numElementos] = acoplamiento.group(1);
                cantidades[numElementos] = Integer.parseInt(acoplamiento.group(2));
                numElementos++;
        }
    }

    public String[] getListaAtomos() {
        String[] aux = new String[numElementos];
        for(int i=0; i<numElementos;i++){
            aux[i]=atomos[i];
        }
        return aux;
    }
    
    public int getAtomo(String atomo){
        int pos=-1;
        for(int i=0; i<numElementos && pos==-1;i++){
            if(atomos[i].equals(atomo)){
                pos = i;
            }
        }
        if(pos != -1){
            //Estoy aquí dentro pq he encontrado el átomo
            return cantidades[pos];
        }else{
            //Estoy aquí dentro pq NO he encontrado el átomo
            return 0;
        }
    }
    
    public int getNumAtomosDistintos(){
        return this.numElementos;
    }
    
    public int getNumAtomosTotales(){
        int sumatoria=0;
        for(int i=0;  i<numElementos; i++){
            sumatoria+=cantidades[i];
        }
        return sumatoria;
    }
    
    public String toString(){
        String salida="";
        for(int i=0; i<numElementos;i++){
            salida = salida +atomos[i];
            if(cantidades[i]!=1)
                salida = salida + cantidades[i];
        }
        return salida;
    }
}
