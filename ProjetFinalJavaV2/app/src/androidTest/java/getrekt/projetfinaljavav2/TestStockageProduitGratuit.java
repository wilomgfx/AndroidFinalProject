package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.Rabais2Pour1;
import getrekt.projetfinaljavav2.models.RabaisProduitGratuit;
import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;
import getrekt.projetfinaljavav2.models.repo.RepoRabaisProduitGratuit;

/**
 * Created by Joel on 5/16/2015.
 */
public class TestStockageProduitGratuit extends AndroidTestCase{
    RepoRabaisProduitGratuit repoRabais;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoRabais = RepoRabaisProduitGratuit.get(getContext());
        repoRabais.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoRabais.deleteAll();
        repoRabais = null;
    }

    public void testSaveAndGetAll(){
        RabaisProduitGratuit rabaisProduitGratuit = RabaisProduitGratuit.get(this.getContext());

        List<ProduitGratuit> lstProds = new ArrayList<ProduitGratuit>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        double seuil = 10.00;
        ProduitGratuit p = new ProduitGratuit(prod, seuil);
        lstProds.add(p);

        rabaisProduitGratuit.setProduitsGratuits(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        repoRabais.save(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 1);
    }

    public void testGetByID()
    {
        RabaisProduitGratuit rabaisProduitGratuit = RabaisProduitGratuit.get(this.getContext());

        List<ProduitGratuit> lstProds = new ArrayList<ProduitGratuit>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        double seuil = 10.00;
        ProduitGratuit p = new ProduitGratuit(prod, seuil);
        lstProds.add(p);

        rabaisProduitGratuit.setProduitsGratuits(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 1);

        RabaisProduitGratuit rab = repoRabais.getById(pId);

        assertEquals(rab.getProduitsGratuits().size(), lstProds.size());

        assertEquals(rab.getProduitsGratuits().get(0).getProd().getBarCode(), prod.getBarCode());
    }

    public void testDeleteOne()
    {
        RabaisProduitGratuit rabaisProduitGratuit = RabaisProduitGratuit.get(this.getContext());

        List<ProduitGratuit> lstProds = new ArrayList<ProduitGratuit>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        double seuil = 10.00;
        ProduitGratuit p = new ProduitGratuit(prod, seuil);
        lstProds.add(p);

        rabaisProduitGratuit.setProduitsGratuits(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        repoRabais.save(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteOne(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        RabaisProduitGratuit rabaisProduitGratuit = RabaisProduitGratuit.get(this.getContext());

        List<ProduitGratuit> lstProds = new ArrayList<ProduitGratuit>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        double seuil = 10.00;
        ProduitGratuit p = new ProduitGratuit(prod, seuil);
        lstProds.add(p);

        rabaisProduitGratuit.setProduitsGratuits(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteOne(pId);

        assertEquals(repoRabais.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        RabaisProduitGratuit rabaisProduitGratuit = RabaisProduitGratuit.get(this.getContext());

        List<ProduitGratuit> lstProds = new ArrayList<ProduitGratuit>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        double seuil = 10.00;
        ProduitGratuit p = new ProduitGratuit(prod, seuil);
        lstProds.add(p);

        rabaisProduitGratuit.setProduitsGratuits(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabaisProduitGratuit);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteAll();

        assertEquals(repoRabais.getAll().size(), 0);
    }
}
