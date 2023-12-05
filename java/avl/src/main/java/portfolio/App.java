package portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.TreeNode;

/**
 * Hello world!
 */
public final class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        TArbolBB<String> root = new TArbolBB<String>();
        root.insertar(new TElementoAB<String>(7, null));
        root.insertar(new TElementoAB<String>(5, null));
        root.insertar(new TElementoAB<String>(6, null));
        // root.printTree();
        System.out.println();
        root.insertar(new TElementoAB<String>(3, null));
        // root.printTree();
        System.out.println();
        root.insertar(new TElementoAB<String>(2, null));
        // root.printTree();
        System.out.println();
        root.insertar(new TElementoAB<String>(1, null));
        // root.printTree();
        System.out.println();
        root.insertar(new TElementoAB<String>(0, null));
        // root.printTree();
        System.out.println();
        root.insertar(new TElementoAB<String>(10, null));
        root.printTree();
        System.out.println();

        // System.out.println(root.alturaArbol());

        root.eliminar(0);
        // root.eliminar(10);
        root.eliminar(1);
        root.eliminar(10);
        root.eliminar(7);
        root.printTree();
    }
}
