package pr1.iu;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.*;

import es.ucm.fdi.pe.*;
import pr1.base.*;

public class Application {
	
	private GUI gui;

	public Application() {
		// define your data
		gui = new GUI(this);
		
	}
	
	public void init(String precision, String probabilidadCruce, String probabilidadMutacion, String metodoSeleccion, Boolean elitismo, String funcion, String tamanoPoblacion, String numGeneraciones, String semilla, String n, String participantes, String tipoAlgoritmo){
		float _precision = Float.parseFloat(precision);
		float _probabilidadCruce = Float.parseFloat(probabilidadCruce);
		float _probabilidadMutacion = Float.parseFloat(probabilidadMutacion);
		int _tamanoPoblacion = Integer.parseInt(tamanoPoblacion);
		int _numGeneraciones = Integer.parseInt(numGeneraciones);
		long _semilla = Long.parseLong(semilla);
		int _n = Integer.parseInt(n);
		int _participantes = Integer.parseInt(participantes);
		
		long t = System.currentTimeMillis();
		
		// COMPROBAR PARAMETROS BIEN COLOCADOS
		Algoritmo algoritmo = new Algoritmo(_tamanoPoblacion, _precision, _probabilidadCruce/100, _probabilidadMutacion/100, funcion, _numGeneraciones, _semilla, _participantes, _n, elitismo, tipoAlgoritmo, metodoSeleccion);
		
		ArrayList<Double> mejorAbsoluto = new ArrayList<Double>(_numGeneraciones);
		ArrayList<Double> mejorGeneracion = new ArrayList<Double>(_numGeneraciones);
		ArrayList<Double> mediaGeneracion = new ArrayList<Double>(_numGeneraciones);
		
		//	Ejecucion del algoritmo
		String result = algoritmo.execute(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		
	}

	
}
