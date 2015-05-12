package getrekt.projetfinaljavav2.Monnayeur;

import getrekt.projetfinaljavav2.monnayeur.CashRegister;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauMachine;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.monnayeur.MoneyMachine;

public class TestLajoieCorriveau extends TestAbstract {

	@Override
	public CashRegister fullCashReg() {
		return new LajoieCorriveauReg();
	}

	@Override
	public MoneyMachine getMonnayeur() {
		return new LajoieCorriveauMachine();
	}

	@Override
	public String getStudentName() {
		return "Lajoie-Corriveau Joel";
	}
	
}
