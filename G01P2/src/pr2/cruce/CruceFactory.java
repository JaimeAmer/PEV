package pr2.cruce;

public class CruceFactory {
	static public Cruce crear(String cruce) {
		switch(cruce) {
		case "Discreto":
			return new CruceDiscretoUniforme();
		case "PMX":
			return new CrucePMX();
		case "OX":
			return new CruceOX();
		case "OX_PosPri":
			return new CruceOX_PosPri();
		case "OX_OrPri":
			return new CruceOX_OrPri();
		case "CX":
			return new CruceCX();		
		case "ERX":
			return new CruceERX();
		case "CodOrd":
			return new CruceCodOrd();
		case "Propio":
			return new CrucePropio();
		default:
			return new CrucePropio();
		}
	}
}
