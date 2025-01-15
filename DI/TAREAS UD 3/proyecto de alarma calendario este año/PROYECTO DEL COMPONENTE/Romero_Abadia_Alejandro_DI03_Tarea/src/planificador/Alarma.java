/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planificador;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Alejandro Romero Abadía
 */
public class Alarma implements Serializable{
    
    private LocalDateTime fechaHora;
    private String tarea;

    
    public Alarma(LocalDateTime fechaHora, String tarea) {
        this.fechaHora = fechaHora;
        this.tarea = tarea;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }
    
    
    
}
