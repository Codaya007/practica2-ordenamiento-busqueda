/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author vivic
 */
public enum Ordenamientos {
    SHELL("Shell"), QUICK("Quick Sort");

    private String nombre;
    
    //Añadir un onstructor
    Ordenamientos(String nb) {
        nombre = nb;
    }

    //Añadir un método
    public String getNombre() {
        return nombre;
    }
}
