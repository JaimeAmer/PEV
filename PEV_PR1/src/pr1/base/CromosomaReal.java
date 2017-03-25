package pr1.base;

import java.util.Random;

abstract public class CromosomaReal extends Cromosoma{
	
	protected Float[] genes;
	protected Integer[] longitud;
	protected Boolean maximizar;
	
	public Integer getLongitudTotal(){
		return genes.length;
	}
	
	public void copia(Cromosoma otro){
		CromosomaReal cr = (CromosomaReal) otro;
		if(genes.length == cr.genes.length){
			for(int i=0; i<genes.length; i++){
				genes[i] = cr.genes[i];
			}
		}
	}

	public static void cruzarRealDiscretoUniforme(Cromosoma padre1,
			Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2,
			Random randomizer) {
		// TODO Auto-generated method stub
		CromosomaReal padreR1 = (CromosomaReal) padre1;
		CromosomaReal padreR2 = (CromosomaReal) padre2;
		CromosomaReal hijoR1 = (CromosomaReal) hijo1;
		CromosomaReal hijoR2 = (CromosomaReal) hijo2;
		
		for(int i=0; i<hijoR1.genes.length; i++){
			hijoR1.genes[i] = padreR1.genes[i];
			hijoR2.genes[i] = padreR2.genes[i];
		}
		
		float probabilidadCruce = randomizer.nextFloat()*0.7f+0.1f;
		
		//	Hijo 1
		for(int i=0; i<padreR2.genes.length; i++){
			if(randomizer.nextFloat() < probabilidadCruce){
				hijoR1.genes[i] = padreR2.genes[i];
			}
		}
		
		//	Hijo 2
		for(int i=0; i<padreR1.genes.length; i++){
			if(randomizer.nextFloat() < probabilidadCruce){
				hijoR2.genes[i] = padreR1.genes[i];
			}
		}
	}

	public static void cruzarRealMonoPunto(Cromosoma padre1, Cromosoma padre2,
			Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		
		CromosomaReal padreR1 = (CromosomaReal) padre1;
		CromosomaReal padreR2 = (CromosomaReal) padre2;
		CromosomaReal hijoR1 = (CromosomaReal) hijo1;
		CromosomaReal hijoR2 = (CromosomaReal) hijo2;
		
		//	Cruce Externo
		for(int i=0; i<hijoR1.genes.length; i++){
			hijoR1.genes[i] = padreR1.genes[i];
			hijoR2.genes[i] = padreR2.genes[i];
		}
		
		int corte;
		if(padreR1.getLongitudTotal() == 1){
			corte = 0;
		}
		else corte = randomizer.nextInt(padreR1.getLongitudTotal()-1)+1;
		
		//	Hijo 1
		for(int i=corte; i<padreR2.genes.length; i++){
			hijoR1.genes[i] = padreR2.genes[i];
		}
		
		//	Hijo 2
		for(int i=corte; i<padreR1.genes.length; i++){
			hijoR2.genes[i] = padreR1.genes[i];
		}
		
	}

	public static void cruzarRealAritmetico(Cromosoma padre1, Cromosoma padre2,
			Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		// TODO Auto-generated method stub
		CromosomaReal padreR1 = (CromosomaReal) padre1;
		CromosomaReal padreR2 = (CromosomaReal) padre2;
		CromosomaReal hijoR1 = (CromosomaReal) hijo1;
		CromosomaReal hijoR2 = (CromosomaReal) hijo2;
		
		for(int i=0; i<hijoR1.genes.length; i++){
			hijoR1.genes[i] = padreR1.genes[i];
			hijoR2.genes[i] = padreR2.genes[i];
		}
		
		float probabilidadCruce = randomizer.nextFloat()*0.9f+0.1f;
		
		//	Hijo 1 e Hijo 2
		for(int i=0; i<padreR2.genes.length; i++){
			float result1 = (padreR2.genes[i] * probabilidadCruce + padreR1.genes[i] * (1-probabilidadCruce)) / 2.0f;
			hijoR1.genes[i] = result1;
			float result2 = (padreR2.genes[i] * (1-probabilidadCruce) + padreR1.genes[i]*probabilidadCruce) / 2.0f;
			hijoR2.genes[i] = result2;
		}
		
	}

	public static void cruzarRealSBX(Cromosoma padre1, Cromosoma padre2,
			Cromosoma hijo1, Cromosoma hijo2, Random randomizer) {
		CromosomaReal padreR1 = (CromosomaReal) padre1;
		CromosomaReal padreR2 = (CromosomaReal) padre2;
		CromosomaReal hijoR1 = (CromosomaReal) hijo1;
		CromosomaReal hijoR2 = (CromosomaReal) hijo2;
		
		int x = 2;
		float beta = (float) (0.5f*(x+1.0f)*(Math.pow(randomizer.nextFloat(), x)));
		for(int i=0; i<padreR1.genes.length; i++){
			float xmedia = (padreR1.genes[i] + padreR2.genes[i]) / 2.0f;
			float sbx = 0.5f*beta*Math.abs(padreR1.genes[i] - padreR2.genes[i]);
			hijoR1.genes[i] = xmedia-sbx;
			hijoR2.genes[i] = xmedia+sbx;
		}
		
	}
	
	public void mutar(Float probabilidad, Random randomizer){
		for(int i=0; i<genes.length; i++){
			if(randomizer.nextFloat() < probabilidad){
				float aleat = randomizer.nextFloat()+0.1f;
				float result = (float) ((genes[i]+aleat)%Math.PI);
				genes[i] = result;
			}
		}
	}

	abstract protected Float[] getFenotipo();

	abstract protected Float getAptitud();

	abstract public Boolean esMaximizacion();

}
