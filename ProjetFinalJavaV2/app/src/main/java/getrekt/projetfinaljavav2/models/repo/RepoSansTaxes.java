package getrekt.projetfinaljavav2.models.repo;

/**
 * Created by William on 2015-05-19.
 */

import android.util.Log;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2015-05-19.
 */
public class RepoSansTaxes {
    Gson gson = new Gson();
    final Class<Double> classe = Double.class;

    Context context;

    private RepoSansTaxes(Context c)
    {
        this.context = c;
        this.createStorage();
    }
    private static RepoSansTaxes repoUnique;

    public static synchronized RepoSansTaxes get(Context context)
    {
        if(repoUnique == null)
            repoUnique = new RepoSansTaxes(context);

        return repoUnique;
    }

    public Boolean checkIfExits(){
        File base = context.getFilesDir();
        File f = new File(base, 1+".SansTaxes");
        return f.exists();
    }

    public long save(Double o) {
        synchronized (classe) {
            // set the id
            //
            String serialise = gson.toJson(o);
            File base = context.getFilesDir();
            try {
                FileUtils.writeStringToFile(new File(base, 1+".SansTaxes"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;

        }
    }
    public Double getIt(){
        synchronized (classe)
        {
            String content;
            try
            {
                File base = context.getFilesDir();

                File f = new File(base, 1+".SansTaxes");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, 1 + ".SansTaxes"));
                Double d = gson.fromJson(content, classe);

                return d;
            }
            catch (Exception e)
            {
                Log.e("Error", "Could not retrieve SansTaxes with id " + 1 + ".");
                return null;
            }
        }
    }

    public void deleteIt() {
        synchronized (classe)
        {
            File base = context.getFilesDir();
            File f = new File(base, 1+".SansTaxes");

            f.delete();
        }
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
                        if(ext.equals(".SansTaxes")) {
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
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }

}