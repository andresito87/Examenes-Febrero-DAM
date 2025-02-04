package progressbarextendedapg;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Clase que extiende {@link JProgressBar} para proporcionar una barra de
 * progreso personalizada con rangos y colores asociados a cada zona.
 * <p>
 * La clase {@code ProgressBarExtended} permite definir rangos de valores
 * asociados a diferentes colores y notificar eventos personalizados cuando el
 * valor de la barra cambia y se alcanza un rango específico. Estos eventos se
 * manejan a través de la interfaz {@link RangoAlcanzadoListener}.
 * </p>
 *
 * <h2>Características principales:</h2>
 * <ul>
 * <li>Define tres zonas de valores mediante la clase {@link Rangos}.</li>
 * <li>Cambia el color de la barra de progreso según la zona alcanzada.</li>
 * <li>Notifica a los escuchadores registrados cuando se alcanza una nueva
 * zona.</li>
 * <li>Soporta eventos personalizados y un editor gráfico en herramientas
 * visuales.</li>
 * <li>Proporciona métodos para gestionar los colores y los límites de los
 * rangos.</li>
 * </ul>
 *
 * <p>
 * Esta clase es útil para aplicaciones en las que se necesita resaltar
 * diferentes zonas de progreso con comportamientos específicos.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 * <p>
 * Desarrollo Interfaces Desarrollo de aplicaciones multiplataforma 2024/2025
 * </p>
 */
public class ProgressBarExtended extends JProgressBar implements Serializable {

    public Rangos rangos;
    public Color color1, color2, color3;
    protected RangoAlcanzadoListener listener;

    /**
     * Constructor de la clase {@code ProgressBarExtended}.
     * <p>
     * Inicializa la barra de progreso con intervalos definidos y estilos
     * personalizados. También añade un {@link ChangeListener} para detectar
     * cambios en el estado de la barra y ejecutar acciones según la zona
     * alcanzada.
     * </p>
     */
    public ProgressBarExtended() {
        // Definimos los intervalos 
        rangos = new Rangos(50, 75);

        // estilos extra a la barra de progreso
        this.setFont(new Font("Arial", Font.BOLD, 28)); // Cambia el estilo del texto

        // Añadimos ChangeListener y redefinimos el método stateChanged
        this.addChangeListener((ChangeEvent e) -> {
            comprobarZona();
        });
    }

    /**
     * Obtiene los intervalos definidos para la barra de progreso.
     *
     * @return el objeto {@link Rangos} que contiene los límites de los
     * intervalos.
     */
    public Rangos getRangos() {
        return rangos;
    }

    /**
     * Establece nuevos intervalos para la barra de progreso.
     *
     * @param rangos el objeto {@link Rangos} que define los nuevos límites.
     */
    public void setRangos(Rangos rangos) {
        this.rangos = rangos;
    }

    /**
     * Obtiene el color asociado al primer rango de la barra de progreso.
     *
     * @return el color asociado al primer rango.
     */
    public Color getColor1() {
        return color1;
    }

    /**
     * Establece el color para el primer rango de la barra de progreso.
     *
     * @param color1 el nuevo color a asignar al primer rango.
     */
    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    /**
     * Obtiene el color asociado al segundo rango de la barra de progreso.
     *
     * @return el color asociado al segundo rango.
     */
    public Color getColor2() {
        return color2;
    }

    /**
     * Establece el color para el segundo rango de la barra de progreso.
     *
     * @param color2 el nuevo color a asignar al segundo rango.
     */
    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    /**
     * Obtiene el color asociado al tercer rango de la barra de progreso.
     *
     * @return el color asociado al tercer rango.
     */
    public Color getColor3() {
        return color3;
    }

    /**
     * Establece el color para el tercer rango de la barra de progreso.
     *
     * @param color3 el nuevo color a asignar al tercer rango.
     */
    public void setColor3(Color color3) {
        this.color3 = color3;
    }

    /**
     * Clase que representa un evento de rango alcanzado en la barra de
     * progreso.
     * <p>
     * Extiende la clase {@link java.util.EventObject} y se utiliza para
     * notificar a los escuchadores registrados que se ha alcanzado un rango
     * definido.
     * </p>
     */
    public class RangoAlcanzado extends java.util.EventObject {

