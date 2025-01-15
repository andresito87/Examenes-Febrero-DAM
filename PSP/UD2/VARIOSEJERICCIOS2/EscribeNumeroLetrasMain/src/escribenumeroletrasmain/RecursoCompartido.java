package escribenumeroletrasmain;

public class RecursoCompartido {

    int numero;
    char letra;
    boolean isLetter;

    public RecursoCompartido() {
        numero = 1;
        letra = 'A';
        isLetter = false; //la última
    }

    public void escribeLetra() {

        System.out.println("Método escribeLetra: " + letra);
        letra++;
        isLetter = true;
    }

    public void escribeNumero() {
        System.out.println("Método escribeNumero: " + numero);
        numero++;
        isLetter = false;

    }

    public char getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getIsLetter() {
        return isLetter;
    }

}
