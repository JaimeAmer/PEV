package pr2.mutacion;

import java.util.ArrayList;
import java.util.Random;

import pr2.base.Permutaciones;

public class MutacionPropia implements Mutacion {

	@Override
	public int mutar(int[] individuo, Float probabilidadMutacion, Random randomizer) {
		int mutaciones = 0;
		// Se generan los puntos de corte
		int c1, c2;
		do {
			c1 = 1 + randomizer.nextInt(individuo.length - 1);
			c2 = 1 + randomizer.nextInt(individuo.length - 1);
		
			// Se asegura que el primero sea menor que el segundo
			if(c1 > c2)
			{
				int tmp = c1;
				c1 = c2;
				c2 = tmp;
			}
		} while (c1 == c2);
		
		ArrayList<Integer> candidatos = new ArrayList<>();
		for(int i = c1; i <= c2; i++) {
			candidatos.add(individuo[i]);
		}
		
		// Entre los puntos de corte cambiamos los puntos aleatoriamente
		Permutaciones permutaciones = new Permutaciones();
		for(int i = c1; i <= c2; i++) {
			individuo[i] = permutaciones.siguienteNumero(randomizer, candidatos);
			mutaciones++;
		}
		return mutaciones;
	}	
}