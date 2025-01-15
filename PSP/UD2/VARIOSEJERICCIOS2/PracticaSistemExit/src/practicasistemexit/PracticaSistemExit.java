
package practicasistemexit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PracticaSistemExit {

   
    public static void main(String[] args) throws IOException {
        
        String programaAEjecutar = null;
        Process proceso;
        int valueExit = 0;
        
        
        if (args.length > 0){
            programaAEjecutar = args[0];
       
        proceso = lanzaProceso(programaAEjecutar);
            try {
                valueExit = proceso.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(PracticaSistemExit.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.exit(1);
        }
        
        if (args.length == 0 ){
            
            EscribeErrorArchivo("No se ha introducido ningún programa...");
            
            System.exit(-1);
        }
        
        
        
    }
    
    public static Process lanzaProceso(String comandoAEjecutar){
        String comando = comandoAEjecutar;
        Process proceso = null;
        try {
            proceso = Runtime.getRuntime().exec("cmd /c " + comando);
        } catch (IOException ex) {
            System.out.println("Error de salida de datos");
            Logger.getLogger(PracticaSistemExit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return proceso;
    }
    
    public static void EscribeErrorArchivo(String mensajeError) throws IOException{
        File f = new File("ErroresPrograma.txt");
       
        if (!f.exists())
            f.createNewFile();
        
         FileWriter fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        
        pw.write(mensajeError);
        fw.close();
        pw.close();
        
        
    }
    
}
