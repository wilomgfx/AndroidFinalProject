package getrekt.projetfinaljavav2.monnayeur;

public class NotEnoughMoneyException extends CashException {
	
	public NotEnoughMoneyException(String message)
	{
		super(message);
	}
}
