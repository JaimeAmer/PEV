package pr2.base;

import java.util.Random;


public abstract class Cromosoma implements Comparable<Cromosoma> {

	private Gen[] genes;
	
	public abstract int[] getFenotipo();
	
	abstract protected float getAptitud();
	
	public abstract void setFenotipo(int[] valido);
	
	public static void copiarCromosoma(Cromosoma destino, Cromosoma origen) {
		// Para cada gen del cromosoma
		for(int numGen = 0; numGen < origen.getGenes().length; numGen++) {
			// Para cada alelo/bit del gen
			for(int numBit = 0; numBit < origen.getGenes()[numGen].getLongitud(); numBit++) {
				destino.getGenes()[numGen].setBit(numBit, origen.getGenes()[numGen].getBit(numBit));
			}
		}
	}
	
	public int longitudTotal(){
		int longitudTotal = 0;
		
		for(int i=0; i<getGenes().length; i++){
			longitudTotal += getGenes()[i].getLongitud();
		}
		
		return longitudTotal;
	}
	
	public void copia(Cromosoma c){
		if(getGenes().length == c.getGenes().length){
			for(int numGen = 0; numGen < getGenes().length; numGen++){
				for(int numBit = 0; numBit < getGenes()[0].getLongitud(); numBit++) {
					getGenes()[numGen].setBit(numBit, c.getGenes()[numGen].getBit(numBit));
				}				
			}
		}
	}
	
	abstract public Boolean esMaximizacion();
	
	/**
	 * Devuelve -1 si este cromosoma es menor que "o"
	 * Devuelve 1 si es mayor
	 * Devuelve 0 si son iguales
	 */
	@Override
	public int compareTo(Cromosoma o) {
		if(longitudTotal() < o.longitudTotal())
			return 1;
		if (longitudTotal() > o.longitudTotal())
			return -1;
		
		int[] lista1 = getFenotipo();
		int[] lista2 = o.getFenotipo();
		for(int i = 0; i < lista1.length; i++) {
			if(lista1[i] < lista2[i])
				return 1;
			if(lista1[i] > lista2[i])
				return -1;
		}
		return 0;
	}

	public Gen[] getGenes() {
		return genes;
	}

	public void setGenes(Gen[] genes) {
		this.genes = genes;
	}

	public int longitud() {
		return genes.length;
	}
}
