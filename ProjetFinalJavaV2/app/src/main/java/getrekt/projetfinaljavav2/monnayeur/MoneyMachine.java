package getrekt.projetfinaljavav2.monnayeur;

public interface MoneyMachine {

	public Change computeChange(double amount, CashRegister register) throws CashException;
	
}
