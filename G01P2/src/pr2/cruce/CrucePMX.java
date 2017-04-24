package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Permutaciones;

public class CrucePMX implements Cruce {
	@Override
	public int cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int cruces = 0;
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
		
		if(c1 == c2)
		{
			hijo1.setFenotipo(datosPadre1);
			hijo2.setFenotipo(datosPadre2);
			return 0;
		}
		
		if((c1 == 0) && (c2 == datosPadre1.length - 1))
		{
			hijo1.setFenotipo(datosPadre2);
			hijo2.setFenotipo(datosPadre1);
			return datosPadre1.length;
		}
		
		cruces = resolverConflictos(datosPadre1, datosPadre2, datosHijo1, auxiliar, c1, c2);
		cruces += resolverConflictos(datosPadre2, datosPadre1, datosHijo2, auxiliar, c1, c2);

		Permutaciones permutaciones = new Permutaciones();
		if(!permutaciones.validar(datosHijo1)) {
			int a = 2;
		}
		if(!permutaciones.validar(datosHijo2)) {
			int a = 2;
		}
		
		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);
		
		return cruces;
	}

	private int resolverConflictos(int[] datosPadre1, int[] datosPadre2, int[] datosHijo, int[] auxiliar, int c1, int c2) {
		int cruces = 0;
		int longitud = datosPadre1.length;
		// Marcamos el hijo todo con x (-1)
		for(int i = 0; i < longitud; i++) {
			datosHijo[i] = -1;
		}
		
		// Copia la subcadena del padre al hijo
		for(int i = c1; i <= c2; i++) {
			// Padre1: 		1 2 3 4 5 6 7 8 9
			// Padre2: 		4 5 2 1 8 7 6 9 3
			// Al terminar:
			// Hijo: 		4 2 3 1 8 7 6 5 9 
			// Aux: 		4 _ _ _ _ 7 6 5 _			
			auxiliar[datosPadre2[i]] = datosPadre1[i];
			datosHijo[i] = datosPadre2[i];
		}
		
		// Resolución de conflictos de lado izquierdo
		for(int i = 0; i < c1; i++) {
			int valor = datosPadre1[i];
			// Busca sólo en la subcadena modificada
			int indice = 0;
			boolean encontrado = false;
			while(!encontrado && indice < longitud) {
				if(datosHijo[indice] != -1 && datosHijo[indice] == valor) {
					encontrado = true;
					datosHijo[i] = auxiliar[valor];
					cruces++;
				}
				indice++;				
			}
			if(!encontrado) {
				datosHijo[i] = valor;
				cruces++;
			}
		}
		
		// Resolución de conflictos de lado derecho
		for(int i = c2 + 1; i < longitud; i++) {
			int valor = datosPadre1[i];
			// Busca sólo en la subcadena modificada
			int indice = c1;
			boolean encontrado = false;
			while(!encontrado && indice <= c2) {
				if(datosHijo[indice] != -1 && datosHijo[indice] == valor) {
					encontrado = true;
					datosHijo[i] = auxiliar[valor];
					cruces++;
				}
				indice++;
			}
			if(!encontrado) {
				datosHijo[i] = valor;
				cruces++;
			}
		}
		return cruces;
	}
}
