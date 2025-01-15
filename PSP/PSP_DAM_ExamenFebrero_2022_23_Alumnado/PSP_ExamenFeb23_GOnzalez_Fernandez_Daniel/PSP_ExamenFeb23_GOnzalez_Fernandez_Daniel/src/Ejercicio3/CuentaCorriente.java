package Ejercicio3;


import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class CuentaCorriente {
    private float saldo;
    private ArrayList<String> movimientos;
    
    public CuentaCorriente(float saldoInicial){
        this.saldo = saldoInicial;
        movimientos = new ArrayList<String>();
    }
    
    public synchronized void ingresar(String nombre, float dinero){
        saldo += dinero;
        movimientos.add(nombre + " ha ingresado "+dinero+" €.- Saldo actual: "+saldo+" €.");
    }
    
    public synchronized void retirar(String nombre, float dinero){
        if(saldo>=dinero){
            saldo -=dinero;
            movimientos.add(nombre + " ha retirado "+dinero+" €.- Saldo actual: "+saldo+" €.");
        }else{
            movimientos.add(nombre + " NO ha podido retirar "+dinero+" €.- Saldo actual: "+saldo+" €.");
        }
    }
    
    public String getMovimientos(){
        String salida ="";
        for(int i=0;i<movimientos.size();i++)
            salida = salida + movimientos.get(i)+"\n";
        return salida;
    }
}
