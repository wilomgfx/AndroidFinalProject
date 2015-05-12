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

import getrekt.projetfinaljavav2.models.RabaisProduitGratuit;

/**
 * Created by 1387434 on 2015-05-11.
 */
public class RepoRabaisProduitGratuit implements CRUD<RabaisProduitGratuit>{
    Gson gson = new Gson();
    final Class<RabaisProduitGratuit> classe = RabaisProduitGratuit.class;

    Context context;

    private static RepoRabaisProduitGratuit repoUnique;

    public static synchronized RepoRabaisProduitGratuit get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepoRabaisProduitGratuit(context);

        return repoUnique;
    }

    private RepoRabaisProduitGratuit(Context c)
    {
        this.context = c;
        this.createStorage();
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
                    else {
                        String ext = FilenameUtils.getExtension(f.getName());
                        if(ext.equals("RabaisProduitGratuit")) {
                            f.delete();
                        }
                    }
                }
            }
            //folder.delete();
        }
        catch (Exception e)
        {
            Log.e("File", "Could not delete properly. Errors happened.");
        }
    }

    private long nextAvailableId(){
        synchronized (classe) {
            long max = 0;

            for (RabaisProduitGratuit a : getAll())
            {
                if (a.getId() > max)
                {
                    max = a.getId();
                }
            }
            return max+1;
        }
    }


    @Override
    public long save(RabaisProduitGratuit a)
    {
        synchronized (classe) {
            // set the id
            if (a.getId() == null) a.setId(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getId() + ".RabaisProduitGratuit"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getId();

        }
    }

    @Override
    public void saveMany(Iterable<RabaisProduitGratuit> list)
    {
        for (RabaisProduitGratuit p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(RabaisProduitGratuit... list)
    {
        for (RabaisProduitGratuit p : list ){
            this.save(p);
        }
    }

    @Override
    public RabaisProduitGratuit getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, id+".RabaisProduitGratuit");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".RabaisProduitGratuit"));
                RabaisProduitGratuit ti = gson.fromJson(content, classe);

                return ti;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve RabaisProduitGratuit with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<RabaisProduitGratuit> getAll() {
        synchronized(classe)
        {
            List<RabaisProduitGratuit> titems = new ArrayList<RabaisProduitGratuit>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("RabaisProduitGratuit"))
                {
                    try
                    {
                        String content = FileUtils.readFileToString(f);
                        RabaisProduitGratuit ti = gson.fromJson(content, classe);
                        titems.add(ti);
                    } catch (Exception e) {
                        Log.e("Error", "Could not read file " + f.getName() + ".");
                        e.printStackTrace();
                    }
                }
            }

            return titems;
        }
    }

    @Override
    public void deleteOne(Long o) {
        this.deleteOne(this.getById(o));
    }

    @Override
    public void deleteOne(RabaisProduitGratuit o) {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, o.getId()+".RabaisProduitGratuit");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}
