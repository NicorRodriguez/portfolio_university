package nrodriguez;

public class Cola<T> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;

    private int size = 0;

    public Cola() {
        primero = ultimo = null;
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
    public void queue(Nodo<T> nodo) {
        if (primero == null) ultimo = nodo;
        nodo.setSiguiente(primero);
        primero.setAnterior(nodo);
        primero = nodo;
    }

    @Override
    public Nodo<T> unqueue() {
        Nodo<T> aux = ultimo;

        if (ultimo == null || ultimo.getAnterior() == null) {
            primero = ultimo;
            return null;
        };

        ultimo = ultimo.getAnterior();

        return aux;
    }

    @Override
    public Nodo<T> peek() {
        return ultimo;
    }

    @Override
    public Nodo<T> search(Comparable clave) {
        Nodo<T> nodoActual = primero;
        while (nodoActual != null) {
            if (nodoActual.getEtiqueta().equals(clave)) {
                return nodoActual;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return null;
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
    public void setPrimero(Nodo<T> unNodo) {
        unNodo.setSiguiente(primero);
        primero.setAnterior(unNodo);
        primero = unNodo;
    }
}
