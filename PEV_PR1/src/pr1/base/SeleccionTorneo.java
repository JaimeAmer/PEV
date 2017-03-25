package pr1.base;

import java.util.Random;

public class SeleccionTorneo implements AlgoritmoSeleccion{

	private Integer participantes;
	
	public SeleccionTorneo(Integer participantes) {
		this.participantes = participantes;
	}

	@Override
	public void seleccionar(Float[] aptitudes,
			Float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, Integer tamanoPoblacion,
			Cromosoma[] poblacion, Boolean minimizacion,
			Random randomizer) {
		
		Float[] elegidosValue = new Float[participantes];
		Integer[] elegidosIndex = new Integer[participantes];
		
		for(int i=0; i<tamanoPoblacion; i++){
			for(int j=0; j<participantes; j++){
				int indiceAleat = randomizer.nextInt(tamanoPoblacion);
				elegidosValue[j] = aptitudes[indiceAleat];
				elegidosIndex[j] = indiceAleat;
			}
			
			float maximoValor = Integer.MIN_VALUE;
			int maximo = 0;
			for(int j=0; j<participantes; j++){
				if(elegidosValue[j].floatValue() > maximoValor){
					maximoValor = elegidosValue[j].floatValue();
					maximo = j;
				}
			}
			
			seleccionados[i].copia(poblacion[elegidosIndex[maximo]]);
		}
	}
}
