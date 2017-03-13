package pr1;

import java.math.*;
import java.util.Vector;



public class Funcion1 extends Cromosoma{

	 private final double minimo = -250.0;
	 private final double maximo = 250.0;
		
	
	
	private double f(double x){
		
		if(x >= minimo && x <= maximo)
			return -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
		else return 0;
	}
	
	
}
