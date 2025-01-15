/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planificador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.EventListener;
import java.util.EventObject;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Alejandro Romero Abadía
 */
public class Planificador extends JLabel implements Serializable, ActionListener {
    
    private boolean formatoFecha = true;    // True para formato de fecha grande (01/01/2024), false para formato de fecha corto (01/01/24).
    private boolean formatoHora = true;     // True para formato de hora de 24 horas (23:00), false para formato de hora de 12 horas (11:00 FM).
    private Alarma alarma;                  // Propiedad Alarma.
    Timer t;                                // Temporizador.
    private ActivarAlarma listener;         // Oyente para programar el evento de la alarma.
    // Constantes para formatear la etiqueta de la alarma según de haya seleccionado los formatos de fecha y hora.
    private final DateTimeFormatter FECHAGRANDE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter FECHAPEQUENA = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter HORA24 = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter HORA12 = DateTimeFormatter.ofPattern("hh:mm a");
    
    
    // Constructor público sin argumentos.
    public Planificador() {
        
        this.alarma = new Alarma(LocalDateTime.now(), "");  // Genera una fecha inicial que es la actual.
        super.setFont(new Font("Times New Roman", Font.BOLD, 18));  // Configurar fuente de la etiqueta.
        actualizarEtiqueta();           // Actualiza la etiqueta de la alarma con los datos de la propiedad Alarma.

    }

    public boolean isFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(boolean formatoFecha) {
        this.formatoFecha = formatoFecha;
        actualizarEtiqueta();   // Actualiza la etiqueta de la alarma con los datos de la propiedad Alarma.
    }

    public boolean isFormatoHora() {
        return formatoHora;
    }

    public void setFormatoHora(boolean formatoHora) {
        this.formatoHora = formatoHora;
        actualizarEtiqueta();   // Actualiza la etiqueta de la alarma con los datos de la propiedad Alarma.
    }

    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        
        if(t != null){
            setActivo(false);   // Si ya existe una alarma programada se detiene el temporizador.
        }
        
        this.alarma = alarma;
        actualizarEtiqueta();   // Actualiza la etiqueta de la alarma con los datos de la propiedad Alarma.
        
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaAlarma = this.alarma.getFechaHora();
        long diferenciaTiempo = ChronoUnit.MILLIS.between(fechaActual, fechaAlarma);    // Se comprueba la diferencia entre la fecha actual y la que se ha programado para la alarma.
        t = new Timer((int)diferenciaTiempo, this);     // Temporizador que se dispara cuando pasa el tiempo entre la fecha actual y la programada.
        setActivo(true);    // Activar el temporizador.
    }

    // Activa o detiene el temporizador.
    public void setActivo(boolean valor){
        if(valor == true){
            t.start();
        }else{
            t.stop();
        }
    }
    
    // Comprueba si el temporizador está activo.
    public boolean getActivo() {

        return t.isRunning();

    }
    
    // Actualiza la etiqueta de la alarma según los datos de la alarma.
    private void actualizarEtiqueta(){
        
        String textoFecha = "Alarma: ";
        
        if(formatoFecha){
            
            textoFecha += this.alarma.getFechaHora().format(FECHAGRANDE);
        }else{
            
            textoFecha += this.alarma.getFechaHora().format(FECHAPEQUENA);
        }
        
        textoFecha += " - ";
        
        if(formatoHora){
            
            textoFecha += this.alarma.getFechaHora().format(HORA24);
        }else{
            
            textoFecha += this.alarma.getFechaHora().format(HORA12);
        }
        
        super.setText(textoFecha);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        t.stop();   // Cuando se acaba el tiempo del temporizador se detiene.
        listener.capturarActivarAlarma(new AlarmaEvent(this));  // Genera el evento de la alarma.

    }
    
    // Clase de tipo evento (EventObject) que servirá para generar el evento.
    public class AlarmaEvent extends EventObject{
        public AlarmaEvent(Object source){
            super(source);
        }
    }
    
    // Interfaz para añadir los oyentes a otras clases.
    public interface ActivarAlarma extends EventListener{
        void capturarActivarAlarma(AlarmaEvent evt);
    }
    
    // Se registra un oyente.
    public void addActivarAlarma(ActivarAlarma listener){
        this.listener = listener;
    }
    
    // Se elimina un oyente.
    public void removeActivarAlarma(ActivarAlarma listener){
        this.listener = null;
    }
    
}
