package nrodriguez;

public class Pila<T> extends Lista<T> implements IPila<T> {

    public Nodo<T> pop() {
        if (esVacia()) {
            return null;
        }
        Nodo<T> actual = getPrimero();
        setPrimero(actual.getSiguiente());
        actual.setSiguiente(null);
        return actual;
    }

    public boolean vacio() {
        return esVacia();
    }

    public Nodo<T> peek() {
        return getPrimero();
    }

    @Override
    public boolean isEmpty() {
        return getPrimero() == null;
    }

    @Override
    public Nodo<T> search(Comparable label) {
        Nodo<T> actual = getPrimero();

        while (actual != null) {
            if (actual.getEtiqueta().compareTo(label) == 0) {
                return actual;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return cantElementos();
    }

    @Override
    public void push(Nodo nodo) {
        insertar(nodo);
    }
}
