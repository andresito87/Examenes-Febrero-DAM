
package escribenumeroletrasmain;


public class EscribeNumeroLetrasMain {

   
    public static void main(String[] args) {
       String nombreEscritorLetras = "escritorLetras";
       String nombreEscritorNumeros = "escritorNumeros";
       RecursoCompartido recursoCompartido = new RecursoCompartido();
       Thread EscritorLetras = new EscritorLetras(nombreEscritorLetras,recursoCompartido);
       Thread EscritorNumeros = new EscritorNumeros(nombreEscritorNumeros,recursoCompartido);
       EscritorLetras.start();
       EscritorNumeros.start();
    }
    
}
