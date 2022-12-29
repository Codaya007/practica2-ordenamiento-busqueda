/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Utilidades;

import Constantes.Gatos;
import Controlador.Listas.ListaEnlazada;
import Modelo.Gato;

/**
 *
 * @author vivic
 */
public class Utilidades {

    public static ListaEnlazada<Gato> generarDatosGatosAleatorios(Integer numeroDatos) {
        ListaEnlazada<Gato> result = new ListaEnlazada();
        result.setSize(numeroDatos);

        for (int i = 0; i < numeroDatos; i++) {
            // Creo un nuevo gato aleatorio
            Gato nuevoGato = new Gato();
            nuevoGato.setColor(Gatos.colores[(int) generarNumeroAleatorio(0, Gatos.colores.length - 1)]);
            nuevoGato.setSexo(Gatos.sexos[(int) generarNumeroAleatorio(0, Gatos.sexos.length - 1)]);
            nuevoGato.setRaza(Gatos.razas[(int) generarNumeroAleatorio(0, Gatos.razas.length - 1)]);
            nuevoGato.setFechaNacimiento(generarFecha());

            if ("Hembra".equals(nuevoGato.getSexo())) {
                nuevoGato.setNombre(Gatos.nombresHembras[(int) generarNumeroAleatorio(0, Gatos.nombresHembras.length - 1)]);
            } else {
                nuevoGato.setNombre(Gatos.nombresMachos[(int) generarNumeroAleatorio(0, Gatos.nombresMachos.length - 1)]);
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
        return (float) (Math.random() * maximo + minimo);
    }

    public static String generarFecha() {
        String date = "";

        // Primero ponemos el dia
        date += (int) generarNumeroAleatorio(1, 31);
        date += "/";

        // Luego el mes
        date += (int) generarNumeroAleatorio(1, 12);
        date += "/";

        // Luego el año
        date += (int) generarNumeroAleatorio(2015, 2022);

        return date;
    }
}
