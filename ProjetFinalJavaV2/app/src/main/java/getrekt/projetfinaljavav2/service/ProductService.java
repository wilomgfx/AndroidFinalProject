package getrekt.projetfinaljavav2.service;

import android.content.Context;

import java.util.List;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.InvalidDataException;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.Rabais2Pour1;
import getrekt.projetfinaljavav2.models.RabaisProduitGratuit;
import getrekt.projetfinaljavav2.models.repo.RepoRabais2Pour1;
import getrekt.projetfinaljavav2.models.repo.RepoRabaisProduitGratuit;
import getrekt.projetfinaljavav2.models.repo.RepositoryProduitsFichiers;

/**
 * Created by 1387434 on 2015-05-05.
 */
public class ProductService {
    private Context context;
    private Rabais2Pour1 rabais2Pour1;
    private RabaisProduitGratuit rabaisProduitGratuit;
    private RepoRabais2Pour1 repoRabais2Pour1;
    private RepoRabaisProduitGratuit repoProduitGratuit;

    private RepositoryProduitsFichiers repoFichiers;

    public ProductService(Context pContext)
    {
        this.context = pContext;

        repoFichiers = RepositoryProduitsFichiers.get(context);

        repoProduitGratuit = RepoRabaisProduitGratuit.get(context);
        repoRabais2Pour1 = RepoRabais2Pour1.get(context);

        rabais2Pour1 = Rabais2Pour1.get(context);
        rabaisProduitGratuit = RabaisProduitGratuit.get(context);
//        if(repoRabais2Pour1.getAll().size() !=0){
//            rabais2Pour1 = repoRabais2Pour1.getAll().get(0);
//        }
//        else
//        {
//            rabais2Pour1 = Rabais2Pour1.get();
//        }
//        if(repoProduitGratuit.getAll().size() !=0){
//            rabaisProduitGratuit = repoProduitGratuit.getAll().get(0);
//        }
//        else
//        {
//            //rabaisProduitGratuit = RabaisProduitGratuit.get();
//        }

    }

    public void createStorage()
    {
        repoFichiers.createStorage();
    }

    public void deleteStorage()
    {
        repoFichiers.deleteStorage();
    }

    public long save(Product a)
    {
        return repoFichiers.save(a);
    }

    public void saveMany(Iterable<Product> list)
    {
        repoFichiers.saveMany(list);
    }

    public void saveMany(Product... list)
    {
        repoFichiers.saveMany(list);
    }

    public Product getById(Long id)
    {
        return repoFichiers.getById(id);
    }

    public Product getByCodeBarre(String pBarcode)
    {
        try
        {
            Product prod = repoFichiers.getByCodeBarre(pBarcode);
            return prod;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<Product> getAll() {
        return repoFichiers.getAll();
    }

    public void deleteOne(Long o) {
        repoFichiers.deleteOne(o);
    }

    public void deleteOne(Product o) {
        repoFichiers.deleteOne(o);
    }

    public void deleteAll() {
        repoFichiers.deleteAll();
    }

    public void generateProducts() {
        Product p1 = new Product("Céréales Cherios", "Des céréales au miel", 19.99, "7011043018498");
        Product p2 = new Product("Pomme", "Fruit", 0.99, "6797413659379");
        Product p3 = new Product("Yogourt", "Grec", 0.49, "7651921095861");
        Product p4 = new Product("Muffins", "Au raisin.", 1.15, "5729286863776");
        Product p5 = new Product("Lait 2%", "2 Litres.", 2.99, "6075631136316");
        Product p6 = new Product("Éclairs au chocolat", "Patisserie", 3.49, "6116088359160");
        Product p7 = new Product("Biscuits Oreo", "Original", 2.49, "3298482123547");
        Product p8 = new Product("Tarte aux pommes", "Tarte", 6.99, "1629924473432");
        Product p9 = new Product("Carottes", "2 lb", 2.39, "5648382418072");
        Product p10 = new Product("Soupe aux tomates", "Chunkee", 5.10, "5942916222891");
        Product p11 = new Product("Nouilles Alphabits", "Soupe", 4.49, "8038722591249");
        Product p12 = new Product("Shnitzels", "Plat Allemand", 6.59, "1589477250587");
        Product p13 = new Product("Casseau de fraises", "Fruits", 7.19, "7611473873003");
        Product p14 = new Product("Casseau de bleuets", "Fruits", 7.19, "5088408786417");
        Product p15 = new Product("Ketchup", "Condiment", 3.99, "3481608318716");
        Product p16 = new Product("Cornichons", "3lb", 4.99, "4407073486209");
        Product p17 = new Product("Viande froide", "5lb", 6.19, "3593013272573");
        Product p18 = new Product("Laitue", "Legume", 2.89, "4233891349934");
        Product p19 = new Product("Biscuits Ritz", "Croustilles", 7.89, "2657604046187");
        Product p20 = new Product("Sardines", "5lb", 8.99, "7224672377618");

        repoFichiers.saveMany(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20);
    }

    public boolean exists(Product pProd)
    {
        try
        {
            repoFichiers.getByCodeBarre(pProd.getBarCode());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public Rabais2Pour1 getRabais2Pour1()
    {
        return rabais2Pour1;
    }

    public RabaisProduitGratuit getRabaisProduitGratuit()
    {
        return rabaisProduitGratuit;
    }

    public Long save(Rabais2Pour1 pRab)
    {
        repoRabais2Pour1.deleteAll();
        return repoRabais2Pour1.save(pRab);
    }

    public Long save(RabaisProduitGratuit pRab)
    {
        repoProduitGratuit.deleteAll();
        return repoProduitGratuit.save(pRab);
    }

    public Double canAddProduct(String name,String desc,String prix,String barcode) throws InvalidDataException{
        if(barcode.length() < 13){
            throw  new InvalidDataException(context.getString(R.string.invalid_product_info_barcodeNot13Digits));
        }
        if(!name.isEmpty() && !desc.isEmpty() && !prix.isEmpty() && !barcode.isEmpty()){
            //pour rendre le en .00
            Double prixD = Double.parseDouble(prix);
            prixD = (double)Math.round(prixD *100)/100;
            return prixD;
        }
        else {
            throw  new InvalidDataException(context.getString(R.string.invalid_product_info));
        }
    }

    public void canAddFreeProduct(String barcode,String amount)throws InvalidDataException{
        if(barcode.isEmpty() && amount.isEmpty()){
            throw  new InvalidDataException(context.getString(R.string.invalid_product_info));
        }
        else if(barcode.length() < 13){
            throw  new InvalidDataException(context.getString(R.string.invalid_product_info_barcodeNot13Digits));
        }

    }
}

