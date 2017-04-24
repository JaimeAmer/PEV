package pr2.mutacion;

import java.util.Random;

public class MutacionIntercambio implements Mutacion {

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
		
		int tmp = individuo[c1];
		individuo[c1] = individuo[c2];
		individuo[c2] = tmp;
		mutaciones = 2;
		return mutaciones;
	}
}
