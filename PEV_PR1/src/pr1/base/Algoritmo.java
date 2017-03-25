package pr1.base;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import pr1.espec.FuncionFactory;

public class Algoritmo {

	private Integer tamanoPoblacion;
	private Integer simulaciones;
	private Cromosoma[] poblacion;
	private Float precision;
	private Float probabilidadCruce;
	private Float probabilidadMutacion;
	private String funcion;
	private Boolean elitismo;
	private Integer n;
	private Random randomizer;
	private Integer numElites;
	private Cromosoma[] elites;
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
			randomizer = new Random(semilla);
		}
		//	Inicializamos la poblacion
		this.poblacion = new Cromosoma[this.tamanoPoblacion];
		for(int i=0; i<this.tamanoPoblacion; i++){
			Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			this.poblacion[i] = elem;
		}
		
		//	El mejor individuo lo elegimos aleatoriamente, solo por la grafica
		this.individuoMejor = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
		this.valorMejor = 0f;
		
		if(this.elitismo.booleanValue()){
			this.elites = new Cromosoma[this.numElites];
			for(int i=0; i<this.numElites; i++){
				Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
				this.elites[i] = elem;
			}
		}
	}
	
	public String execute(Double[] mejorAbsoluto, Double[] mejorGeneracion, Double[] mediaGeneracion){
		Float[] aptitudes = new Float[this.tamanoPoblacion];
		Float[] aptitudesDesp = new Float[this.tamanoPoblacion];
		Float[] puntuaciones = new Float[this.tamanoPoblacion];
		Float[] puntuacionesAcum = new Float[this.tamanoPoblacion];
		
		for(int i=0; i<this.simulaciones; i++){
			Double[] infogen = new Double[2];// 0 mejor de la generacion, 1 media
			evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion[0].esMaximizacion().booleanValue());
			mejorAbsoluto[i] = this.valorMejor.doubleValue();
			mejorGeneracion[i] = infogen[0];
			mediaGeneracion[i] = infogen[1];
			
			
			//	Seleccionamos el cruce
			seleccionar(aptitudesDesp, puntuacionesAcum);
			
			//	Cruzamos
			cruzar();
			
			//	Mutamos
			mutar();
			
			//	Si hay elitismo, volvemos a evaluar para sustituir los peores por la elite
			if(this.elitismo.booleanValue()){
				evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion[0].esMaximizacion().booleanValue());
				introducirElites(aptitudesDesp);
			}
		}
		
		return this.individuoMejor.toString();
	}
	
	private void introducirElites(Float[] aptitudes){
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
			this.poblacion[monticuloMin.poll().getValue()].copia(this.elites[i]);
		}
	}
	
	//	Metodo que evalua la poblacion actual
	private void evaluar(Float[] aptitudes, Float[] aptitudesDesp, Float[] puntuaciones, Float[] puntuacionesAcum, Double[] infogen, Boolean maximizacion){
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
				aptitudes[i] = this.poblacion[i].getAptitud().floatValue();
				sumAptitudes += aptitudes[i];
				
				//	Actualizamos el mejor
				if(aptitudes[i] > mejorAptitudEnGen){
					mejorAptitudEnGen = aptitudes[i];
					mejorCromosomaGen = i;
				}
				
				//	Guardamos el mas pequeño
				menorAptitud = Float.min(menorAptitud, aptitudes[i]);
			}
			
			//	Actualizamos el mejor global
			if(mejorAptitudEnGen > this.valorMejor){
				this.valorMejor = mejorAptitudEnGen;
				this.individuoMejor.copia(this.poblacion[mejorCromosomaGen]);
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
				aptitudes[i] = this.poblacion[i].getAptitud().floatValue();
				sumAptitudes += aptitudes[i];
				
				//	Actualizamos el mejor
				if(aptitudes[i] < mejorAptitudEnGen){
					mejorAptitudEnGen = aptitudes[i];
					mejorCromosomaGen = i;
				}
				
				//	Guardamos el mas grande
				mayorAptitud = Float.max(mayorAptitud, aptitudes[i]);				
			}
			
			//	Actualizamos el mejor global
			if(mejorAptitudEnGen < this.valorMejor.floatValue()){
				this.valorMejor = mejorAptitudEnGen;
				this.individuoMejor.copia(this.poblacion[mejorCromosomaGen]);
			}
			
			desplazarAptitudes(aptitudes, aptitudesDesp, mayorAptitud, maximizacion);
		}
		
		for(int i=0; i<this.tamanoPoblacion; i++){
			sumAptitudesDesp += aptitudesDesp[i];
		}
		
		//	Calculamos las puntuaciones para la seleccion
		puntuaciones[0] = aptitudesDesp[0]/sumAptitudesDesp;
		puntuacionesAcum[0] = puntuaciones[0];
		
		for(int i=1; i<this.tamanoPoblacion; i++){
			puntuaciones[i] = aptitudesDesp[i]/sumAptitudesDesp;
			puntuacionesAcum[i] = puntuaciones[i]+puntuacionesAcum[i-1];
		}
		puntuacionesAcum[this.tamanoPoblacion-1] = 1f; // Asegurarse que el ultimo valor es 1
		
		//	Informacion de la generacion
		infogen[0] = (double) mejorAptitudEnGen;
		infogen[1] = (double) (sumAptitudes/this.tamanoPoblacion);
		
	}
	
	private void desplazarAptitudes(Float[] aptitudes, Float[] aptitudesDesp, Float aptitud, Boolean maximizacion){
		if(maximizacion){
			for(int i=0; i<aptitudes.length; i++){
				aptitudesDesp[i] = aptitudes[i]+aptitud;
			}
		}
		else for(int i=0; i<aptitudes.length; i++){
			aptitudesDesp[i] = aptitud-aptitudes[i];
		}
	}
	
	// Metodo que decide los cromosomas que se cruzan en funcion del algoritmo de seleccion
	private void seleccionar(Float[] aptitudes, Float[] puntuacionesAcum){
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
				Par<Float, Integer> p = new Par<Float, Integer>(aptitudes[i], i);
				monticuloMax.add(p);
			}
			
			//	Guardamos los elites
			for(int i=0; i<this.numElites; i++){
				this.elites[i].copia(this.poblacion[monticuloMax.poll().getValue()]);
			}
		}
		
		Cromosoma[] seleccionados = new Cromosoma[this.tamanoPoblacion];
		for(int i=0; i<this.tamanoPoblacion; i++){
			Cromosoma elem = FuncionFactory.getFuncion(funcion, precision, n, randomizer);
			seleccionados[i] = elem;
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
		int[] cruzar = new int[this.tamanoPoblacion];
		
		//	Obtenemos los cromosomas
		for(int i=0; i<this.tamanoPoblacion; i++){
			if(randomizer.nextFloat() < this.probabilidadCruce){
				cruzar[numSeleccionados] = i;
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
			Cromosoma p1 = this.poblacion[cruzar[i]];
			Cromosoma p2 = this.poblacion[cruzar[i+1]];
			Cromosoma.cruzar(p1, p2, h1, h2, randomizer, this.tipoSeleccion);
			
			//	Sustituimos a los padres
			this.poblacion[cruzar[i]] = h1;
			this.poblacion[cruzar[i+1]] = h2;
		}
	}
	
	//	Metodo que muta la poblacion
	private void mutar(){
		for(int i=0; i<this.tamanoPoblacion; i++){
			this.poblacion[i].mutar(probabilidadMutacion, randomizer);
		}
	}
	
	public long getSemilla(){
		return this.semilla;
	}
}

