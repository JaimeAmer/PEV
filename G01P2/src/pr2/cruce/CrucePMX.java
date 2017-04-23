package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public class CrucePMX implements Cruce {
	@Override
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int[] datosPadre1 = padre1.getFenotipo();
		int[] datosPadre2 = padre2.getFenotipo();
		int[] datosHijo1 = hijo1.getFenotipo();
		int[] datosHijo2 = hijo2.getFenotipo();
		int[] auxiliar = new int[datosPadre1.length];
		
		// Se eligen los puntos de cruce
		int c1 = randomizer.nextInt(datosPadre1.length);
		int c2 = randomizer.nextInt(datosPadre1.length);
		
		// Se asegura que el primero sea menor que el segundo
		if(c1 > c2)
		{
			int tmp = c1;
			c1 = c2;
			c2 = tmp;
		}
		
		if((c1 == 0) && (c2 == datosPadre1.length - 1) || (c1 == c2))
		{
			hijo1.setFenotipo(datosPadre1);
			hijo2.setFenotipo(datosPadre2);
			return;
		}
		
		resolverConflictos(datosPadre1, datosHijo1, auxiliar, c1, c2);
		resolverConflictos(datosPadre2, datosHijo2, auxiliar, c1, c2);
		
		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);		
	}

	private void resolverConflictos(int[] datosPadre, int[] datosHijo, int[] auxiliar, int c1, int c2) {
		// Copia la subcadena del padre al hijo
		for(int i = c1; i <= c2; i++) {
			// Padre: 		1 2 3 4 5 6 7 8 9
			// Hijo: 		4 5 2 1 8 7 6 9 3
			// Al terminar:
			// Hijo: 		4 5 2 4 5 6 7 9 3 
			// Aux: 		_ _ _ 1 8 7 6 _ _			
			auxiliar[datosHijo[i]] = datosPadre[i];
			datosHijo[i] = datosPadre[i];			
		}
		
		// Resolución de conflictos de lado izquierdo
		for(int i = 0; i < c1; i++) {
			int valor = datosHijo[i];
			// Busca sólo en la subcadena modificada
			int indice = c1;
			boolean encontrado = false;
			while(!encontrado && indice <= c2) {
				if(datosHijo[indice] == valor) {
					encontrado = true;
					datosHijo[indice] = auxiliar[valor];
				}
				indice++;
			}
		}
		
		// Resolución de conflictos de lado derecho
		for(int i = c2 + 1; i < datosHijo.length; i++) {
			int valor = datosHijo[i];
			// Busca sólo en la subcadena modificada
			int indice = c1;
			boolean encontrado = false;
			while(!encontrado && indice <= c2) {
				if(datosHijo[indice] == valor) {
					encontrado = true;
					datosHijo[indice] = auxiliar[valor];
				}
				indice++;
			}
		}
	}
}
