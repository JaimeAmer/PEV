package pr1;

import java.util.Vector;


public abstract class Cromosoma {

	private Vector<Gen> cromosoma;
	private double fenotipo;
	private double aptitud;
	private double puntuacion;
	private double punt_acum;
	
	public Cromosoma(){
		this.cromosoma = new Vector<Gen>();
	}
	
	public Cromosoma(Vector<Gen> cromosoma){
		this.cromosoma = cromosoma;
	}
	
	private Gen getAlelo(int alelo){
		return cromosoma.elementAt(alelo);
	}
	
	public Gen getGenotipo(int alelo){
		return this.getAlelo(alelo);
	}
	
	public double getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(double fenotipo) {
		this.fenotipo = fenotipo;
	}

	public double getAptitud() {
		return aptitud;
	}

	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public double getPunt_acum() {
		return punt_acum;
	}

	public void setPunt_acum(double punt_acum) {
		this.punt_acum = punt_acum;
	}
	/*
	public double getFenotipo(int alelo){
		double fenotipo = 0;
		Vector<Boolean> gen = this.getAlelo(alelo).getGen();
		
		for(int i=gen.size(); i > 0; i--){
			fenotipo = fenotipo + Math.pow(2, Double.parseDouble(gen.elementAt(i).toString()));
		}
		
		
		return fenotipo;
	}
	*/
	
	
	
	
	
}
