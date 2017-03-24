package pr1.base;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class SeleccionTorneoProbabilistico implements AlgoritmoSeleccion{

	private Integer participantes;
	private static final Float probMejor = 0.7f; 
	
	public SeleccionTorneoProbabilistico(Integer participantes) {
		this.participantes = participantes;
	}

	@Override
	public void seleccionar(Float[] aptitudes,
			Float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, Integer tamanoPoblacion,
			Cromosoma[] poblacion, Boolean minimizacion,
			Random randomizer) {
		// TODO Auto-generated method stub
		Float[] elegidosValue = new Float[participantes];
		Integer[] elegidosIndex = new Integer[participantes];
		
		for(int i=0; i<tamanoPoblacion; i++){
			for(int j=0; j<participantes; j++){
				int indiceAleat = randomizer.nextInt(tamanoPoblacion);
				elegidosValue[j] = aptitudes[indiceAleat];
				elegidosIndex[j] = indiceAleat;
			}
			
			boolean mejor = randomizer.nextFloat() < probMejor;
			
			float maximoValor = Integer.MIN_VALUE;
			float minimoValor = Float.MAX_VALUE;
			
			int maximo = 0;
			int minimo = 1;
			for(int j=0; j<participantes; j++){
				if(elegidosValue[j].floatValue() > maximoValor){
					maximoValor = elegidosValue[j].floatValue();
					maximo = j;
				}
				else if(elegidosValue[j].floatValue() < minimoValor){
					minimoValor = elegidosValue[j].floatValue();
					minimo = j;
				}
			}
			
			if(mejor){
				seleccionados[i].copia(poblacion[elegidosIndex[maximo]]);
			}
			else seleccionados[i].copia(poblacion[elegidosIndex[minimo]]);
			
		}
	}

	
}
