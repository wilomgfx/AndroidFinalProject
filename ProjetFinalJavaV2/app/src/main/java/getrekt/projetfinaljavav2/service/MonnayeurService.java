package getrekt.projetfinaljavav2.service;

import android.content.Context;

import getrekt.projetfinaljavav2.monnayeur.CashException;
import getrekt.projetfinaljavav2.monnayeur.CashRegister;
import getrekt.projetfinaljavav2.monnayeur.Change;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauMachine;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.monnayeur.MoneyMachine;
import getrekt.projetfinaljavav2.monnayeur.StringUtilsMonnayeur;

/**
 * Created by 1387434 on 2015-05-05.
 */
public class MonnayeurService {

    Context context;

    MoneyMachine regMachine;

    CashRegister cashReg;



    public MonnayeurService(Context pContext)
    {
        context = pContext;

        regMachine = new LajoieCorriveauMachine();

        cashReg = new LajoieCorriveauReg();

    }

    public Change PayerAvecLaCaisse(Double amount) throws CashException {

        Change result = regMachine.computeChange(/*(float) float ?*/ amount, cashReg);

        //pretty printing the change
        return  result;


    }

    public String PrettyPrintChange(Change change) throws CashException {
        //pretty printing the change
        return  StringUtilsMonnayeur.toString(change);
    }


}
