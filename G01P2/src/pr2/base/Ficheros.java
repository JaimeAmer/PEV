package pr2.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ficheros {
	private int[][] _d;
	private int[][] _f;
	private File nombreArchivo;
	
	public Ficheros(File nombreArchivo){
		this.nombreArchivo = nombreArchivo;
	}
	
	// Funci�n que lee las matrices f y d del archivo
	public void cargaMatrices() throws IOException, FileNotFoundException, InputMismatchException {
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		Scanner sc = new Scanner(br);
		
		int tama�o = sc.nextInt();
		_d = new int[tama�o][tama�o];
		_f = new int[tama�o][tama�o];
		
		// Carga la matriz de distancias
		for(int i = 0; i < tama�o; i++) {
			for(int j = 0; j < tama�o; j++) {
				_d[i][j] = sc.nextInt();				
			}
		}
		
		// Carga la matriz de flujo
		for(int i = 0; i < tama�o; i++) {
			for(int j = 0; j < tama�o; j++) {
				_f[i][j] = sc.nextInt();				
			}
		}
		
		sc.close();
		br.close();
	}
	
	public int[][] get_d() {
		return _d;
	}

	public int[][] get_f() {
		return _f;
	}
}
