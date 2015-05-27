package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.Rabais2Pour1;
import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;

/**
 * Created by Joel on 5/10/2015.
 */
public class TestStockageRabais extends AndroidTestCase {
    RepoRabais2Pour1 repoRabais;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoRabais = RepoRabais2Pour1.get(getContext());
        repoRabais.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoRabais.deleteAll();
        repoRabais = null;
    }

    public void testSaveAndGetAll(){
        Rabais2Pour1 rabais2Pour1 = Rabais2Pour1.get(this.getContext());

        List<Product> lstProds = new ArrayList<Product>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        lstProds.add(prod);

        rabais2Pour1.setProduitsRabais(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        repoRabais.save(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 1);
    }

    public void testGetByID()
    {
        Rabais2Pour1 rabais2Pour1 = Rabais2Pour1.get(this.getContext());

        List<Product> lstProds = new ArrayList<Product>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        lstProds.add(prod);

        rabais2Pour1.setProduitsRabais(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 1);

        Rabais2Pour1 rab = repoRabais.getById(pId);

        assertEquals(rab.getProduitsRabais().size(), lstProds.size());

        assertEquals(rab.getProduitsRabais().get(0).getBarCode(), prod.getBarCode());
    }

    public void testDeleteOne()
    {
        Rabais2Pour1 rabais2Pour1 = Rabais2Pour1.get(this.getContext());

        List<Product> lstProds = new ArrayList<Product>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        lstProds.add(prod);

        rabais2Pour1.setProduitsRabais(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        repoRabais.save(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteOne(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        Rabais2Pour1 rabais2Pour1 = Rabais2Pour1.get(this.getContext());

        List<Product> lstProds = new ArrayList<Product>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        lstProds.add(prod);

        rabais2Pour1.setProduitsRabais(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteOne(pId);

        assertEquals(repoRabais.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        Rabais2Pour1 rabais2Pour1 = Rabais2Pour1.get(this.getContext());

        List<Product> lstProds = new ArrayList<Product>();

        Product prod = new Product("Test1", "nope", 12.50, "1",true);
        lstProds.add(prod);

        rabais2Pour1.setProduitsRabais(lstProds);

        assertEquals(repoRabais.getAll().size(), 0);

        Long pId = repoRabais.save(rabais2Pour1);

        assertEquals(repoRabais.getAll().size(), 1);

        repoRabais.deleteAll();

        assertEquals(repoRabais.getAll().size(), 0);
    }
}
