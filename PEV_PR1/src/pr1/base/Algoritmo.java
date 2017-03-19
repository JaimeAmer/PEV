package pr1.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import pr1.espec.FuncionFactory;

public class Algoritmo {

	private Integer tamanoPoblacion;
	private Integer simulaciones;
	private ArrayList<Cromosoma> poblacion;
	private Float precision;
	private Float probabilidadCruce;
	private Float probabilidadMutacion;
	private String funcion;
	private Boolean elitismo;
	private Integer n;
	private Random randomizer;
	private Integer numElites;
	private ArrayList<Cromosoma> elites;
	private Integer participantes;
	private Long semilla;
	private String tipoSeleccion;
	
	private Float valorMejor;
	private Cromosoma individuoMejor;
	
	public Algoritmo(int tamanoPoblacion, float precision, float probabilidadCruce, float probabilidadMutacion, String funcion, int simulaciones, long semilla, int participantes, int n, boolean elitismo ,String tipoAlgoritmo, String tipoSeleccion){
		this.tamanoPoblacion = tamanoPoblacion;
		this.precision = precision;
		this.probabilidadCruce = probabilidadCruce;
		this.probabilidadMutacion = probabilidadMutacion;
		this.simulaciones = simulaciones;
		this.funcion = funcion;
		this.randomizer = new Random();
		this.n = n;
		this.elitismo = elitismo;
		this.numElites = (int) (this.tamanoPoblacion*0.02);
		//	Si buscamos elite, que haya 1
		if(this.numElites==0)
			this.numElites = 1;
		this.participantes = participantes;
		this.tipoSeleccion = tipoSeleccion;
		this.semilla = semilla;
		
		if(this.semilla != 0){
			randomizer.setSeed(semilla);
		}
		else{
			this.semilla = System.currentTimeMillis();
			randomizer = new Random(semilla);
		}
		
		//	Inicializamos la poblacion
		this.poblacion = new ArrayList<Cromosoma>(this.tamanoPoblacion);
		for(int i=0; i<this.tamanoPoblacion; i++){
			Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			this.poblacion.add(i, elem);
		}
		
		//	El mejor individuo lo elegimos aleatoriamente, solo por la grafica
		this.individuoMejor = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
		this.valorMejor = 0f;
		
		if(this.elitismo.booleanValue()){
			this.elites = new ArrayList<Cromosoma>(this.numElites);
			for(int i=0; i<this.numElites; i++){
				Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
				this.elites.add(i, elem);
			}
		}
	}
	
