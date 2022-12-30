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
import Modelo.Busquedas;
import Modelo.Gato;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author vivic
 */
public class ctrlBusqueda {

    private Integer numeroDatos = 20000;
    private ListaEnlazada<Gato> datos = Utilidades.generarDatosGatosAleatorios(numeroDatos);
    private ListaEnlazada<Gato> datosEncontrados;
    private Busquedas tipoBusqueda = Busquedas.Lineal;
    private Long duracionBusqueda = null;
    private String ultimaPalabraBuscada;
    
    
    // public ctrlBusqueda(){
    //     cargar("Gatos");
    // }

    public Integer getNumeroDatos() {
        return numeroDatos;
    }

    public void setNumeroDatos(Integer numeroDatos) {
        if (numeroDatos < 0 || numeroDatos > 20000) {
            throw new Error("Inserte un número mayor a 0 y menor o igual a 20000");
        }

        this.numeroDatos = numeroDatos;
    }

    public ListaEnlazada<Gato> getDatos() {
        return datos;
    }

    public void volverAGenerarDatos() throws IOException, ListaNullException, PosicionException {
        this.datos = Utilidades.generarDatosGatosAleatorios(this.numeroDatos);
        guardar(datos, "Gatos");
    }

    public ListaEnlazada<Gato> getDatosEncontrados() {
        return datosEncontrados;
    }

    public long buscarDato(String query) throws ListaNullException, PosicionException, IOException {
        long startTime = System.nanoTime();

        switch (tipoBusqueda) {
            case Binaria:
                datosEncontrados = datos.binarySearch(query);
                break;
            case Lineal:
                datosEncontrados = datos.linealSearch(query);
                break;
            default:
                throw new Error("Los tipos de búsqueda permitidas son: " + Busquedas.Binaria + " y " + Busquedas.Lineal);
        }

        guardar(datosEncontrados, "GatosEncontrados");
        long endTime = System.nanoTime();

        long difference = endTime - startTime;

        System.out.println(this.tipoBusqueda + ": Ha tardado " + difference + " nanosegundos.");

        this.duracionBusqueda = difference;
        this.ultimaPalabraBuscada = query;

        return difference;
    }

    public Busquedas getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(Busquedas tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public Long getDuracionBusqueda() {
        return duracionBusqueda;
    }

    // String camposPermitidos[] = {"nombre", "raza", "fechaNacimiento", "color", "sexo"};
    public String getUltimaPalabraBuscada() {
        return ultimaPalabraBuscada;
    }

    public void setUltimaPalabraBuscada(String ultimaPalabraBuscada) {
        this.ultimaPalabraBuscada = ultimaPalabraBuscada;
    }

    public String getInformacionBusqueda() {
        if (this.duracionBusqueda == null || this.ultimaPalabraBuscada == null) {
            throw new Error("Primero realice una búsqueda para poder obtener la información.");
        }

        String result = "La palabra '" + this.ultimaPalabraBuscada + "' se ha buscado en los "
                + this.numeroDatos + " gatos mediante el método " + this.tipoBusqueda;
        result += "\nSe encontraron " + this.datosEncontrados.getUltimaPosicionOcupada() + " resultados";

        result += "\n\nLa ejecución ha durado " + this.duracionBusqueda + " ns";

        return result;
    }

    public void guardar(ListaEnlazada<Gato> datos, String nombreArchivo) throws IOException, ListaNullException, PosicionException {
        Gson json = new Gson();
        Integer resultadosObtenidos = datos.getUltimaPosicionOcupada();
        Integer datosAGuardar = resultadosObtenidos <= 20 ? resultadosObtenidos : 20;

        System.out.println("Se van a guardar " + datosAGuardar + " gatos en el json " + nombreArchivo + ".json");

        Gato[] gatos = new Gato[datosAGuardar];

        for (int i = 0; i < datosAGuardar; i++) {
            gatos[i] = datos.obtener(i);
        }

        String jsons = json.toJson(gatos);
        FileWriter fw = new FileWriter(nombreArchivo + ".json");
        fw.write(jsons);
        fw.flush();
    }
    
    public final void cargar(String nombreArchivo) {
        try {
            System.out.println("Cargando data...");
            Gson json = new Gson();
            FileReader fr = new FileReader(nombreArchivo + ".json");
            StringBuilder jsons = new StringBuilder();
            // boolean isComa;
            int valor = fr.read();
            while (valor != -1) {
                jsons.append((char) valor);
                valor = fr.read();
            }
            Gato[] aux = json.fromJson(jsons.toString(), Gato[].class);
            for (int i = aux.length - 1; i >= 0; i--) {
                // insertarAlInicio estaba antes
                datos.insertarCabecera(aux[i]);
            }
        } catch (Exception e) {
            System.out.println("No se encontraron objetos guardados en el json!");
        }
    }

}
