package pr1.base;

public class AlgoritmoSeleccionFactory {

	 static public AlgoritmoSeleccion getAlgoritmoSeleccion(TipoAlgoritmo a, Integer participantes){
		switch(a){
		case TORNEO:
			return new SeleccionTorneo(participantes);
		case RANKING:
			return new SeleccionRanking();
		case RULETA:
			return new SeleccionRuleta();
		case TORNEO_PROBABILISTICO:
			return new SeleccionTorneoProbabilistico(participantes);
		default:
			return new SeleccionRuleta();
		}
	}
}

