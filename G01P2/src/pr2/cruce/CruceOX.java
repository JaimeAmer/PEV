package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public class CruceOX implements Cruce {

	@Override
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int[] datosPadre1 = padre1.getFenotipo();
		int[] datosPadre2 = padre2.getFenotipo();
		int[] datosHijo1 = hijo1.getFenotipo();
		int[] datosHijo2 = hijo2.getFenotipo();
		
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
		
		if((c1 == 0) && (c2 == (datosPadre1.length - 1)) || (c1 == c2))
		{
			hijo1.setFenotipo(datosPadre1);
			hijo2.setFenotipo(datosPadre2);
			return;
		}
		
		generarHijo(datosPadre1, datosPadre2, datosHijo1, c1, c2);
		generarHijo(datosPadre2, datosPadre1, datosHijo2, c1, c2);

		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);		
	}

	private void generarHijo(int[] datosPadre1, int[] datosPadre2, int[] datosHijo, int c1, int c2) {
		// Marcamos el hijo todo con x (-1)
		for(int i = 0; i < datosHijo.length; i++) {
			datosHijo[i] = -1;
		}
		
		for(int i = c1; i <= c2; i++) {
			datosHijo[i] = datosPadre2[i];
		}
		
		int indicePadre = (c2 + 1) % datosPadre1.length;
		int indiceHijo = (c2 + 1) % datosPadre1.length;
		while(indiceHijo != c1) {
			int valor = datosPadre1[indicePadre];
			indicePadre = (indicePadre + 1) % datosPadre1.length;
			int indice = 0;
			boolean terminar = false;
			boolean encontrado = false;
			while(indice < datosPadre1.length && !terminar) {
				if(datosHijo[indice] != -1) {
					if(valor == datosHijo[indice]) {
						encontrado = true;
						terminar = true;
					}
				}
				indice++;
			}
			if(!encontrado) {
				datosHijo[indiceHijo] = valor;
				indiceHijo = (indiceHijo + 1) % datosPadre1.length;
			}			
		}
	}
}
