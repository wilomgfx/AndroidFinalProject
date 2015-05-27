package getrekt.projetfinaljavav2.service;

import android.content.Context;
import android.util.Log;

import getrekt.projetfinaljavav2.models.NotEnoughtMoneyToPay;
import getrekt.projetfinaljavav2.models.repo.RepoCaisse;
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

    RepoCaisse repoCaisse;


    public MonnayeurService(Context pContext)
    {
        repoCaisse = RepoCaisse.get(pContext);
        context = pContext;

        regMachine = new LajoieCorriveauMachine();

        //cashReg = new LajoieCorriveauReg();
        //le premier donc le seul techniquement.
        Integer sizess = repoCaisse.getAll().size();
        Log.i("Cashregsize",sizess.toString());
        if(repoCaisse.getAll().size() ==0) {
            LajoieCorriveauReg regPleine = new LajoieCorriveauReg();
            repoCaisse.save(regPleine);
            cashReg = repoCaisse.getAll().get(0);
        }
        else{
            cashReg = repoCaisse.getAll().get(repoCaisse.getAll().size()-1);
        }

    }

    public Change PayerAvecLaCaisse(Double amountGiven,Double montantTotalDue) throws CashException,NotEnoughtMoneyToPay {

        if(amountGiven < montantTotalDue){
            throw  new NotEnoughtMoneyToPay("Not enought money added... amount to pay : "+montantTotalDue + " amount given : " + amountGiven);
        }
        if(amountGiven >= montantTotalDue){
            Double newAmount = amountGiven-montantTotalDue;
            //rounding to 2 digits
            newAmount = (double)Math.round(newAmount *100)/100;

            Change result = regMachine.computeChange(/*(float) float ?*/ newAmount, cashReg);
            repoCaisse.save((LajoieCorriveauReg)cashReg);
            return  result;
        }

        Change result = regMachine.computeChange(/*(float) float ?*/ amountGiven, cashReg);

        //sauvegarde la caisse
        repoCaisse.save((LajoieCorriveauReg)cashReg);

        //pretty printing the change
        return  result;


    }
    public CashRegister getRegister(){
        return cashReg;
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
    /***
     *  Pretty prints the change to the screen in a good looking string format
     * @param register register to pretty print
     * @return a pretty printed register to show to the client
    */
    public  String prettyPrintReg(CashRegister register){
        return  StringUtilsMonnayeur.toString(register);
    }


}
