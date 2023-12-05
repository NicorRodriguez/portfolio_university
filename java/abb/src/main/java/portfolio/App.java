package portfolio;

public final class App {

    public static void main(String[] args) {
        TArbolBB<String> root = new TArbolBB<String>();
        root.insertar(new TElementoAB<String>(4, null));
        root.insertar(new TElementoAB<String>(3, null));
        root.insertar(new TElementoAB<String>(6, null));
        root.insertar(new TElementoAB<String>(2, null));
        root.insertar(new TElementoAB<String>(7, null));
        root.insertar(new TElementoAB<String>(5, null));
        
        
        root.printTree();
    }
}
