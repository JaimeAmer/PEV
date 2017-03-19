package pr1.espec;

import java.util.ArrayList;
import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion5 extends Cromosoma{

	private final float x_minimo = -10f;
	private final float x_maximo = 10f;
	private final float y_minimo = -10f;
	private final float y_maximo = 10f;
	
	public Funcion5 (float precision, Random randomizer){
		longitud = new ArrayList<Integer>(2);
		int x_r = (int) Math.ceil(((Math.log(1+(x_maximo-x_minimo)/precision))/Math.log(2)));
		int y_r = (int) Math.ceil(((Math.log(1+(y_maximo-y_minimo)/precision))/Math.log(2)));
		
		longitud.set(0, x_r);
		longitud.set(1, y_r);
		genes = new ArrayList<Gen>(2);
		genes.set(0, new Gen(x_r, randomizer));
		genes.set(1, new Gen(y_r, randomizer));
	}
	
	@Override
	protected ArrayList<Float> getFenotipo() {
		ArrayList<Float> array = new ArrayList<Float>(8);
		float x_r = (float) (x_minimo+(x_maximo-x_minimo)*valorGen(genes.get(0))/(Math.pow(2, longitud.get(0))-1));
		float y_r = (float) (y_minimo+(y_maximo-y_minimo)*valorGen(genes.get(1))/(Math.pow(2, longitud.get(1))-1));
		array.set(0, x_r);
		array.set(1, y_r);
		
		return array;
	}

	@Override
	protected Float getAptitud() {
		float x_r = 0;
		float y_r = 0;
		float x = getFenotipo().get(0);
		float y = getFenotipo().get(1);
		float f = 0;
		
		for(int i=1; i<=5; i++){
			x_r += i*Math.cos((i+1)*x+1);
		}
		
		for(int i=1; i<=5; i++){
			y_r += i*Math.cos((i+1)*y+1);
		}
		f = x_r * y_r;
		
		return f;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}
	
	public String toString(){
		return "Valor mejor: " + getAptitud() + " en x: " + getFenotipo().get(0) + " y: " + getFenotipo().get(1);
	}

}
