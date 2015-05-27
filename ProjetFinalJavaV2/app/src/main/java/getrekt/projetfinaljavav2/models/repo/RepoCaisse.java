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
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;

/**
 * Created by 1387434 on 2015-04-20.
 */
public class RepoCaisse implements CRUD<LajoieCorriveauReg>
{
    Gson gson = new Gson();
    Class<LajoieCorriveauReg> classe = LajoieCorriveauReg.class;

    Context context;

    private static RepoCaisse repoUnique;

    private RepoCaisse(Context c)
    {
        this.context = c;
        this.createStorage();
    }

    public static synchronized RepoCaisse get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepoCaisse(context);

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
                        if(ext.equals("LajoieCorriveauReg"))
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

            for (LajoieCorriveauReg a : getAll())
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
    public long save(LajoieCorriveauReg a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getID() + ".LajoieCorriveauReg"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<LajoieCorriveauReg> list)
    {
        for (LajoieCorriveauReg p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(LajoieCorriveauReg... list)
    {
        for (LajoieCorriveauReg p : list ){
            this.save(p);
        }
    }

    @Override
    public LajoieCorriveauReg getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, id+".product");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".LajoieCorriveauReg"));
                LajoieCorriveauReg p = gson.fromJson(content, classe);

                return p;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve product with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<LajoieCorriveauReg> getAll() {
        synchronized(classe)
        {
            List<LajoieCorriveauReg> prods = new ArrayList<LajoieCorriveauReg>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("LajoieCorriveauReg")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        LajoieCorriveauReg prod = gson.fromJson(content, classe);
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
    public void deleteOne(LajoieCorriveauReg o) {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, o.getID()+".LajoieCorriveauReg");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}

