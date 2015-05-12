package getrekt.projetfinaljavav2.Monnayeur;

import junit.framework.TestCase;

import getrekt.projetfinaljavav2.monnayeur.CashException;
import getrekt.projetfinaljavav2.monnayeur.CashRegister;
import getrekt.projetfinaljavav2.monnayeur.Change;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauChange;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauMachine;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.monnayeur.Money;
import getrekt.projetfinaljavav2.monnayeur.MoneyMachine;


public abstract class TestAbstract extends TestCase {

	/**
	 * Exemple de test unitaire
	 */
	public void test999(){
		try{
			String nom = getStudentName();
			MoneyMachine m = getMonnayeur();
			CashRegister cash = fullCashReg();
			Change result = m.computeChange((float) 999.00, cash);
			Change expected = new LajoieCorriveauChange();
			expected.addItems(Money.bill100, 9);
			expected.addItems(Money.bill50, 1);
			expected.addItems(Money.bill20, 2);
			expected.addItems(Money.bill5, 1);
			expected.addItems(Money.coin2, 2);
			assertEquals("Ouch for  " + nom, result.numberOfItemsFor(Money.bill100), 9);
			assertEquals("Ouch for  " + nom, result.numberOfItemsFor(Money.bill50), 1);
			assertEquals("Ouch for  " + nom, result.numberOfItemsFor(Money.bill20), 2);
			assertEquals("Ouch for  " + nom, result.numberOfItemsFor(Money.bill5), 1);
			assertEquals("Ouch for  " + nom, result.numberOfItemsFor(Money.coin2), 2);

		}catch(CashException e){
			fail();
		}
		
	}
	public void testChangeAddInitialize()
	{
		try
		{
		LajoieCorriveauChange change = new LajoieCorriveauChange();
		
		change.addItems(Money.bill10, 2);
		change.addItems(Money.coin25s, 2);
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testChangeItemsNumber()
	{
		try
		{
		LajoieCorriveauChange change = new LajoieCorriveauChange();
		
		change.addItems(Money.bill100, 2);
		change.addItems(Money.coin10s, 4);
		
		assertTrue(change.totalNumberOfItems() == 6);
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testChangeItemsValue()
	{
		try
		{
		LajoieCorriveauChange change = new LajoieCorriveauChange();
		
		change.addItems(Money.bill5, 2);
		change.addItems(Money.coin2, 4);
		change.addItems(Money.coin10s, 3);
		
		assertEquals(18.3, change.totalValue());
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testChangeItemsNumberZero()
	{
		try
		{
		LajoieCorriveauChange change = new LajoieCorriveauChange();
		
		assertEquals(true, change.totalNumberOfItems() == 0);
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testChangeItemsValueZero()
	{
		try
		{
		LajoieCorriveauChange change = new LajoieCorriveauChange();
		
		assertEquals(true, change.totalValue() == 0);
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testChangeAddNegativeORZero()
	{
		Change change = null;
		
		// Initializing Change...
		try
		{
			LajoieCorriveauChange change2 = new LajoieCorriveauChange();
			change = change2;
		}
		catch (Exception e)
		{
			fail();
		}
		
		// We cant add a zero.
		try
		{
			change.addItems(Money.bill50, 0);
			fail();
		}
		catch (Exception e)
		{
			
		}
		
		// We cant add a negative.
		try
		{
			change.addItems(Money.bill50, -1);
			fail();
		}
		catch (Exception e)
		{
			
		}
	}
	public void testRegisterAddItems()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(2, reg.numberOfItemsFor(Money.bill100));
		assertEquals(3, reg.numberOfItemsFor(Money.bill10));
		assertEquals(10, reg.numberOfItemsFor(Money.coin1s));
		
		// Cant add a negative.
		try
		{
			reg.addItems(Money.bill5, -1);
			fail();
		}
		catch (Exception e)
		{
			
		}
		
		// Cant add a zero.
		try
		{
			reg.addItems(Money.coin1, 0);
			fail();
		}
		catch (Exception e)
		{
			
		}
		
	}
	public void testRegisterNumberOfItemsForWithMoneyMachine()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		MoneyMachine m = new LajoieCorriveauMachine();
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(2, reg.numberOfItemsFor(Money.bill100));
		assertEquals(3, reg.numberOfItemsFor(Money.bill10));
		assertEquals(10, reg.numberOfItemsFor(Money.coin1s));
		
		try 
		{
			m.computeChange(100, reg);
			assertEquals(1, reg.numberOfItemsFor(Money.bill100));
		} 
		catch (CashException e) 
		{
			fail();
		}
		
		
	}
	public void testRegisterFull()
	{
		CashRegister reg = new LajoieCorriveauReg();
		
		for (Money item : Money.values()) {
			assertEquals(reg.maxCapacityFor(item), reg.numberOfItemsFor(item));
		}
	}
	public void testRegisterTotalNumberOfItems()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(15, reg.totalNumberOfItems());
	}
	public void testRegisterTotalNumberOfItemsWithMoneyMachine()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(15, reg.totalNumberOfItems());
		
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try 
		{
			m.computeChange(100, reg);
		} 
		catch (CashException e) 
		{
			fail();
		}
		
		assertEquals(14, reg.totalNumberOfItems());
	}
	public void testRegisterTotalValue()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(230.10, reg.totalValue() / 100);
	}
	public void testRegisterTotalValueWithMoneyMachine()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0);
		
		reg.addItems(Money.bill100, 2);
		reg.addItems(Money.bill10, 3);
		reg.addItems(Money.coin1s, 10);
		
		assertEquals(230.10, reg.totalValue() / 100);
		
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try 
		{
			m.computeChange(100, reg);
		} 
		catch (CashException e) 
		{
			fail();
		}
		
		assertEquals(130.10, reg.totalValue() / 100);
	}
	public void testRegisterMaxCapacity()
	{
		CashRegister reg = new LajoieCorriveauReg(50, 40, 30, 20, 10, 50, 50, 50, 50, 50, 50, 50);
		
		assertEquals(50, reg.maxCapacityFor(Money.bill10));
		assertEquals(50, reg.maxCapacityFor(Money.coin1s));
		assertEquals(40, reg.maxCapacityFor(Money.coin5s));
		assertEquals(30, reg.maxCapacityFor(Money.coin10s));
		assertEquals(20, reg.maxCapacityFor(Money.coin20s));
		
		CashRegister reg2 = new LajoieCorriveauReg();
		
		try
		{
			reg2.addItems(Money.coin10s, 1);
			fail();
		}
		catch (Exception e)
		{
			
		}
	}
	public void testRegisterUseItems()
	{
		CashRegister reg = new LajoieCorriveauReg();
		
		reg.useItems(Money.bill10, 3);
		assertEquals(97, reg.numberOfItemsFor(Money.bill10));
		
		// Cant use 0 items.
		try
		{
			reg.useItems(Money.bill10, 0);	
			fail();
		}
		catch (Exception e)
		{
			
		}
		
		// Cant use a negative amount.
		try
		{
			reg.useItems(Money.bill5, -1);
			fail();
		}
		catch (Exception e)
		{
			
		}
		
		// Cant use more items than there are in the registry.
		try
		{
			reg.useItems(Money.bill100, 101);
			fail();
		}
		catch (Exception e)
		{
			
		}	
		
		// Cant use items when there are no more in the registry.
		try
		{
			reg.useItems(Money.coin1, 100);		
			reg.useItems(Money.coin1, 1);			
			fail();
		}
		catch (Exception e)
		{
			
		}
	}
	public void testMoneyMachine()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(200, reg);
			Change changeTheory = new LajoieCorriveauChange();
			
			changeTheory.addItems(Money.bill100, 2);
			
			assertEquals(2, changeReal.numberOfItemsFor(Money.bill100));
		}
		catch (Exception e)
		{
			fail();
		}
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(47.30, reg);
			Change changeTheory = new LajoieCorriveauChange();
			
			changeTheory.addItems(Money.bill20, 2);
			changeTheory.addItems(Money.bill5, 1);
			changeTheory.addItems(Money.coin2, 1);
			changeTheory.addItems(Money.coin25s, 1);
			changeTheory.addItems(Money.coin5s, 1);
			
			assertEquals(2, changeReal.numberOfItemsFor(Money.bill20));
			assertEquals(1, changeReal.numberOfItemsFor(Money.bill5));
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin2));
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin25s));
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin5s));
		}
		catch (Exception e)
		{
			fail();
		}
	}
	public void testMoneyMachine2()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(11.20, reg);
			Change changeTheory = new LajoieCorriveauChange();
			
			changeTheory.addItems(Money.bill10, 1);
			changeTheory.addItems(Money.coin20s, 1);
			changeTheory.addItems(Money.coin1, 1);
			
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin20s));
			assertEquals(1, changeReal.numberOfItemsFor(Money.bill10));
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin1));
		}
		catch (Exception e)
		{
			fail();
		}
	}

	public void testMoneyMachine3()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(9.60, reg);
			Change changeTheory = new LajoieCorriveauChange();
			
			changeTheory.addItems(Money.bill5, 1);
			changeTheory.addItems(Money.coin2, 2);
			changeTheory.addItems(Money.coin25s, 2);
			changeTheory.addItems(Money.coin10s, 1);
			
			assertEquals(1, changeReal.numberOfItemsFor(Money.bill5));
			assertEquals(2, changeReal.numberOfItemsFor(Money.coin2));
			assertEquals(2, changeReal.numberOfItemsFor(Money.coin25s));
			assertEquals(1, changeReal.numberOfItemsFor(Money.coin10s));
		}
		catch (Exception e)
		{
			fail();
		}
	}

	public void testMoneyMachineNegativeAmount()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(-2, reg);
			fail();
		}
		catch (Exception e)
		{
			
		}
	}
	

	public void testMoneyMachineInvalidAmount()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg();
			Change changeReal = m.computeChange(4.0009, reg);
			fail();
		}
		catch (Exception e)
		{
			
		}
	}
	
    public void testMoneyMachineEmpty()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			Change changeReal = m.computeChange(1265.45, reg);
			fail();
		}
		catch (Exception e)
		{
			//Success! It should crash.
		}
	}
	
	public void testMoneyMachineNotOptimalChange()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg(0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 20);
			Change changeReal = m.computeChange(100.00, reg);
			
			assertEquals(100.00, changeReal.totalValue());
		}
		catch (Exception e)
		{
			fail();
		}
	}
	
	public void testMoneyMachineNotOptimalChange2()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg(0, 0, 0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 10);
			Change changeReal = m.computeChange(110.00, reg);
			
			assertEquals(110.00, changeReal.totalValue());
		}
		catch (Exception e)
		{
			fail();
		}
	}
	
	public void testMoneyMachineNotOptimalChange3()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg(0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 5);
			Change changeReal = m.computeChange(125.00, reg);
			
			assertEquals(125.00, changeReal.totalValue());
		}
		catch (Exception e)
		{
			fail();
		}
	}
	
