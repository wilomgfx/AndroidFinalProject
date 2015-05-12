package getrekt.projetfinaljavav2.models;

/**
 * Created by Joel on 5/10/2015.
 */
public class RabaisProduitGratuit {

    private Long id;

    private Product produitGratuit;

    private double seuil;

    private static RabaisProduitGratuit rabais;

    public static synchronized RabaisProduitGratuit get()
    {
        if(rabais == null)
            rabais = new RabaisProduitGratuit();

        return rabais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduitGratuit() {
        return produitGratuit;
    }

    public void setProduitGratuit(Product produitGratuit) {
        this.produitGratuit = produitGratuit;
    }

    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    private RabaisProduitGratuit()
    {
    }
}

