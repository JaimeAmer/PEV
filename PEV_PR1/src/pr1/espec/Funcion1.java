package pr1.espec;

import java.util.ArrayList;
import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion1 extends Cromosoma{

	 private final double minimo = -250.0;
	 private final double maximo = 250.0;
		
	public Funcion1(Float precision, Random randomizer){
		longitud = new ArrayList<Integer>(2);
		int x = (int) Math.ceil(((Math.log(1+(maximo-minimo)/precision))/Math.log(2)));
		longitud.add(0, x);
		genes = new ArrayList<Gen>(1);
		genes.add(0, new Gen(longitud.get(0), randomizer));
		
		maximizar = false;
	}



	@Override
	protected ArrayList<Float> getFenotipo() {
		ArrayList<Float> array = new ArrayList<Float>(8);
		float x = (float) (minimo + (maximo-minimo)*valorGen(genes.get(0)) / (Math.pow(2, longitud.get(0).floatValue())-1));
		array.add(0, x);
		array.add(1, (float) 0);
		return array;
	}



	@Override
	protected Float getAptitud() {
		float x = getFenotipo().get(0);
		float f = (float) -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
		return f;
	}



	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}
	
	public String toString(){
		return "Valor mejor: " + getAptitud() + " en x: " + getFenotipo().get(0);
	}
	
}
