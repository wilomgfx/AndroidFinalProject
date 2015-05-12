package getrekt.projetfinaljavav2.monnayeur;

/**
 * Classe permettant d'afficher facilement une caisse ou du change dans la console
 * @author joris
 *
 */
public class StringUtilsMonnayeur {

	public static String toString(CashRegister cashreg){
		String result = "Cash Register contains \n";
		for (Money m : Money.values()){
			result += "  "+String.format("%7s", cashreg.numberOfItemsFor(m)+"")+"    @    "+m.pretty()+"\n";
		}
		return result;
	}

	public static String toString(Change change){
		String result = "Change is \n";
		for (Money m : Money.values()){
			if (change.numberOfItemsFor(m) > 0){
				result += "  "+String.format("%3s", change.numberOfItemsFor(m)+"")+"    @    "+m.pretty()+"\n";
			}
		}
		return result;
	}

}
