/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Listas.Excepciones.ListaNullException;
import Controlador.Listas.Excepciones.PosicionException;
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
    private ListaEnlazada<Float> datosOrdenados = new ListaEnlazada();
    private Ordenamientos ordenamiento = Ordenamientos.SHELL;
    private Ordenes orden = Ordenes.Ascendente;
    private Long duracionOrdenamiento = null;

    public Integer getNumeroDatos() {
        return numeroDatos;
    }

    public void setNumeroDatos(Integer numeroDatos) {
        if (numeroDatos < 0 || numeroDatos > 20000) {
            throw new Error("Inserte un número mayor a 0 y menor o igual a 20000");
        }

        this.numeroDatos = numeroDatos;
    }

    public ListaEnlazada<Float> getDatos() {
        return datos;
    }

    public void volverAGenerarDatos() {
        this.datos = Utilidades.generarNumerosFlotantesAleatorios(this.numeroDatos);
    }

    public Ordenamientos getOrdenamiento() {
        return ordenamiento;
    }

    public void setOrdenamiento(Ordenamientos ordenamiento) {
        this.ordenamiento = ordenamiento;
    }

    public ListaEnlazada<Float> getDatosOrdenados() {
        return datosOrdenados;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
        this.orden = orden;
    }

    public long ordenarDatos() throws ListaNullException, PosicionException {
        long startTime = System.nanoTime();

        switch (ordenamiento) {
            case SHELL:
                datosOrdenados = datos.shell(orden);
                System.out.println("La lista obtenida de aplicar el metodo shell: ");
                datosOrdenados.imprimir();
                break;
            case QUICK:
                datosOrdenados = datos.quickSort(orden);
                break;
            default:
                throw new Error("Los ordenamiento permitidos son: " + Ordenamientos.SHELL + " y " + Ordenamientos.QUICK);
        }

        long endTime = System.nanoTime();

        long difference = endTime - startTime;

        System.out.println(this.ordenamiento + ": Ha tardado " + difference + " nanosegundos.");

        this.duracionOrdenamiento = difference;
        return difference;
    }

    public String informacionOrdenamiento() {
        if (this.duracionOrdenamiento == null) {
            throw new Error("Primero realice un ordenamiento de los datos para poder obtener la información.");
        }
        String result = "Los " + this.numeroDatos + " datos se han ordenado mediante el método " + this.ordenamiento;
        result += "\nEl ordenamiento se ha realizado de forma " + this.orden.toString();
        result += "\n\nLa ejecución ha durado " + this.duracionOrdenamiento + " ns";

        return result;
    }

}
