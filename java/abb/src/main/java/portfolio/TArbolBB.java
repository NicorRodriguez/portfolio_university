package portfolio;
public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB() {
        raiz = null;
    }

    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (raiz == null) {
            raiz = unElemento;
            return true;
        }
        boolean resultado = raiz.insertar(unElemento);
        return resultado;
    }

    public boolean insertarBalanceado(TElementoAB<T> unElemento) {
        if (raiz == null) {
            raiz = unElemento;
            return true;
        }
        boolean resultado = raiz.insertar(unElemento);
        // raiz.balancear(unElemento);
        // System.out.printf("Cantidad de Iteraciones: " + unElemento.contadorIteraciones + "\n");
        return resultado;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (raiz == null) {
            return null;
        }
        return raiz.buscar(unaEtiqueta);
    }

    @Override
    public String preOrden() {
        if (raiz == null) {
            return "Arbol Vacio";
        }
        return raiz.preOrden();
    }

    @Override
    public String inOrden() {
        if (raiz == null) {
            return "Arbol Vacio";
        }
        return raiz.inOrden();
    }

    @Override
    public String postOrden() {
        if (raiz == null) {
            return "Arbol Vacio";
        }
        return raiz.postOrden();
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (raiz == null) {
            System.out.println("Arbol vacio");
            return;
        }
        raiz.eliminar(unaEtiqueta);
    }

    public int alturaArbol() {
        if (raiz == null) {
            return 0;
        }
        return raiz.alturaArbol();
    }

    public int hojasArbol() {
        if (raiz == null) {
            return 0;
        }
        return raiz.hojasArbol();
    }

    public void printTree() {
        if (raiz == null) return;

        // Print right subtree with an indent
        printTreeHelper(raiz.getHijoDer(), "", true);

        // Print root node
        System.out.println(raiz.getEtiqueta());

        // Print left subtree with an indent
        printTreeHelper(raiz.getHijoIzq(), "", false);
    }

    private static void printTreeHelper(TElementoAB node, String indent, boolean isRight) {
        if (node == null) return;

        printTreeHelper(node.getHijoDer(), indent + "    ", true);
        System.out.println(indent + (isRight ? "┌── " : "└── ") + node.getEtiqueta());
        printTreeHelper(node.getHijoIzq(), indent + "    ", false);
    }

}
