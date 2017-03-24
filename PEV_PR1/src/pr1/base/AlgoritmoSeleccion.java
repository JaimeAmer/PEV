package pr1.base;

import java.util.ArrayList;
import java.util.Random;

public interface AlgoritmoSeleccion {

	void seleccionar(Float[] aptitudes, Float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, Integer tamanoPoblacion, Cromosoma[] poblacion, Boolean minimizacion, Random randomizer);

}
