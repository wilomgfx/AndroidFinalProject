package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.Transaction;
import getrekt.projetfinaljavav2.models.TransactionItem;
import getrekt.projetfinaljavav2.models.repo.RepositoryTransac;

/**
 * Created by 1387434 on 2015-04-24.
 */
public class TestStockageTransactions extends AndroidTestCase {
    RepositoryTransac repoTrans;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoTrans = RepositoryTransac.get(getContext());
        //repoTrans = new RepositoryTransac(getContext());
        repoTrans.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoTrans.deleteAll();
        repoTrans = null;
    }

    public void testSaveAndGetAll(){
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        TransactionItem t = new TransactionItem(3, p);

        List<TransactionItem> items = new ArrayList<TransactionItem>();
        items.add(t);

        Transaction tt = new Transaction(items, new Date());

        repoTrans.save(tt);
        assertEquals(repoTrans.getAll().size(), 1);
    }

    public void testMultipleSaveAndGetAll()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        Product p2 = new Product("Produit 2", "", 12.00, "1234436",true);
        Product p3 = new Product("Produit 3", "", 13.00, "1234437",true);
        Product p4 = new Product("Produit 4", "", 14.00, "1234438",true);

        TransactionItem t = new TransactionItem(3, p);
        TransactionItem t1 = new TransactionItem(6, p2);
        TransactionItem t2 = new TransactionItem(2, p);
        TransactionItem t3 = new TransactionItem(1, p3);

        List<TransactionItem> lst1 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst2 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst3 = new ArrayList<TransactionItem>();

        lst1.add(t);

        lst2.add(t2);
        lst2.add(t3);

        lst3.add(t1);
        lst3.add(t3);

        Transaction tt1 = new Transaction(lst1, new Date());
        Transaction tt2 = new Transaction(lst2, new Date());
        Transaction tt3 = new Transaction(lst3, new Date());

        repoTrans.saveMany(tt1, tt2, tt3);

        assertEquals(repoTrans.getAll().size(), 3);
    }

    public void testMultipleSaveAndGetAll2()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        Product p2 = new Product("Produit 2", "", 12.00, "1234436",true);
        Product p3 = new Product("Produit 3", "", 13.00, "1234437",true);
        Product p4 = new Product("Produit 4", "", 14.00, "1234438",true);

        TransactionItem t = new TransactionItem(3, p);
        TransactionItem t1 = new TransactionItem(6, p2);
        TransactionItem t2 = new TransactionItem(2, p);
        TransactionItem t3 = new TransactionItem(1, p3);

        List<TransactionItem> lst1 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst2 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst3 = new ArrayList<TransactionItem>();

        lst1.add(t);

        lst2.add(t2);
        lst2.add(t3);

        lst3.add(t1);
        lst3.add(t3);

        Transaction tt1 = new Transaction(lst1, new Date());
        Transaction tt2 = new Transaction(lst2, new Date());
        Transaction tt3 = new Transaction(lst3, new Date());

        List<Transaction> lstTransactions = new ArrayList<Transaction>();

        lstTransactions.add(tt1);
        lstTransactions.add(tt2);
        lstTransactions.add(tt3);

        repoTrans.saveMany(lstTransactions);

        assertEquals(repoTrans.getAll().size(), 3);
    }

    public void testGetByID()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        TransactionItem t = new TransactionItem(1, p);

        List<TransactionItem> lst = new ArrayList<TransactionItem>();
        lst.add(t);

        Transaction tt = new Transaction(lst, new Date());

        Long idDeLaTrans = repoTrans.save(tt);

        Transaction tt2 = repoTrans.getById(idDeLaTrans);

        assertEquals(tt.getId(), tt2.getId());
        assertEquals(tt.getTotalMontant(), tt2.getTotalMontant());
        assertEquals(tt.getTransItems().size(), tt2.getTransItems().size());
    }

    public void testDeleteOne()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        TransactionItem t = new TransactionItem(2, p);

        List<TransactionItem> lstTrans = new ArrayList<TransactionItem>();
        lstTrans.add(t);

        Transaction tt = new Transaction(lstTrans, new Date());

        Long idDelaTrans = repoTrans.save(tt);

        repoTrans.deleteOne(tt);

        assertEquals(repoTrans.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        TransactionItem t = new TransactionItem(2, p);

        List<TransactionItem> lstTrans = new ArrayList<TransactionItem>();
        lstTrans.add(t);

        Transaction tt = new Transaction(lstTrans, new Date());

        Long idDelaTrans = repoTrans.save(tt);

        repoTrans.deleteOne(idDelaTrans);

        assertEquals(repoTrans.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435",true);
        Product p2 = new Product("Produit 2", "", 12.00, "1234436",true);
        Product p3 = new Product("Produit 3", "", 13.00, "1234437",true);
        Product p4 = new Product("Produit 4", "", 14.00, "1234438",true);

        TransactionItem t = new TransactionItem(3, p);
        TransactionItem t1 = new TransactionItem(6, p2);
        TransactionItem t2 = new TransactionItem(2, p);
        TransactionItem t3 = new TransactionItem(1, p3);

        List<TransactionItem> lst1 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst2 = new ArrayList<TransactionItem>();
        List<TransactionItem> lst3 = new ArrayList<TransactionItem>();

        lst1.add(t);

        lst2.add(t2);
        lst2.add(t3);

        lst3.add(t1);
        lst3.add(t3);

        Transaction tt1 = new Transaction(lst1, new Date());
        Transaction tt2 = new Transaction(lst2, new Date());
        Transaction tt3 = new Transaction(lst3, new Date());

        repoTrans.saveMany(tt1, tt2, tt3);

        repoTrans.deleteAll();

        assertEquals(repoTrans.getAll().size(), 0);
    }
}

