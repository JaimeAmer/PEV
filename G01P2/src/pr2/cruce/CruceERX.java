package pr2.cruce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Par;

public class CruceERX implements Cruce {

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
		HashSet<Integer>[] tabla = new HashSet[longitud];
		
		// Marcamos el hijo todo con x (-1)
		for(int i = 0; i < longitud; i++) {
			datosHijo[i] = -1;
		}			
		
		// Genera la tabla de conectividades
		for(int posicion = 0; posicion < longitud; posicion++) {
			int elemento = datosPadre1[posicion];
			int indIzq = posicion == 0 ? longitud - 1 : posicion - 1;
			int indDer = posicion == longitud - 1 ? 0 : posicion + 1;
			tabla[elemento] = new HashSet<>();
			tabla[elemento].add(datosPadre1[indIzq]);
			tabla[elemento].add(datosPadre1[indDer]);
			
			int posicion2 = buscaElemento(datosPadre2, elemento);
			indIzq = posicion2 == 0 ? longitud - 1 : posicion2 - 1;
			indDer = posicion2 == longitud - 1 ? 0 : posicion2 + 1;
			tabla[elemento].add(datosPadre2[indIzq]);
			tabla[elemento].add(datosPadre2[indDer]);
		}
		int indice;
		do {
			indice = 0;
			HashSet<Integer> conexionesNuevas;
			boolean bloqueo = false;
			do {
				int valor = datosPadre1[indice];
				datosHijo[indice] = valor;
				indice++;
				// Lista de conexiones del elemento índice
				HashSet<Integer> conexiones = tabla[valor];
				// Tabla con las nuevas conexiones, empieza con todas las conexiones
				conexionesNuevas = new HashSet<>(conexiones);
				// Elimina las conexiones existentes
				for(int i = 0; i < indice; i++) {
					conexionesNuevas.remove(datosHijo[i]);
				}				
				if(conexionesNuevas.isEmpty()) {
					bloqueo = true;
				}
				else {
					valor = siguienteElemento(conexionesNuevas, tabla, randomizer);					
				}
			} while (!bloqueo && indice < longitud);
		} while (indice != longitud);
	}

	// Busca el elemento menos conectado
	private int siguienteElemento(HashSet<Integer> conexiones, HashSet<Integer>[] tabla, Random randomizer) {
		// El Par contiene el elemento y su número de conexiones
		// Si el elemento 8 contiene de vecinos 9 7 1 los valores de Par serán:
		// (9, 4), (7, 2), (1, 3) -> El 9 tiene 4 vecinos, el 7 tiene 2...
		ArrayList<Par<Integer, Integer>> numConexiones = new ArrayList<>();
		for(Integer i : conexiones) {
			numConexiones.add(new Par<Integer, Integer>(i, tabla[i].size()));
		}
		// Busca en numConexiones el valor mínimo
		int minimo = Integer.MAX_VALUE;
		for(Par<Integer, Integer> par : numConexiones) {
			int numVecinos = par.getValue();
			minimo = Math.min(numVecinos, minimo);
		}
		// Busca aleatoriamente en numConexiones hasta que coincida con el mínimo
		// Ej: Si contiene 2 2 3 elegirá aleatoriamente un valor 
		int valor = -1;
		int indice = -1;
		while(valor != minimo) {
			indice = randomizer.nextInt(numConexiones.size());
			valor = numConexiones.get(indice).getValue();			
		}
		int resultado = numConexiones.get(indice).getKey();
		return resultado;
	}

	private int buscaElemento(int[] datosPadre, int elemento) {
		for(int i = 0; i < datosPadre.length; i++) {
			if(datosPadre[i] == elemento)
				return i;
		}
		return -1;
	}
}
