package portfolio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    @Test
    void testAltura() {
        TArbolBB<String> root = new TArbolBB<String>();
        root.insertar(new TElementoAB<String>(7, null));
        root.insertar(new TElementoAB<String>(5, null));
        root.insertar(new TElementoAB<String>(6, null));
        root.insertar(new TElementoAB<String>(3, null));
        root.insertar(new TElementoAB<String>(2, null));
        root.insertar(new TElementoAB<String>(1, null));
        root.insertar(new TElementoAB<String>(0, null));
        root.insertar(new TElementoAB<String>(10, null));

        assertEquals(3, root.alturaArbol());
    }

    @Test
    void testSearch() {
        TArbolBB<String> root = new TArbolBB<String>();
        root.insertar(new TElementoAB<String>(7, null));
        root.insertar(new TElementoAB<String>(5, null));
        root.insertar(new TElementoAB<String>(6, null));
        root.insertar(new TElementoAB<String>(3, null));
        root.insertar(new TElementoAB<String>(2, null));
        root.insertar(new TElementoAB<String>(1, null));
        root.insertar(new TElementoAB<String>(0, null));
        root.insertar(new TElementoAB<String>(10, null));

        assertEquals(10, root.buscar(10).getEtiqueta());
    }
}
