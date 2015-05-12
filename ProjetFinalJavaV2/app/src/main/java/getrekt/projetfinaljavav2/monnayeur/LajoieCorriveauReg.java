package getrekt.projetfinaljavav2.monnayeur;

import java.util.HashMap;
import java.util.Map;

public class LajoieCorriveauReg implements getrekt.projetfinaljavav2.monnayeur.CashRegister {

	private Map<Money, Integer> register = new HashMap<Money, Integer>();
	private Map<Money, Integer> maxQty = new HashMap<Money, Integer>();
	
	// Les quantit�s maximales peuvent �tre chang�es ici.
	public LajoieCorriveauReg()
	{
		// QTY Max pour : 1 Cent
		maxQty.put(Money.coin1s, 100);
		
		// QTY Max pour : 5 Cent
		maxQty.put(Money.coin5s, 100);
		
		// QTY Max pour : 10 Cent
		maxQty.put(Money.coin10s, 100);
		
		// QTY Max pour : 20 Cent
		maxQty.put(Money.coin20s, 100);
		
		// QTY Max pour : 25 Cent
		maxQty.put(Money.coin25s, 100);
		
		// QTY Max pour : 1 Dollar
		maxQty.put(Money.coin1, 100);
		
		// QTY Max pour : 2 Dollar
		maxQty.put(Money.coin2, 100);
		
		// QTY Max pour : 5$ Bill
		maxQty.put(Money.bill5, 100);
		
		// QTY Max pour : 10$ Bill
		maxQty.put(Money.bill10, 100);
		
		// QTY Max pour : 20$ Bill
		maxQty.put(Money.bill20, 100);
		
		// QTY Max pour : 50$ Bill
		maxQty.put(Money.bill50, 100);
		
		// QTY Max pour : 100$ Bill
		maxQty.put(Money.bill100, 100);
		
		// Remplit la caisse.
		for (Money item : Money.values()) {
			register.put(item, maxQty.get(item));
		}
	}
	
	public LajoieCorriveauReg(int pCoin1s, int pCoin5s, int pCoin10s, int pCoin20s,  int pCoin25s,  int pCoin1,  int pCoin2, int pBill5,  int pBill10,  int pBill20,  int pBill50,  int pBill100)
	{
		// QTY Max pour : 1 Cent
		maxQty.put(Money.coin1s, pCoin1s);

		// QTY Max pour : 5 Cent
		maxQty.put(Money.coin5s, pCoin5s);

		// QTY Max pour : 10 Cent
		maxQty.put(Money.coin10s, pCoin10s);

		// QTY Max pour : 20 Cent
		maxQty.put(Money.coin20s, pCoin20s);

		// QTY Max pour : 25 Cent
		maxQty.put(Money.coin25s, pCoin25s);

		// QTY Max pour : 1 Dollar
		maxQty.put(Money.coin1, pCoin1);

		// QTY Max pour : 2 Dollar
		maxQty.put(Money.coin2, pCoin2);

		// QTY Max pour : 5$ Bill
		maxQty.put(Money.bill5, pBill5);

		// QTY Max pour : 10$ Bill
		maxQty.put(Money.bill10, pBill10);

		// QTY Max pour : 20$ Bill
		maxQty.put(Money.bill20, pBill20);

		// QTY Max pour : 50$ Bill
		maxQty.put(Money.bill50, pBill50);

		// QTY Max pour : 100$ Bill
		maxQty.put(Money.bill100, pBill100);

		// Remplit la caisse.
		for (Money item : Money.values()) {
			register.put(item, maxQty.get(item));
		}
	}
	
	public LajoieCorriveauReg(int pCoin1s, int pCoin5s, int pCoin10s, int pCoin20s,  int pCoin25s,  int pCoin1,  int pCoin2, int pBill5,  int pBill10,  int pBill20,  int pBill50,  int pBill100, int qty)
	{
		// QTY Max pour : 1 Cent
		maxQty.put(Money.coin1s, pCoin1s);

		// QTY Max pour : 5 Cent
		maxQty.put(Money.coin5s, pCoin5s);

		// QTY Max pour : 10 Cent
		maxQty.put(Money.coin10s, pCoin10s);

		// QTY Max pour : 20 Cent
		maxQty.put(Money.coin20s, pCoin20s);

		// QTY Max pour : 25 Cent
		maxQty.put(Money.coin25s, pCoin25s);

		// QTY Max pour : 1 Dollar
		maxQty.put(Money.coin1, pCoin1);

		// QTY Max pour : 2 Dollar
		maxQty.put(Money.coin2, pCoin2);

		// QTY Max pour : 5$ Bill
		maxQty.put(Money.bill5, pBill5);

		// QTY Max pour : 10$ Bill
		maxQty.put(Money.bill10, pBill10);

		// QTY Max pour : 20$ Bill
		maxQty.put(Money.bill20, pBill20);

		// QTY Max pour : 50$ Bill
		maxQty.put(Money.bill50, pBill50);

		// QTY Max pour : 100$ Bill
		maxQty.put(Money.bill100, pBill100);

		// Remplit la caisse.
		for (Money item : Money.values()) {
			register.put(item, qty);
		}
	}

	@Override
	public int numberOfItemsFor(Money m) {
		return register.get(m);
	}

	@Override
	public void addItems(Money m, int number) {
		if (number <= 0)
			throw new IllegalArgumentException("On ne peut ajouter un montant n�gatif d'objet ou un montant nul (0).");
		if (register.get(m) == maxQty.get(m))
			throw new IllegalArgumentException("La caisse est pleine pour le type de monnaie " + m.toString() + ".");
		
		register.put(m, number);
	}

	@Override
	public double totalValue() {
		double total = 0;
		
		for(Map.Entry<Money, Integer> entry : register.entrySet())
		{
			total += entry.getValue() * entry.getKey().centValue;
		}
		
		return total;
	}

	@Override
	public int totalNumberOfItems() {
		int cpt = 0;
		
		for(Map.Entry<Money, Integer> entry : register.entrySet())
		{
			cpt += entry.getValue();
		}
		
		return cpt;
	}

	@Override
	public int maxCapacityFor(Money m) {
		return maxQty.get(m);
	}

	@Override
	public void useItems(Money m, int number) {
		if (number <= 0)
			throw new IllegalArgumentException("On ne peut retirer un montant nul ou n�gatif de la caisse.");
		
		int oldAmount = register.get(m);
		int newAmount = oldAmount - number;
		if (newAmount < 0)
			throw new IllegalArgumentException("La caisse n'a pas assez de change de type " + m.toString() + " pour effectuer le retrait.");
		
		register.put(m, newAmount);
	}
	
}
