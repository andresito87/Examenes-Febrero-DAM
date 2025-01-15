package appcomprobadorpalindromos;

/**
 *
 * @author Roi Iglesias
 */
public class AppComprobadorPalindromos {

    public static void main(String[] args) {

        String palabra;
        if (args.length == 1) {
            palabra = args[0];

            if (esPalindromo(palabra)) {
                System.out.println("La palabra " + palabra + " es palindromo");
            } else {
                System.out.println("La palabra " + palabra + " no es palindromo");
            }
        } else if (args.length == 0){
            System.out.println("La cadena esta vacia");
        }

    }

    public static boolean esPalindromo(String cadena) {

        cadena = cadena.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
                .replace("ú", "u").replace(" ", "").replace(".", "").replace(",", "");

        String invertida = new StringBuilder(cadena).reverse().toString();
        return invertida.equals(cadena);
    }

}
