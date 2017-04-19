package pr2.base;

import java.util.Random;


public class Gen {
	// Alelo codificado en Gray
	private Boolean[] alelo;
	
	public Gen(Integer n, Random randomizer){
		this.alelo = new Boolean[n];
		for(int i=0; i<n; i++)
			alelo[i] = randomizer.nextBoolean();
	}
	
	public Boolean[] getAlelo(){
		return this.alelo;
	}
	
	public void setBit(int numAlelo, boolean valor){
		this.alelo[numAlelo] = valor;
	}
	
	public boolean getBit(int numAlelo) {
		return alelo[numAlelo];
	}
	
	public int getLongitud() {
		return alelo.length;
	}
	
	// Convierte de Gray a decimal
	public int getGray2Decimal() {
		// Conversión de Gray a Binario
		Boolean [] bin = new Boolean[alelo.length];
		bin[0] = false;
		for(int i = 1; i < alelo.length; i++) {
			// ^ => operador binario XOR
			bin[i] = bin[i-1] ^ alelo[i];
		}
		
		// Conversión de binario a decimal
		int valor = 0;
		for(int i = 0; i < alelo.length; i++){
			if(alelo[i].booleanValue()){
				//valor += (int) Math.pow(2, i);
				valor |= 1 << i;
			}
		}
		return valor;
	}
}
