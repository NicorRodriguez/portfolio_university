package nrodriguez.grafodirigido;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // vertices del grafo.-
    Comparable[][] matrizPredecesores;
    Double[][] matrizCosto = null;

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino.
     * En caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean inv�lidas, retorna falso.
     *
     * @param nomVerticeOrigen
     * @param nomVerticeDestino
     * @return 
     */
    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
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
        boolean res = false;
        TVertice vertAEliminar = buscarVertice(nombreVertice);
        if ((nombreVertice != null)) {
            for (TVertice vertice : vertices.values()) {
                vertAEliminar.eliminarAdyacencia(vertice.getEtiqueta());
                vertice.eliminarAdyacencia(nombreVertice);
            }
            vertices.remove(nombreVertice);
        }
        matrizPredecesores = null;
        matrizCosto = null;
        return res;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las
     * etiquetas pasadas por par�metro deben ser v�lidas.
     *
     * @param etiquetaOrigen
     * @param etiquetaDestino
     * @return True si existe la adyacencia, false en caso contrario
     */
    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
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
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea
     * valida, se deben cumplir los siguientes casos: 1) Las etiquetas pasadas
     * por parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @param arista
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    @Override
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen()!= null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
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
    @Override
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
     if (!existeVertice(unaEtiqueta)) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    
    
    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }
    
 

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Object[] etiquetasarray = this.getEtiquetasOrdenado();
        Comparable[] excentricidad = new Comparable[]{Double.MAX_VALUE, null};
        for (int i = 0; i < etiquetasarray.length; i++) {
            Comparable excentricidadVertice = this.obtenerExcentricidad((Comparable) etiquetasarray[i]);
            if ((excentricidadVertice.compareTo(-1d) > 0) && excentricidadVertice.compareTo(excentricidad[0]) < 0) {
                excentricidad[0] = excentricidadVertice;
                excentricidad[1] = i;
            }
        }
        return (Comparable) etiquetasarray[(int) excentricidad[1]];
    }

    @Override
    public Double[][] floyd() {
        if(matrizCosto != null){
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
                    if((matrizCosto[i][k] + matrizCosto[k][j]) < matrizCosto[i][j]){
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
            if ((floyd[i][indiceEtiqueta] != Double.MAX_VALUE) && i != indiceEtiqueta && floyd[i][indiceEtiqueta] > excentricidad) {
                excentricidad = floyd[i][indiceEtiqueta];
            }
        }
        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        if(matrizCosto == null) floyd();
        int n = vertices.size();
        boolean[][] matrizWarshall = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizWarshall[i][j] = matrizCosto[i][j] != Double.MAX_VALUE;
            }
        }

        return matrizWarshall;
    }

    @Override
    public Collection<TVertice> bpf() {
        Collection<TVertice>  res = new LinkedList<TVertice>();
        for (TVertice vertice : vertices.values()) {
            if(!vertice.getVisitado()){
                res.addAll(bpf(vertice));
            }
        }
        return res;
    }

   
    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        Collection<TVertice>  visitados = new LinkedList<TVertice>();
        TVertice vertice = buscarVertice(etiquetaOrigen);
        vertice.bpf(visitados);
        return visitados;
    }


    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        Collection<TVertice>  visitados = new LinkedList<TVertice>();
        vertice.bpf(visitados);
        return visitados;
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }
    }
    
    
}
