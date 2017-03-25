package pr1.base;

import java.util.Random;


public class Gen {
	private Boolean[] alelo;
	
	public Gen(Integer n, Random randomizer){
		this.alelo = new Boolean[n];
		for(int i=0; i<n; i++)
			alelo[i] = randomizer.nextBoolean();
	}
	
	public Boolean[] getAlelo(){
		return this.alelo;
	}
	
	public void setAlelo(Boolean[] alelos){
		this.alelo = alelos.clone();
	}
}
