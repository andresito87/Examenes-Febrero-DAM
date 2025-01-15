package MisControles;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

public class LongPropertyEditor extends PropertyEditorSupport {

    public TextFieldPanel panel;

    public LongPropertyEditor() {
        this.panel = new TextFieldPanel();
    }

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public Component getCustomEditor() {
        return panel;
    }

    @Override
    public String[] getTags() {
        return new String[]{"1", "5", "10", "15", "20", "25", "30"};
    }

    @Override
    public String getJavaInitializationString() {
        return Integer.toString((Integer) getValue());
    }

    @Override
    public Object getValue() {
        return panel.getSelectedValue();
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            panel.jSliderLongitud.setValue((Integer) value);
            super.setValue(value);
        } else if (value instanceof String) {
            try {
                int intValue = Integer.parseInt((String) value);
                panel.jSliderLongitud.setValue(intValue);
                super.setValue(intValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid value: " + value);
            }
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + value);
        }
    }

}
