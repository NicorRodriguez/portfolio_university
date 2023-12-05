package nrodriguez;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

  private final Map<Comparable, TVertice> vertices; //lista de vertices del grafo.-
  Comparable[][] matrizPredecesores;
  Double[][] matrizCosto = null;

  public TGrafoDirigido(
    Collection<TVertice> vertices,
    Collection<TArista> aristas
  ) {
    this.vertices = new HashMap<>();
    for (TVertice vertice : vertices) {
      insertarVertice(vertice.getEtiqueta());
    }
    for (TArista arista : aristas) {
      insertarArista(arista);
    }
  }

  /**
   * Metodo encargado de eliminar una arista dada por un origen y destino. En
   * caso de no existir la adyacencia, retorna falso. En caso de que las
   * etiquetas sean invalidas, retorna falso.
   *
   * @param nomVerticeOrigen
   * @param nomVerticeDestino
   * @return
   */
  @Override
  public boolean eliminarArista(
    Comparable nomVerticeOrigen,
    Comparable nomVerticeDestino
  ) {
    if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
      TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
      if (vertOrigen != null) {
        return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
      }
    }
    return false;
  }

  /**
   * Metodo encargado de eliminar un vertice en el grafo. En caso de no
   * existir el v�rtice, retorna falso. En caso de que la etiqueta sea
   * inv�lida, retorna false.
   *
   * @param nombreVertice
   * @return
   */
  @Override
  public boolean eliminarVertice(Comparable nombreVertice) {
    if (nombreVertice != null) {
      getVertices().remove(nombreVertice);
      return getVertices().containsKey(nombreVertice);
    }
    return false;
  }

  /**
   * Metodo encargado de verificar la existencia de una arista. Las etiquetas
   * pasadas por par�metro deben ser v�lidas.
   *
   * @param etiquetaOrigen
   * @param etiquetaDestino
   * @return True si existe la adyacencia, false en caso contrario
   */
  @Override
  public boolean existeArista(
    Comparable etiquetaOrigen,
    Comparable etiquetaDestino
  ) {
    TVertice vertOrigen = buscarVertice(etiquetaOrigen);
    TVertice vertDestino = buscarVertice(etiquetaDestino);
    if ((vertOrigen != null) && (vertDestino != null)) {
      return vertOrigen.buscarAdyacencia(vertDestino) != null;
    }
    return false;
  }

  /**
   * Metodo encargado de verificar la existencia de un vertice dentro del
   * grafo.-
   *
   * La etiqueta especificada como par�metro debe ser v�lida.
   *
   * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
   * @return True si existe el vertice con la etiqueta indicada, false en caso
   * contrario
   */
  @Override
  public boolean existeVertice(Comparable unaEtiqueta) {
    return getVertices().get(unaEtiqueta) != null;
  }

  /**
   * Metodo encargado de verificar buscar un vertice dentro del grafo.-
   *
   * La etiqueta especificada como parametro debe ser valida.
   *
   * @param unaEtiqueta Etiqueta del v�rtice a buscar.-
   * @return El vertice encontrado. En caso de no existir, retorna nulo.
   */
  private TVertice buscarVertice(Comparable unaEtiqueta) {
    return getVertices().get(unaEtiqueta);
  }

  /**
   * Matodo encargado de insertar una arista en el grafo (con un cierto
   * costo), dado su vertice origen y destino.- Para que la arista sea valida,
   * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
   * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
   * dentro del grafo.- 3) No es posible ingresar una arista ya existente
   * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
   * costo debe ser mayor que 0.
   *
   * @param arista
   * @return True si se pudo insertar la adyacencia, false en caso contrario
   */
  @Override
  public boolean insertarArista(TArista arista) {
    boolean tempbool = false;
    if (
      (arista.getEtiquetaOrigen() != null) &&
      (arista.getEtiquetaDestino() != null)
    ) {
      TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
      TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
      tempbool = (vertOrigen != null) && (vertDestino != null);
      if (tempbool) {
        // getLasAristas().add(arista);
        return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
      }
    }
    return false;
  }

  /**
   * Metodo encargado de insertar un vertice en el grafo.
   *
   * No pueden ingresarse v�rtices con la misma etiqueta. La etiqueta
   * especificada como par�metro debe ser v�lida.
   *
   * @param unaEtiqueta Etiqueta del v�rtice a ingresar.
   * @return True si se pudo insertar el vertice, false en caso contrario
   */
  public boolean insertarVertice(Comparable unaEtiqueta) {
    if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
      TVertice vert = new TVertice(unaEtiqueta);
      getVertices().put(unaEtiqueta, vert);
      return getVertices().containsKey(unaEtiqueta);
    }
    return false;
  }

  @Override
  public boolean insertarVertice(TVertice vertice) {
    Comparable unaEtiqueta = vertice.getEtiqueta();
    if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
      getVertices().put(unaEtiqueta, vertice);
      return getVertices().containsKey(unaEtiqueta);
    }
    return false;
  }

  public Object[] getEtiquetasOrdenado() {
    TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(
      this.getVertices()
    );
    return mapOrdenado.keySet().toArray();
  }

  @Override
  public void desvisitarVertices() {
    for (TVertice vertice : this.vertices.values()) {
      vertice.setVisitado(false);
    }
  }

  /**
   * @return the vertices
   */
  @Override
  public Map<Comparable, TVertice> getVertices() {
    return vertices;
  }

  @Override
  public Collection<TVertice> bpf(TVertice vertice) {
    Collection<TVertice> visitados = new LinkedList<TVertice>();
    vertice.bpf(visitados);
    return visitados;
  }

  public boolean tieneCiclo(Comparable etiquetaOrigen) {
    TVertice vertV = vertices.get(etiquetaOrigen);
    if (vertV != null) {
      desvisitarVertices();
      TCamino camino = new TCamino(vertV);
      return vertV.tieneCiclo(camino);
    }
    return false;
  }

  @Override
  public boolean tieneCiclo(TCamino camino) {
    desvisitarVertices();
    if (camino == null) {
      return false;
    }
    return camino.getOrigen().tieneCiclo(camino);
  }

  @Override
  public Collection<TVertice> bpf() {
    Collection<TVertice> res = new LinkedList<TVertice>();
    for (TVertice vertice : vertices.values()) {
      if (!vertice.getVisitado()) {
        res.addAll(bpf(vertice));
      }
    }
    return res;
  }

  @Override
  public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
    Collection<TVertice> visitados = new LinkedList<TVertice>();
    vertices.get(etiquetaOrigen).bpf(null);
    return visitados;
  }

  @Override
  public Comparable centroDelGrafo() {
    Object[] etiquetasarray = this.getEtiquetasOrdenado();
    Comparable[] excentricidad = new Comparable[] { Double.MAX_VALUE, null };
    for (int i = 0; i < etiquetasarray.length; i++) {
      Comparable excentricidadVertice =
        this.obtenerExcentricidad((Comparable) etiquetasarray[i]);
      if (
        (excentricidadVertice.compareTo(-1d) > 0) &&
        excentricidadVertice.compareTo(excentricidad[0]) < 0
      ) {
        excentricidad[0] = excentricidadVertice;
        excentricidad[1] = i;
      }
    }
    return (Comparable) etiquetasarray[(int) excentricidad[1]];
  }

  @Override
  public Double[][] floyd() {
    if (matrizCosto != null) {
      return matrizCosto;
    }
    matrizCosto = UtilGrafos.obtenerMatrizCostos(this.getVertices());

    matrizPredecesores = new Comparable[vertices.size()][vertices.size()];
    Object[] etiquetas = vertices.keySet().toArray();
    for (int k = 0; k < matrizCosto.length; k++) {
      for (int i = 0; i < matrizCosto.length; i++) {
        if (i == k || matrizCosto[i][k] == Double.MAX_VALUE) {
          break;
        }
        for (int j = 0; j < matrizCosto.length; j++) {
          if ((matrizCosto[i][k] + matrizCosto[k][j]) < matrizCosto[i][j]) {
            matrizCosto[i][j] = matrizCosto[i][k] + matrizCosto[k][j];
            matrizPredecesores[i][j] = etiquetas[k].toString();
          }
        }
      }
    }
    return matrizCosto;
  }

  @Override
  public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
    Double[][] floyd = this.floyd();
    Map<Comparable, TVertice> vertices = this.getVertices();
    Object[] etiquetas = vertices.keySet().toArray();
    Integer indiceEtiqueta = null;
    for (int i = 0; i < etiquetas.length; i++) {
      if (etiquetas[i].equals(etiquetaVertice)) {
        indiceEtiqueta = i;
        break;
      }
    }
    Double excentricidad = -1d;
    for (int i = 0; i < floyd.length; i++) {
      if (
        (floyd[i][indiceEtiqueta] != Double.MAX_VALUE) &&
        i != indiceEtiqueta &&
        floyd[i][indiceEtiqueta] > excentricidad
      ) {
        excentricidad = floyd[i][indiceEtiqueta];
      }
    }
    return excentricidad;
  }

  @Override
  public boolean[][] warshall() {
    Double[][] matrizADevolver = UtilGrafos.obtenerMatrizCostos(this.vertices);
    boolean[][] matrizADevolverWarshall = new boolean[matrizADevolver.length][matrizADevolver.length];

    for (int k = 0; k < matrizADevolverWarshall.length; k++) {
      for (int i = 0; i < matrizADevolverWarshall.length; i++) {
        for (int j = 0; j < matrizADevolverWarshall.length; j++) {
          //Inicializo las conexiones directas con true (deberia funcar igual que hacerlo antes arriba)
          if ((i != j) && (matrizADevolver[i][j] != Double.MAX_VALUE)) {
            matrizADevolverWarshall[i][j] = true;
          }

          if ((i != j) && (matrizADevolverWarshall[i][j] == false)) {
            matrizADevolverWarshall[i][j] =
              matrizADevolverWarshall[i][k] && matrizADevolverWarshall[k][j];
          }
        }
      }
    }
    return matrizADevolverWarshall;
  }

  @Override
  public TCaminos todosLosCaminos(
    Comparable etiquetaOrigen,
    Comparable etiquetaDestino
  ) {
    desvisitarVertices();
    TCaminos todosLosCaminos = new TCaminos();
    TVertice v = buscarVertice(etiquetaOrigen);
    if (v != null) {
      TCamino caminoPrevio = new TCamino(v);
      v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
      return todosLosCaminos;
    }
    return null;
  }

  @Override
  public boolean tieneCiclo() {
    boolean result = false;
    if (vertices.isEmpty()) {
      System.out.println("El grafo está vacio");
      return result;
    }
    desvisitarVertices();

    for (TVertice vertV : this.vertices.values()) {
      if (result) {
        break;
      }
      if (!vertV.getVisitado()) {
        TCamino camino = new TCamino(vertV);
        result = vertV.tieneCiclo(camino);
        if (result) {
          camino.imprimirEtiquetasConsola();
        }
      }
    }
    if (!result) {
      System.out.println("no hay ciclos");
    }
    return result;
  }

  public Collection<TVertice> bea(Comparable etiquetaOrigen) {
    desvisitarVertices();
    LinkedList<TVertice> col = new LinkedList<>();
    getVertices().get(etiquetaOrigen).bea(col);
    return col;
  }

  @Override
  public Collection<TVertice> bea() {
    LinkedList<TVertice> resultado = new LinkedList<>();
    this.desvisitarVertices();
    // this.getVertices()
    //   .forEach((key, value) -> {
    //     if (!value.getVisitado()) {
    //       value.bea(resultado);
    //     }
    //   });
    return resultado;
  }

  public Collection<Comparable> ClasificacionTopologica(Comparable destino) {
    TVertice vert = buscarVertice(destino);
    if (vert == null) {
      return null;
    }
    Collection<Comparable> res = new LinkedList<>();
    vert.ClasificacionTopologica(res);
    return res;
  }
}
