package pr1.base;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class SeleccionTorneoProbabilistico implements AlgoritmoSeleccion{

	private Integer participantes;
	private static final Float probMejor = 0.7f; 
	
	public SeleccionTorneoProbabilistico(Integer participantes) {
		this.participantes = participantes;
	}

	@Override
	public void seleccionar(ArrayList<Float> aptitudes,
			ArrayList<Float> puntuacionesAcumuladas,
			ArrayList<Cromosoma> seleccionados, Integer tamanoPoblacion,
			ArrayList<Cromosoma> poblacion, Boolean minimizacion,
			Random randomizer) {
		// TODO Auto-generated method stub
		ArrayList<Float> elegidosValue = new ArrayList<Float>(participantes);
		ArrayList<Integer> elegidosIndex = new ArrayList<Integer>(participantes);
		
		for(int i=0; i<tamanoPoblacion; i++){
			for(int j=0; j<participantes; j++){
				int indiceAleat = randomizer.nextInt(tamanoPoblacion);
				elegidosValue.add(j, aptitudes.get(indiceAleat));
				elegidosIndex.add(j, indiceAleat);
			}
			
			boolean mejor = randomizer.nextFloat() < probMejor;
			
			float maximoValor = Integer.MIN_VALUE;
			float minimoValor = Float.MAX_VALUE;
			
			int maximo = 0;
			int minimo = 1;
			for(int j=0; j<participantes; j++){
				if(elegidosValue.get(j).floatValue() > maximoValor){
					maximoValor = elegidosValue.get(j).floatValue();
					maximo = j;
				}
				else if(elegidosValue.get(j).floatValue() < minimoValor){
					minimoValor = elegidosValue.get(j).floatValue();
					minimo = j;
				}
			}
			
			if(mejor){
				seleccionados.get(i).copia(poblacion.get(elegidosIndex.get(maximo)));
			}
			else seleccionados.get(i).copia(poblacion.get(elegidosIndex.get(minimo)));
			
		}
	}

	
}
