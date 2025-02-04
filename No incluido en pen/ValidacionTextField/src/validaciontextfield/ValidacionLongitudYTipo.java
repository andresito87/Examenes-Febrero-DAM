/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciontextfield;

  import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 *
 * @author andres
 */
public class ValidacionLongitudYTipo {
  

    public static void main(String[] args) {
        JFrame frame = new JFrame("Validación de JTextField");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        // ======= Validación: Números y Longitud Exacta =======
        JLabel label1 = new JLabel("Número (5 dígitos):");
        JTextField textField1 = new JTextField(10);
        JLabel messageLabel1 = new JLabel("");

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            void validate() {
                String text = textField1.getText();
                if (text.matches("\\d{5}")) {  // Exactamente 5 dígitos
                    messageLabel1.setForeground(Color.GREEN);
                    messageLabel1.setText("Válido");
                } else {
                    messageLabel1.setForeground(Color.RED);
                    messageLabel1.setText("Debe tener 5 números");
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { validate(); }
            @Override public void removeUpdate(DocumentEvent e) { validate(); }
            @Override public void changedUpdate(DocumentEvent e) { validate(); }
        });

        // ======= Validación: Alfanumérico y Longitud Entre 3-8 =======
        JLabel label2 = new JLabel("Alfanumérico (3-8 caracteres):");
        JTextField textField2 = new JTextField(10);
        JLabel messageLabel2 = new JLabel("");

        textField2.getDocument().addDocumentListener(new DocumentListener() {
            void validate() {
                String text = textField2.getText();
                if (text.matches("[a-zA-Z0-9]{3,8}")) { // De 3 a 8 caracteres alfanuméricos
                    messageLabel2.setForeground(Color.GREEN);
                    messageLabel2.setText("Válido");
                } else {
                    messageLabel2.setForeground(Color.RED);
                    messageLabel2.setText("Debe tener 3-8 caracteres");
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { validate(); }
            @Override public void removeUpdate(DocumentEvent e) { validate(); }
            @Override public void changedUpdate(DocumentEvent e) { validate(); }
        });

        // Agregar los componentes al frame
        frame.add(label1);
        frame.add(textField1);
        frame.add(messageLabel1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(messageLabel2);

        frame.setVisible(true);
    }
}


