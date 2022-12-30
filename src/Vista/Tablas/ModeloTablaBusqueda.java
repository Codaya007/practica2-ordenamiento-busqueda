package Vista.Tablas;

import Controlador.Listas.ListaEnlazada;
import Modelo.Gato;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vivi
 */
public class ModeloTablaBusqueda extends AbstractTableModel {

    private ListaEnlazada<Gato> datos;

    public ListaEnlazada<Gato> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Gato> datos) {
        this.datos = datos;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        // System.out.println("Size" + datos.getUltimaPosicionOcupada());
        return datos.getUltimaPosicionOcupada();
    }

    @Override
    public String getColumnName(int i) {

        switch (i) {
            case 0:
                return "N°";
            case 1:
                return "Nombre";
            case 2:
                return "Color";
            case 3:
                return "Sexo";
            case 4:
                return "Raza";
            case 5:
                return "Fecha nacimiento";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Gato gato = null;
        
        try {
            gato = datos.obtener(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        switch (i1) {
            case 0:
                return (gato != null) ? gato.getID().toString() : (i + 1);
            case 1:
                return (gato != null) ? gato.getNombre() : "NO DEFINIDO";
            case 2:
                return (gato != null) ? gato.getColor() : "NO DEFINIDO";
            case 3:
                return (gato != null) ? gato.getSexo() : "NO DEFINIDO";
            case 4:
                return (gato != null) ? gato.getRaza() : "NO DEFINIDO";
            case 5:
                return (gato != null) ? gato.getFechaNacimiento() : "NO DEFINIDO";
            default:
                return null;
        }
    }

}
