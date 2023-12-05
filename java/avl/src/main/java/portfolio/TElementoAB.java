package portfolio;

public class TElementoAB<T> implements IElementoAB<T> {

    private final Comparable etiqueta;
    private TElementoAB hijoIzq;
    private TElementoAB hijoDer;
    private final T datos;
    public int miAltura = 0;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        hijoDer = elemento;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiqueta) == 0) {
            return this;
        }
        if (unaEtiqueta.compareTo(etiqueta) < 0) {
            return hijoIzq.buscar(unaEtiqueta);
        } else {
            return hijoDer.buscar(unaEtiqueta);
        }
    }

    @Override
    public int insertar(TElementoAB<T> elemento) {
        if (elemento.getEtiqueta().compareTo(etiqueta) == 0) {
            return -1;
        }

        if (etiqueta.compareTo(elemento.etiqueta) > 0) {
            if (hijoIzq != null) miAltura = hijoIzq.insertar(elemento); else hijoIzq = elemento;
            return miAltura + 1;
        }

        if (etiqueta.compareTo(elemento.etiqueta) < 0) {
            if (hijoDer != null) miAltura = hijoDer.insertar(elemento); else hijoDer = elemento;
            return miAltura + 1;
        }

        return -1;
    }

    @Override
    public String preOrden() {
        String res = "";

        res += etiqueta + ", ";

        if (hijoIzq != null) res += hijoIzq.preOrden();
        if (hijoDer != null) res += hijoDer.preOrden();

        return res;
    }

    @Override
    public String inOrden() {
        String res = "";

        if (hijoIzq != null) res += hijoIzq.inOrden();

        res += etiqueta + ", ";

        if (hijoDer != null) res += hijoDer.inOrden();

        return res;
    }

    @Override
    public String postOrden() {
        String res = "";

        if (hijoIzq != null) res += hijoIzq.postOrden();

        if (hijoDer != null) res += hijoDer.postOrden();

        res += etiqueta + ", ";

        return res;
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        TElementoAB aux = null;
        if (etiqueta.compareTo(unaEtiqueta) > 0) {
            if (hijoIzq != null) {
                hijoIzq = hijoIzq.eliminar(unaEtiqueta);
            }
            aux = this;
        }
        if (etiqueta.compareTo(unaEtiqueta) < 0) {
            if (hijoDer != null) {
                hijoDer = hijoDer.eliminar(unaEtiqueta);
            }
            aux = this;
        }
        if (etiqueta.compareTo(unaEtiqueta) == 0) aux = quitaNodo();

        int alturaIzq = -1;
        int alturaDer = -1;

        if (getHijoIzq() != null) {
            alturaIzq = getHijoIzq().altura();
        }
        if (getHijoDer() != null) {
            alturaDer = getHijoDer().altura();
        }

        miAltura = Math.max(alturaDer, alturaIzq) + 1;

        return aux;
    }

    private TElementoAB quitaNodo() {
        if (hijoIzq == null) {
            return hijoDer;
        }
        if (hijoDer == null) {
            return hijoIzq;
        }

        TElementoAB elHijo = hijoIzq;
        TElementoAB elPadre = this;

        while (elHijo.hijoDer != null) {
            elPadre = elHijo;
            elHijo = elHijo.hijoDer;
        }

        if (elPadre != this) {
            elPadre.hijoDer = elHijo.hijoIzq;
            elHijo.hijoIzq = hijoIzq;
        }

        elHijo.hijoDer = hijoDer;

        return elHijo;
    }

    // altura del arbol
    public int altura() {
        return this.miAltura;
    }

    public int hojasArbol() {
        int res = 0;
        if (hijoDer != null) {
            res += hijoDer.hojasArbol();
        }

        if (hijoIzq != null) {
            res += hijoIzq.hojasArbol();
        }
        if (hijoDer == null && hijoIzq == null) {
            return 1 + res;
        }
        return res;
    }

    // ROTACIONES

    /*
     * t  		 n
     *   \ 		/ \
     *    n    t   A
     *   / \    \
     *  A   B    B
     */

    public TElementoAB<T> leftRotation() {
        TElementoAB<T> newRoot = getHijoDer();
        setHijoDer(newRoot.getHijoIzq());
        newRoot.setHijoIzq(this);

        int alturaIzq = -1;
        int alturaDer = -1;

        if (getHijoIzq() != null) {
            alturaIzq = getHijoIzq().altura();
        }
        if (getHijoDer() != null) {
            alturaDer = getHijoDer().altura();
        }

        miAltura = Math.max(alturaDer, alturaIzq) + 1;
        return newRoot;
    }

    /*
     *     t  	  n
     *    / 	 / \
     *   n      A   t
     *  / \        /
     * A   B      B
     */

    public TElementoAB<T> rightRotation() {
        TElementoAB<T> newRoot = getHijoIzq();
        setHijoIzq(newRoot.getHijoDer());
        newRoot.setHijoDer(this);

        int alturaIzq = -1;
        int alturaDer = -1;

        if (getHijoIzq() != null) {
            alturaIzq = getHijoIzq().altura();
        }
        if (getHijoDer() != null) {
            alturaDer = getHijoDer().altura();
        }

        miAltura = Math.max(alturaDer, alturaIzq) + 1;
        return newRoot;
    }

    public TElementoAB<T> rotacionLL() {
        setHijoIzq(getHijoIzq().leftRotation());
        return rightRotation();
    }

    public TElementoAB<T> rotacionRL() {
        setHijoDer(getHijoDer().rightRotation());
        return leftRotation();
    }

    public TElementoAB<T> balancear() {
        int alturaIzq = -1;
        int alturaDer = -1;

        if (getHijoIzq() != null) {
            setHijoIzq(hijoIzq.balancear());
            alturaIzq = getHijoIzq().altura();
        }
        if (getHijoDer() != null) {
            setHijoDer(hijoDer.balancear());
            alturaDer = getHijoDer().altura();
        }
        if (alturaIzq == alturaDer) {
            return this;
        }
        int AlturaIzqChild = -1;
        int AlturaDerChild = -1;
        
        if (alturaDer - alturaIzq > 1) {
            if(hijoDer.hijoDer != null) AlturaDerChild = hijoDer.hijoDer.altura();
            if(hijoDer.hijoIzq != null) AlturaIzqChild = hijoDer.hijoIzq.altura();

            if (AlturaDerChild > AlturaIzqChild) return leftRotation();
            else return rotacionRL();
        }

        if (alturaIzq - alturaDer > 1) {
            if(hijoIzq.hijoDer != null) AlturaDerChild = hijoIzq.hijoDer.altura();
            if(hijoIzq.hijoIzq != null) AlturaIzqChild = hijoIzq.hijoIzq.altura();

            if (AlturaIzqChild > AlturaDerChild) return rightRotation();
            else return rotacionLL();
        }

        if (getHijoIzq() != null) {
            alturaIzq = getHijoIzq().altura();
        }
        if (getHijoDer() != null) {
            alturaDer = getHijoDer().altura();
        }

        miAltura = Math.max(alturaDer, alturaIzq) + 1;

        return this;
    }
}
