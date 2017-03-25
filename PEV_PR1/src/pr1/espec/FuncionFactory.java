package pr1.espec;

import java.util.Random;

import pr1.base.Cromosoma;

public class FuncionFactory {

	static public Cromosoma getFuncion(String funcion, float precision, int n, Random randomizer){
		switch(funcion){
		case "Funcion 1":
			return new Funcion1(precision, randomizer);
		case "Funcion 2":
			return new Funcion2(precision, randomizer);
		case "Funcion 3":
			return new Funcion3(precision, randomizer);
		case "Funcion 4":
			return new Funcion4(precision, n, randomizer);
		case "Funcion 5":
			return new Funcion5(precision, randomizer);
		case "Funcion 4-R":
			return new Funcion4R(precision, n, randomizer);
		default:
			return new Funcion1(precision, randomizer);
		}
	}
	
}
