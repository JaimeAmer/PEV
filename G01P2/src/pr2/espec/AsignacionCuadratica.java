package pr2.espec;

import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Gen;


public class AsignacionCuadratica extends Cromosoma{
	private int _minimo;
	private int _maximo;
	private int _numLocalizaciones;
	private int[][] _f;
	private int[][] _d;
	
	public AsignacionCuadratica(int numLocalizaciones, int[][] d, int[][] f, Random randomizer){
		_numLocalizaciones = numLocalizaciones;
		_maximo = numLocalizaciones;
		_minimo = 1;
		_f = f;
		_d = d;
		// Cálculo del número de bits del gen para tener el rango deseado
		int numBits = (int) Math.ceil(((Math.log(1+(_maximo-_minimo)))/Math.log(2)));
		genes = new Gen[numLocalizaciones];
		for(int i = 0; i < numLocalizaciones; i++){
			genes[i] = new Gen(numBits, randomizer);
		}
	}

	@Override
	protected int[] getFenotipo() {
		int[] array = new int[_numLocalizaciones];
		for(int i = 0; i < genes.length; i++) {
			int x = (int) (_minimo + (_maximo-_minimo) * genes[i].getGray2Decimal() / (Math.pow(2, _numLocalizaciones)-1));
			array[i] = x;
		}
		return array;
	}

	@Override
	protected float getAptitud() {
		int[] s = getFenotipo();
		float coste = 0;
		for(int i = 0; i < _numLocalizaciones; i++) {
			for(int j = 0; j < _numLocalizaciones; j++) {
				coste += _f[i][j] * _d[s[i]][s[j]];
			}
		}
		return coste;
	}

	@Override
	public Boolean esMaximizacion() {
		return false;
	}
	
	public String toString(){
		return "Valor mejor: f(x)= " + getAptitud() + " , en x = " + getFenotipo()[0];
	}
}
