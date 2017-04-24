package pr2.cruce;

import java.util.ArrayList;
import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Permutaciones;

public class CruceOX_OrPri implements Cruce {

	@Override
	public int cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int cruces = 0;
		int[] datosPadre1 = padre1.getFenotipo();
		int[] datosPadre2 = padre2.getFenotipo();
		int[] datosHijo1 = hijo1.getFenotipo();
		int[] datosHijo2 = hijo2.getFenotipo();
				
		cruces = generarHijo(datosPadre1, datosPadre2, datosHijo1, randomizer);
		cruces += generarHijo(datosPadre2, datosPadre1, datosHijo2, randomizer);
		
		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);
		
		return cruces;
	}

	private int generarHijo(int[] datosPadre1, int[] datosPadre2, int[] datosHijo, Random randomizer) {
		int cruces = 0;
		int longitud = datosPadre1.length;
		int[] auxiliar = new int[longitud];
		
		// Marcamos el hijo todo con x (-1)
		for(int i = 0; i < longitud; i++) {
			datosHijo[i] = -1;
		}
		
		// Se eligen el número de posiciones al azar
		int numPosiciones = 4;//1 + randomizer.nextInt(longitud - 1);
		// Se eligen los índices al azar y se guardan los valores en un array auxiliar
		Permutaciones numeros = new Permutaciones();
		ArrayList<Integer> candidatos = numeros.generaCandidatos(longitud);		
		for(int i = 0; i < numPosiciones; i++) {
			int indice = numeros.siguienteNumero(randomizer, candidatos);
			auxiliar[i] = datosPadre1[indice];
		}
		
		// Copia del P2 al hijo excepto los valores del array auxiliar
		for(int i = 0; i < longitud; i++) {
			int valor = datosPadre2[i];
			int indice = 0;
			boolean encontrado = false;
			while(!encontrado && indice < numPosiciones) {
				if(auxiliar[indice++] == valor) 
					encontrado = true;				
			}
			if(!encontrado)
				datosHijo[i] = valor;
		}
		
		// Rellena los huecos del hijo con el array auxiliar
		for(int i = 0; i < numPosiciones; i++) {
			int indice = 0;
			while(datosHijo[indice] != -1) {
				indice++;
			}
			datosHijo[indice] = auxiliar[i];
			cruces++;
		}
		return cruces;
	}	
}
