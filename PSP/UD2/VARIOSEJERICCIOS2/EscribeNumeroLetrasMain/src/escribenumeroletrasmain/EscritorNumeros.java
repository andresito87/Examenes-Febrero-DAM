package escribenumeroletrasmain;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EscritorNumeros extends Thread {

    RecursoCompartido recursoCompartido;
    String nombre;

    public EscritorNumeros(String nombre, RecursoCompartido recursoCompartido) {
        this.nombre = nombre;
        this.recursoCompartido = recursoCompartido;
    }

    @Override
    public void run() {

        while (recursoCompartido.numero < 27) {
            synchronized (recursoCompartido) {

                if (recursoCompartido.getIsLetter() == false) {
                    try {
                        recursoCompartido.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EscritorNumeros.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    recursoCompartido.escribeNumero();
                    recursoCompartido.notify();
                }

            }
        }
    }
}
