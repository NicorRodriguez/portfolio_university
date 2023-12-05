package nrodriguez.grafodirigido;

import java.util.Collection;

public final class App {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(
      "grafodirigido/src/main/java/nrodriguez/grafodirigido/aeropuertos_1.txt",
      "grafodirigido/src/main/java/nrodriguez/grafodirigido/conexiones_1.txt",
      false,
      TGrafoDirigido.class
    );

    Object[] etiquetasarray = gd.getEtiquetasOrdenado();

    Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
    UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
    Double[][] mfloyd = gd.floyd();
    UtilGrafos.imprimirMatrizMejorado(
      mfloyd,
      gd.getVertices(),
      "Matriz luego de FLOYD"
    );

    // gd.eliminarVertice("Asuncion");

    // UtilGrafos.imprimirMatrizWarshall(gd.warshall(), gd.getVertices(), "Matriz Warshall");
    // for (int i = 0; i < etiquetasarray.length; i++) {
    //     System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
    // }
    System.out.println();
    System.out.println("Centro del grafo: " + gd.centroDelGrafo());

    // System.out.println(gd.getVertices().size());
    // Collection recorrido = gd.bpf();
    // System.out.println(recorrido.size());
    // imprimir etiquetas del bpf de todo el grafo....
    // Collection recorrido_Asuncion = gd.bpf("Asuncion");
    // imprimir etiquetas del bpf desde Asunción....

  }
}