        /**
         * Crea una nueva instancia del evento {@code RangoAlcanzado}.
         *
         * @param source el objeto que generó este evento (normalmente, la barra
         * de progreso).
         */
        public RangoAlcanzado(Object source) {
            super(source);
        }
    }

    /**
     * Interfaz que define los métodos necesarios para escuchar eventos de rango
     * alcanzado.
     * <p>
     * Los objetos que implementen esta interfaz pueden ser registrados como
     * escuchadores y serán notificados cuando se alcancen los rangos
     * específicos en la barra de progreso.
     * </p>
     */
    public interface RangoAlcanzadoListener extends EventListener {

        /**
         * Método llamado cuando se alcanza la primera zona del rango.
         *
         * @param rangoAlcanzado el evento que contiene información sobre el
         * rango alcanzado.
         */
        void capturarZona1Alcanzada(RangoAlcanzado rangoAlcanzado);

        /**
         * Método llamado cuando se alcanza la segunda zona del rango.
         *
         * @param rangoAlcanzado el evento que contiene información sobre el
         * rango alcanzado.
         */
        void capturarZona2Alcanzada(RangoAlcanzado rangoAlcanzado);

        /**
         * Método llamado cuando se alcanza la tercera zona del rango.
         *
         * @param rangoAlcanzado el evento que contiene información sobre el
         * rango alcanzado.
         */
        void capturarZona3Alcanzada(RangoAlcanzado rangoAlcanzado);
    }

    /**
     * Registra un nuevo escuchador para recibir notificaciones cuando se
     * alcance el rango definido.
     *
     * @param listener el objeto que implementa la interfaz
     * {@link RangoAlcanzadoListener} que se registrará como escuchador. No
     * puede ser null.
     */
    public void addRangoAlcanzadoListener(RangoAlcanzadoListener listener) {
        this.listener = listener;
    }

    /**
     * Elimina el escuchador previamente registrado para las notificaciones de
     * rango alcanzado.
     *
     * @param listener el objeto escuchador que se desea eliminar. Si no hay un
     * escuchador registrado o el escuchador proporcionado no coincide, no
     * ocurre nada.
     */
    public void removeRangoAlcanzadoListener(RangoAlcanzadoListener listener) {
        this.listener = null;
    }

// Lógica pedida por en la tarea
//    public void comprobarZona() {
//        if (this.getValue() < this.rangos.getRango1()) {
//            this.setForeground(this.color1);
//
//            if (this.getValue() == this.rangos.getRango1() - 1) {
//                if (listener != null) {
//                    listener.capturarZona1Alcanzada(new RangoAlcanzado(this));
//                }
//            }
//        } else if (this.rangos.getRango1()<= this.getValue()
//                && this.getValue() <= this.rangos.getRango2()) {
//            this.setForeground(this.color2);
//            if (this.getValue() == this.rangos.getRango1()
//                    || this.getValue() == this.rangos.getRango2()) {
//                if (listener != null) {
//                    listener.capturarZona2Alcanzada(new RangoAlcanzado(this));
//                }
//            }
//        } else {
//            this.setForeground(this.color3);
//            if (this.getValue() == this.rangos.getRango2() + 1) {
//                if (listener != null) {
//                    listener.capturarZona3Alcanzada(new RangoAlcanzado(this));
//                }
//            }
//        }
//    }
    // método comprobar con lógica mejorada, comprueba todas las causísticas si el
    // slider se encuentra disminuyendo o aumentando su valor
    private int valorAnterior = 0; // variable para almacenar el valor anterior

