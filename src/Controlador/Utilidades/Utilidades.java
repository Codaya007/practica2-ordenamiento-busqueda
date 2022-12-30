/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Utilidades;

import Constantes.Constantes;
import Controlador.Listas.ListaEnlazada;
import Modelo.Gato;

/**
 *
 * @author vivic
 */
public class Utilidades {

    public static <T> boolean contains(final T[] array, final T v) {
        for (final T e : array) {
            if (e == v || v != null && v.equals(e)) {
                return true;
            }
        }

        return false;
    }

    public static ListaEnlazada<Gato> generarDatosGatosAleatorios(Integer numeroDatos) {
        ListaEnlazada<Gato> result = new ListaEnlazada();
        result.setSize(numeroDatos);

        for (int i = 0; i < numeroDatos; i++) {
            // Creo un nuevo gato aleatorio
            Gato nuevoGato = new Gato();
            nuevoGato.setColor(Constantes.colores[(int) generarNumeroAleatorio(0, Constantes.colores.length)]);
            nuevoGato.setSexo(Constantes.sexos[(int) generarNumeroAleatorio(0, Constantes.sexos.length)]);
            nuevoGato.setRaza(Constantes.razas[(int) generarNumeroAleatorio(0, Constantes.razas.length)]);
            nuevoGato.setFechaNacimiento(generarFecha());

            if ("Hembra".equals(nuevoGato.getSexo())) {
                nuevoGato.setNombre(Constantes.nombresHembras[(int) generarNumeroAleatorio(0, Constantes.nombresHembras.length)]);
            } else {
                nuevoGato.setNombre(Constantes.nombresMachos[(int) generarNumeroAleatorio(0, Constantes.nombresMachos.length)]);
            }

            // Lo añado a la lista
            result.insertar(nuevoGato);
        }

        return result;
    }

    public static ListaEnlazada<Float> generarNumerosFlotantesAleatorios(Integer numeroDatos) {
        ListaEnlazada<Float> result = new ListaEnlazada();
        result.setSize(numeroDatos);
        final Integer MINIMO = 1;
        final Integer MAXIMO = 1000;

        for (int i = 0; i < numeroDatos; i++) {
            result.insertar(generarNumeroAleatorio(MINIMO, MAXIMO));
        }

        return result;
    }

    public static float generarNumeroAleatorio(Integer minimo, Integer maximo) {
        return (float) (Math.random() * (maximo - minimo) + minimo);
    }

    public static String generarFecha() {
        String date = "";
        String dia = "", mes = "", anio = "";

        // Primero ponemos el dia
        dia += (int) generarNumeroAleatorio(1, 31);

        // Luego el mes
        mes += (int) generarNumeroAleatorio(1, 12);

        // Luego el año
        anio += (int) generarNumeroAleatorio(2015, 2022);

        // System.out.println("Anio: " + anio);
        
        return dia + "/" + mes + "/" + anio;
    }
}
