package getrekt.projetfinaljavav2.models.repo;

import java.io.File;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.models.Rabais2Pour1;


/**
 * Created by Joel on 5/10/2015.
 */
public class RepoRabais2Pour1 implements CRUD<Rabais2Pour1> {
    Gson gson = new Gson();
    final Class<Rabais2Pour1> classe = Rabais2Pour1.class;

    Context context;

    private static RepoRabais2Pour1 repoUnique;

    public static synchronized RepoRabais2Pour1 get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepoRabais2Pour1(context);

        return repoUnique;
    }

    private RepoRabais2Pour1(Context c)
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
                        if(ext.equals("rabais2pour1")) {
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

            for (Rabais2Pour1 a : getAll())
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
    public long save(Rabais2Pour1 a)
    {
        synchronized (classe) {
            // set the id
            if (a.getId() == null) a.setId(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getId() + ".rabais2pour1"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getId();

        }
    }

    @Override
    public void saveMany(Iterable<Rabais2Pour1> list)
    {
        for (Rabais2Pour1 p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(Rabais2Pour1... list)
    {
        for (Rabais2Pour1 p : list ){
            this.save(p);
        }
    }

    @Override
    public Rabais2Pour1 getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, id+".rabais2pour1");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".rabais2pour1"));
                Rabais2Pour1 ti = gson.fromJson(content, classe);

                return ti;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve Rabais2Pour1 with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<Rabais2Pour1> getAll() {
        synchronized(classe)
        {
            List<Rabais2Pour1> titems = new ArrayList<Rabais2Pour1>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("rabais2pour1"))
                {
                    try
                    {
                        String content = FileUtils.readFileToString(f);
                        Rabais2Pour1 ti = gson.fromJson(content, classe);
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
    public void deleteOne(Rabais2Pour1 o) {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, o.getId()+".rabais2pour1");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}