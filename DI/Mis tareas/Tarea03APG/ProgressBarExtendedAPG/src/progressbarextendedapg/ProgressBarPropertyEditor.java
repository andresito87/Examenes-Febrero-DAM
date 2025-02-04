package progressbarextendedapg;

import java.awt.Component;
import java.beans.*;

/**
 * Clase que implementa un editor de propiedades personalizado para la clase
 * {@code Rangos}.
 * <p>
 * La clase {@code ProgressBarPropertyEditor} extiende
 * {@link PropertyEditorSupport} y proporciona un editor visual personalizado
 * para ajustar los valores de rango de una barra de progreso en un entorno de
 * desarrollo visual.
 * </p>
 *
 * <h2>Características principales:</h2>
 * <ul>
 * <li>Permite editar los valores de {@code Rangos} mediante una interfaz
 * gráfica.</li>
 * <li>Proporciona compatibilidad con un editor personalizado (panel de
 * edición).</li>
 * <li>Genera automáticamente el código necesario para inicializar los valores
 * editados.</li>
 * </ul>
 *
 * <p>
 * Esta clase es especialmente útil en herramientas de diseño visual, como
 * NetBeans o IntelliJ IDEA, donde se necesita un editor para ajustar
 * propiedades complejas visualmente.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 * <p>
 * Desarrollo Interfaces Desarrollo de aplicaciones multiplataforma 2024/2025
 * </p>
 */
public class ProgressBarPropertyEditor extends PropertyEditorSupport {

    /**
     * Panel personalizado que actúa como editor gráfico para los rangos.
     */
    public ProgressBarPanel rangoPanel = null;

    /**
     * Constructor de la clase {@code ProgressBarPropertyEditor}.
     * <p>
     * Inicializa un nuevo panel de edición ({@link ProgressBarPanel}) que será
     * utilizado para ajustar los valores de rango.
     * </p>
     */
    public ProgressBarPropertyEditor() {
        this.rangoPanel = new ProgressBarPanel();
    }

    /**
     * Indica si este editor soporta un editor personalizado.
     *
     * @return {@code true} siempre, ya que se utiliza un editor personalizado.
     */
    @Override
    public boolean supportsCustomEditor() {
        return true; // siempre devuelve true si usamos un editor personalizado
    }

    /**
     * Devuelve el componente gráfico que actúa como editor personalizado.
     *
     * @return el objeto {@link ProgressBarPanel} que representa el editor
     * personalizado.
     */
    @Override
    public Component getCustomEditor() {
        return this.rangoPanel; // devuelve el objeto ProgressBarPanel que será nuestro editor
    }

    /**
     * Genera la cadena de inicialización en Java para los valores editados.
     * <p>
     * Este método devuelve el código Java necesario para inicializar un objeto
     * {@link Rangos} con los valores ajustados en el editor.
     * </p>
     *
     * @return una cadena que representa la inicialización del objeto
     * {@code Rangos}.
     */
    @Override
    public String getJavaInitializationString() {
        Rangos rangos = (Rangos) getValue();
        // devuelve la cadena necesaria para generar el editor de rangos
        return ("new progressbarextendedapg.Rangos(" + rangos.getRango1() + ", " + rangos.getRango2() + ")");
    }

    /**
     * Obtiene el valor editado en el editor personalizado.
     * <p>
     * Este método recupera un objeto {@link Rangos} con los valores
     * seleccionados por el usuario en el editor gráfico.
     * </p>
     *
     * @return un objeto {@link Rangos} que contiene los valores seleccionados.
     */
    @Override
    public Object getValue() {
        return this.rangoPanel.getSelectedValue(); // devuelve el objeto Rangos con los valores inializados
    }

}
