package monticulo;
import es.uva.eda.clases.*;

public class MonticuloMinimo extends Monticulo {

	public MonticuloMinimo(int tamañoMaximo) {
		super(tamañoMaximo);
		this.nodo[0] = new Nombre("minimo",Integer.MIN_VALUE);
	}

	@Override
	protected boolean condicion(int hijo, int padre) {
		boolean a=this.nodo[hijo].getNumApariciones() >= this.nodo[padre].getNumApariciones();
		return this.nodo[hijo].getNumApariciones() >= this.nodo[padre].getNumApariciones();

	}
	
}
