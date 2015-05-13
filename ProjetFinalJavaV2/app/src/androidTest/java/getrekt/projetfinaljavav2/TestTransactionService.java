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
        Double result = 0.00;
        Double expected = 114.98;
        result = transacService.addTax(100.00);
        assertEquals(expected,result);
    }
}
