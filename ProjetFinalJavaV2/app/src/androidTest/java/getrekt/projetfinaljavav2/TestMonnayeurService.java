package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import getrekt.projetfinaljavav2.models.NotEnoughtMoneyToPay;
import getrekt.projetfinaljavav2.monnayeur.CashException;
import getrekt.projetfinaljavav2.monnayeur.Change;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauChange;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.monnayeur.Money;
import getrekt.projetfinaljavav2.service.MonnayeurService;

/**
 * Created by William on 2015-05-13.
 */
public class TestMonnayeurService extends AndroidTestCase {

    MonnayeurService moneyService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        moneyService = new MonnayeurService(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        moneyService = null;
    }

    public void testPayerAvecLeMontantExact() throws CashException,NotEnoughtMoneyToPay {
//        LajoieCorriveauChange change = new LajoieCorriveauChange();
//
//        change.addItems(Money.bill5, 2);
//        change.addItems(Money.coin2, 4);
//        change.addItems(Money.coin10s, 3);

        Change changeReel;

        changeReel = moneyService.PayerAvecLaCaisse(18.3,18.3);
        assertEquals(0.00,changeReel.totalValue());
    }
    public void testPayerAvecPlusQueLeMontantDue()throws CashException,NotEnoughtMoneyToPay{
        LajoieCorriveauChange change = new LajoieCorriveauChange();

        change.addItems(Money.coin1, 1);
        change.addItems(Money.coin10s, 7);

        Change changeReel;


        changeReel = moneyService.PayerAvecLaCaisse(20.00,18.3);
        assertEquals(change.totalValue(),changeReel.totalValue());

//        changeReel = moneyService.PayerAvecLaCaisse(18.3);
//        assertEquals(change.totalValue(),changeReel.totalValue());

    }

}
