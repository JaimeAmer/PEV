package pr1.base;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public abstract class Cromosoma {

	protected ArrayList<Gen> genes;
	protected ArrayList<Integer> longitud;
	protected Boolean maximizar;
	
	static protected Integer valorGen(Gen g){
		Integer valor = 0;
		for(int i=0; i<g.getAlelo().size(); i++){
			if(g.getAlelo().get(i).booleanValue()){
				valor += (int) Math.pow(2, i);
			}
		}
		return valor;
	}
	
	abstract protected ArrayList<Float> getFenotipo();
	
	abstract protected Float getAptitud();
	
	static private void cruzarPv(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2, Random randomizer){
		for(int i=0; i<hijo1.genes.size(); i++){
			hijo1.genes.get(i).setAlelo(padre1.genes.get(i).getAlelo());
			hijo2.genes.get(i).setAlelo(padre2.genes.get(i).getAlelo());
		}
		
		int corte = randomizer.nextInt(padre1.longitudTotal()-1)+1;
		
		int primerGen = 0;
		int longitudAcumulada = hijo1.longitud.get(0).intValue();
		while(longitudAcumulada < corte){
			primerGen++;
			longitudAcumulada += hijo1.longitud.get(primerGen).intValue();
		}
		
		//	Hijo 1
		int pos = longitudAcumulada - corte;
		Vector<Boolean> genesPadre = (Vector<Boolean>) padre2.genes.get(primerGen).getAlelo();
		Vector<Boolean> genesHijo = (Vector<Boolean>) hijo1.genes.get(primerGen).getAlelo();
		for(int i=pos; i>0; i--){
			int at = genesHijo.size() - i;
			Boolean elem = genesPadre.get(at).booleanValue();
			genesHijo.add(at, elem);
		}
		
		hijo1.genes.get(primerGen).setAlelo(genesHijo);
		
		for(int i=primerGen+1; i<padre2.genes.size(); i++){
			hijo1.genes.get(i).setAlelo(padre2.genes.get(i).getAlelo());
		}
		
		//	Hijo 2
		genesPadre = (Vector<Boolean>) padre1.genes.get(primerGen).getAlelo().clone();
		genesHijo = (Vector<Boolean>) hijo2.genes.get(primerGen).getAlelo().clone();
		for(int i=pos; i>0; i--){
			genesHijo.add(genesHijo.size()-i, genesPadre.get(genesHijo.size()-i));
		}
		
		hijo2.genes.get(primerGen).setAlelo(genesHijo);
		
		for(int i=primerGen+1; i<padre1.genes.size(); i++){
			hijo2.genes.get(i).setAlelo(padre1.genes.get(i).getAlelo());
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
		for(int i=0; i<genes.size(); i++){
			Vector<Boolean> alelos = genes.get(i).getAlelo();
			for(int j=0; j<alelos.size(); j++){
				if(randomizer.nextFloat() < probabilidadMutacion){
					alelos.add(j, !alelos.get(j).booleanValue());
				}
			}
		}
	}
	
	public Integer longitudTotal(){
		Integer longitudTotal = 0;
		
		for(int i=0; i<genes.size(); i++){
			longitudTotal += longitud.get(i).intValue();
		}
		
		return longitudTotal;
	}
	
	public void copia(Cromosoma c){
		if(genes.size() == c.genes.size()){
			for(int i=0; i<genes.size(); i++){
				genes.get(i).setAlelo(c.genes.get(i).getAlelo());
			}
		}
	}
	
	abstract public Boolean esMaximizacion();
}
