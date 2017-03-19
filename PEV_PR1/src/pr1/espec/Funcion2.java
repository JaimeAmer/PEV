package pr1.espec;

import java.util.ArrayList;
import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion2 extends Cromosoma{

	private final float x_minimo = -512;
	private final float x_maximo = 512;
	private final float y_minimo = -512;
	private final float y_maximo = 512;
	
	public Funcion2(float precision, Random randomizer){
		longitud = new ArrayList<Integer>(2);
		int x_r = (int) Math.ceil(((Math.log(1+(x_maximo-x_minimo)/precision))/Math.log(2)));
		int y_r = (int) Math.ceil(((Math.log(1+(y_maximo-y_minimo)/precision))/Math.log(2)));
		longitud.add(0, x_r);
		longitud.add(1, y_r);
		genes = new ArrayList<Gen>(2);
		
		genes.add(0, new Gen(x_r, randomizer));
		genes.add(1, new Gen(y_r, randomizer));
		
		maximizar = true;
		
	}
	
	@Override
	protected ArrayList<Float> getFenotipo() {
		ArrayList<Float> array = new ArrayList<Float>(8);
		float x_r = (float) (x_minimo + (x_maximo-x_minimo)*valorGen(genes.get(0))/(Math.pow(2, longitud.get(0))-1));
		float y_r = (float) (y_minimo + (y_maximo-y_minimo)*valorGen(genes.get(1))/(Math.pow(2, longitud.get(1))-1));
		array.add(0, x_r);
		array.add(1, y_r);
		
		return array;
	}

	@Override
	protected Float getAptitud() {
		float x = getFenotipo().get(0);
		float y = getFenotipo().get(1);
		float f = (float) -((y+47*Math.sin(Math.sqrt(Math.abs(y+(x/2)+47)))-x*Math.sin(Math.sqrt(Math.abs(x-(y+47))))));
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
