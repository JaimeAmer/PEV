package pr1.espec;

import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion2 extends Cromosoma{

	private final float x1_minimo = -512;
	private final float x1_maximo = 512;
	private final float x2_minimo = -512;
	private final float x2_maximo = 512;
	
	public Funcion2(float precision, Random randomizer){
		longitud = new Integer[2];
		int x1_r = (int) Math.ceil(((Math.log(1+(x1_maximo-x1_minimo)/precision))/Math.log(2)));
		int x2_r = (int) Math.ceil(((Math.log(1+(x2_maximo-x2_minimo)/precision))/Math.log(2)));
		longitud[0] = x1_r;
		longitud[1] = x2_r;
		genes = new Gen[2];
		
		genes[0] = new Gen(x1_r, randomizer);
		genes[1] = new Gen(x2_r, randomizer);
		
		maximizar = false;
		
	}
	
	@Override
	protected Float[] getFenotipo() {
		Float[] array = new Float[8];
		float x1_r = (float) (x1_minimo + (x1_maximo-x1_minimo)*valorGen(genes[0])/(Math.pow(2, longitud[0])-1));
		float x2_r = (float) (x2_minimo + (x2_maximo-x2_minimo)*valorGen(genes[1])/(Math.pow(2, longitud[1])-1));
		array[0] = x1_r;
		array[1] = x2_r;
		
		return array;
	}

	@Override
	protected Float getAptitud() {
		float x1 = getFenotipo()[0];
		float x2 = getFenotipo()[1];
		float f = (float) (-(x2+47)*Math.sin(Math.sqrt(Math.abs(x2+(x1/2)+47)))-x1*Math.sin(Math.sqrt(Math.abs(x1-(x2+47)))));
		return f;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}

	public String toString(){
		return "Valor mejor: f(x)= " + getAptitud() + " , en x1 = " + getFenotipo()[0] + ", x2 = " + getFenotipo()[1];
	}
}
