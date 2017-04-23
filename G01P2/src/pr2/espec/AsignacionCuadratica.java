package pr2.espec;

import java.util.Random;

import pr2.base.Cromosoma;
import pr2.base.Gen;
import pr2.base.Permutaciones;


public class AsignacionCuadratica extends Cromosoma{
	private int _numLocalizaciones;
	private int[][] _f;
	private int[][] _d;
	private int _numBits;
	
	public AsignacionCuadratica(int numLocalizaciones, int[][] d, int[][] f, Random randomizer){
		_numLocalizaciones = numLocalizaciones;
		_f = f;
		_d = d;
		// Cálculo del número de bits del gen para tener el rango deseado
		_numBits = (int) Math.ceil(((Math.log(1+(_numLocalizaciones - 1)))/Math.log(2)));
		setGenes(new Gen[numLocalizaciones]);
		Permutaciones generador = new Permutaciones();
		int[] permutacion =  generador.generar(_numLocalizaciones, randomizer);
		for(int i = 0; i < numLocalizaciones; i++){
			getGenes()[i] = new Gen(_numBits, permutacion[i]);
		}
	}

	/**
	 * Devuelve un array con la lista de localizaciones.
	 */
	@Override
	public int[] getFenotipo() {
		int[] array = new int[_numLocalizaciones];
		for(int i = 0; i < getGenes().length; i++) {
			array[i] = getGenes()[i].getGray2Entero();
		}
		return array;
	}

	@Override
	public void setFenotipo(int[] array) {		
		for(int i = 0; i < getGenes().length; i++) {
			getGenes()[i].setAlelo(array[i]);
		}
	}
	
	/**
	 * Devuelve el costo de una lista de localizaciones.
	 */
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
		String text = "[";
		for(int i = 0; i < getFenotipo().length; i++) {
			if(text.length() > 1) 
				text += ",";
			text += String.format("%d", getFenotipo()[i]);
		}
		text += "]";
		return "Valor mejor: f(x)= " + getAptitud() + " , en x = " + text;
	}		
}
