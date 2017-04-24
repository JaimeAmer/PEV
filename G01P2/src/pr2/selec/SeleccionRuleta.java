package pr2.selec;

import java.util.Random;

import pr2.base.Cromosoma;

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
