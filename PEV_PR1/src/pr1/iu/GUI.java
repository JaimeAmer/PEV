package pr1.iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.*;

import net.miginfocom.swing.MigLayout;

import org.math.plot.Plot2DPanel;

import es.ucm.fdi.pe.ConfigPanel;

public class GUI extends JFrame{

	public GUI(Application application){
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		// define the legend position
		plot.addLegend("SOUTH");	
		ConfigPanel<Integer> panel = new ConfigPanel<Integer>();
		panel.setSize(600,600);
		panel.setVisible(true);
		
		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", x, y);
		
		// put the PlotPanel in a JFrame like a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setSize(600, 600);
		frame.setContentPane(plot);
		frame.setVisible(true);
		
	}
}
