package validaciontextfield;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidarAlGuardar {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Validación rápida");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        // Campos de texto
        JLabel label1 = new JLabel("Número (5 dígitos):");
        JTextField textField1 = new JTextField(10);

        JLabel label2 = new JLabel("Alfanumérico (3-8 caracteres):");
        JTextField textField2 = new JTextField(10);

        JButton saveButton = new JButton("Guardar");
        
        textField1.setName("Número");
        textField2.setName("Alfanumérico");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder errores = new StringBuilder();

                // Validar cada campo y agregar errores si hay
                validarNumeros(textField1, 5, errores);
                validarAlfanumerico(textField2, 3, 8, errores);

                // Si hay errores, mostrarlos todos juntos
                if (errores.length() > 0) {
                    JOptionPane.showMessageDialog(frame, errores.toString(), "Errores de validación", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Si todo es válido
                    JOptionPane.showMessageDialog(frame, "Datos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Agregar al frame
        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(new JLabel()); // Espacio vacío
        frame.add(saveButton);

        frame.setVisible(true);
    }

    // ✅ Método genérico para validar números con longitud exacta y agregar error a StringBuilder
    private static void validarNumeros(JTextField field, int longitudExacta, StringBuilder errores) {
        String text = field.getText();
        if (!text.matches("\\d{" + longitudExacta + "}")) {
            errores.append("❌ Error en '").append(field.getName()).append("': Debe tener exactamente ")
                    .append(longitudExacta).append(" números.\n");
        }
    }

    // ✅ Método genérico para validar alfanumérico con longitud mínima y máxima y agregar error a StringBuilder
    private static void validarAlfanumerico(JTextField field, int min, int max, StringBuilder errores) {
        String text = field.getText();
        if (!text.matches("[a-zA-Z0-9]{" + min + "," + max + "}")) {
            errores.append("❌ Error en '").append(field.getName()).append("': Debe tener entre ")
                    .append(min).append(" y ").append(max).append(" caracteres alfanuméricos.\n");
        }
    }
}
