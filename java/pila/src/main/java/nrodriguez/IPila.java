package nrodriguez;

public interface IPila<T> {
    /**
     * Metodo encargado de agregar un nodo a la pila.
     *
     * @param nodo - Nodo a agregar
     */
    public void push(Nodo<T> nodo);

    /**
     * Metodo encargado de quitar el nodo del tope de la pila.
     *
     * @return Nodo en el tope de la pila.
     */
    public Nodo<T> pop();

    /**
     * Metodo encargado de devolver el nodo en el tope de la pila sin eliminarlo de la pila.
     *
     * @return Nodo en el tope de la pila.
     */
    public Nodo<T> peek();

    /**
     * Metodo encargado de devolver el estado de la pila.
     *
     * @return True en caso de que la pila este vacia.
     */
    public boolean isEmpty();

    /**
     * Metodo encargado de buscar un nodo en la pila.
     *
     * @param nodo - Nodo a buscar
     * @return Nodo encontrado en la pila o null en caso de que no lo encuentre.
     */
    public Nodo<T> search(Comparable label);

    /**
     * Devolver la cantidad de elementos en la pila.
     *
     * @return Tamaño de la pila.
     */
    int size();
}
