package pr2.iu;

import java.awt.Color;

import pr2.base.Algoritmo;

public class Application {
	
	private GUI gui;

	public Application() {
		// define your data
		gui = new GUI(this);
		gui.setVisible(true);
	}
	
	public void init(int numLocalizaciones, int[][] d, int[][] f, float probabilidadCruce, float probabilidadMutacion, String metodoSeleccion, boolean elitismo, int tamanoPoblacion, int numGeneraciones, long semilla, int participantes, String tipoAlgoritmo){
		
		long t = System.currentTimeMillis();
		
		// COMPROBAR PARAMETROS BIEN COLOCADOS
		Algoritmo algoritmo = new Algoritmo(numLocalizaciones, d, f, tamanoPoblacion, probabilidadCruce, probabilidadMutacion, numGeneraciones, semilla, participantes, elitismo, tipoAlgoritmo, metodoSeleccion);
		Double[] mejorAbsoluto = new Double[numGeneraciones];
		Double[] mejorGeneracion = new Double[numGeneraciones];
		Double[] mediaGeneracion = new Double[numGeneraciones];
		
		//	Ejecucion del algoritmo
		String result = algoritmo.execute(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		t = System.currentTimeMillis()-t;
		
		gui.setResultado(result, t, semilla);
		gui.resetPlots();
		gui.addPlot(mejorAbsoluto, "Mejor Absoluto", Color.BLUE);
		gui.addPlot(mejorGeneracion, "Mejor de la Generacion", Color.RED);
		gui.addPlot(mediaGeneracion, "Media de la Generacion", Color.GREEN);
	}
}
