package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public class CrucePropio implements Cruce {

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
		int puntoCruce = 1 + randomizer.nextInt(longitud - 1);
		
		for(int i = 0; i < puntoCruce; i++) {
			datosHijo[i] = datosPadre1[i];
		}
		
		// La parte derecha del hijo se obtiene del segundo padre.
		// Se va leyendo del padre y si no existe en el hijo se copia
		int indicePadre = 0;
		for(int i = puntoCruce; i < longitud; i++) {
			int valor;
			boolean encontrado;
			do {
				encontrado = false;
				valor = datosPadre2[indicePadre];
				int j = 0;
				while(!encontrado && j < i) {				
					if(valor == datosHijo[j])
						encontrado = true;
					j++;
				}
				if(encontrado)
					indicePadre++;
			} while (encontrado && indicePadre < longitud);
			datosHijo[i] = valor;
			cruces++;
		}
		return cruces;
	}
}
