package getrekt.projetfinaljavav2;

import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.Rabais2Pour1;
import getrekt.projetfinaljavav2.models.RabaisProduitGratuit;
import getrekt.projetfinaljavav2.models.repo.RepoCaisse;
import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;
import getrekt.projetfinaljavav2.models.repo.RepoRabaisProduitGratuit;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.monnayeur.Money;

/**
 * Created by Joel on 5/16/2015.
 */
public class TestStockageCaisse extends AndroidTestCase{
    RepoCaisse repoCaisse;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoCaisse = RepoCaisse.get(getContext());
        repoCaisse.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoCaisse.deleteAll();
        repoCaisse = null;
    }

    public void testSaveAndGetAll(){


        LajoieCorriveauReg reg = new LajoieCorriveauReg();

        assertEquals(repoCaisse.getAll().size(), 0);

        repoCaisse.save(reg);

        assertEquals(repoCaisse.getAll().size(), 1);
    }

    public void testGetByID()
    {
        LajoieCorriveauReg reg = new LajoieCorriveauReg();

        reg.useItems(Money.bill10, 10);

        double value = reg.totalValue();

        assertEquals(repoCaisse.getAll().size(), 0);

        Long pId = repoCaisse.save(reg);

        assertEquals(repoCaisse.getAll().size(), 1);

        LajoieCorriveauReg regGet = repoCaisse.getById(pId);

        assertEquals(regGet.totalValue(), value);
    }

    public void testDeleteALL()
    {
        LajoieCorriveauReg reg = new LajoieCorriveauReg();

        assertEquals(repoCaisse.getAll().size(), 0);

        Long pId = repoCaisse.save(reg);

        assertEquals(repoCaisse.getAll().size(), 1);

        repoCaisse.deleteAll();

        assertEquals(repoCaisse.getAll().size(), 0);
    }
}

