package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.repo.RepositoryProduitsFichiers;


/**
 * Created by 1387434 on 2015-04-21.
 */
public class TestStockageFichiers extends AndroidTestCase
{
    RepositoryProduitsFichiers repoProd;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoProd = RepositoryProduitsFichiers.get(getContext());
        //repoProd = new RepositoryProduitsFichiers(getContext());
        repoProd.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoProd.deleteAll();
        repoProd = null;
    }

    public void testSaveAndGetAll(){
        Product p = new Product("Produit ", "", 10.00, "1234434");
        repoProd.save(p);
        assertEquals(repoProd.getAll().size(), 1);
    }

    public void testMultipleSaveAndGetAll()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");
        Product p2 = new Product("Produit 2", "", 12.00, "1234436");
        Product p3 = new Product("Produit 3", "", 13.00, "1234437");
        Product p4 = new Product("Produit 4", "", 14.00, "1234438");

        repoProd.saveMany(p,p2,p3,p4);

        assertEquals(repoProd.getAll().size(), 4);
    }

    public void testMultipleSaveAndGetAll2()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");
        Product p2 = new Product("Produit 2", "", 12.00, "1234436");
        Product p3 = new Product("Produit 3", "", 13.00, "1234437");
        Product p4 = new Product("Produit 4", "", 14.00, "1234438");

        List<Product> prodlist = new ArrayList<Product>();

        prodlist.add(p);
        prodlist.add(p2);
        prodlist.add(p3);
        prodlist.add(p4);

        repoProd.saveMany(prodlist);

        assertEquals(repoProd.getAll().size(), 4);
    }

    public void testGetByID()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");

        Long idDuProd = repoProd.save(p);

        Product p2 = repoProd.getById(idDuProd);

        assertTrue(p.getBarCode().equals(p2.getBarCode()));
    }

    public void testDeleteOne()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(p);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(idDuProd);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");
        Product p2 = new Product("Produit 2", "", 12.00, "1234436");
        Product p3 = new Product("Produit 3", "", 13.00, "1234437");
        Product p4 = new Product("Produit 4", "", 14.00, "1234438");

        repoProd.saveMany(p,p2,p3,p4);

        repoProd.deleteAll();

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testGetByCodeBarre()
    {
        Product p = new Product("Produit 1", "", 10.00, "1234435");
        String barCode = "1234435";

        repoProd.save(p);

        Product o = repoProd.getByCodeBarre(barCode);

        assertEquals(o.getBarCode(), barCode);
    }

    public void testGetByCodeBarreNull()
    {
        try {
            Product o = repoProd.getByCodeBarre("99999999999");
            Assert.fail();
        }
        catch(Exception e)
        {

        }
    }
}