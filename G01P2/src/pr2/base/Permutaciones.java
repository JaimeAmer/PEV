package pr2.base;

import java.util.ArrayList;
import java.util.Random;

public class Permutaciones {
	/**
	 * Genera una permutaci�n de la longitud deseada
	 * No se repite ning�n n�mero
	 * El rango de n�meros generados ir� de 1 a longitud
	 */
	public int[] generar(int longitud, Random randomizer) {
		int[] resultado = new int[longitud];
		// Genera la lista de candidatos
		ArrayList<Integer> candidatos = generaCandidatos(longitud);
		
		for(int i = 0; i < longitud; i++) {
			resultado[i] = siguienteNumero(randomizer, candidatos);
		}
		return resultado;
	}

	// Comprueba que muestra es una permutaci�n v�lida.
	// Comprueba que cada n�mero est� dentro de los l�mites 
	// y si hay n�meros repetidos los sustituye
	public int[] validar(int[] muestra, Random randomizer) {
		int[] resultado = new int[muestra.length];
		
		ArrayList<Integer> candidatos = generaCandidatos(muestra.length);
		
		for(int i = 0; i < muestra.length; i++) {
			resultado[i] = validarNumero(randomizer, candidatos, muestra[i], muestra.length);
		}
		
		return resultado;
	}

	public boolean validar(int[] muestra) {
		ArrayList<Integer> candidatos = generaCandidatos(muestra.length);
		
		for(int i = 0; i < muestra.length; i++) {
			int valor = candidatos.indexOf(muestra[i]);
			if (valor == -1) {
				return false;
			}
			candidatos.remove(valor);
		}		
		return true;
	}
	
	private int validarNumero(Random randomizer, ArrayList<Integer> candidatos, int numero, int limite) {
		// Comprueba el rango del n�mero 1-limite
		if(numero < 1 || numero > limite) {
			return siguienteNumero(randomizer, candidatos);
		}
		// Debe estar en la lista de candidatos, si no est� elige uno
		return extraeNumero(numero, candidatos, randomizer);
	}

	// Si el n�mero est� en la lista de candidatos lo extrae y lo devuelve
	// Si no est�, elige un candidato y lo devuelve
	private int extraeNumero(int numero, ArrayList<Integer> candidatos, Random randomizer) {
		int indice = candidatos.indexOf(numero);
		if(indice > -1) {
			candidatos.remove(indice);
			return numero;
		}
		return siguienteNumero(randomizer, candidatos);
	}

	// Extrae aleatoriamente un candidato y lo devuelve
	public int siguienteNumero(Random randomizer, ArrayList<Integer> candidatos) {
		int indice = randomizer.nextInt(candidatos.size());
		int valor = candidatos.get(indice);
		candidatos.remove(indice);
		return valor;
	}
	
	public ArrayList<Integer> generaCandidatos(int longitud) {
		ArrayList<Integer> candidatos = new ArrayList<>();
		for(int i = 0; i < longitud; i++) {
			candidatos.add(i, i);
		}
		return candidatos;
	}
}
