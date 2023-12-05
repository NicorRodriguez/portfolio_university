package nrodriguez;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        Conjunto<String> conjunto = new Conjunto<String>();
        Conjunto<String> conjunto2 = new Conjunto<String>();
        Conjunto<String> conjunto3 = new Conjunto<String>();

        conjunto.insertar(new Nodo<String>(7, null));
        conjunto.insertar(new Nodo<String>(10, null));
        conjunto.insertar(new Nodo<String>(11, null));
        conjunto2.insertar(new Nodo<String>(10, null));
        conjunto2.insertar(new Nodo<String>(11, null));

        conjunto3.diferencia(conjunto, conjunto2);

        System.out.println(conjunto.imprimir());
        System.out.println(conjunto2.imprimir());
        System.out.println(conjunto3.imprimir());
    }
}
