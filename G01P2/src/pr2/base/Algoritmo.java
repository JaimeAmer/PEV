package pr2.base;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.text.MutableAttributeSet;

import pr2.cruce.Cruce;
import pr2.cruce.CruceFactory;
import pr2.espec.AsignacionCuadratica;
import pr2.mutacion.InversionEspecial;
import pr2.mutacion.Mutacion;
import pr2.mutacion.MutacionFactory;
import pr2.selec.AlgoritmoSeleccion;
import pr2.selec.AlgoritmoSeleccionFactory;

public class Algoritmo {

	private int numLocalizaciones;
	private int tamanoPoblacion;
	private int simulaciones;
	private Cromosoma[] poblacion;
	private Float probabilidadCruce;
	private Float probabilidadMutacion;
	private Boolean elitismo;
	private Random randomizer;
	private int numElites;
	private Cromosoma[] elites;
	private int participantes;
	private Long semilla;
	private String tipoSeleccion;
	private int[][] _f;
	private int[][] _d;
	private float aptitudMinimaGlobal;
	private float aptitudMaximaGlobal;
	private double aptitudMediaGlobal;
	private int totalCruces;
	private int totalMutaciones;
	private int totalInversiones;
	private Cromosoma individuoMejor;
	private Cruce _cruce;
	private Mutacion _mutacion;
	private InversionEspecial inversionEspecial;
	
	public Algoritmo(int numLocalizaciones, int[][] d, int[][] f, int tamanoPoblacion, float probabilidadCruce, float probabilidadMutacion, 
			int simulaciones, long semilla, int participantes, boolean elitismo, String nombreCruce, String nombreMutacion, String tipoSeleccion,
			float porcentajeInversion, int puntoInicioInversion, int puntoFinalInversion){
		this.tamanoPoblacion = tamanoPoblacion;
		this.probabilidadCruce = probabilidadCruce;
		this.probabilidadMutacion = probabilidadMutacion;
		this.simulaciones = simulaciones;
		this.randomizer = new Random();
		this.elitismo = elitismo;
		this.numElites = (int) (this.tamanoPoblacion*0.02);
		//	Si buscamos elite, que haya 1
		if(this.numElites==0)
			this.numElites = 1;
		this.participantes = participantes;
		this.tipoSeleccion = tipoSeleccion;
		this.semilla = semilla;
		this.numLocalizaciones = numLocalizaciones;
		_d = d;
		_f = f;
		_cruce = CruceFactory.crear(nombreCruce);
		_mutacion = MutacionFactory.crear(nombreMutacion);
		inversionEspecial = new InversionEspecial(porcentajeInversion, puntoInicioInversion, puntoFinalInversion);
		
		if(this.semilla != 0){
			randomizer.setSeed(semilla);
		}
		else{
			randomizer = new Random(semilla);
		}
		// Creamos la poblacion
		this.poblacion = new Cromosoma[this.tamanoPoblacion];
		for(int i=0; i<this.tamanoPoblacion; i++){
			Cromosoma elem = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
			this.poblacion[i] = elem;
		}
		
		// Eliminar duplicados.
		eliminarDuplicados();
				
		//	El mejor individuo lo elegimos aleatoriamente, solo por la grafica
		this.individuoMejor = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
		aptitudMinimaGlobal = Float.MAX_VALUE;
		aptitudMaximaGlobal = Float.MIN_VALUE;
		aptitudMediaGlobal = 0;
		totalCruces = 0;
		totalMutaciones = 0;
		totalInversiones = 0;
		
		if(this.elitismo.booleanValue()){
			this.elites = new Cromosoma[this.numElites];
			for(int i=0; i<this.numElites; i++){
				Cromosoma elem = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
				this.elites[i] = elem;
			}
		}
	}
	
	private void eliminarDuplicados() {
		boolean duplicados = false;
		
		Comparator<Cromosoma> cmp = new Comparator<Cromosoma>() {
			@Override
			public int compare(Cromosoma c1, Cromosoma c2){
				return c1.compareTo(c2);
			}
		};
		
		do
		{
			duplicados = false;
			// Ordenamos
			Arrays.sort(poblacion, cmp);

			// Comprobamos duplicados y sustituimos
			for(int i = 0; i < poblacion.length - 1; i++){
				if(poblacion[i].compareTo(poblacion[i+1]) == 0) {
					poblacion[i] = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
					duplicados = true;
				}
			}
		} while(duplicados);		
	}

	public String execute(Double[] mejorAbsoluto, Double[] mejorGeneracion, Double[] mediaGeneracion){
		Float[] aptitudes = new Float[this.tamanoPoblacion];
		Float[] aptitudesDesp = new Float[this.tamanoPoblacion];
		Float[] puntuaciones = new Float[this.tamanoPoblacion];
		Float[] puntuacionesAcum = new Float[this.tamanoPoblacion];
		
		for(int i=0; i<this.simulaciones; i++){
			Double[] infogen = new Double[2];// 0 mejor de la generacion, 1 media
			evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion[0].esMaximizacion().booleanValue());
			mejorAbsoluto[i] = (double) aptitudMinimaGlobal;
			mejorGeneracion[i] = infogen[0];
			mediaGeneracion[i] = infogen[1];
			
			
			//	Seleccionamos el cruce
			seleccionar(aptitudesDesp, puntuacionesAcum);
			
			//	Cruzamos
			cruzar();
			
			//	Mutamos
			mutar();
			
			// Inversion Especial
			inversionEspecial();
						
			//	Si hay elitismo, volvemos a evaluar para sustituir los peores por la elite
			if(this.elitismo.booleanValue()){
				evaluar(aptitudes, aptitudesDesp, puntuaciones, puntuacionesAcum, infogen, this.poblacion[0].esMaximizacion().booleanValue());
				introducirElites(aptitudesDesp);
			}			
		}
		
