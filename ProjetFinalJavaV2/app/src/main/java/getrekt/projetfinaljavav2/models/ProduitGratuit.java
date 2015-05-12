package getrekt.projetfinaljavav2.models;

/**
 * Created by 1387434 on 2015-05-12.
 */
public class ProduitGratuit {
    private double seuil;

    private Product prod;


    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public ProduitGratuit(Product pProd, double pSeuil)
    {
        this.setProd(pProd);
        this.setSeuil(pSeuil);
    }
}
