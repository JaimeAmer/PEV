package pr2.selec;

import java.util.Random;

import pr2.base.Cromosoma;

public interface AlgoritmoSeleccion {

	void seleccionar(Float[] aptitudes, Float[] puntuacionesAcumuladas, Cromosoma[] seleccionados, Integer tamanoPoblacion, Cromosoma[] poblacion, Boolean minimizacion, Random randomizer);

}
