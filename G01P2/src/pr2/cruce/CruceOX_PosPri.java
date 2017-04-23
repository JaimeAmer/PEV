package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public class CruceOX_PosPri implements Cruce {

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
		
		// Se eligen el número de posiciones al azar
		int numPosiciones = randomizer.nextInt(longitud);
		// Se eligen los índices al azar y se copia del padre al hijo
		for(int i = 0; i < numPosiciones; i++) {
			int indice = randomizer.nextInt(longitud);
			datosHijo[indice] = datosPadre2[indice];			
		}
		
		int posicionLibreHijo = 0;
		// Busca el primer valor vacío del hijo
		while(datosHijo[posicionLibreHijo] != -1 && posicionLibreHijo < longitud) {
			posicionLibreHijo++;					
		}
		int indicePadre = 0;
		boolean hijoCompleto = false;
		while(!hijoCompleto) {
			int valor = datosPadre1[indicePadre];
			indicePadre = (indicePadre + 1) % longitud;
			int indice = 0;
			boolean terminar = false;
			boolean encontrado = false;
			while(indice < longitud && !terminar) {
				if(datosHijo[indice] != -1) {
					if(valor == datosHijo[indice]) {
						encontrado = true;
						terminar = true;
					}
				}
				indice++;
			}
			if(!encontrado) {
				datosHijo[posicionLibreHijo] = valor;
				posicionLibreHijo = (posicionLibreHijo + 1) % longitud;
				while(posicionLibreHijo < longitud && datosHijo[posicionLibreHijo] != -1) {
					posicionLibreHijo++;					
				}
				hijoCompleto = posicionLibreHijo == longitud;
			}			
		}
	}
}
