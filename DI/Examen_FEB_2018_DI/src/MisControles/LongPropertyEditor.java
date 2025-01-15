package MisControles;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author andres
 */
public class LongPropertyEditor extends PropertyEditorSupport {

    public TextFieldPanel longitudPanel = null;

    public LongPropertyEditor() {
        this.longitudPanel = new TextFieldPanel();
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);

        if (value instanceof TextFieldExtended) {
            longitudPanel.setTextField((TextFieldExtended) value); // Conecta el panel con el componente
        }
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return this.longitudPanel;
    }

    @Override
    public String getJavaInitializationString() {
        String longitud = (String) this.getValue();
        return longitud;
    }

    @Override
    public Object getValue() {
        return this.longitudPanel.getSelectedValue();
    }

}
