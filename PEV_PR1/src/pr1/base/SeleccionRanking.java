package pr1.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class SeleccionRanking implements AlgoritmoSeleccion{

	private final Float beta = 1.5f;
	
	@Override
	public void seleccionar(ArrayList<Float> aptitudes,
			ArrayList<Float> puntuacionesAcumuladas,
			ArrayList<Cromosoma> seleccionados, Integer tamanoPoblacion,
			ArrayList<Cromosoma> poblacion, Boolean minimizacion, Random randomizer) {
		// TODO Auto-generated method stub
		
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
		
		for(int i=0; i<aptitudes.size(); i++){
			Par<Float, Integer> par = new Par<Float, Integer>(aptitudes.get(i), i);
			monticuloMax.add(par);
		}
		
		ArrayList<Integer> indicesOrd = new ArrayList<Integer>(poblacion.size());
		
		for(int i=0; !monticuloMax.isEmpty(); i++){
			indicesOrd.add(i, monticuloMax.poll().getValue());
		}
		
		ArrayList<Float> probabilidades = new ArrayList<Float>(poblacion.size());
		for(int i=0; i<probabilidades.size(); i++){
			float prob = (1.0f / probabilidades.size()) * (beta-2*(beta-1)*(i-1.0f)/probabilidades.size()-1);
			probabilidades.add(i, prob);
		}
		
		for(int i=0; i<seleccionados.size(); i++){
			float aleatorio = randomizer.nextFloat();
			float sumaProb = probabilidades.get(0).floatValue();
			int j=1;
			for(; i<probabilidades.size()-1 && aleatorio > sumaProb; j++){
				sumaProb += probabilidades.get(j).floatValue();
			}
			
			seleccionados.get(i).copia(poblacion.get(indicesOrd.get(j)));
		}
		
		
	}

}
