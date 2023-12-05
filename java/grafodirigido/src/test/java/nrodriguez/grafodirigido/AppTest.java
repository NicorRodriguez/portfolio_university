package nrodriguez.grafodirigido;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
    private TGrafoDirigido grafo;

    @Before
    public void setUp() {

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

        grafo = new TGrafoDirigido(verticesList, aristasList);
    }

    @Test
    public void testCentroDelGrafo() {
        Comparable centroEsperado = "B";
        Comparable centroObtenido = grafo.centroDelGrafo();
        assertEquals(0, centroEsperado.compareTo(centroObtenido));
    }

    @Test
    public void testObtenerExcentricidad() {
        Comparable etiquetaVertice = "A";
        double excentricidadEsperada = 4;
        Comparable excentricidadObtenida = grafo.obtenerExcentricidad(etiquetaVertice);
        assertEquals(excentricidadEsperada, excentricidadObtenida);
    }
}
