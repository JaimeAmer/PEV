package pr1.iu;

import java.awt.Color;

import pr1.base.Algoritmo;

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
		Double[] mejorAbsoluto = new Double[numGeneraciones];
		Double[] mejorGeneracion = new Double[numGeneraciones];
		Double[] mediaGeneracion = new Double[numGeneraciones];
		
		//	Ejecucion del algoritmo
		String result = algoritmo.execute(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		t = (System.currentTimeMillis()-t)/1000;
		
		gui.setResultado(result, t, semilla);
		gui.resetPlots();
		gui.addPlot(mejorAbsoluto, "Mejor Absoluto", Color.RED);
		gui.addPlot(mejorGeneracion, "Mejor Generacion", Color.BLUE);
		gui.addPlot(mediaGeneracion, "Media Generacion", Color.GREEN);
		
		
	}

}
