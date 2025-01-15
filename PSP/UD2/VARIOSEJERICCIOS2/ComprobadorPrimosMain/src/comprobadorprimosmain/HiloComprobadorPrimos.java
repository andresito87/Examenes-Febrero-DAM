package comprobadorprimosmain;

public class HiloComprobadorPrimos extends Thread {

    int numero;
    boolean esPrimo;

    public HiloComprobadorPrimos(int numero) {
        this.numero = numero;
        esPrimo = true;
    }
    
    @Override
    public void run(){
        if (compruebaPrimo(this.numero)){
            System.out.println("Soy el hilo " + this.getName() + " y el numero " +
                    + this.numero + "es Primo");
        } else 
            System.out.println("Soy el hilo " + this.getName() + " y el numero " +
                    + this.numero + "no es Primo");
        
        
    }

    public boolean compruebaPrimo(int numero) {
        int candidatoDivisor = 3;
        esPrimo = true;
        if (numero % 2 == 0) {
            esPrimo = false;
        }
        while (candidatoDivisor < (int) Math.sqrt(numero) && !esPrimo) {
            if (numero % candidatoDivisor == 0) {
                esPrimo = false;
            } else {
                candidatoDivisor += 2;
            }
        }
        return esPrimo;
        
    }

}
