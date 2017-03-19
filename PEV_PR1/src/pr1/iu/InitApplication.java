package pr1.iu;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import pr1.base.*;

public class InitApplication {
	
	private GUI gui;

	public InitApplication(String[] args) {
		// define your data
		gui = new GUI(this);
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		
		// define the legend position
		plot.addLegend("SOUTH");
		
		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", x, y);
		
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
	
	private void init(String precision, String probabilidadCruce, String probabilidadMutacion, String metodoSeleccion, Boolean elitismo, String funcion, String tamanoPoblacion, String numGeneraciones, String semilla, String n, String participantes, String tipoAlgoritmo){
		float _precision = Float.parseFloat(precision);
		float _probabilidadCruce = Float.parseFloat(probabilidadCruce);
		float _probabilidadMutacion = Float.parseFloat(probabilidadMutacion);
		int _tamanoPoblacion = Integer.parseInt(tamanoPoblacion);
		int _numGeneraciones = Integer.parseInt(numGeneraciones);
		long _semilla = Long.parseLong(semilla);
		int _n = Integer.parseInt(n);
		Integer _participantes = Integer.parseInt(participantes);
		
		long t = System.currentTimeMillis();
		
		// COMPROBAR PARAMETROS BIEN COLOCADOS
		Algoritmo algoritmo = new Algoritmo(_tamanoPoblacion, _precision, _probabilidadCruce, _probabilidadMutacion, funcion, _numGeneraciones, semilla, participantes, n, elitismo, tipoAlgoritmo, metodoSeleccion);
		
		ArrayList<Double> mejorAbsoluto = new ArrayList<Double>(_numGeneraciones);
		ArrayList<Double> mejorGeneracion = new ArrayList<Double>(_numGeneraciones);
		ArrayList<Double> mediaGeneracion = new ArrayList<Double>(_numGeneraciones);
		
		//	Ejecucion del algoritmo
		String result = algoritmo.execute(mejorAbsoluto, mejorGeneracion, mediaGeneracion);
		
		//	Mostrar resultados
		gui.fillPlot(mejorAbsoluto, mejorGeneracion, mediaGeneracion, _numGeneraciones, result);
		
		//	Mostrar la semilla utilizada
		gui.setSeed(algoritmo.getSemilla());
		t = System.currentTimeMillis();
		gui.setTime((float)t/1000);
	}

}
