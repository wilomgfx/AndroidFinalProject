package getrekt.projetfinaljavav2.monnayeur;

public interface CashRegister extends Change{

	public int maxCapacityFor(Money m);
	
	public void useItems(Money m, int number);
	
}
