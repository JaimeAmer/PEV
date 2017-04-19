package pr2.base;

import java.util.Random;


public abstract class Cromosoma {

	protected Gen[] genes;
	
	abstract protected int[] getFenotipo();
	
	abstract protected float getAptitud();
	
	static private void cruzarPv(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer){
		copiarCromosoma(hijo1, padre1);
		copiarCromosoma(hijo2, padre2);
		
		int corte = randomizer.nextInt(padre1.longitudTotal()-1)+1;
		
		cruzarPadreHijo(padre1, hijo1, corte);
		cruzarPadreHijo(padre2, hijo2, corte);		
	}
	
	private static void cruzarPadreHijo(Cromosoma padre, Cromosoma hijo, int corte) {
		int longitudAcumulada = hijo.longitudTotal();
		for(int i = corte; i < longitudAcumulada; i++){
			int numGen = i / hijo.genes[0].getLongitud();
			int numBit = i % hijo.genes[0].getLongitud();
			Boolean bitHijo = hijo.genes[numGen].getAlelo()[numBit];
			Boolean bitPadre = padre.genes[numGen].getAlelo()[numBit];
			hijo.genes[numGen].setBit(numBit, bitPadre);
			padre.genes[numGen].setBit(numBit, bitHijo);
		}
	}

	private static void copiarCromosoma(Cromosoma destino, Cromosoma origen) {
		// Para cada gen del cromosoma
		for(int numGen = 0; numGen < origen.genes.length; numGen++) {
			// Para cada alelo/bit del gen
			for(int numBit = 0; numBit < origen.genes[numGen].getLongitud(); numBit++) {
				destino.genes[numGen].setBit(numBit, origen.genes[numGen].getBit(numBit));
			}
		}
	}

	static public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer, String cruce){
		if(padre1 instanceof CromosomaReal){
			switch(cruce){
			case "Discreto Uniforme":
				CromosomaReal.cruzarRealDiscretoUniforme(padre1, padre2, hijo1, hijo2, randomizer);
				break;
			case "Monopunto":
				CromosomaReal.cruzarRealMonoPunto(padre1, padre2, hijo1, hijo2, randomizer);
				break;
			case "Aritmetico":
				CromosomaReal.cruzarRealAritmetico(padre1, padre2, hijo1, hijo2, randomizer);
				break;
			case "SBX":
				CromosomaReal.cruzarRealSBX(padre1, padre2, hijo1, hijo2, randomizer);
				break;
			}
		}
		else cruzarPv(padre1, padre2, hijo1, hijo2, randomizer);
	}
	
	public void mutar(Float probabilidadMutacion, Random randomizer){
		for(int i=0; i<genes.length; i++){
			Boolean[] alelos = genes[i].getAlelo();
			for(int j=0; j<alelos.length; j++){
				if(randomizer.nextFloat() < probabilidadMutacion){
					alelos[j] = !alelos[j].booleanValue();
				}
			}
		}
	}
	
	public int longitudTotal(){
		int longitudTotal = 0;
		
		for(int i=0; i<genes.length; i++){
			longitudTotal += genes[i].getLongitud();
		}
		
		return longitudTotal;
	}
	
	public void copia(Cromosoma c){
		if(genes.length == c.genes.length){
			for(int numGen = 0; numGen < genes.length; numGen++){
				for(int numBit = 0; numBit < genes[0].getLongitud(); numBit++) {
					genes[numGen].setBit(numGen, c.genes[numGen].getBit(numBit));
				}				
			}
		}
	}
	
	abstract public Boolean esMaximizacion();
}
