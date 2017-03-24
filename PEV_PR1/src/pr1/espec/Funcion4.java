package pr1.espec;

import java.util.ArrayList;
import java.util.Random;

import pr1.base.Cromosoma;
import pr1.base.Gen;


public class Funcion4 extends Cromosoma{

	private float x_maximo = (float) Math.PI;
	private float x_minimo = 0;
	private int n;
	
	public Funcion4(float precision, int n, Random randomizer){
		this.n = n;
		longitud = new Integer[this.n];
		for(int i=0; i<this.n; i++){
			int aux = (int) Math.ceil(((Math.log(1+(x_maximo-x_minimo)/precision))/Math.log(2)));
			longitud[i] = aux;
		}
		
		genes = new Gen[this.n];
		for(int i=0; i<this.n; i++){
			genes[i] = new Gen(longitud[i], randomizer);
		}
		
		maximizar = false;
	}
	
	@Override
	protected Float[] getFenotipo() {
		Float[] array = new Float[n];
		for(int i=0; i<n; i++){
			float result = (float) (x_minimo+(x_maximo-x_minimo)*valorGen(genes[i])/(Math.pow(2, longitud[i])-1));
			array[i] = result;
		}
		
		return array;
	}
	
	private float getFenotipo(int pos){
		float f = (float) (x_minimo+(x_maximo-x_minimo)*valorGen(genes[pos])/(Math.pow(2, longitud[pos])-1));
		return f;
	}

	@Override
	protected Float getAptitud() {
		float result = 0f;
		for(int i=1; i<=n; i++){
			float x = getFenotipo(i-1);
			result += Math.sin(x)*Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 20);
		}
		
		return -1*result;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}
	
	public String toString(){
		String s = "Valor mejor: " + getAptitud();
		for(int i=0; i<n; i++){
			s += ", x" + (i+1) + " = " + getFenotipo()[i];
		}
		
		return s;
	}

}