	public String execute(ArrayList<Double> mejorAbsoluto, ArrayList<Double> mejorGeneracion, ArrayList<Double> mediaGeneracion){
		ArrayList<Float> aptitudes = new ArrayList<Float>(this.tamanoPoblacion);
		ArrayList<Float> aptitudesDesp = new ArrayList<Float>(this.tamanoPoblacion);
		ArrayList<Float> puntuaciones = new ArrayList<Float>(this.tamanoPoblacion);
		ArrayList<Float> puntuacionesAcum = new ArrayList<Float>(this.tamanoPoblacion);
		
		for(int i=0; i<this.simulaciones; i++){
			ArrayList<Double> infogen = new ArrayList<Double>(2);// 0 mejor de la generacion, 1 media
			evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion.get(0).esMaximizacion().booleanValue());
			mejorAbsoluto.add(i, this.valorMejor.doubleValue());
			mejorGeneracion.add(i, infogen.get(0));
			mediaGeneracion.add(i, infogen.get(1));
			
			//	Seleccionamos el cruce
			seleccionar(aptitudesDesp, puntuacionesAcum);
			
			//	Cruzamos
			cruzar();
			
			//	Mutamos
			mutar();
			
			//	Si hay elitismo, volvemos a evaluar para sustituir los peores por la elite
			if(this.elitismo.booleanValue()){
				evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion.get(0).esMaximizacion().booleanValue());
				introducirElites(aptitudesDesp);
			}
		}
		
		return this.individuoMejor.toString();
	}
	
	private void introducirElites(ArrayList<Float> aptitudes){
		Comparator<Par<Float, Integer>> cmp = new Comparator<Par<Float,Integer>>() {
			@Override
			public int compare(Par<Float, Integer> p1, Par<Float, Integer> p2){
				if(p1.getKey() < p2.getKey())
					return -1;
				else if(p2.getKey() == p2.getKey())
					return 0;
				else if(p1.getKey() > p1.getKey())
					return 1;
				else return 0;
			}
		};
		
		PriorityQueue<Par<Float, Integer>> monticuloMin = new PriorityQueue<Par<Float, Integer>>(cmp);
		for(int i=0; i<this.tamanoPoblacion; i++){
			//	Posicion de los peores
			this.poblacion.get(monticuloMin.poll().getValue()).copia(this.elites.get(i));
		}
	}
	
	//	Metodo que evalua la poblacion actual
	private void evaluar(ArrayList<Float> aptitudes, ArrayList<Float> aptitudesDesp, ArrayList<Float> puntuaciones, ArrayList<Float> puntuacionesAcum, ArrayList<Double> infogen, Boolean maximizacion){
		float sumAptitudesDesp = 0;
		float sumAptitudes = 0;
		float mejorAptitudEnGen = 0;
		//	Si se trata de una maximizacion
		if(maximizacion){
			mejorAptitudEnGen = Float.MIN_VALUE;
			int mejorCromosomaGen = 0;
			float menorAptitud = Float.MAX_VALUE;
			
			//	Calculamos aptitudes
			for(int i=0; i<this.tamanoPoblacion; i++){
				aptitudes.set(i, this.poblacion.get(i).getAptitud().floatValue());
				sumAptitudes += aptitudes.get(i);
				
				//	Actualizamos el mejor
				if(aptitudes.get(i) > mejorAptitudEnGen){
					mejorAptitudEnGen = aptitudes.get(i);
					mejorCromosomaGen = i;
				}
				
				//	Guardamos el mas pequeño
				menorAptitud = Float.min(menorAptitud, aptitudes.get(i).floatValue());
			}
			
			//	Actualizamos el mejor global
			if(mejorAptitudEnGen > this.valorMejor){
				this.valorMejor = mejorAptitudEnGen;
				this.individuoMejor.copia(this.poblacion.get(mejorCromosomaGen));
			}
			
			desplazarAptitudes(aptitudes, aptitudesDesp, menorAptitud, maximizacion);
		}
		//	Si se trata de una minimizacion
		else{
			mejorAptitudEnGen = Float.MAX_VALUE;
			int mejorCromosomaGen = 0;
			float mayorAptitud = Float.MIN_VALUE;
			
			//	Calculamos aptitudes
			for(int i=0; i<this.tamanoPoblacion; i++){
				aptitudes.add(i, this.poblacion.get(i).getAptitud().floatValue());
				sumAptitudes += aptitudes.get(i);
				
				//	Actualizamos el mejor
				if(aptitudes.get(i) < mejorAptitudEnGen){
					mejorAptitudEnGen = aptitudes.get(i);
					mejorCromosomaGen = i;
				}
				
				//	Guardamos el mas grande
				mayorAptitud = Float.max(mayorAptitud, aptitudes.get(i).floatValue());				
			}
			
			//	Actualizamos el mejor global
			if(mejorAptitudEnGen < this.valorMejor.floatValue()){
				this.valorMejor = mejorAptitudEnGen;
				this.individuoMejor.copia(this.poblacion.get(mejorCromosomaGen));
			}
			
			desplazarAptitudes(aptitudes, aptitudesDesp, mayorAptitud, maximizacion);
		}
		
		for(int i=0; i<this.tamanoPoblacion; i++){
			sumAptitudesDesp += aptitudesDesp.get(i).floatValue();
		}
		
		//	Calculamos las puntuaciones para la seleccion
		puntuaciones.add(0, aptitudesDesp.get(0).floatValue()/sumAptitudesDesp);
		puntuacionesAcum.add(0, puntuaciones.get(0));
		
		for(int i=1; i<this.tamanoPoblacion; i++){
			puntuaciones.add(i, aptitudesDesp.get(i).floatValue()/sumAptitudesDesp);
			puntuacionesAcum.add(i, puntuaciones.get(i).floatValue()+puntuacionesAcum.get(i-1).floatValue());
		}
		puntuacionesAcum.set(this.tamanoPoblacion-1, 1f); // Asegurarse que el ultimo valor es 1
		
		//	Informacion de la generacion
		infogen.add(0, (double) mejorAptitudEnGen);
		infogen.add(1, (double) (sumAptitudes/this.tamanoPoblacion));
		
	}
	
	private void desplazarAptitudes(ArrayList<Float> aptitudes, ArrayList<Float> aptitudesDesp, float aptitud, boolean maximizacion){
		if(maximizacion){
			for(int i=0; i<aptitudes.size(); i++){
				aptitudesDesp.add(i, aptitudes.get(i).floatValue()+aptitud);
			}
		}
		else for(int i=0; i<aptitudes.size(); i++){
			aptitudesDesp.add(i, aptitud-aptitudes.get(i).floatValue());
		}
	}
	
	// Metodo que decide los cromosomas que se cruzan en funcion del algoritmo de seleccion
	private void seleccionar(ArrayList<Float> aptitudes, ArrayList<Float> puntuacionesAcum){
		//	Si hay elitismo, buscamos la elite
		if(this.elitismo.booleanValue()){
			Comparator<Par<Float, Integer>> cmp = new Comparator<Par<Float,Integer>>() {
				@Override
				public int compare(Par<Float, Integer> p1, Par<Float, Integer> p2) {
					if(p1.getKey() > p2.getKey())
						return -1;
					else if(p1.getKey() == p2.getKey())
						return 0;
					else if (p1.getKey() < p2.getKey())
						return 1;
					else return 0;
				}
			};
			
			PriorityQueue<Par<Float, Integer>> monticuloMax = new PriorityQueue<Par<Float, Integer>>(cmp);
			
			for(int i=0; i<this.tamanoPoblacion; i++){
				Par<Float, Integer> p = new Par<Float, Integer>(aptitudes.get(i), i);
				monticuloMax.add(p);
			}
			
			//	Guardamos los elites
			for(int i=0; i<this.numElites; i++){
				this.elites.get(i).copia(this.poblacion.get(monticuloMax.poll().getValue()));
			}
		}
		
		ArrayList<Cromosoma> seleccionados = new ArrayList<Cromosoma>(this.tamanoPoblacion);
		for(int i=0; i<this.tamanoPoblacion; i++){
			Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			seleccionados.add(i, elem);
		}
		
		//	Construimos el algoritmo elegido
		AlgoritmoSeleccion algoritmo = AlgoritmoSeleccionFactory.getAlgoritmoSeleccion(this.tipoSeleccion, this.participantes);
		algoritmo.seleccionar(aptitudes, puntuacionesAcum, seleccionados, tamanoPoblacion, poblacion, individuoMejor.esMaximizacion(), randomizer);
		//	Creamos la nueva poblacion
		this.poblacion = seleccionados;
	}
	
	//	Metodo que cruza la poblacion
	private void cruzar(){
		int numSeleccionados = 0;
		ArrayList<Integer> cruzar = new ArrayList<Integer>(this.tamanoPoblacion);
		
		//	Obtenemos los cromosomas
		for(int i=0; i<this.tamanoPoblacion; i++){
			if(randomizer.nextFloat() < this.probabilidadCruce){
				cruzar.add(numSeleccionados, i);
				numSeleccionados++;
			}
		}
		
		//	Redondeo a par
		if(numSeleccionados % 2 == 1){
			numSeleccionados--;
		}
		
		//	Cruzamos
		for(int i=0; i<numSeleccionados/2; i++){
			Cromosoma h1 = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			Cromosoma h2 = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			Cromosoma p1 = this.poblacion.get(cruzar.get(i));
			Cromosoma p2 = this.poblacion.get(cruzar.get(i+1));
			Cromosoma.cruzar(p1, p2, h1, h2, randomizer, this.tipoSeleccion);
			
			//	Sustituimos a los padres
			this.poblacion.set(cruzar.get(i), h1);
			this.poblacion.set(cruzar.get(i+1), h2);
		}
	}
	
	//	Metodo que muta la poblacion
	private void mutar(){
		for(int i=0; i<this.tamanoPoblacion; i++){
			this.poblacion.get(i).mutar(probabilidadMutacion, randomizer);
		}
	}
	
	public long getSemilla(){
		return this.semilla;
	}
}

