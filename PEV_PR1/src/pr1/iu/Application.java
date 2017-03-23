package pr1.iu;

import java.awt.Color;
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
		gui.setVisible(true);
	}
	
	public void init(float precision, float probabilidadCruce, float probabilidadMutacion, String metodoSeleccion, boolean elitismo, String funcion, int tamanoPoblacion, int numGeneraciones, long semilla, int n, int participantes, String tipoAlgoritmo){
		
		long t = System.currentTimeMillis();
		
		// COMPROBAR PARAMETROS BIEN COLOCADOS
		Algoritmo algoritmo = new Algoritmo(tamanoPoblacion, precision, probabilidadCruce, probabilidadMutacion, funcion, numGeneraciones, semilla, participantes, n, elitismo, tipoAlgoritmo, metodoSeleccion);
		ArrayList<Double> mejorAbsoluto = new ArrayList<Double>(numGeneraciones);
		ArrayList<Double> mejorGeneracion = new ArrayList<Double>(numGeneraciones);
		ArrayList<Double> mediaGeneracion = new ArrayList<Double>(numGeneraciones);
		
		//	Ejecucion del algoritmo
		String result = algoritmo.execute(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		gui.setResultado(result, t);
		gui.addPlot(mejorAbsoluto, "Mejor Absoluto", Color.RED);
		gui.addPlot(mejorGeneracion, "Mejor Generacion", Color.BLUE);
		gui.addPlot(mediaGeneracion, "Media Generacion", Color.GREEN);
		
		
	}

}
