package pr1.base;

import java.util.ArrayList;
import java.util.Random;

public class SeleccionEstocasticoUniversal implements AlgoritmoSeleccion{

	private Integer participantes;
	
	public SeleccionEstocasticoUniversal(Integer participantes) {
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
		/*
		for(int i=0; i<tamanoPoblacion; i++){
			for(int j=0; j<participantes; j++){
				int indiceAleat = randomizer.nextInt(tamanoPoblacion);
				elegidosValue.add(j, aptitudes.get(indiceAleat));
				elegidosIndex.add(j, indiceAleat);
			}
			
			float maximoValor = Integer.MIN_VALUE;
			int maximo = 0;
			for(int j=0; j<participantes; j++){
				if(elegidosValue.get(j).floatValue() > maximoValor){
					maximoValor = elegidosValue.get(j).floatValue();
					maximo = j;
				}
			}
			
			seleccionados.get(i).copia(poblacion.get(elegidosIndex.get(maximo)));
		}*/
	}
}
