package pr2.mutacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pr2.espec.AsignacionCuadratica;

public class MutacionHeuristica implements Mutacion {
	private float _costeMinimo;
	private int[] _mejorIndividuo;
	@Override
	public int mutar(int[] individuo, Float probabilidadMutacion, Random randomizer) {
		int mutaciones = 0;
		// Limitamos el máximo número de valores a permutar para que el número de permutaciones no sea excesiva
		int min = Math.min(6, individuo.length);
		int num = 2 + randomizer.nextInt(min - 2);
		mutaciones = num;
		
		// Se seleccionan los valores a permutar
		List<Integer> valores = new ArrayList<>();
		int[] indices = new int[num];
		for(int i = 0; i < num; i++) {
			// Se genera un índice que no exista en indices
			boolean existe;
			int ind;
			do {
				ind = randomizer.nextInt(individuo.length);
				existe = false;
				for(int j = 0; j < i; j++) {
					if(indices[j] == ind) {
						existe = true;
					}
				}
			} while(existe);
			valores.add(individuo[ind]);
			indices[i] = ind;
		}
		_costeMinimo = Float.MAX_VALUE;
		_mejorIndividuo = new int[individuo.length];
		for(int i = 0; i < individuo.length;i ++) {
			_mejorIndividuo[i] = individuo[i];
		}
		
		int[] copia = new int[individuo.length];
		for(int i = 0; i < individuo.length;i ++) {
			copia[i] = individuo[i];
		}
		permute(valores, 0, copia, indices);
		for(int i = 0; i < individuo.length; i++) {
			individuo[i] = _mejorIndividuo[i];
		}
		return mutaciones;
	}
	
	private void permute(List<Integer> arr, int k, int[] individuo, int[] indices) {
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1, individuo, indices);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
        	// Aquí hay una permutacion completa
        	// Copiamos la permutacion en el individuo
        	for(int i = 0; i < indices.length; i++) {
        		individuo[indices[i]] = arr.get(i);
        	}
        	float coste = AsignacionCuadratica.getAptitud(individuo);
        	if(coste < _costeMinimo) {
        		_costeMinimo = coste;
        		for(int i = 0; i < _mejorIndividuo.length; i++) {
        			_mejorIndividuo[i] = individuo[i];
        		}
        	}
            //System.out.println(java.util.Arrays.toString(arr.toArray()));
        }
    }
}
