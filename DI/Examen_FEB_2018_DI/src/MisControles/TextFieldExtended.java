package MisControles;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.JTextField;

/**
 *
 * @author andres
 */
public class TextFieldExtended extends JTextField implements Serializable {

    public int longitud;
    public Color color;
    public LongitudModificadaListener listener;

    public TextFieldExtended() {
        this.setPreferredSize(new Dimension(longitud, 24));
        this.setSize(new Dimension(longitud, 24));
        comprobarEstaVacio();
        this.revalidate();
        this.repaint();
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        System.out.println("Se cambia la longitud");
        this.longitud = longitud;
        this.setSize(new Dimension(longitud, 24));
        this.setColumns(longitud); // Cambia el número de columnas visibles
        this.setPreferredSize(new Dimension(longitud, 24));
        this.invalidate();         // Marca el componente como no válido
        this.revalidate();         // Solicita una revalidación del diseño
        this.repaint();            // Redibuja el componente
        if (this.getParent() != null) {
            this.getParent().revalidate(); // Revalida el diseño del contenedor
            this.getParent().repaint();    // Redibuja el contenedor
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public class LongitudModificada extends java.util.EventObject {

        public LongitudModificada(Object source) {
            super(source);
        }

    }

    public interface LongitudModificadaListener extends EventListener {

        void cambiarLongitud(LongitudModificada longitud);
    }

    public void addLongitudModificadaListener(LongitudModificadaListener listener) {
        this.listener = listener;
    }

    public void removeLongitudModificadaListener(LongitudModificadaListener listener) {
        this.listener = null;
    }

    private void comprobarEstaVacio() {
        String texto = this.getText();
        if (texto != null && !texto.isEmpty()) {
            this.setBackground(Color.WHITE);
            this.setColor(Color.WHITE);
            if (listener != null) {
                this.listener.cambiarLongitud(new LongitudModificada(this));
            }
        } else {
            this.setBackground(new Color(240, 180, 180));
            this.setColor(new Color(240, 180, 180));
            if (listener != null) {
                this.listener.cambiarLongitud(new LongitudModificada(this));
            }
        }
        this.revalidate();
        this.repaint();
    }

}
