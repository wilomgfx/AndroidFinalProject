package getrekt.projetfinaljavav2.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;

/**
 * Created by Joel on 5/10/2015.
 */
public class Rabais2Pour1 {
    private static Rabais2Pour1 rabais;

    private Long id;

    private List<Product> lstProduitsAvecRabais;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProduitsRabais() {
        return lstProduitsAvecRabais;
    }

    public void setProduitsRabais(List<Product> lstProduitsAvecRabais) {
        this.lstProduitsAvecRabais = lstProduitsAvecRabais;
    }

    private Rabais2Pour1()
    {
        this.lstProduitsAvecRabais = new ArrayList<Product>();
    }

    public static synchronized Rabais2Pour1 get(Context context)
    {
        if(rabais == null)
        {
            RepoRabais2Pour1 rep = RepoRabais2Pour1.get(context);

            if(rep.getAll().size() == 0)
                rabais = new Rabais2Pour1();
            else
                rabais = rep.getAll().get(0);
        }

        return rabais;
    }

    public void addRabais(Product pProd)
    {
        lstProduitsAvecRabais.add(pProd);
    }

    public void removeRabais(Product pProd)
    {
        lstProduitsAvecRabais.remove(pProd);
    }

    public void deleteAllRabais()
    {
        lstProduitsAvecRabais.clear();
    }
}