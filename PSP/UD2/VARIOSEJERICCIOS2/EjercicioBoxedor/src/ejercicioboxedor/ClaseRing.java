package ejercicioboxedor;

import java.util.Random;


public class ClaseRing {

    private final int MaxGolpes;
    private int totalGolpes;
    boolean finGolpes;


    public ClaseRing() {
        MaxGolpes = 20;
        totalGolpes = 0;
        finGolpes = false;
    }

    public boolean golpear(ClaseBoxeador boxeador) throws InterruptedException {
        System.out.println("Golpeando el boxeador..." + boxeador.nombreBoxeador);
       
        Thread.sleep(new Random().nextInt(5000));
        if (totalGolpes <= MaxGolpes) {
            boxeador.golpear();
             totalGolpes++;
            finGolpes = false;
        } else if (totalGolpes > MaxGolpes){
            finGolpes = true;
        }
        return finGolpes;
    }

   
}
