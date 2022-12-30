package Controlador.Listas;

import Controlador.Listas.Excepciones.ListaNullException;
import Controlador.Listas.Excepciones.PosicionException;
import Modelo.Ordenes;

public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size;

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public ListaEnlazada() {
        cabecera = null;
        size = 0;
    }

    public boolean estaVacia() {
        return cabecera == null;
    }

    /**
     * private Integer tamanio(){ Integer tamanio = 0; NodoLista <E> aux =
     * cabecera; while (aux != null) { tamanio++; aux = aux.getSiguiente(); }
     * return tamanio; }
     *
     * @param dato
     */
    public void insertar(E dato) {
        NodoLista<E> nodo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            this.cabecera = nodo;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodo);
        }
        // size++;
    }

    public int getUltimaPosicionOcupada() {
        int posicion = 0;

        if (!estaVacia()) {
            NodoLista<E> aux = cabecera;
            posicion += 1;

            while (aux.getSiguiente() != null) {
                posicion += 1;
                aux = aux.getSiguiente();
            }
        }

        return posicion;
    }

    public void insertarCabecera(E dato) {
        NodoLista<E> nodo = new NodoLista<>(dato, null);
        if (estaVacia()) {
            insertar(dato);
        } else {
            nodo.setSiguiente(cabecera);
            cabecera = nodo;
            size++;
        }
    }

    public void reemplazarDato(E dato, Integer pos) throws PosicionException, ListaNullException {
        System.out.println("Reemplazando " + dato + " en posicion " + pos);
        if (estaVacia()) {
            cabecera = new NodoLista<>(dato, null);
        } else if (pos == getUltimaPosicionOcupada()) {
            insertar(dato);
        } else {
            NodoLista<E> aux = cabecera;
            // Si quiero reemplazar el elemento numero 1 por 50:
            // 0    1   2   3
            // 12 , 23, 34, 12
            for (int i = 0; i < pos; i++) { // 
                aux = aux.getSiguiente(); //1. 12
            }

            aux.setDato(dato); // 12 -> 50
        }

    }

    public void insertarPosicion(E dato, Integer pos) throws PosicionException {
        if (estaVacia()) {
            insertar(dato);
        } else if (pos >= 0 && pos < size) {
            NodoLista<E> nodo = new NodoLista<>(dato, null);
            NodoLista<E> aux = cabecera;
            if (pos == (size - 1)) {
                insertar(dato);
            } else if (pos == 0) {
                insertarCabecera(dato);
            } else {
                for (int i = 0; i < pos - 1; i++) {
                    aux = aux.getSiguiente();
                }
                NodoLista<E> siguiente = aux.getSiguiente();
                aux.setSiguiente(nodo);
                nodo.setSiguiente(siguiente);
                size++;
            }
        } else {
            throw new PosicionException();
        }
    }

    public E obtener(Integer pos) throws ListaNullException, PosicionException {
        // System.out.println(estaVacia());
        if (!estaVacia()) {
            E dato = null;
            if (pos >= 0 && pos <= getUltimaPosicionOcupada()) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        } else {
            System.out.println("Valor de posicion q da error: " + pos);
            throw new ListaNullException();
        }
    }

    public E eliminarDato(Integer pos) throws PosicionException, ListaNullException {
        E dato = null;
        if (!estaVacia()) {
            System.out.println("Pocisión: " + pos + " Size: " + size + " Última: " + getUltimaPosicionOcupada());
            if (pos >= 0 && pos < size) {
                if (pos == 0) {
                    dato = cabecera.getDato();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos - 1; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getDato();
                    NodoLista<E> proximo = aux.getSiguiente();
                    aux.setSiguiente(proximo.getSiguiente());
                    size--;
                }
                return dato;

            } else {
                throw new PosicionException("Error en eliminar dato: No existe la posicion dada");
            }

        } else {
            throw new ListaNullException("Error en eliminar dato: La lista esta vacia, por endde no hay esa posicion");
        }
    }

    public ListaEnlazada<E> copiarLista() throws ListaNullException, PosicionException {
        ListaEnlazada<E> copy = new ListaEnlazada();
        copy.setSize(size);

        for (int i = 0; i < this.getUltimaPosicionOcupada(); i++) {
            copy.insertar(this.obtener(i));
        }

        return copy;
    }

    public ListaEnlazada<E> shell(Ordenes orden) throws ListaNullException, PosicionException {
        ListaEnlazada<E> result = copiarLista();
        result.setSize(size);

        int n = getUltimaPosicionOcupada();
        int salto = n;
        boolean ordenado;

        while (salto > 1) {
            salto = salto / 2;

            do {
                ordenado = true;

                if (orden.equals(Ordenes.Ascendente)) {
                    for (int j = 0; j <= n - 1 - salto; j++) {
                        int k = j + salto;
                        if (esMayor(result.obtener(j), result.obtener(k))) {

                            E aux = result.obtener(j);
                            result.reemplazarDato(result.obtener(k), j);
                            result.reemplazarDato(aux, k);

                            ordenado = false;
                        }
                    }
                } else {
                    for (int j = 0; j <= n - 1 - salto; j++) {
                        int k = j + salto;
                        if (esMayor(result.obtener(k), result.obtener(j))) {

                            E aux = result.obtener(j);
                            result.reemplazarDato(result.obtener(k), j);
                            result.reemplazarDato(aux, k);

                            ordenado = false;
                        }
                    }

                }

            } while (!ordenado);
        }

        // result.imprimir();
        return result;
    }

    public ListaEnlazada<E> quickSortSubLista(ListaEnlazada<E> arreglo, int inicio, int fin, Ordenes orden) throws ListaNullException, PosicionException {
        int i = inicio; // i siempre avanza en el arreglo hacia la derecha
        int j = fin; // j siempre avanza hacia la izquierda

        // el pivote es el valor del medio
        E pivote = arreglo.obtener((inicio + fin) / 2);

        if (Ordenes.Ascendente == orden) {
            do {
                // si ya esta ordenado incrementa i
                while (esMayor(pivote, arreglo.obtener(i))) {
                    i++;
                }
                // si ya esta ordenado decrementa j
                while (esMayor(arreglo.obtener(j), pivote)) {
                    j--;
                }
                if (i <= j) { // Hace el intercambio
                    System.out.println("Se hace intercambio con i: " + i + " y j: " + j);
                    E aux = arreglo.obtener(i);
                    arreglo.reemplazarDato(arreglo.obtener(j), i);
                    arreglo.reemplazarDato(aux, j);
                    i++;
                    j--;
                }

                arreglo.imprimir();
            } while (i <= j);
        } else {
            do {
                // si ya esta ordenado incrementa i
                while (esMayor(arreglo.obtener(i), pivote)) {
                    i++;
                }
                // si ya esta ordenado decrementa j
                while (esMayor(pivote, arreglo.obtener(j))) {
                    j--;
                }
                if (i <= j) { // Hace el intercambio
                    System.out.println("Se hace intercambio con i: " + i + " y j: " + j);
                    E aux = arreglo.obtener(i);
                    arreglo.reemplazarDato(arreglo.obtener(j), i);
                    arreglo.reemplazarDato(aux, j);
                    i++;
                    j--;
                }

                arreglo.imprimir();
            } while (i <= j);
        }

        if (inicio < j) {
            quickSortSubLista(arreglo, inicio, j, orden); // invocación recursiva
        }
        if (i < fin) {
            quickSortSubLista(arreglo, i, fin, orden); // invocacion recursiva
        }

        return arreglo;
    }

    public ListaEnlazada<E> quickSort(Ordenes orden) throws ListaNullException, PosicionException {
        ListaEnlazada<E> listaOrdenada = copiarLista();

        quickSortSubLista(listaOrdenada, 0, getUltimaPosicionOcupada() - 1, orden);

        return listaOrdenada;
    }

    public ListaEnlazada<E> binarySearch(String query) throws ListaNullException, PosicionException {
        ListaEnlazada<E> listaOrdenada = quickSort(Ordenes.Ascendente);
        
        ListaEnlazada<E> result = new ListaEnlazada<>();
        result.setSize(size);
        

        boolean encontrado = false;
        int inicio = 0;
        int fin = getUltimaPosicionOcupada();
        
        while (inicio < fin && !encontrado) {
            int medio = (inicio + fin) / 2;

            System.out.println("medio: "+ medio);
            if (listaOrdenada.obtener(medio).toString().toLowerCase().contains(query.trim().toLowerCase())) {
                encontrado = true;
                result.insertar(listaOrdenada.obtener(medio));
                // result.imprimir();
            } else {
                Character charMedio = listaOrdenada.obtener(medio).toString().charAt(0);
                Character charQuery = query.charAt(0);

                if (charMedio.compareTo(charQuery) > 0) {
                    fin = medio - 1;
                } else {
                    inicio = medio + 1;
                }
            }

        }

        return result;
    }

    public ListaEnlazada<E> linealSearch(String query) throws ListaNullException, PosicionException {
        ListaEnlazada<E> result = new ListaEnlazada();

        Integer encontrados = 0;
        result.setSize(this.size);

        Integer ultimaPosicionOcupada = this.getUltimaPosicionOcupada();

        for (int i = 0; i < ultimaPosicionOcupada; i++) {
            if (this.obtener(i).toString().toLowerCase().contains(query.toLowerCase())) {
                result.insertar(this.obtener(i));
                encontrados += 1;
            }
        }
        // result.setSize(encontrados);

        System.out.println("Buscando query " + query + " por búsqueda lineal, se encontraron " + encontrados);

        return result;
    }

    public void imprimir() {
        System.out.println("=================== Lista enlazada ======================");
        NodoLista<E> aux = cabecera;
        Integer contador = 0;
        while (aux != null) {
            System.out.println(contador + ". " + aux.getDato().toString());
            aux = aux.getSiguiente();
            contador += 1;
        }
        System.out.println("==========================================================");
    }

    public boolean esMayor(E valorA, E valorB) {
        String claseA = valorA.getClass().getSimpleName();
        String claseB = valorB.getClass().getSimpleName();

        if (!claseA.equals(claseB)) {
            throw new Error("Para comparar, los datos deben ser de la misma clase");
        }

        if ("Float".equals(claseA)) {
            return Float.compare((float) valorA, (float) valorB) > 0;
        } else if ("Integer".equals(claseA)) {
            return Integer.compare((int) valorA, (int) valorB) > 0;
        } else {
            Character a = valorA.toString().charAt(0);
            Character b = valorB.toString().charAt(0);

            return a.compareTo(b) > 0;
        }
    }
}
