package clinica_veterinaria;

import javax.swing.ImageIcon;

/**
 *
 * @author andres
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // creación del menu principal
        FormularioPrincipal principal = new FormularioPrincipal();
        // Cargar la imagen del icono desde los recursos
        ImageIcon icono = new ImageIcon(Main.class.getResource("/imagenes/icono_veterinaria.png"));
        // Establecer el icono en el JFrame
        principal.setIconImage(icono.getImage());
        // se muestra visible
        principal.setVisible(true);
    }
    
}
