package pr1.base;

import java.util.Vector;


public class Gen {
	private Vector<Boolean> gen;
	
	public Gen(){
		this.gen = new Vector<Boolean>();
	}
	
	public Gen(Vector<Boolean> gen){
		this.gen = gen;
	}
	
	public Vector<Boolean> getGen(){
		return this.gen;
	}
	
	public void setGen(Vector<Boolean> gen){
		this.gen = gen;
	}
	
	public int sizeGen(){
		return gen.size();
	}
	
}
