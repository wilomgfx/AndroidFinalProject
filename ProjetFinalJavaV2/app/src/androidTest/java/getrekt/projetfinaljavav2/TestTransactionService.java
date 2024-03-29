package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.InvalidDataException;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.TransactionItem;
import getrekt.projetfinaljavav2.service.ProductService;
import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by 1252991 on 2015-05-12.
 */
public class TestTransactionService extends AndroidTestCase {

    TransactionService transacService;
    ProductService productService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        transacService = new TransactionService(getContext());
        productService = new ProductService(getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        transacService = null;
        productService = null;
    }

    public void testPayTax() throws InvalidDataException{
        transacService.AppliquerSeuilSansTaxes(0.00);
        Double result = 0.00;
        Double expected = 114.98;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilPlusPetitQueMontant() throws InvalidDataException{
        transacService.AppliquerSeuilSansTaxes(50.00);
        Double result = 0.00;
        Double expected = 100.00;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilPlusGrandQueMontant() throws InvalidDataException{
        transacService.AppliquerSeuilSansTaxes(105.00);
        Double result = 0.00;
        Double expected = 114.98;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecSeuilEgalAuMontant() throws InvalidDataException{
        transacService.AppliquerSeuilSansTaxes(100.00);
        Double result = 0.00;
        Double expected = 100.00;
        result = transacService.addTaxToAmount(100.00);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecMontantPlusPetitQueSeuil() throws InvalidDataException {
        transacService.AppliquerSeuilSansTaxes(25.55);
        Double result = 0.00;
        Double expected = 18.14;
        result = transacService.addTaxToAmount(15.78);
        assertEquals(expected,result);
    }
    public void testPayTaxAvecMontantPlusGrandQueSeuil() throws InvalidDataException{
        transacService.AppliquerSeuilSansTaxes(25.55);
        Double result = 0.00;
        Double expected = 30.17;
        result = transacService.addTaxToAmount(30.17);
        assertEquals(expected,result);
    }

    public void testRabais2Pour1()
    {
        productService.getRabais2Pour1().deleteAllRabais();

        Product testProd = new Product("TestProd", "Desc", 20.00, "11111111",true);

        productService.getRabais2Pour1().addRabais(testProd);

        List<TransactionItem> lstTrans = new ArrayList<TransactionItem>();
        double totalPresent = 0.00;
        double supposedPrice = 0.00;

        TransactionItem item = new TransactionItem(2, testProd);

        lstTrans.add(item);

        totalPresent += item.getQty() * item.getProduct().getPrice();
        supposedPrice += 1 * item.getProduct().getPrice();

        Product testProdNoRabais = new Product("TestProdNoRabais", "Desc", 10.00, "22222222",true);

        TransactionItem item2 = new TransactionItem(3, testProdNoRabais);

        lstTrans.add(item2);

        totalPresent += item2.getQty() * item2.getProduct().getPrice();
        supposedPrice += item2.getQty() * item2.getProduct().getPrice();

        double nouveauTot = transacService.Appliquer2Pour1(lstTrans, totalPresent);

        assertEquals(nouveauTot, supposedPrice);
    }

    public void testRabaisProduitGratuit()
    {
        productService.getRabaisProduitGratuit().deleteAllRabais();

        Product testProd = new Product("TestProd", "Desc", 0.00, "11111111",true);

        productService.getRabaisProduitGratuit().addProduitGratuit(testProd, 10.00);

        List<TransactionItem> lstTrans = new ArrayList<TransactionItem>();
        double totalPresent = 0.00;
        double supposedPrice = 0.00;

        Product testProdNoRabais = new Product("TestProdNoRabais", "Desc", 10.00, "22222222",true);

        TransactionItem item2 = new TransactionItem(3, testProdNoRabais);

        lstTrans.add(item2);

        totalPresent += item2.getQty() * item2.getProduct().getPrice();
        supposedPrice += 3 * item2.getProduct().getPrice();

        List<TransactionItem> newlist = transacService.AppliquerProduitGratuit(lstTrans, totalPresent);

        int nbrOfItems = 3;

        for(TransactionItem item : newlist)
        {
            lstTrans.add(item);
            nbrOfItems += item.getQty();
        }

        assertEquals(nbrOfItems, 6);
    }
    public void testTaxesSelonDifferentProduitsPasDeTaxes(){
        Product testProdNoTaxes = new Product("TestProdNoTaxes", "Desc", 10.00, "22222223",false);
        //Product testProdTaxes = new Product("TestProdTaxes", "Desc", 10.00, "22222222",true);

        double total = 0.00;
        double totalProduitSansTaxes = 0.00;

        List<TransactionItem> lstItems = new ArrayList<>();
        TransactionItem itemNoTaxes = new TransactionItem(1,testProdNoTaxes);
        //TransactionItem itemTaxes = new TransactionItem(1,testProdTaxes);

        lstItems.add(itemNoTaxes);
       // lstItems.add(itemTaxes);


        for(TransactionItem transItem : lstItems)
        {
            //check si le produit est taxable
            if(transItem.getProduct().getEstTaxable()){
                total += transItem.getQty() * transItem.getProduct().getPrice();
            }
            else{
                totalProduitSansTaxes += transItem.getQty() * transItem.getProduct().getPrice();
            }
        }

        totalProduitSansTaxes = total+totalProduitSansTaxes;
        total = transacService.Appliquer2Pour1(lstItems, total);
        double totalAvecTaxes = transacService.addTaxToAmount(total);

        //devrait etre 0.0 parce que le produit n'est pas taxable
        assertEquals(0.0,totalAvecTaxes);
    }
    public void testTaxesSelonDifferentProduitsAvecTaxes(){
       // Product testProdNoTaxes = new Product("TestProdNoTaxes", "Desc", 10.00, "22222223",false);
        Product testProdTaxes = new Product("TestProdTaxes", "Desc", 10.00, "22222222",true);

        double total = 0.00;
        double totalProduitSansTaxes = 0.00;

        List<TransactionItem> lstItems = new ArrayList<>();
        //TransactionItem itemNoTaxes = new TransactionItem(1,testProdNoTaxes);
        TransactionItem itemTaxes = new TransactionItem(1,testProdTaxes);

        //lstItems.add(itemNoTaxes);
        lstItems.add(itemTaxes);


        for(TransactionItem transItem : lstItems)
        {
            //check si le produit est taxable
            if(transItem.getProduct().getEstTaxable()){
                total += transItem.getQty() * transItem.getProduct().getPrice();
            }
            else{
                totalProduitSansTaxes += transItem.getQty() * transItem.getProduct().getPrice();
            }
        }

        totalProduitSansTaxes = total+totalProduitSansTaxes;
        total = transacService.Appliquer2Pour1(lstItems, total);
        double totalAvecTaxes = transacService.addTaxToAmount(total);

        //devrait etre 11.5 parce que le produit est taxable
        assertEquals(11.5,totalAvecTaxes);
    }
    public void testTaxesSelonDifferentProduitsUnTaxableEtUnNonTaxable(){
        Product testProdNoTaxes = new Product("TestProdNoTaxes", "Desc", 10.00, "22222223",false);
        Product testProdTaxes = new Product("TestProdTaxes", "Desc", 20.00, "22222222",true);

        double total = 0.00;
        double totalProduitSansTaxes = 0.00;

        List<TransactionItem> lstItems = new ArrayList<>();
        TransactionItem itemNoTaxes = new TransactionItem(1,testProdNoTaxes);
        TransactionItem itemTaxes = new TransactionItem(1,testProdTaxes);

        lstItems.add(itemNoTaxes);
        lstItems.add(itemTaxes);


        for(TransactionItem transItem : lstItems)
        {
            //check si le produit est taxable
            if(transItem.getProduct().getEstTaxable()){
                total += transItem.getQty() * transItem.getProduct().getPrice();
            }
            else{
                totalProduitSansTaxes += transItem.getQty() * transItem.getProduct().getPrice();
            }
        }

        totalProduitSansTaxes = total+totalProduitSansTaxes;
        total = transacService.Appliquer2Pour1(lstItems, total);
        double totalAvecTaxes = transacService.addTaxToAmount(total);

        //devrait etre 23.0 parce qu'un seul des deux produits est taxables
        assertEquals(23.0,totalAvecTaxes);
    }
}
