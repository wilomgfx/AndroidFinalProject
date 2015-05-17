package getrekt.projetfinaljavav2.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;
import getrekt.projetfinaljavav2.models.repo.RepoRabaisProduitGratuit;

/**
 * Created by Joel on 5/10/2015.
 */
public class RabaisProduitGratuit {

    private Long id;

    private List<ProduitGratuit> produitsGratuits;

    private static RabaisProduitGratuit rabais;

    public static synchronized RabaisProduitGratuit get(Context context)
    {
        if(rabais == null)
        {
            RepoRabaisProduitGratuit rep = RepoRabaisProduitGratuit.get(context);

            if(rep.getAll().size() == 0)
                rabais = new RabaisProduitGratuit();
            else
                rabais = rep.getAll().get(0);
        }

        return rabais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProduitGratuit> getProduitsGratuits() {
        return produitsGratuits;
    }

    public void setProduitsGratuits(List<ProduitGratuit> produitsGratuits) {
        this.produitsGratuits = produitsGratuits;
    }

    public void addProduitGratuit(Product pProduct, double pSeuil)
    {
        ProduitGratuit nouveauProdGrat = new ProduitGratuit(pProduct, pSeuil);
        this.getProduitsGratuits().add(nouveauProdGrat);
    }

    public void removeProduitGratuit(ProduitGratuit pProd)
    {
        this.getProduitsGratuits().remove(pProd);
    }

    public void removeProduitGratuitParProduit(Product pProduct)
    {
        for(ProduitGratuit p : this.getProduitsGratuits())
        {
            if(p.getProd().getBarCode().equals(pProduct.getBarCode()))
            {
                this.getProduitsGratuits().remove(p);
                break;
            }
        }
    }

    public void deleteAllRabais()
    {
        produitsGratuits.clear();
    }

    private RabaisProduitGratuit()
    {
        this.produitsGratuits = new ArrayList<ProduitGratuit>();
    }
}

