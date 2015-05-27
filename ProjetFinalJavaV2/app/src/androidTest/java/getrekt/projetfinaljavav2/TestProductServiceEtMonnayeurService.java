package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import getrekt.projetfinaljavav2.models.InvalidDataException;
import getrekt.projetfinaljavav2.models.NotEnoughtMoneyToPay;
import getrekt.projetfinaljavav2.monnayeur.CashException;
import getrekt.projetfinaljavav2.monnayeur.Change;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauChange;
import getrekt.projetfinaljavav2.monnayeur.Money;
import getrekt.projetfinaljavav2.service.MonnayeurService;
import getrekt.projetfinaljavav2.service.ProductService;
import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by William on 2015-05-27.
 */
public class TestProductServiceEtMonnayeurService  extends AndroidTestCase {

    MonnayeurService monnayeurService;
    ProductService productService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        monnayeurService = new MonnayeurService(getContext());
        productService = new ProductService(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        monnayeurService = null;
        productService = null;
    }

    public void testPayerAvecLaCaisseFail() {

        try {
            monnayeurService.PayerAvecLaCaisse(10.22, 20.00);
            fail();
        }
         catch (NotEnoughtMoneyToPay notEnoughtMoneyToPay) {

        } catch (CashException e) {

        }
    }
    public void testPayerAvecLaCaisseAucunChange() {

        Change change = new LajoieCorriveauChange();
        Change changeToGet;
        try {
            changeToGet = monnayeurService.PayerAvecLaCaisse(20.00, 20.00);
            assertEquals(change.totalValue(), changeToGet.totalValue());
        }
        catch (NotEnoughtMoneyToPay notEnoughtMoneyToPay) {
            fail();

        } catch (CashException e) {
            fail();

        }
    }
    public void testPayerAvecLaCaisse() {

        Change change = new LajoieCorriveauChange();
        change.addItems(Money.coin2,1);
        Change changeToGet;
        try {
            changeToGet = monnayeurService.PayerAvecLaCaisse(22.00, 20.00);
            assertEquals(change.totalValue(),changeToGet.totalValue());
        }
        catch (NotEnoughtMoneyToPay notEnoughtMoneyToPay) {
            fail();

        } catch (CashException e) {
            fail();

        }
    }
    public void testCanAddProductFailAllEmpty(){
        try {
            productService.canAddProduct("","","","");
            fail();
        } catch (InvalidDataException e) {
        }
    }
    public void testCanAddProductFailBarcodeLength(){
        try {
            productService.canAddProduct("pipo","alo","11.11","12345678910");
            fail();
        } catch (InvalidDataException e) {
        }
    }
    public void testCanAddFreeProductFailBarcodeLength(){
        try {
            productService.canAddProduct("pipo","alo","11.11","12345678910");
            fail();
        } catch (InvalidDataException e) {
        }
    }
    public void testCanAddFreeProductFailAllEmpty(){
        try {
            productService.canAddFreeProduct("", "");
            fail();
        } catch (InvalidDataException e) {
        }
    }

}
