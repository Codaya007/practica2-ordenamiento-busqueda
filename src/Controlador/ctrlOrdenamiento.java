/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Listas.ListaEnlazada;
import Controlador.Utilidades.Utilidades;
import Modelo.Ordenamientos;
import Modelo.Ordenes;

/**
 *
 * @author vivic
 */
public class ctrlOrdenamiento {

    private Integer numeroDatos = 20000;
    private ListaEnlazada<Float> datos = Utilidades.generarNumerosFlotantesAleatorios(numeroDatos);
    private Ordenamientos ordenamiento = Ordenamientos.SHELL;
    private Ordenes orden = Ordenes.Ascendente;

    public Integer getNumeroDatos() {
        return numeroDatos;
    }

    public void setNumeroDatos(Integer numeroDatos) {
        if (numeroDatos < 0 || numeroDatos >= 20000) {
            throw new Error("Inserte un número mayor a 0 y menor o igual a 20000");
        }

        this.numeroDatos = numeroDatos;
    }

    public ListaEnlazada<Float> getDatos() {
        return datos;
    }

    public void volverAGenerarDatos(ListaEnlazada<Float> datos) {
        this.datos = Utilidades.generarNumerosFlotantesAleatorios(this.numeroDatos);
    }

    public Ordenamientos getOrdenamiento() {
        return ordenamiento;
    }

    public void setOrdenamiento(Ordenamientos ordenamiento) {
        this.ordenamiento = ordenamiento;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
        this.orden = orden;
    }

    public long ordenarDatos() {
        long startTime = System.nanoTime();
        
        switch (ordenamiento) {
            case SHELL:
                System.out.println("Shell");
                break;
            case QUICK:
                System.out.println("Quick");
                break;
            default:
                throw new Error("Los ordenamiento permitidos son: " + Ordenamientos.SHELL.getNombre() + " y " + Ordenamientos.QUICK.getNombre());
        }

        long endTime = System.nanoTime();
        
        long difference = endTime - startTime;

        System.out.println(this.ordenamiento + ": Ha tardado " + difference + " nanosegundos.");
        
        return difference;
    }

}
