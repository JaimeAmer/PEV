package pr1.espec;

import java.util.Random;

import pr1.base.Cromosoma;

public class FuncionFactory {

	static public Cromosoma getFuncion(String funcion, float precision, int n, Random randomizer){
		switch(funcion){
		case "Funcion1":
			return new Funcion1(precision, randomizer);
		case "Funcion2":
			return new Funcion2(precision, randomizer);
		case "Funcion3":
			return new Funcion3(precision, randomizer);
		default:
			return new Funcion1(precision, randomizer);
		}
	}
	
}
