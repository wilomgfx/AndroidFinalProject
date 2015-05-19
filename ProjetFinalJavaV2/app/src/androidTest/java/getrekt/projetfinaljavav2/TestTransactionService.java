package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by 1252991 on 2015-05-12.
 */
public class TestTransactionService extends AndroidTestCase {

    TransactionService transacService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        transacService = new TransactionService(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        transacService = null;
    }

    public void testPayTax(){
        transacService.AppliquerSeuilSansTaxes(0.00);
        Double result = 0.00;
        Double expected = 114.98;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilPlusPetitQueMontant(){
        transacService.AppliquerSeuilSansTaxes(50.00);
        Double result = 0.00;
        Double expected = 57.49;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilPlusGrandQueMontant(){
        transacService.AppliquerSeuilSansTaxes(105.00);
        Double result = 0.00;
        Double expected = 100.00;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilEgalAuMontant(){
        transacService.AppliquerSeuilSansTaxes(100.00);
        Double result = 0.00;
        Double expected = 100.00;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecMontantPlusPetitQueSeuil(){
        transacService.AppliquerSeuilSansTaxes(25.55);
        Double result = 0.00;
        Double expected = 15.78;
        result = transacService.addTaxToAmount(15.78);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecMontantPlusGrandQueSeuil(){
        transacService.AppliquerSeuilSansTaxes(25.55);
        Double result = 0.00;
        Double expected = 5.31;
        result = transacService.addTaxToAmount(30.17);
        assertEquals(expected,result);
    }
}
