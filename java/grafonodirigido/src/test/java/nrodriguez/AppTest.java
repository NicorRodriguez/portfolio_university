package nrodriguez;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    TGrafoNoDirigido GrafoSimplificado;

    @Before
    public void SetUp() {
        List<TVertice> verticesList = new ArrayList<TVertice>(5);
        List<TArista> aristasList = new ArrayList<TArista>(5);

        verticesList.add(new TVertice<>("A"));
        verticesList.add(new TVertice<>("B"));
        verticesList.add(new TVertice<>("C"));
        verticesList.add(new TVertice<>("D"));
        verticesList.add(new TVertice<>("E"));

        aristasList.add(new TArista("A", "B", 1));
        aristasList.add(new TArista("B", "C", 1));
        aristasList.add(new TArista("C", "D", 1));
        aristasList.add(new TArista("D", "E", 1));
        aristasList.add(new TArista("E", "A", 1));

        GrafoSimplificado = new TGrafoNoDirigido(verticesList, aristasList);
    }

    @Test
    public void testPrim() {

        boolean res = GrafoSimplificado.esConexo();
        if (res) {
            TGrafoNoDirigido myTgrafito2 = GrafoSimplificado.Prim();
            // myTgrafito2.imprimirGrafo(false);
            System.out.println(myTgrafito2.getCostoTotal());
            assertEquals(4.0, myTgrafito2.getCostoTotal(), 0);
            return;
        }
        assertFalse(res);
    }

    @Test
    public void testKruskal() {

        TGrafoNoDirigido arbolExpansionMinima = GrafoSimplificado.Kruskal();

        double x = calcularPesoTotal(arbolExpansionMinima);

        assertEquals(8, x, 0);
    }

    // Método auxiliar para calcular el peso total de las aristas en el árbol de expansión mínima
    private double calcularPesoTotal(TGrafoNoDirigido grafo) {
        double pesoTotal = 0;
        for (TArista arista : grafo.getLasAristas()) {
            pesoTotal += arista.getCosto();
        }
        return pesoTotal;
    }
}
