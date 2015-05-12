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

import getrekt.projetfinaljavav2.models.Transaction;

/**
 * Created by 1387434 on 2015-04-20.
 */
public class RepositoryTransac implements CRUD<Transaction>
{
    Gson gson = new Gson();
    Class<Transaction> classe = Transaction.class;

    Context context;

    private static RepositoryTransac repoUnique;

    public static synchronized RepositoryTransac get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepositoryTransac(context);

        return repoUnique;
    }

    private RepositoryTransac(Context c)
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
                        if(ext.equals("transaction")) {
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

            for (Transaction a : getAll())
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
    public long save(Transaction a)
    {
        synchronized (classe) {
            // set the id
            if (a.getId() == null) a.setId(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, a.getId() + ".transaction"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getId();

        }
    }

    @Override
    public void saveMany(Iterable<Transaction> list)
    {
        for (Transaction p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(Transaction... list)
    {
        for (Transaction p : list ){
            this.save(p);
        }
    }

    @Override
    public Transaction getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, id+".transaction");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".transaction"));
                Transaction ti = gson.fromJson(content, classe);

                return ti;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve Transaction with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<Transaction> getAll() {
        synchronized(classe)
        {
            List<Transaction> titems = new ArrayList<Transaction>();

            File base = context.getFilesDir();
            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("transaction"))
                {
                    try
                    {
                        String content = FileUtils.readFileToString(f);
                        Transaction ti = gson.fromJson(content, classe);
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
    public void deleteOne(Transaction o) {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, o.getId()+".transaction");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}

