package getrekt.projetfinaljavav2.service;

import android.content.Context;

import getrekt.projetfinaljavav2.models.NotEnoughtMoneyToPay;
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

    public Change PayerAvecLaCaisse(Double amount,Double montantTotalDue) throws CashException,NotEnoughtMoneyToPay {

        if(amount < montantTotalDue){
            throw  new NotEnoughtMoneyToPay("Not enought money added... amount to pay : "+montantTotalDue + " amount given : " + amount);
        }

        Change result = regMachine.computeChange(/*(float) float ?*/ amount, cashReg);

        //pretty printing the change
        return  result;


    }

    /***
     *  Pretty prints the change to the screen in a good looking string format
     * @param change change to pretty print
     * @return a pretty printed change to show to the client
     * @throws CashException
     */
    public String PrettyPrintChange(Change change) throws CashException {
        //pretty printing the change
        return  StringUtilsMonnayeur.toString(change);
    }


}
