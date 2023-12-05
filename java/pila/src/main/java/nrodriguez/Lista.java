package nrodriguez;

public class Lista<T> implements ILista<T> {

    private Nodo<T> primero;

    private int size = 0;

    public Lista() {
        primero = null;
    }

    @Override
    public void insertar(Nodo<T> nodo) {
        if (nodo == null || nodo.getSiguiente() != null) {
            throw new IllegalArgumentException("El nodo ya esta en otra lista");
        }
        if (primero == null) {
            primero = nodo;
        } else {
            nodo.setSiguiente(primero);
            primero = nodo;
        }

        size++;
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        Nodo<T> nodoActual = primero;
        while (nodoActual != null) {
            if (nodoActual.getEtiqueta().equals(clave)) {
                return nodoActual;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        Nodo<T> nodoActual = primero;

        size--;

        if (nodoActual == null) return false;
        if (nodoActual.getEtiqueta() == clave) {
            primero = nodoActual.getSiguiente();
            return true;
        }

        while (nodoActual.getSiguiente() != null) {
            if (nodoActual.getSiguiente().getEtiqueta() == clave) {
                nodoActual.setSiguiente(nodoActual.getSiguiente().getSiguiente());
                return true;
            }
            nodoActual = nodoActual.getSiguiente();
        }

        size++;

        return false;
    }

    @Override
    public String imprimir() {
        return imprimir(", ");
    }

    @Override
    public String imprimir(String separador) {
        Nodo<T> nodoActual = primero;
        String result = "";

        while (nodoActual != null) {
            result += nodoActual.getEtiqueta() + separador;
            nodoActual = nodoActual.getSiguiente();
        }

        return result;
    }

    @Override
    public int cantElementos() {
        return size;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        unNodo.setSiguiente(primero);
        primero = unNodo;
    }
}