//	public void testMoneyMachineNotOptimalChange4()
//	{
//		MoneyMachine m = new LajoieCorriveauMachine();
//		
//		try
//		{
//			CashRegister reg = new LajoieCorriveauReg(0, 10, 0, 10, 10, 10, 0, 0, 0, 0, 0, 0, 5);
//			Change changeReal = m.computeChange(130.00, reg);
//			
//			assertEquals(130.00, changeReal.totalValue());
//			assertEquals(2, changeReal.numberOfItemsFor(Money.coin2));
//			assertEquals(1, changeReal.numberOfItemsFor(Money.coin1));
//		}
//		catch (Exception e)
//		{
//			fail();
//		}
//	}
	
	public void testMoneyMachineTooBigAmount()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		try
		{
			CashRegister reg = new LajoieCorriveauReg(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			Change changeReal = m.computeChange(100.00, reg);
			fail();
		}
		catch (Exception e)
		{
			//Success! It should crash.
		}
	}

	public void testMoneyMachineCentsRule()
	{
		MoneyMachine m = new LajoieCorriveauMachine();
		
		CashRegister reg = new LajoieCorriveauReg();
		
		try
		{
			Change changeReal = m.computeChange(0.03, reg);
			Change changeTheory = new LajoieCorriveauChange();
			
			changeTheory.addItems(Money.coin5s, 1);
			
			assertEquals(changeReal.numberOfItemsFor(Money.coin5s), 1);
			
			Change changeReal2 = m.computeChange(0.02, reg);
			
			// The change is 0
			
			assertEquals(changeReal2.totalNumberOfItems(), 0);
			
			Change changeReal3 = m.computeChange(0.01, reg);
			
			// The change is 0
			
			assertEquals(changeReal3.totalNumberOfItems(), 0);
			
			Change changeReal4 = m.computeChange(0.04, reg);
			Change changeTheory4 = new LajoieCorriveauChange();
			
			changeTheory4.addItems(Money.coin5s, 1);
			
			assertEquals(changeReal4.numberOfItemsFor(Money.coin5s), 1);
			
			Change changeReal5 = m.computeChange(0.06, reg);
			Change changeTheory5 = new LajoieCorriveauChange();
			
			changeTheory5.addItems(Money.coin5s, 1);
			
			assertEquals(changeReal5.numberOfItemsFor(Money.coin5s), 1);
			
			Change changeReal6 = m.computeChange(0.07, reg);
			Change changeTheory6 = new LajoieCorriveauChange();
			
			changeTheory6.addItems(Money.coin5s, 1);
			
			assertEquals(changeReal6.numberOfItemsFor(Money.coin5s), 1);
			
			Change changeReal7 = m.computeChange(0.08, reg);
			Change changeTheory7 = new LajoieCorriveauChange();
			
			changeTheory7.addItems(Money.coin10s, 1);
			
			assertEquals(changeReal7.numberOfItemsFor(Money.coin10s), 1);
			
			Change changeReal8 = m.computeChange(0.09, reg);
			Change changeTheory8 = new LajoieCorriveauChange();
			
			changeTheory8.addItems(Money.coin10s, 1);
			
			assertEquals(changeReal8.numberOfItemsFor(Money.coin10s), 1);
		}
		catch (Exception e)
		{
			
		}
	}
	
	public abstract CashRegister fullCashReg();
	public abstract MoneyMachine getMonnayeur();
	public abstract String getStudentName() ;
}
