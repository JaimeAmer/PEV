package pr2.mutacion;

import java.util.Random;

public class InversionEspecial {

	private int puntoInicial, puntoFinal; 
	private float porcentaje;
	
	public InversionEspecial(float porcentajeInversion, int puntoInicioInversion, int puntoFinalInversion) {
		porcentaje = porcentajeInversion;
		puntoInicial = puntoInicioInversion;
		puntoFinal = puntoFinalInversion;
	}

	public int ejecutar(int[] individuo, Random randomizer)
	{
		int inversiones = 0;
		int longitud = individuo.length;
		if (puntoInicial < 1) {
			puntoInicial = 1;
		}
		if(puntoInicial >= longitud - 2) {
			puntoInicial = longitud - 2;
		}
		
		if (puntoFinal < 1) {
			puntoFinal = 1;
		}
		if(puntoFinal >= longitud - 2) {
			puntoFinal = longitud - 2;
		}
		
		if(randomizer.nextInt(100) > porcentaje) {
			int tmp = individuo[puntoInicial];
			for(int i = puntoInicial; i < puntoFinal; i++) {
				individuo[i] = individuo[i + 1];
				inversiones++;
			}
			individuo[puntoFinal] = tmp;
			inversiones++;
		}			
		return inversiones;
	}
}
