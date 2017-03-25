package pr1.espec;

import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion3 extends Cromosoma{

	private final float x_maximo = 12.1f;
	private final float x_minimo = -3.0f;
	private final float y_maximo = 5.8f;
	private final float y_minimo = 4.1f;
	
	
	public Funcion3(float precision, Random randomizer){
		longitud = new Integer[2];
		int x_r = (int) Math.ceil(((Math.log(1+(x_maximo-x_minimo)/precision))/Math.log(2)));
		int y_r = (int) Math.ceil(((Math.log(1+(y_maximo-y_minimo)/precision))/Math.log(2)));
		longitud[0] = x_r;
		longitud[1] = y_r;
		genes = new Gen[2];
		genes[0] = new Gen(x_r, randomizer);
		genes[1] = new Gen(y_r, randomizer);
		maximizar = true;
	}
	
	@Override
	protected Float[] getFenotipo() {
		Float[] array = new Float[8];
		float x_r = (float) (x_minimo + (x_maximo-x_minimo)*valorGen(genes[0])/(Math.pow(2, longitud[0])-1));
		float y_r = (float) (y_minimo + (y_maximo-y_minimo)*valorGen(genes[1])/(Math.pow(2, longitud[1])-1));
		
		array[0] = x_r;
		array[1] = y_r;
		
		return array;
	}

	@Override
	protected Float getAptitud() {
		float x = getFenotipo()[0];
		float y = getFenotipo()[1];
		float f = (float) (21.5+x*Math.sin(4*Math.PI*x)+y*Math.sin(20*Math.PI*y));
		
		return f;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}
	
	public String toString(){
		return "Valor mejor: f(x,y)= " + getAptitud() + " , en x= " + getFenotipo()[0] + ", y= " + getFenotipo()[1];
	}

}
