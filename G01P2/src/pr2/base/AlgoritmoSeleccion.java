package pr2.base;

import java.util.Random;

public interface AlgoritmoSeleccion {

	void seleccionar(Float[] aptitudes, Float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, Integer tamanoPoblacion, Cromosoma[] poblacion, Boolean minimizacion, Random randomizer);

}
