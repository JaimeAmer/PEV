package pr1.base;

public enum TipoAlgoritmo {
	RULETA, TORNEO, TORNEO_PROBABILISTICO, RANKING;
	
	public String toString(TipoAlgoritmo a){
		switch(a){
		case RULETA:
			return "RULETA";
		case TORNEO:
			return "TORNEO";
		case TORNEO_PROBABILISTICO:
			return "TORNEO PROBABILISTICO";
		case RANKING:
			return "RANKING";
		default:
			return "";
		}
	}
}