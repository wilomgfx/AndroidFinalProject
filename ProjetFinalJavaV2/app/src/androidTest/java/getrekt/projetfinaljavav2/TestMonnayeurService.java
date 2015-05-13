package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

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

    public void testPayerReturnsGoodAmount() throws CashException {
        LajoieCorriveauChange change = new LajoieCorriveauChange();

        change.addItems(Money.bill5, 2);
        change.addItems(Money.coin2, 4);
        change.addItems(Money.coin10s, 3);

        Change changeReel;

        changeReel = moneyService.PayerAvecLaCaisse(18.3);
        assertEquals(change.totalValue(),changeReel.totalValue());
    }
    public void testPayerReturnsBadAmount(){
        LajoieCorriveauChange change = new LajoieCorriveauChange();

        change.addItems(Money.bill5, 2);
        change.addItems(Money.coin2, 4);
        change.addItems(Money.coin10s, 3);
        Change changeReel;

        try {
            changeReel = moneyService.PayerAvecLaCaisse(11.3);
            assertFalse(change.totalValue() == changeReel.totalValue());
        } catch (CashException e) {

        }

    }
}
