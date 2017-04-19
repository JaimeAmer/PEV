package pr2.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Ficheros {
	private int[][] _d;
	private int[][] _f;
	
	// Función que lee las matrices f y d del archivo
	public void cargaMatrices(String nombreArchivo) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Scanner sc = new Scanner(br);
		
		int tamaño = sc.nextInt();
		_d = new int[tamaño][tamaño];
		_f = new int[tamaño][tamaño];
		
		// Carga la matriz de distancias
		for(int i = 0; i < tamaño; i++) {
			for(int j = 0; j < tamaño; j++) {
				_d[i][j] = sc.nextInt();				
			}
		}
		
		// Carga la matriz de flujo
		for(int i = 0; i < tamaño; i++) {
			for(int j = 0; j < tamaño; j++) {
				_f[i][j] = sc.nextInt();				
			}
		}
	}
	
	public int[][] get_d() {
		return _d;
	}

	public int[][] get_f() {
		return _f;
	}
}
