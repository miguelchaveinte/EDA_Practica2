package monticulo;

import java.util.Random;
import es.uva.eda.clases.*;

public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Monticulo minHeap = new MonticuloMinimo(10);
		
		System.out.println("Insertando nodos a los monticulos");
		for (int i = 0; i < 10; i++) {
			minHeap.insertar(new Nombre("fulano",rand.nextInt(98)+1));
			System.out.println("Luego de " + (i+1) + " insercion:");
			System.out.println("Raiz del monticulo de Manimo: " + minHeap.pispear());

		}
		
		System.out.println("Monticulo de Minimo Completo");
		minHeap.mostrar();
		System.out.println("Monticulo de Minimo Completo");
		minHeap.intercambiar(new Nombre("fulano",0));
		minHeap.mostrar();
		
		for (int i = 0; i < 10; i++) {
			Nombre a=minHeap.eliminar();
			System.out.println("elimine el "+a.getNumApariciones());
			minHeap.mostrar();
			
		}

		
	}

}
