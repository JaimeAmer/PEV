package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;

public interface Cruce {
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer);
}
