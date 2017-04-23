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
	
	public Gen(int numBits, int valor) {
		alelo = getEntero2Gray(valor, numBits);
	}

	public void setAlelo(int valor) {
		alelo = getEntero2Gray(valor, alelo.length);
	}

	public void setBit(int numAlelo, boolean valor){
		this.alelo[numAlelo] = valor;
	}

	public Boolean[] getAlelo(){
		return this.alelo;
	}
	
	public boolean getBit(int numAlelo) {
		return alelo[numAlelo];
	}
	
	public int getLongitud() {
		return alelo.length;
	}
	
	// Convierte de entero a Gray
	public Boolean[] getEntero2Gray(int entero, int numBits) {
		// Conversión de entero a binario
		Boolean[] bin = new Boolean[numBits];
		int i = 0;
		for (i = 0; i < numBits; i++) {
			bin[i] = false;
		}
		i = 0;
		do {
			bin[i++] = (entero % 2) == 1;
		} while ((entero /= 2) > 0);
		
		// Conversión de binario a Gray
		Boolean[] gray = new Boolean[numBits];
		gray[numBits - 1] = bin[numBits - 1];
		for(i = numBits - 2; i >= 0; i--) {
			// ^ => operador binario XOR
			gray[i] = bin[i+1] ^ bin[i];
		}		
		return gray;
	}
	
	// Convierte de Gray a entero
	public int getGray2Entero() {
		// Conversión de Gray a binario
		boolean [] bin = new boolean[alelo.length];
		bin[alelo.length - 1] = alelo[alelo.length -1];
		for(int i = alelo.length - 2; i >= 0; i--) {
			// ^ => operador binario XOR
			bin[i] = bin[i+1] ^ alelo[i];
		}
		
		// Conversión de binario a decimal
		int valor = 0;
		for(int i = 0; i < bin.length; i++){
			if(bin[i]){
				//valor += (int) Math.pow(2, i);
				valor |= 1 << i;
			}
		}
		return valor;
	}
}
