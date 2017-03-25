package pr1.espec;

import java.util.Random;

import pr1.base.CromosomaReal;

public class Funcion4R extends CromosomaReal{

	private float x_maximo = (float) Math.PI;
	private float x_minimo = 0;
	private int n;
	
	public Funcion4R(float precision, int n, Random randomizer){
		this.n = n;
		this.genes = new Float[this.n];
		
		for(int i=0; i<this.n; i++){
			this.genes[i] = randomizer.nextFloat()*(x_maximo-x_minimo)+x_minimo;
		}
		
		this.maximizar = false;
	}
	
	@Override
	protected Float[] getFenotipo() {
		return genes;
	}
	
	protected Float getFenotipo(int pos){
		return genes[pos];
	}

	@Override
	protected Float getAptitud() {
		float result = 0;
		for(int i=1; i<this.n; i++){
			float x = getFenotipo(i-1);
			result +=Math.sin(x)*Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 20);
		}
		return -result;
	}

	@Override
	public Boolean esMaximizacion() {
		return maximizar;
	}

	public String toString(){
		String s = "Valor mejor: f(x)= " + getAptitud();
		for(int i=0; i<this.n; i++){
			s = s + ", x" + (i+1) + " = " + getFenotipo()[i];
		}
		return s;
	}
	
}
