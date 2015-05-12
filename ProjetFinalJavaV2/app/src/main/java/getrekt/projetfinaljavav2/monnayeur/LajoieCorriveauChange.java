package getrekt.projetfinaljavav2.monnayeur;

import java.util.ArrayList;
import java.util.List;

public class LajoieCorriveauChange implements Change {

	List<Money> items = new ArrayList<Money>();
	
	@Override
	public int numberOfItemsFor(Money m) {
		int cpt = 0;
		
		for (Money item : items) {
			if(item.centValue == m.centValue)
				cpt++;
		}
		
		return cpt;
	}

	@Override
	public void addItems(Money m, int number) {
		if(number <= 0)
			throw new IllegalArgumentException("Bad amount");
		
		for (int i = 0; i < number; i++) {
			items.add(m);
		}	
	}

	@Override
	public double totalValue() {
		double total = 0;
		
		for (Money item : items) {
			total += item.centValue;
		}
		
		return total / 100;
	}

	@Override
	public int totalNumberOfItems() {
		int cpt = 0;
		
		for (Money item : items) {
			cpt++;
		}
		
		return cpt;
	}

}
