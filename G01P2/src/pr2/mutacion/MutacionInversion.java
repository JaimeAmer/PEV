package pr2.mutacion;

import java.util.Random;

public class MutacionInversion implements Mutacion {

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
		
		// Se invierten los elementos de la zona de corte
		int[] corte = new int[c2 - c1 + 1];
		for(int i = c1; i <= c2; i++) {
			corte[i - c1] = individuo[i];
		}
		
		for(int i = corte.length - 1; i >= 0; i--) {
			int indice = c1 + (corte.length - 1) - i;
			individuo[indice] = corte[i];
			mutaciones++;
		}
		return mutaciones;
	}
}
