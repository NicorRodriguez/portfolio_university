package nrodriguez;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TVertice<T> implements IVertice {

  private Comparable etiqueta;
  private LinkedList<TAdyacencia> adyacentes;
  private boolean visitado;
  private T datos;

  public Comparable getEtiqueta() {
    return etiqueta;
  }

  public LinkedList<TAdyacencia> getAdyacentes() {
    return adyacentes;
  }

  public T getDatos() {
    return datos;
  }

  public TVertice(Comparable unaEtiqueta) {
    this.etiqueta = unaEtiqueta;
    adyacentes = new LinkedList();
    visitado = false;
  }

  public void setVisitado(boolean valor) {
    this.visitado = valor;
  }

  public boolean getVisitado() {
    return this.visitado;
  }

  @Override
  public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
    if (verticeDestino != null) {
      return buscarAdyacencia(verticeDestino.getEtiqueta());
    }
    return null;
  }

  @Override
  public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
    TAdyacencia ady = buscarAdyacencia(verticeDestino);
    if (ady != null) {
      return ady.getCosto();
    }
    return Double.MAX_VALUE;
  }

  @Override
  public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
    if (buscarAdyacencia(verticeDestino) == null) {
      TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
      return adyacentes.add(ady);
    }
    return false;
  }

  @Override
  public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
    TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
    if (ady != null) {
      adyacentes.remove(ady);
      return true;
    }
    return false;
  }

  @Override
  public TVertice primerAdyacente() {
    if (this.adyacentes.getFirst() != null) {
      return this.adyacentes.getFirst().getDestino();
    }
    return null;
  }

  @Override
  public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
    for (TAdyacencia adyacencia : adyacentes) {
      if (
        adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0
      ) {
        return adyacencia;
      }
    }
    return null;
  }

  @Override
  public void bpf(Collection<TVertice> visitados) {
    this.visitado = true;
    for (TAdyacencia ady : adyacentes) {
      TVertice<T> vertice = ady.getDestino();
      if (!vertice.getVisitado()) {
        visitados.add(vertice);
        vertice.bpf(visitados);
      }
    }
  }

  @Override
  public TCaminos todosLosCaminos(
    Comparable etVertDest,
    TCamino caminoPrevio,
    TCaminos todosLosCaminos
  ) {
    this.setVisitado(true);
    for (TAdyacencia adyacencia : this.getAdyacentes()) {
      TVertice destino = adyacencia.getDestino();
      if (!destino.getVisitado()) {
        if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
          TCamino copia = caminoPrevio.copiar();
          copia.agregarAdyacencia(adyacencia);
          todosLosCaminos.getCaminos().add(copia);
        } else {
          caminoPrevio.agregarAdyacencia(adyacencia);
          destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
          caminoPrevio.eliminarAdyacencia(adyacencia);
        }
      }
    }
    this.setVisitado(false);
    return todosLosCaminos;
  }

  @Override
  public void bea(Collection<TVertice> visitados) {
    this.setVisitado(true);
    Queue<TVertice> cola = new LinkedList<TVertice>();
    visitados.add(this);
    for (TAdyacencia ady : this.adyacentes) {
      if (!ady.getDestino().getVisitado()) {
        visitados.add(ady.getDestino());
        cola.add(ady.getDestino());
        ady.getDestino().setVisitado(true);
      }
    }
  }

  public void bea(Map<Comparable, TVertice> resultado, int conexo) {
    this.setVisitado(true);
    Queue<TVertice> cola = new LinkedList<TVertice>();
    resultado.put(conexo, this);
    for (TAdyacencia ady : this.adyacentes) {
      if (!ady.getDestino().getVisitado()) {
        resultado.put(conexo, ady.getDestino());
        cola.add(ady.getDestino());
        ady.getDestino().setVisitado(true);
      }
    }
  }

  @Override
  public TVertice siguienteAdyacente(TVertice w) {
    TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
    int index = adyacentes.indexOf(adyacente);
    if (index + 1 < adyacentes.size()) {
      return adyacentes.get(index + 1).getDestino();
    }
    return null;
  }

  @Override
  public boolean tieneCiclo(TCamino camino) {
    setVisitado(true);
    boolean ciclo = false;
    for (TAdyacencia adyacencia : this.getAdyacentes()) {
      if (ciclo) {
        break;
      }
      TVertice w = adyacencia.getDestino();
      if (!w.getVisitado()) {
        camino.agregarAdyacencia(adyacencia);
        ciclo = w.tieneCiclo(camino);
      } else {
        if (camino.buscarVertice(w.getEtiqueta())) {
          ciclo = true;
          System.out.println(
            "hay ciclo : " + camino.imprimirDesdeClave(w.etiqueta)
          );
        }
      }
    }
    camino.getOtrosVertices().remove(this.getEtiqueta());
    return ciclo;
  }

  @Override
  // Este metodo tiene como precondicion que todos los vertices del grafo se encuentren con getVisitado en false.
  public boolean conectadoCon(TVertice destino) {
    setVisitado(true);
    boolean encontrado = false;
    if (this.getEtiqueta().equals(destino.getEtiqueta())) {
      return true;
    }
    for (TAdyacencia adyacente : adyacentes) {
      TVertice vertAdy = adyacente.getDestino();
      if (!vertAdy.getVisitado()) {
        encontrado = vertAdy.conectadoCon(destino);
        if (encontrado) {
          return true;
        }
      }
    }
    return encontrado;
  }

  public void ClasificacionTopologica(Collection<Comparable> lista) {
    setVisitado(true);
    for (TAdyacencia ady : (LinkedList<TAdyacencia>) this.adyacentes) {
      TVertice vert = ady.getDestino();
      if (!vert.getVisitado()) {
        vert.ClasificacionTopologica(lista);
      }
    }
    lista.add(etiqueta);
  }
}
