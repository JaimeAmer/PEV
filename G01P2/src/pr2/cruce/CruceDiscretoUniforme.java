package pr2.cruce;

import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Permutaciones;
import pr2.espec.AsignacionCuadratica;

public class CruceDiscretoUniforme implements Cruce {
	
	public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer){
		Cromosoma.copiarCromosoma(hijo1, padre1);
		Cromosoma.copiarCromosoma(hijo2, padre2);
		
		int corte = randomizer.nextInt(padre1.longitudTotal()-1)+1;
		
		cruzarPadreHijo(padre1, hijo1, corte);
		cruzarPadreHijo(padre2, hijo2, corte);
		
		// Si la permutación no es válida crea una válida
		Permutaciones permutaciones = new Permutaciones();
		int[] valido = permutaciones.validar(hijo1.getFenotipo(), randomizer);
		hijo1.setFenotipo(valido);
		valido = permutaciones.validar(hijo2.getFenotipo(), randomizer);
		hijo2.setFenotipo(valido);
	}	

	private static void cruzarPadreHijo(Cromosoma padre, Cromosoma hijo, int corte) {
		int longitudAcumulada = hijo.longitudTotal();
		for(int i = corte; i < longitudAcumulada; i++){
			int numGen = i / hijo.getGenes()[0].getLongitud();
			int numBit = i % hijo.getGenes()[0].getLongitud();
			Boolean bitHijo = hijo.getGenes()[numGen].getAlelo()[numBit];
			Boolean bitPadre = padre.getGenes()[numGen].getAlelo()[numBit];
			hijo.getGenes()[numGen].setBit(numBit, bitPadre);
			padre.getGenes()[numGen].setBit(numBit, bitHijo);
		}
	}

}
