package pr1.base;

import java.util.ArrayList;
import java.util.Random;

public interface AlgoritmoSeleccion {

	void seleccionar(ArrayList<Float> aptitudes, ArrayList<Float> puntuacionesAcumuladas, ArrayList<Cromosoma> seleccionados, Integer tamanoPoblacion, ArrayList<Cromosoma> poblacion, Boolean minimizacion, Random randomizer);

}
