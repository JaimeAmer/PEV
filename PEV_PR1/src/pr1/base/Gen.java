package pr1.base;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class Gen {
	private Vector<Boolean> alelo;
	
	public Gen(Integer n, Random randomizer){
		this.alelo = new Vector<Boolean>();
		for(int i=0; i<n; i++)
			alelo.insertElementAt(randomizer.nextBoolean(), i);
	}
	
	public Vector<Boolean> getAlelo(){
		return this.alelo;
	}
	
	public void setAlelo(Vector<Boolean> genesHijo){
		this.alelo = genesHijo;
	}
}
