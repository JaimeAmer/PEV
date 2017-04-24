package pr2.mutacion;

import java.util.Random;

import pr2.base.Cromosoma;

public interface Mutacion {
	public int mutar(int[] individuo, Float probabilidadMutacion, Random randomizer);	
}
