package pr2.selec;

import java.util.Random;

import pr2.base.Cromosoma;

public class SeleccionEstocasticoUniversal implements AlgoritmoSeleccion{


	@Override
	public void seleccionar(Float[] aptitudes,
			Float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, Integer tamanoPoblacion,
			Cromosoma[] poblacion, Boolean minimizacion,
			Random randomizer) {
		
		
		int numMarcas = tamanoPoblacion;
		float distanciaMarcas = 1 / (float) numMarcas;
		// Valor entre 0 y 1/N
		float posicionInicial = randomizer.nextFloat() / tamanoPoblacion;
		// Valor a seleccionar
		float posicion = posicionInicial;
		
		int i=0;
		int selec = 0;
		while(posicion < 1.0){
			while(selec < tamanoPoblacion && posicion > puntuacionesAcumuladas[selec].floatValue()){
				selec++;
			}
			// Ha encontrado el individuo
			if(selec < tamanoPoblacion && i < tamanoPoblacion) {
				seleccionados[i++].copia(poblacion[selec]);
			}
			posicion += distanciaMarcas;
		}
	}
}
