package portfolio;

public class TElementoAB<T> implements IElementoAB<T> {

	private final Comparable etiqueta;
	private TElementoAB hijoIzq;
	private TElementoAB hijoDer;
	private final T datos;
	public int miAltura = 0;

	/**
	 * @param unaEtiqueta
	 * @param unosDatos
	 */
	@SuppressWarnings("unchecked")
	public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
		etiqueta = unaEtiqueta;
		datos = unosDatos;
	}

	@Override
	public Comparable getEtiqueta() {
		return etiqueta;
	}

	@Override
	public TElementoAB<T> getHijoIzq() {
		return hijoIzq;
	}

	@Override
	public TElementoAB<T> getHijoDer() {
		return hijoDer;
	}

	@Override
	public void setHijoIzq(TElementoAB<T> elemento) {
		hijoIzq = elemento;
	}

	@Override
	public void setHijoDer(TElementoAB<T> elemento) {
		hijoDer = elemento;
	}

	@Override
	public TElementoAB<T> buscar(Comparable unaEtiqueta) {
		if (unaEtiqueta.compareTo(etiqueta) == 0) {
			return this;
		}
		if (unaEtiqueta.compareTo(etiqueta) < 0) {
			return hijoIzq.buscar(unaEtiqueta);
		} else {
			return hijoDer.buscar(unaEtiqueta);
		}
	}

	@Override
	public boolean insertar(TElementoAB<T> elemento) {
		if(this.etiqueta.compareTo(elemento.etiqueta) == 0){
			return false;
		}

		if(this.etiqueta.compareTo(elemento.etiqueta) < 0){
			if(this.hijoIzq != null) return this.hijoIzq.insertar(elemento);
			this.hijoIzq =elemento;
			return true;
		}

		if(this.etiqueta.compareTo(elemento.etiqueta) > 0){
			if(this.hijoDer != null) return this.hijoDer.insertar(elemento);
			this.hijoDer =elemento;
			return true;
		}

		return false;
	}

	@Override
	public String preOrden() {
		String res = "";

		res += etiqueta + ", ";

		if (hijoIzq != null)
			res += hijoIzq.preOrden();
		if (hijoDer != null)
			res += hijoDer.preOrden();

		return res;
	}

	@Override
	public String inOrden() {
		String res = "";

		if (hijoIzq != null)
			res += hijoIzq.inOrden();

		res += etiqueta + ", ";

		if (hijoDer != null)
			res += hijoDer.inOrden();

		return res;
	}

	@Override
	public String postOrden() {
		String res = "";

		if (hijoIzq != null)
			res += hijoIzq.postOrden();

		if (hijoDer != null)
			res += hijoDer.postOrden();

		res += etiqueta + ", ";

		return res;
	}

	@Override
	public T getDatos() {
		return datos;
	}

	@Override
	public TElementoAB eliminar(Comparable unaEtiqueta) {
		if (etiqueta.compareTo(unaEtiqueta) < 0) {
			if (hijoIzq != null) {
				hijoIzq = hijoIzq.eliminar(unaEtiqueta);
			}
			return this;
		}
		if (etiqueta.compareTo(unaEtiqueta) > 0) {
			if (hijoDer != null) {
				hijoIzq = hijoDer.eliminar(unaEtiqueta);
			}
			return this;
		}

		return quitaNodo();
	}

	private TElementoAB quitaNodo() {
		if (hijoIzq == null) {
			return hijoDer;
		}
		if (hijoDer == null) {
			return hijoIzq;
		}

		TElementoAB elHijo = hijoIzq;
		TElementoAB elPadre = this;

		while (elHijo.hijoDer != null) {
			elPadre = elHijo;
			elHijo = elHijo.hijoDer;
		}

		if (elPadre != this) {
			elPadre.hijoDer = elHijo.hijoIzq;
			elHijo.hijoIzq = hijoIzq;
		}

		elHijo.hijoDer = hijoDer;

		return elHijo;
	}

	// altura del arbol
	public int alturaArbol() {
		if (hijoDer == null || hijoIzq == null) {
			return 0;
		}
		return 1 + Integer.max(hijoDer.alturaArbol(), hijoIzq.alturaArbol());
	}

	public int hojasArbol() {
		int res = 0;
		if (hijoDer != null) {
			res += hijoDer.hojasArbol();
		}

		if (hijoIzq != null) {
			res += hijoIzq.hojasArbol();
		}
		if (hijoDer == null && hijoIzq == null) {
			return 1 + res;
		}
		return res;
	}

}
