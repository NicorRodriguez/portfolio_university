package nrodriguez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TGrafoNoDirigido
  extends TGrafoDirigido
  implements IGrafoNoDirigido {

  protected TAristas lasAristas = new TAristas();

  /**
   *
   * @param vertices
   * @param aristas
   */
  public TGrafoNoDirigido(
    Collection<TVertice> vertices,
    Collection<TArista> aristas
  ) {
    super(vertices, aristas);
    lasAristas.insertarAmbosSentidos(aristas);
  }

  @Override
  public boolean insertarArista(TArista arista) {
    boolean tempbool = false;
    TArista arInv = new TArista(
      arista.getEtiquetaDestino(),
      arista.getEtiquetaOrigen(),
      arista.getCosto()
    );
    if (lasAristas == null) {
      lasAristas = new TAristas();
    }
    tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
    lasAristas.add(arInv);
    lasAristas.add(arista);
    return tempbool;
  }

  public TAristas getLasAristas() {
    return lasAristas;
  }

  private LinkedList<Comparable> copiarEtisVertices() {
    Collection<TVertice> col = super.getVertices().values();
    LinkedList<Comparable> copiaV = new LinkedList<>();
    for (TVertice tVertice : col) {
      copiaV.add(tVertice.getEtiqueta());
    }
    return copiaV;
  }

  @Override
  public TGrafoNoDirigido Prim() {
    TArista aristaMin;
    Collection<Comparable> VerticesU = new LinkedList<>();
    Comparable u, v;
    TAristas T = new TAristas();
    LinkedList<Comparable> VerticesV = copiarEtisVertices();
    u = VerticesV.getFirst();
    VerticesU.add(u);
    VerticesV.remove(u);
    while (!VerticesV.isEmpty()) {
      aristaMin = this.lasAristas.buscarMin(VerticesU, VerticesV);
      v = aristaMin.getEtiquetaDestino();
      VerticesU.add(v);
      VerticesV.remove(v);
      T.add(aristaMin);
    }
    LinkedList<TVertice> vertices = new LinkedList<>();
    for (Comparable eti : VerticesU) {
      vertices.add(new TVertice<>(eti));
    }
    return new TGrafoNoDirigido(vertices, T);
  }

  @Override
  public TGrafoNoDirigido Kruskal() {
    LinkedList<TVertice> vert = new LinkedList();
    // for (TVertice tVertice : this.getVertices().values()) {
    //   vert.add(tVertice);
    // }
    LinkedList<TArista> aris = new LinkedList();
    TGrafoNoDirigido resultado = new TGrafoNoDirigido(vert, aris);
    LinkedList<TArista> aristasOrdenadas = this.ordenarAristasPorCosto();

    for (TArista a : aristasOrdenadas) {
      if (!resultado.getVertices().containsKey(a.getEtiquetaOrigen())) {
        resultado.insertarVertice(a.getEtiquetaOrigen());
      }
      if (!resultado.getVertices().containsKey(a.getEtiquetaDestino())) {
        resultado.insertarVertice(a.getEtiquetaDestino());
      }
      TCaminos caminos = resultado.todosLosCaminos(
        a.getEtiquetaOrigen(),
        a.getEtiquetaDestino()
      );
      if (caminos.getCaminos().size() == 0) {
        boolean test = resultado.insertarArista(a);
        System.out.println("fds");
        // resultado.insertarArista(a.aristaInversa());
      }
    }
    return resultado;
  }

  public double getCostoTotal() {
    double costo = 0;
    for (TArista tArista : lasAristas) {
      costo += tArista.getCosto();
    }
    return costo / 2;
  }

  private LinkedList<TArista> ordenarAristasPorCosto() {
    double[] costos = new double[this.lasAristas.size()];
    int i = 0;
    for (TArista a : this.lasAristas) {
      costos[i] = a.getCosto();
      i++;
    }
    Arrays.sort(costos);
    LinkedList<TArista> aristasOrdenadas = new LinkedList();
    for (int j = 0; j < costos.length; j++) {
      for (TArista a : this.lasAristas) {
        if (a.getCosto() == costos[j] && !aristasOrdenadas.contains(a)) {
          aristasOrdenadas.add(a);
        }
      }
    }
    return aristasOrdenadas;
  }

  @Override
  public Collection<TVertice> bea(Comparable etiquetaOrigen) {
    LinkedList<TVertice> resultado = new LinkedList<TVertice>(); // this.getVertices().size()
    for (TVertice tVertice : resultado) {
      if (!tVertice.getVisitado()) {
        tVertice.bea(resultado);
      }
    }
    return resultado;
  }

  public Collection<TVertice> bea(TVertice verticeOrigen) {
    ArrayList<TVertice> resultado = new ArrayList<TVertice>(
      this.getVertices().size()
    );
    verticeOrigen.bea(resultado);
    return resultado;
  }

  public Map<Comparable, TVertice> beaConConexo() {
    Map<Comparable, TVertice> resultado = new HashMap<Comparable, TVertice>();
    for (TVertice tVertice : this.getVertices().values()) {
      int c = 1;
      if (!tVertice.getVisitado()) {
        tVertice.bea(resultado, c);
        c++;
      }
    }
    return resultado;
  }

  @Override
  public boolean esConexo() {
    Collection<TVertice> vertices = this.getVertices().values();
    Set<TVertice> visitados = new HashSet<>();

    if (vertices.isEmpty()) {
      // An empty graph is considered connected
      return true;
    }

    Queue<TVertice> queue = new LinkedList<>();
    TVertice inicio = vertices.iterator().next();
    queue.add(inicio);
    visitados.add(inicio);

    while (!queue.isEmpty()) {
      TVertice actual = queue.poll();
      LinkedList<TVertice> vecinos = new LinkedList<>();
      actual.bea(vecinos);
      for (TVertice vecino : vecinos) {
        if (!visitados.contains(vecino)) {
          queue.add(vecino);
          visitados.add(vecino);
        }
      }
    }

    return visitados.size() == vertices.size();
  }

  private TVertice buscarVertice(Comparable unaEtiqueta) {
    return getVertices().get(unaEtiqueta);
  }

  @Override
  public boolean conectados(TVertice u, TVertice v) {
    this.desvisitarVertices();
    if (
      this.existeVertice(u.getEtiqueta()) && this.existeVertice(v.getEtiqueta())
    ) {
      return this.buscarVertice(u.getEtiqueta()).conectadoCon(v);
    }
    return false;
  }
}
