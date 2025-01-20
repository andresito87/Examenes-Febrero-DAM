package slidervolapg;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author andres
 */
public class SliderVolComponente extends JProgressBar implements Serializable {

    public RangosVolumen rangos;
    public Color color1, color2, color3, color4;
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

    public Color getColor4() {
        return color4;
    }

    public void setColor4(Color color4) {
        this.color4 = color4;
    }

    public class RangoAlcanzado extends java.util.EventObject {

        public RangoAlcanzado(Object source) {
            super(source);
        }
    }

    public interface RangoAlcanzadoListener extends EventListener {

        void zonaVolumenMuyBajoAlcanzada(RangoAlcanzado rangoAlcanzado);

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
            zonaActual = 1; // Zona Muy Bajo
            this.setForeground(this.color1);
        } else if (valorActual < this.rangos.getRango2()) {
            zonaActual = 2; // Zona Bajo
            this.setForeground(this.color2);
        } else if (valorActual < this.rangos.getRango3()) {
            zonaActual = 3; // Zona Medio
            this.setForeground(this.color3);
        } else {
            zonaActual = 4; // Zona Alto
            this.setForeground(this.color4);
        }

        // Verificar si la zona ha cambiado
        if (zonaActual != zonaAnterior) {
            // Notificar el cambio de zona dependiendo de si estamos subiendo o bajando
            if (listener != null) {
                switch (zonaActual) {
                    case 1:
                        listener.zonaVolumenMuyBajoAlcanzada(new RangoAlcanzado(this));
                        break;
                    case 2:
                        listener.zonaVolumenBajoAlcanzada(new RangoAlcanzado(this));
                        break;
                    case 3:
                        listener.zonaVolumenMedioAlcanzada(new RangoAlcanzado(this));
                        break;
                    case 4:
                        listener.zonaVolumenAltoAlcanzada(new RangoAlcanzado(this));
                        break;
                }
            }
        }

        // Actualizar la zona anterior
        zonaAnterior = zonaActual;
    }

}
