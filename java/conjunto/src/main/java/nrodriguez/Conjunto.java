package nrodriguez;

public class Conjunto<T> implements IConjunto<T> {

    private Nodo<T> primero;

    private int size = 0;

    public Conjunto() {
        primero = null;
    }

    @Override
    public void insertar(Nodo<T> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo ya esta en otra lista");
        }
        nodo.setSiguiente(null);
        if (primero == null) {
            primero = nodo;
        } else {
            Nodo<T> aux = primero;
            while (aux.getEtiqueta().compareTo(nodo.getEtiqueta()) != 0 && aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            if (aux.getEtiqueta().compareTo(nodo.getEtiqueta()) != 0) aux.setSiguiente(nodo);
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

    @Override
    public void interseccion(Conjunto<T> a, Conjunto<T> b) {
        Nodo<T> elemA = a.getPrimero();

        while (elemA != null) {
            Nodo<T> elemB = b.getPrimero();
            while (elemB != null) {
                if(elemA.getEtiqueta().compareTo(elemB.getEtiqueta()) == 0) this.insertar(new Nodo<T>(elemA.getEtiqueta(), elemA.getDato()));
                elemB = elemB.getSiguiente();
            }
            elemA = elemA.getSiguiente();
        }
    }
    @Override
    public void diferencia(Conjunto<T> a, Conjunto<T> b) {
        Nodo<T> elemA = a.getPrimero();

        while (elemA != null) {
            Nodo<T> elemB = b.getPrimero();
            while (elemB != null) {
                if(elemA.getEtiqueta().compareTo(elemB.getEtiqueta()) == 0) break;
                elemB = elemB.getSiguiente();
            }
            if(elemB == null )this.insertar(new Nodo<T>(elemA.getEtiqueta(), elemA.getDato()));

            elemA = elemA.getSiguiente();
        }
    }

    @Override
    public void union(Conjunto<T> a, Conjunto<T> b) {
        Nodo<T> aux = a.getPrimero();

        if (primero == null) {
            this.insertar(new Nodo<T>(aux.getEtiqueta(), aux.getDato()));
            aux = aux.getSiguiente();
        }

        while (aux != null) {
            this.insertar(new Nodo<T>(aux.getEtiqueta(), aux.getDato()));
            aux = aux.getSiguiente();
        }

        aux = b.getPrimero();

        while (aux != null) {
            this.insertar(new Nodo<T>(aux.getEtiqueta(), aux.getDato()));
            aux = aux.getSiguiente();
        }
    }
}
