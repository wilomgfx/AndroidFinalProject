package getrekt.projetfinaljavav2.models.repo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.Product;


/**
 * Created by 1387434 on 2015-04-20.
 */
public class RepositoryProduitsFichiers implements CRUD<Product>
{
    Gson gson = new Gson();
    Class<Product> classe = Product.class;

    Context context;

    private static RepositoryProduitsFichiers repoUnique;

    private RepositoryProduitsFichiers(Context c)
    {
        this.context = c;
        this.createStorage();
    }

    public static synchronized RepositoryProduitsFichiers get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepositoryProduitsFichiers(context);

        return repoUnique;
    }

    public void createStorage()
    {
        File base = context.getFilesDir();
        base.mkdirs();
    }

    public void deleteStorage()
    {
        File base = context.getFilesDir();
        deleteFolder(base);
    }

    private static void deleteFolder(File folder)
    {
        try
        {
            File[] files = folder.listFiles();
            if(files != null)
            {
                for (File f : files)
                {
                    if (f.isDirectory())
                        deleteFolder(f);
                    else
                    {
                        String ext = FilenameUtils.getExtension(f.getName());
                        if(ext.equals("product"))
                            f.delete();
                    }
                }
            }
            //folder.delete();
        }
        catch (Exception e)
        {
            Log.e("File", "Could not save properly. Errors happened.");
        }
    }

    private long nextAvailableId(){
        synchronized (classe) {
            long max = 0;

            for (Product a : getAll())
            {
                if (a.getID() > max)
                {
                    max = a.getID();
                }
            }
            return max+1;
        }
    }


    @Override
    public long save(Product a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getID()+".product"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<Product> list)
    {
        for (Product p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(Product... list)
    {
        for (Product p : list ){
            this.save(p);
        }
    }

    @Override
    public Product getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, id+".product");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".product"));
                Product p = gson.fromJson(content, classe);

                return p;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve product with id " + id.toString() + ".");
                return null;
            }
        }
    }

    public Product getByCodeBarre(String pBarcode)
    {
        synchronized(classe)
        {
            List<Product> prods = new ArrayList<Product>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("product")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        Product prod = gson.fromJson(content, classe);
                        prods.add(prod);
                    } catch (Exception e) {
                        Log.e("Error", "Could not read file " + f.getName() + ".");
                        e.printStackTrace();
                    }
                }
            }

            Product produc = null;

            for(Product p : prods)
            {
                if(p.getBarCode().equals(pBarcode))
                    produc = p;
            }

            if(produc == null)
                throw new NullPointerException("Produit avec le code barre " + pBarcode + " non trouvé." );

            return produc;
        }
    }

    @Override
    public List<Product> getAll() {
        synchronized(classe)
        {
            List<Product> prods = new ArrayList<Product>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("product")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        Product prod = gson.fromJson(content, classe);
                        prods.add(prod);
                    } catch (Exception e) {
                        Log.e("Error", "Could not read file " + f.getName() + ".");
                        e.printStackTrace();
                    }
                }
            }

            return prods;
        }
    }

    @Override
    public void deleteOne(Long o) {
        this.deleteOne(this.getById(o));
    }

    @Override
    public void deleteOne(Product o) {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, o.getID()+".product");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}

