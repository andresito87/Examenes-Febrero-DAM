/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

/**
 *
 * @author Usuario
 */
public class Ejercicio4 {
    public static void main(String[] arg){
        
    }
    
    public static String traducir(int num) {
        String nombre;
        String[][] arrayNombresNumeros = {
        {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"},
        {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"},
        {"veinte", "veintiuno", "veintidós", "veintitrés", "veinticuatro", "veinticinco", "veintiséis", "veintisiete", "veintiocho", "veintinueve"},
        {"treinta"},
        {"cuarenta"},
        {"cincuenta"},
        {"sesenta"},
        {"setenta"},
        {"ochenta"},
        {"noventa"}};
        int decena;
        int unidad;
        
        decena = num / 10;
        unidad = num % 10;
        if (decena < 3) {    
            nombre = arrayNombresNumeros[decena][unidad];
        } else {
            if (unidad == 0) {
                nombre = arrayNombresNumeros[decena][0];
            } else {
                nombre = arrayNombresNumeros[decena][0] + " y " + arrayNombresNumeros[0][unidad];
            }

        }

        return nombre;
    }
}
