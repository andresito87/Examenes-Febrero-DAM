package MisControles;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class TextFieldExtended extends JTextField {

    private int longitud = 7;
    private Color emptyColor = new Color(240, 180, 180);

    public TextFieldExtended() {
        this.setColumns(longitud);
        this.setBackground(emptyColor);

        // Limitar longitud y manejar eventos
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (getText().length() >= longitud) {
                    e.consume(); // No permite más caracteres
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                verificarTexto();
            }
        });
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
        this.setColumns(longitud);
    }

    private void verificarTexto() {
        if (this.getText().isEmpty()) {
            this.setBackground(emptyColor);
        } else {
            this.setBackground(Color.WHITE);
        }
    }
}
