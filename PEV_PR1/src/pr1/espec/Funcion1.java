package pr1.espec;

import java.util.ArrayList;
import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion1 extends Cromosoma{

	private final double minimo = -250.0;
	private final double maximo = 250.0;
		
	public Funcion1(Float precision, Random randomizer){
		longitud = new Integer[2];
		// Cálculo del número de bits del gen para tener la precisión deseada
		int numBits = (int) Math.ceil(((Math.log(1+(maximo-minimo)/precision))/Math.log(2)));
		longitud[0] = numBits;
		genes = new Gen[1];
		genes[0] = new Gen(numBits, randomizer);		
		maximizar = false;
	}

	@Override
	protected Float[] getFenotipo() {
		Float[] array = new Float[8]; 
		float x = (float) (minimo + (maximo-minimo)*valorGen(genes[0]) / (Math.pow(2, longitud[0].floatValue())-1));
		array[0] = x;
		array[1] = 0f;
		return array;
	}

	@Override
	protected Float getAptitud() {
		float x = getFenotipo()[0];
		float f = (float) -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
		return f;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}
	
	public String toString(){
		return "Valor mejor: " + getAptitud() + " en x: " + getFenotipo()[0];
	}
}
