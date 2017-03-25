package pr1.base;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class SeleccionRanking implements AlgoritmoSeleccion{

	private final Float beta = 1.5f;
	
	@Override
	public void seleccionar(Float[] aptitudes,
			Float[] puntuacionesAcumuladas,
			Cromosoma[] seleccionados, Integer tamanoPoblacion,
			Cromosoma[] poblacion, Boolean minimizacion, Random randomizer) {
				
		Comparator<Par<Float, Integer> > cmp = new Comparator<Par<Float, Integer>>(){
			public int compare(Par<Float, Integer> p1, Par<Float, Integer> p2){
				if(p1.getKey() > p2.getKey())
					return -1;
				else if(p1.getKey() == p2.getKey())
					return 0;
				else if(p1.getKey() < p2.getKey())
					return 1;
				else return 0;
			}
		};
		
		PriorityQueue<Par<Float, Integer>> monticuloMax = new PriorityQueue<Par<Float, Integer>>(cmp);
		
		for(int i=0; i<aptitudes.length; i++){
			Par<Float, Integer> par = new Par<Float, Integer>(aptitudes[i], i);
			monticuloMax.add(par);
		}
		
		Integer[] indicesOrd = new Integer[poblacion.length];
		
		for(int i=0; !monticuloMax.isEmpty(); i++){
			indicesOrd[i] = monticuloMax.poll().getValue();
		}
		
		Float[] probabilidades = new Float[poblacion.length];
		for(int i=0; i<probabilidades.length; i++){
			float prob = (1.0f / probabilidades.length) * (beta-2*(beta-1)*(i-1.0f)/probabilidades.length-1);
			probabilidades[i] = prob;
		}
		
		for(int i=0; i<seleccionados.length; i++){
			float aleatorio = randomizer.nextFloat();
			float sumaProb = probabilidades[0].floatValue();
			int j=1;
			for(; j<probabilidades.length-1 && aleatorio > sumaProb; j++){
				sumaProb += probabilidades[j].floatValue();
			}
			
			seleccionados[i].copia(poblacion[indicesOrd[j]]);
		}
		
		
	}

}
