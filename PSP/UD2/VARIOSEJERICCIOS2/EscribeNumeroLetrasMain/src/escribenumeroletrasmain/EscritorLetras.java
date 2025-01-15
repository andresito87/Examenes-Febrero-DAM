package escribenumeroletrasmain;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EscritorLetras extends Thread {

    RecursoCompartido recursoCompartido;
    String nombre;

    public EscritorLetras(String nombre, RecursoCompartido recursoCompartido) {
        this.nombre = nombre;
        this.recursoCompartido = recursoCompartido;
    }

    @Override
    public void run() {

        while (recursoCompartido.letra <= 'Z') {
            synchronized (recursoCompartido) {

                if (recursoCompartido.getIsLetter() == true) { //la última es letra espera

                    try {
                        recursoCompartido.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EscritorLetras.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    recursoCompartido.escribeLetra();
                    recursoCompartido.notify();
                }

            }
        }
    }
}
