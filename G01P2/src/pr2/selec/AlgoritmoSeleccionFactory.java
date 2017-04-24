package pr2.selec;

public class AlgoritmoSeleccionFactory {

	 static public AlgoritmoSeleccion getAlgoritmoSeleccion(String tipoSeleccion, Integer participantes){
		switch(tipoSeleccion){
		case "Torneo":
			return new SeleccionTorneo(participantes);
		case "Ranking":
			return new SeleccionRanking();
		case "Ruleta":
			return new SeleccionRuleta();
		case "Torneo Probabilistico":
			return new SeleccionTorneoProbabilistico(participantes);
		case "Estocastico Universal":
			return new SeleccionEstocasticoUniversal();
		default:
			return new SeleccionRuleta();
		}
	}
}

