package pr1.base;

import java.util.ArrayList;
import java.util.Random;

public class SeleccionRuleta implements AlgoritmoSeleccion {

	@Override
	public void seleccionar(ArrayList<Float> aptitudes,
			ArrayList<Float> puntuacionesAcumuladas,
			ArrayList<Cromosoma> seleccionados, Integer tamanoPoblacion,
			ArrayList<Cromosoma> poblacion, Boolean minimizacion,
			Random randomizer) {
		
		for(int i=0; i<tamanoPoblacion; i++){
			float probabilidad = randomizer.nextFloat();
			int selec = 0;
			while(probabilidad > puntuacionesAcumuladas.get(selec).floatValue()){
				selec++;
			}
			seleccionados.get(i).copia(poblacion.get(selec));
		}
	}

}
