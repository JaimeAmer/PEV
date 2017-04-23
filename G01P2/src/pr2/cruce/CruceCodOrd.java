package pr2.cruce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pr2.base.Cromosoma;

public class CruceCodOrd implements Cruce {

	@Override
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		int[] datosPadre1 = padre1.getFenotipo();
		int[] datosPadre2 = padre2.getFenotipo();
		int[] datosHijo1 = hijo1.getFenotipo();
		int[] datosHijo2 = hijo2.getFenotipo();
		int longitud = datosPadre1.length;
		int[] resultado1 = new int[longitud];
		int[] resultado2 = new int[longitud];
				
		generarResultado(datosPadre1, resultado1, randomizer);
		generarResultado(datosPadre2, resultado2, randomizer);
		
		cruzar(resultado1, resultado2, randomizer);
		
		ArrayList<Integer> lista = crearLista(datosPadre1);
		generarHijo(lista, resultado1, datosHijo1);
		lista = crearLista(datosPadre1);
		generarHijo(lista, resultado2, datosHijo2);
		
		hijo1.setFenotipo(datosHijo1);
		hijo2.setFenotipo(datosHijo2);		
	}

	private void generarHijo(ArrayList<Integer> lista, int[] resultado, int[] datosHijo) {
		for(int i = 0; i < resultado.length; i++) {
			int indice = resultado[i];
			int valor = lista.get(indice);
			lista.remove(indice);
			datosHijo[i] = valor;
		}
	}

	private void cruzar(int[] resultado1, int[] resultado2, Random randomizer) {
		int puntoCruce = 1 + randomizer.nextInt(resultado1.length - 1);
		
		for(int i = puntoCruce; i < resultado1.length; i++) {
			int temp = resultado1[i];
			resultado1[i] = resultado2[i];
			resultado2[i] = temp;
		}		
	}

	private void generarResultado(int[] datosPadre, int[] resultado, Random randomizer) {
		int longitud = datosPadre.length;
		
		ArrayList<Integer> lista = crearLista(datosPadre);
		for(int i = 0; i < longitud; i++) {
			int valor = datosPadre[i];
			int indice = lista.indexOf(valor);
			lista.remove(indice);
			resultado[i] = indice;
		}		
	}

	private ArrayList<Integer> crearLista(int[] datosPadre) {
		// Se crea una copia del padre y se ordena
		int[] datos = new int[datosPadre.length];
		for(int i = 0; i < datosPadre.length; i++) {
			datos[i] = datosPadre[i];
		}
		Arrays.sort(datos);
		
		// Crea una lista con los datos ordenados
		ArrayList<Integer> lista = new ArrayList<>();
		for(int i = 0; i < datosPadre.length; i++) {
			lista.add(datosPadre[i]);
		}
		
		return lista;
	}
}
