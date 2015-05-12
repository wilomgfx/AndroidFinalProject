package getrekt.projetfinaljavav2.models;

import java.util.ArrayList;

/**
 * Created by Joel on 5/10/2015.
 */
public class RabaisProduitGratuit {

    private Long id;

    private ArrayList<ProduitGratuit> produitsGratuits;

    private static RabaisProduitGratuit rabais;

//    public static synchronized RabaisProduitGratuit get()
//    {
//        if(rabais == null)
//            rabais = new RabaisProduitGratuit();
//
//        return rabais;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<ProduitGratuit> getProduitsGratuits() {
        return produitsGratuits;
    }

    public void setProduitsGratuits(ArrayList<ProduitGratuit> produitsGratuits) {
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

    public RabaisProduitGratuit()
    {
        this.produitsGratuits = new ArrayList<ProduitGratuit>();
    }
}

