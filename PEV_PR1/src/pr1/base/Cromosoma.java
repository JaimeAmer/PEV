package pr1.base;

import java.util.Random;


public abstract class Cromosoma {

	protected Gen[] genes;
	protected Integer[] longitud;
	protected Boolean maximizar;
	
	static protected Integer valorGen(Gen g){
		Integer valor = 0;
		for(int i=0; i<g.getAlelo().length; i++){
			if(g.getAlelo()[i].booleanValue()){
				valor += (int) Math.pow(2, i);
			}
		}
		return valor;
	}
	
	abstract protected Float[] getFenotipo();
	
	abstract protected Float getAptitud();
	
	static private void cruzarPv(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer){
		for(int i=0; i<hijo1.genes.length; i++){
			hijo1.genes[i].setAlelo(padre1.genes[i].getAlelo());
			hijo2.genes[i].setAlelo(padre2.genes[i].getAlelo());
		}
		
		int corte = randomizer.nextInt(padre1.longitudTotal()-1)+1;
		
		int primerGen = 0;
		int longitudAcumulada = hijo1.longitud[0].intValue();
		while(longitudAcumulada < corte){
			primerGen++;
			longitudAcumulada += hijo1.longitud[primerGen].intValue();
		}
		
		//	Hijo 1
		int pos = longitudAcumulada - corte;
		Boolean[] genesPadre = padre2.genes[primerGen].getAlelo().clone();
		Boolean[] genesHijo = hijo1.genes[primerGen].getAlelo().clone();
		for(int i=pos; i>0; i--){
			int at = genesHijo.length - i;
			Boolean elem = genesPadre[at].booleanValue();
			genesHijo[at] = elem;
		}
		
		hijo1.genes[primerGen].setAlelo(genesHijo);
		
		for(int i=primerGen+1; i<padre2.genes.length; i++){
			hijo1.genes[i].setAlelo(padre2.genes[i].getAlelo());
		}
		
		//	Hijo 2
		genesPadre = padre1.genes[primerGen].getAlelo().clone();
		genesHijo = hijo2.genes[primerGen].getAlelo().clone();
		for(int i=pos; i>0; i--){
			genesHijo[genesHijo.length-i] = genesPadre[genesHijo.length-i];
		}
		
		hijo2.genes[primerGen].setAlelo(genesHijo);
		
		for(int i=primerGen+1; i<padre1.genes.length; i++){
			hijo2.genes[i].setAlelo(padre1.genes[i].getAlelo());
		}
				
	}
	
	static public void cruzar(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer, String cruce){
		if(padre1 instanceof CromosomaReal){
			switch(cruce){
			case "DiscretoUniforme":
				CromosomaReal.cruzarRealDiscretoUniforme(padre1, padre2, hijo1, hijo2, randomizer);
				break;
			case "Externo":
				CromosomaReal.cruzarRealExterno(padre1, padre2, hijo1, hijo2, randomizer);
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
	
	public Integer longitudTotal(){
		Integer longitudTotal = 0;
		
		for(int i=0; i<genes.length; i++){
			longitudTotal += longitud[i].intValue();
		}
		
		return longitudTotal;
	}
	
	public void copia(Cromosoma c){
		if(genes.length == c.genes.length){
			for(int i=0; i<genes.length; i++){
				genes[i].setAlelo(c.genes[i].getAlelo());
			}
		}
	}
	
	abstract public Boolean esMaximizacion();
}