		int numGeneraciones = tamanoPoblacion * simulaciones;
		aptitudMediaGlobal /= numGeneraciones;
		
		String resultado = this.individuoMejor.toString() + "\n";
		resultado += String.format("Aptitud máxima: %f, Aptitud mínima: %f, Aptitud media: %f\n", aptitudMaximaGlobal, aptitudMinimaGlobal, aptitudMediaGlobal);
		resultado += String.format("Total de cruces: %d, Total de mutaciones: %d, Total de inversiones: %d", totalCruces, totalMutaciones, totalInversiones);
		
		
		return resultado;
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
			Par<Float, Integer> elem = new Par<Float, Integer>(aptitudes[i], i);
			monticuloMin.add(elem);
		}
		
		for(int i=0; i<this.numElites; i++){
			this.poblacion[monticuloMin.poll().getValue()].copia(this.elites[i]);
		}
	}
	
	//	Metodo que evalua la poblacion actual
	private void evaluar(Float[] aptitudes, Float[] aptitudesDesp, Float[] puntuaciones, Float[] puntuacionesAcum, Double[] infogen, Boolean maximizacion){
		float sumAptitudesDesp = 0;
		float sumAptitudes = 0;
		float aptitudMinimaEnGen = Float.MAX_VALUE;
		float aptitudMaximaEnGen = Float.MIN_VALUE;
		int mejorCromosomaGen = 0;
		float cMax = Float.MIN_VALUE;
		
		//	Calculamos aptitudes
		for(int i=0; i<this.tamanoPoblacion; i++){
			aptitudes[i] = this.poblacion[i].getAptitud();
			sumAptitudes += aptitudes[i];
			
			//	Actualizamos el mejor
			if(aptitudes[i] < aptitudMinimaEnGen){
				aptitudMinimaEnGen = aptitudes[i];
				mejorCromosomaGen = i;
			}
			
			if(aptitudMaximaEnGen < aptitudes[i]) {
				aptitudMaximaEnGen = aptitudes[i];
			}
			
			//	Guardamos el mas grande
			cMax = Float.max(cMax, aptitudes[i]);				
		}
		cMax *= 1.05;
		
		//	Actualizamos el mejor global
		if(aptitudMinimaEnGen < aptitudMinimaGlobal){
			this.aptitudMinimaGlobal = aptitudMinimaEnGen;
			this.individuoMejor.copia(this.poblacion[mejorCromosomaGen]);
		}
		
		if(aptitudMaximaEnGen > aptitudMaximaGlobal){
			aptitudMaximaGlobal = aptitudMaximaEnGen;			
		}
				
		desplazarAdaptacion(aptitudes, aptitudesDesp, cMax, maximizacion);
		
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
		infogen[0] = (double) aptitudMinimaEnGen; // Aptitud mínima (mejor)
		infogen[1] = (double) (sumAptitudes/this.tamanoPoblacion); // Aptitud media
		aptitudMediaGlobal += sumAptitudes;		
	}
	
	private void desplazarAdaptacion(Float[] aptitudes, Float[] aptitudesDesp, Float aptitud, Boolean maximizacion){
		
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
			Cromosoma elem = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
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
		int tamanoPoblacion = poblacion.length;
		int numSeleccionados = 0;
		int[] cruzar = new int[tamanoPoblacion];
		
		//	Seleccionamos aleatoriamente los cromosomas a cruzar
		for(int i=0; i<tamanoPoblacion; i++){
			if(randomizer.nextFloat() < probabilidadCruce){
				cruzar[numSeleccionados] = i;
				numSeleccionados++;
			}
		}
		
		//	Redondeo a par
		if(numSeleccionados % 2 == 1){
			numSeleccionados--;
		}				
		
		// Cruzamos
		for(int i=0; i<numSeleccionados/2; i++){
			Cromosoma h1 = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
			Cromosoma h2 = new AsignacionCuadratica(numLocalizaciones, _d, _f, randomizer);
			Cromosoma p1 = poblacion[cruzar[i]];
			Cromosoma p2 = poblacion[cruzar[i+1]];
			
			totalCruces += _cruce.cruzar(p1, p2, h1, h2, randomizer);			
			
			//	Sustituimos a los padres
			poblacion[cruzar[i]] = h1;
			poblacion[cruzar[i+1]] = h2;
		}
	}
	
	//	Metodo que muta la poblacion
	private void mutar(){
		for(int i=0; i<this.tamanoPoblacion; i++){
			int[] fenotipo = poblacion[i].getFenotipo();
			totalMutaciones += _mutacion.mutar(fenotipo, probabilidadMutacion, randomizer);
			poblacion[i].setFenotipo(fenotipo);
		}
	}
	
	private void inversionEspecial() {
		for(int i=0; i<this.tamanoPoblacion; i++){
			int[] fenotipo = poblacion[i].getFenotipo();
			float aptitudInicial = poblacion[i].getAptitud(); 
			totalInversiones += inversionEspecial.ejecutar(fenotipo, randomizer);
			float aptitudFinal = AsignacionCuadratica.getAptitud(fenotipo);
			// Se modifica el individuo original si la aptitud ha mejorado
			if(aptitudFinal < aptitudInicial) {
				poblacion[i].setFenotipo(fenotipo);
			}			
		}
	}
	
	public long getSemilla(){
		return this.semilla;
	}
}

