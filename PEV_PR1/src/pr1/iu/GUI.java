package pr1.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import es.ucm.fdi.pe.Demo;

public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Plot2DPanel plot;

	public GUI(Application application){
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		
		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		// define the legend position
		plot.addLegend("SOUTH");
		
		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", Color.BLUE, x, y);
		
		
		
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(1280, 720);
		frame.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
		frame.setContentPane(plot);
		frame.setResizable(false);
		frame.setVisible(true);
		this.add(frame, BorderLayout.CENTER);
		Demo d = new Demo();
		d.setSize(800, 600);
		d.setVisible(true);
		this.add(d, BorderLayout.WEST);
		this.setVisible(true);
		
	}
	
	public void setPlot(ArrayList<Double> x, ArrayList<Double> y, String titulo, Color color){
		double[] auxX = new double[x.size()];
		for(int i=0; i<x.size(); i++){
			auxX[i] = x.get(i);
		}
		
		double[] auxY = new double[y.size()];
		for(int i=0; i<y.size(); i++){
			auxY[i] = y.get(i);
		}
		
		plot.addLinePlot(titulo, color, auxX, auxY);
	}
	
}