    /**
     * Comprueba la zona actual de la barra de progreso y actualiza su estado en
     * función del valor actual.
     * <p>
     * Este método determina en qué rango (zona) se encuentra el valor actual de
     * la barra de progreso, actualiza el color asociado a esa zona y notifica a
     * los escuchadores registrados si se produce un cambio de zona.
     * </p>
     *
     * Funcionamiento:
     * <ul>
     * <li>Determina la zona actual según los valores definidos en
     * {@link Rangos}.</li>
     * <li>Compara la zona actual con la zona anterior para detectar
     * cambios.</li>
     * <li>Notifica a los escuchadores registrados a través de la interfaz
     * {@link RangoAlcanzadoListener} si se alcanza una nueva zona.</li>
     * <li>Cambia el color de la barra de progreso según la zona alcanzada.</li>
     * </ul>
     *
     * Comportamiento:
     * <ul>
     * <li>Zona 1: Valor menor que el límite inferior del primer rango.</li>
     * <li>Zona 2: Valor entre el primer rango y el segundo rango
     * (inclusive).</li>
     * <li>Zona 3: Valor mayor que el límite superior del segundo rango.</li>
     * </ul>
     *
     * @see Rangos
     * @see RangoAlcanzadoListener
     */
    public void comprobarZona() {
        int valorActual = this.getValue(); // obtenemos el valor actual

        // averiguamos la zona actual según el valor actual
        int zonaActual;
        if (valorActual < this.rangos.getRango1()) {
            this.setForeground(this.color1);
            zonaActual = 1;
        } else if (valorActual <= this.rangos.getRango2()) {
            this.setForeground(this.color2);
            zonaActual = 2;
        } else {
            this.setForeground(this.color3);
            zonaActual = 3;
        }

        // averiguamos la zona anterior según el valor anterior
        int zonaAnterior;
        if (valorAnterior < this.rangos.getRango1()) {
            zonaAnterior = 1;
        } else if (valorAnterior <= this.rangos.getRango2()) {
            zonaAnterior = 2;
        } else {
            zonaAnterior = 3;
        }

        // verificamos el cambio de zona y en qué dirección
        if (zonaActual != zonaAnterior) {
            if (zonaActual > zonaAnterior) {
                // si la zona actual es mayor, significa que estamos subiendo
                if (zonaActual == 2 && valorAnterior != 0) {
                    this.setForeground(this.color2);
                    if (listener != null) {
                        listener.capturarZona2Alcanzada(new RangoAlcanzado(this));
                    }
                } else if (zonaActual == 3 && valorAnterior != 0) {
                    this.setForeground(this.color3);
                    if (listener != null) {
                        listener.capturarZona3Alcanzada(new RangoAlcanzado(this));
                    }
                }
            } else {
                // si la zona actual es menor, significa que estamos bajando
                if (zonaActual == 2 && valorAnterior != 0) {
                    this.setForeground(this.color2);
                    if (listener != null) {
                        listener.capturarZona2Alcanzada(new RangoAlcanzado(this));
                    }
                } else if (zonaActual == 1 && valorAnterior != 0) {
                    this.setForeground(this.color1);
                    if (listener != null) {
                        listener.capturarZona1Alcanzada(new RangoAlcanzado(this));
                    }
                }
            }
        }

        // actualizamos el valor anterior para la próxima comprobación
        valorAnterior = valorActual;
    }

//    // aunque no se pida me resulta más ilustrativo cambiar el color de la barra, en lugar del texto
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//
//        // Dibuja el fondo (parte vacía) en LIGHT_GRAY
//        g2.setColor(Color.LIGHT_GRAY);
//        g2.fillRect(0, 0, getWidth(), getHeight());
//
//        // Dibuja la parte llena de la barra en el color asignado a foreground
//        int barWidth = (int) (getWidth() * getPercentComplete());
//        g2.setColor(getForeground());
//        g2.fillRect(0, 0, barWidth, getHeight());
//
//        // Configuración de la fuente y el color del texto
//        g2.setFont(new Font("Arial", Font.BOLD, 28));  // Establece la fuente
//        g2.setColor(Color.BLACK);  // Color de la fuente en negro
//
//        // Texto que muestra el valor y porcentaje
//        String text = (int) (getPercentComplete() * 100) + "%";
//        FontMetrics fm = g2.getFontMetrics();
//        int textWidth = fm.stringWidth(text);
//        int textHeight = fm.getAscent();
//
//        // Dibuja el texto en el centro de la barra
//        g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2);
//    }
}
