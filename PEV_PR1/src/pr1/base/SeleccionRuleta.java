package pr1.base;

import java.util.Random;

public class SeleccionRuleta implements AlgoritmoSeleccion {

	@Override
	public void seleccionar(Float[] aptitudes,
			Float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, Integer tamanoPoblacion,
			Cromosoma[] poblacion, Boolean minimizacion,
			Random randomizer) {
		
		for(int i=0; i<tamanoPoblacion; i++){
			float probabilidad = randomizer.nextFloat();
			int selec = 0;
			while(probabilidad > puntuacionesAcumuladas[selec].floatValue()){
				selec++;
			}
			seleccionados[i].copia(poblacion[selec]);
		}
	}

}
