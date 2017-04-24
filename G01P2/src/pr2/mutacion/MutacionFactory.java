package pr2.mutacion;

public class MutacionFactory {
	static public Mutacion crear(String mutacion) {
		switch(mutacion){
		case "Propia":
			return new MutacionPropia();
		case "Intercambio":
			return new MutacionIntercambio();
		case "Insercion":
			return new MutacionInsercion();
		case "Heurística":
			return new MutacionHeuristica();
		case "Inversion":
			return new MutacionInversion();
		default:
			return new MutacionPropia();
		}
	}
	
	/*public void mutar(Float probabilidadMutacion, Random randomizer){
		for(int i=0; i<getGenes().length; i++){
			Boolean[] alelos = getGenes()[i].getAlelo();
			for(int j=0; j<alelos.length; j++){
				if(randomizer.nextFloat() < probabilidadMutacion){
					alelos[j] = !alelos[j].booleanValue();
				}
			}
		}
		Permutaciones permutaciones = new Permutaciones();
		int[] valido = permutaciones.validar(getFenotipo(), randomizer);
		setFenotipo(valido);
	}*/
}