package pr1.base;

import java.util.ArrayList;
import java.util.Random;


public class Gen {
	private ArrayList<Boolean> alelo;
	
	public Gen(Integer n, Random randomizer){
		this.alelo = new ArrayList<Boolean>();
		for(int i=0; i<n; i++)
			alelo.add(i, randomizer.nextBoolean());
	}
	
	public ArrayList<Boolean> getAlelo(){
		return this.alelo;
	}
	
	public void setAlelo(ArrayList<Boolean> alelo){
		this.alelo = alelo;
	}
}
