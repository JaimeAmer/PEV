package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public class CruceCX implements Cruce{

	@Override
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int[] datosPadre1 = padre1.getFenotipo();
		int[] datosPadre2 = padre2.getFenotipo();
		int[] datosHijo1 = hijo1.getFenotipo();
		int[] datosHijo2 = hijo2.getFenotipo();
				
		generarHijo(datosPadre1, datosPadre2, datosHijo1, randomizer);
		generarHijo(datosPadre2, datosPadre1, datosHijo2, randomizer);
		
		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);		
	}

	private void generarHijo(int[] datosPadre1, int[] datosPadre2, int[] datosHijo, Random randomizer) {
		int longitud = datosPadre1.length;
		
		// Marcamos el hijo todo con x (-1)
		for(int i = 0; i < longitud; i++) {
			datosHijo[i] = -1;
		}
		
		int posicion = 0;
		int valor;
		do {
			valor = datosPadre1[posicion];
			datosHijo[posicion] = valor;
			valor = datosPadre2[posicion];
			boolean encontrado = false;
			int indice = 0;
			while(!encontrado) {
				if(datosPadre1[indice] == valor) {
					encontrado = true;
					posicion = indice;					
				}
				indice++;
			}			
		} while(posicion != 0);
		
		for(int i = 0; i < longitud; i++) {
			if(datosHijo[i] == -1) {
				datosHijo[i] = datosPadre2[i];
			}
		}
	}
}
