package customtext;

import java.beans.*;

public class CustomTextfieldBeanInfo extends SimpleBeanInfo {

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor longitud = new PropertyDescriptor("longitud", CustomTextfield.class);
            longitud.setPropertyEditorClass(LongitudPropertyEditor.class); // Vincula el editor
            return new PropertyDescriptor[]{longitud};
        } catch (IntrospectionException e) {
            return null;
        }
    }
}
