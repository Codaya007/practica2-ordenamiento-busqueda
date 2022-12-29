/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Utilidades;

import Modelo.Busquedas;
import Modelo.Ordenamientos;
import Modelo.Ordenes;
import javax.swing.JComboBox;

/**
 *
 * @author vivic
 */
public class Utilidades {

    public static JComboBox cargarComboOrdenes(JComboBox cbx) {
        cbx.removeAllItems();

        for (Ordenes item : Ordenes.values()) {
            cbx.addItem(item);
        }

        return cbx;
    }

    public static JComboBox cargarComboOrdenamientos(JComboBox cbx) {
        cbx.removeAllItems();

        for (Ordenamientos item : Ordenamientos.values()) {
            cbx.addItem(item.getNombre());
        }

        return cbx;
    }

    public static JComboBox cargarComboBusquedas(JComboBox cbx) {
        cbx.removeAllItems();

        for (Busquedas item : Busquedas.values()) {
            cbx.addItem(item);
        }

        return cbx;
    }
}
