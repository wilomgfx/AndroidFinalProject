package getrekt.projetfinaljavav2.monnayeur;

public class LajoieCorriveauMachine implements MoneyMachine {
	
	@Override
	public Change computeChange(double amount, CashRegister register)
			throws CashException {
		// ID�E : Faire un While qui it�re sur l'enum Money
		
		String amountString = Double.toString(amount);
		
		String[] tabAmount = amountString.split("\\.");
		
		int nbrDecimales = tabAmount[1].length();
		
		if(nbrDecimales > 2)
			throw new InvalidAmountException("Le montant a plus de deux d�cimales.");
		
		int remains = (int)(amount * 100);
		
		if(register.totalValue() < remains)
			throw new NotEnoughMoneyException("La Caisse ne contiens pas assez d'argent.");
		if(remains < 0)
			throw new InvalidAmountException("Le montant ne peut pas �tre n�gatif.");
		
		Change change = new LajoieCorriveauChange();
		
		if((int)(remains / 10000) != 0)
		{
			try
			{
				int nbr = (int)(remains / 10000);
				register.useItems(Money.bill100, nbr);
				change.addItems(Money.bill100, nbr);
				
				remains -= nbr * 10000;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 5000) != 0)
		{
			try
			{
				int nbr = (int)(remains / 5000);
				register.useItems(Money.bill50, nbr);
				change.addItems(Money.bill50, nbr);
				
				remains -= nbr * 5000;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 2000) != 0)
		{
			try
			{
				int nbr = (int)(remains / 2000);
				register.useItems(Money.bill20, nbr);
				change.addItems(Money.bill20, nbr);
				
				remains -= nbr * 2000;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 1000) != 0)
		{
			try
			{
				int nbr = (int)(remains / 1000);
				register.useItems(Money.bill10, nbr);
				change.addItems(Money.bill10, nbr);
				
				remains -= nbr * 1000;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 500) != 0)
		{
			try
			{
				int nbr = (int)(remains / 500);
				register.useItems(Money.bill5, nbr);
				change.addItems(Money.bill5, nbr);
				
				remains -= nbr * 500;
			}
			catch (Exception e)
			{
				
			}
		}
		
		if((int)(remains / 200) != 0)
		{
			try
			{
				int nbr = (int)(remains / 200);
				register.useItems(Money.coin2, nbr);
				change.addItems(Money.coin2, nbr);
				
				remains -= nbr * 200;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 100) != 0)
		{
			try
			{
				int nbr = (int)(remains / 100);
				register.useItems(Money.coin1, nbr);
				change.addItems(Money.coin1, nbr);
				
				remains -= nbr * 100;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 25) != 0)
		{
			try
			{
				int nbr = (int)(remains / 25);
				register.useItems(Money.coin25s, nbr);
				change.addItems(Money.coin25s, nbr);
				
				remains -= nbr * 25;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 20) != 0)
		{
			try
			{
				int nbr = (int)(remains / 20);
				register.useItems(Money.coin20s, nbr);
				change.addItems(Money.coin20s, nbr);
				
				remains -= nbr * 20;
			}
			catch (Exception e)
			{
				
			}
		}
		if(((int)(remains / 10) != 0)|| remains == 9 || remains == 8)
		{
			try
			{			
				if(remains == 8 || remains == 9)
				{
					int nbr = 1;
					register.useItems(Money.coin10s, nbr);
					change.addItems(Money.coin10s, nbr);
					
					remains = 0;
				}
				int nbr = (int)(remains / 10);
				register.useItems(Money.coin10s, nbr);
				change.addItems(Money.coin10s, nbr);
				
				remains -= nbr * 10;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 5) != 0)
		{
			try
			{
				int nbr = (int)(remains / 5);
				register.useItems(Money.coin5s, nbr);
				change.addItems(Money.coin5s, nbr);
				
				remains -= nbr * 5;
			}
			catch (Exception e)
			{
				
			}
		}
		if((int)(remains / 1) != 0)
		{
			int nbr = (int)(remains / 1);

			if(nbr < 3)
			{					
				remains -= nbr;
			}
			else
			{
				try
				{
					register.useItems(Money.coin5s, 1);
					change.addItems(Money.coin5s, 1);

					remains = 0;
				}
				catch (Exception e)
				{
					throw new NotEnoughMoneyException("Il n'y a plus assez d'argent dans la caisse.");
				}				
			}
		}
		return change;
	}

}
