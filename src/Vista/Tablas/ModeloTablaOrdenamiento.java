package Vista.Tablas;

import Controlador.Listas.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vivi
 */
public class ModeloTablaOrdenamiento extends AbstractTableModel {

    private ListaEnlazada<Float> datos;

    public ListaEnlazada<Float> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Float> datos) {
        this.datos = datos;
    }

    @Override
    public int getColumnCount() {
        return 2;
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
                return "Valor";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Float dato = null;
        
        try {
            dato = datos.obtener(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        switch (i1) {
            case 0:
                return (i + 1);
            case 1:
                return (dato != null) ? dato : "NO DEFINIDO";
            default:
                return null;
        }
    }

}
