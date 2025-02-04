package slidervolversion2;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author andres
 */
public class SliderVolComponente extends JSlider implements Serializable {

    public RangosVolumen rangos;
    public Color color1, color2, color3;
    protected RangoAlcanzadoListener listener;

    public SliderVolComponente() {
        // Definimos los intervalos 
        rangos = new RangosVolumen(25, 50, 75);

        // estilos extra a la barra de progreso
        this.setFont(new Font("Arial", Font.BOLD, 28)); // Cambia el estilo del texto

        // Añadimos ChangeListener y redefinimos el método stateChanged
        this.addChangeListener((ChangeEvent e) -> {
            comprobarZona();
        });
    }

    public RangosVolumen getRangosVolumen() {
        return rangos;
    }

    public void setRangosVolumen(RangosVolumen rangos) {
        this.rangos = rangos;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public Color getColor3() {
        return color3;
    }

    public void setColor3(Color color3) {
        this.color3 = color3;
    }

    public class RangoAlcanzado extends java.util.EventObject {

        public RangoAlcanzado(Object source) {
            super(source);
        }
    }

    public interface RangoAlcanzadoListener extends EventListener {

        void zonaVolumenBajoAlcanzada(RangoAlcanzado rangoAlcanzado);

        void zonaVolumenMedioAlcanzada(RangoAlcanzado rangoAlcanzado);

        void zonaVolumenAltoAlcanzada(RangoAlcanzado rangoAlcanzado);
    }

    public void addRangoAlcanzadoListener(RangoAlcanzadoListener listener) {
        this.listener = listener;
    }

    public void removeRangoAlcanzadoListener(RangoAlcanzadoListener listener) {
        this.listener = null;
    }

    private int zonaAnterior = -1; // Zona inicial, fuera de rango

    public void comprobarZona() {
        int valorActual = this.getValue(); // Obtén el valor actual del componente

        // Determinar la zona actual basándonos en el valor actual
        int zonaActual;
        if (valorActual < this.rangos.getRango1()) {
            zonaActual = 1; // Zona Bajo
            this.setOpaque(true);
            this.setBackground(this.getColor1());
        } else if (valorActual < this.rangos.getRango2()) {
            zonaActual = 2; // Zona Medio
            this.setOpaque(true);
            this.setBackground(this.getColor2());
        } else {
            zonaActual = 3; // Zona Alto
            this.setOpaque(true);
            this.setBackground(this.getColor3());
        }

        // Verificar si la zona ha cambiado
        if (zonaActual != zonaAnterior) {
            // Notificar el cambio de zona dependiendo de si estamos subiendo o bajando
            if (listener != null) {
                switch (zonaActual) {
                    case 1:
                        listener.zonaVolumenBajoAlcanzada(new RangoAlcanzado(this));
                        break;
                    case 2:
                        listener.zonaVolumenMedioAlcanzada(new RangoAlcanzado(this));
                        break;
                    case 3:
                        listener.zonaVolumenAltoAlcanzada(new RangoAlcanzado(this));
                        break;
                }
            }
        }

        // Actualizar la zona anterior
        zonaAnterior = zonaActual;
    }

}
