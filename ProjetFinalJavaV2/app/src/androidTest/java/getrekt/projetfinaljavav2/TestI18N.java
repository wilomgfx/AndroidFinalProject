package getrekt.projetfinaljavav2;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.test.AndroidTestCase;
import android.app.Activity;
import android.os.Bundle;

import java.util.Locale;

/**
 * Created by Joel on 5/26/2015.
 */
public class TestI18N extends AndroidTestCase {

    private void setLocale(String language, String country) {
        Locale locale = new Locale(language, country);
        // here we update locale for date formatters
        Locale.setDefault(locale);
        // here we update locale for app resources
        Resources res = getContext().getResources();
        Configuration config = res.getConfiguration();
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void testLangueAnglais()
    {
        setLocale("en", "EN");
        String string = getContext().getString(R.string.scanner);
        assertEquals("Product Scan", string);

        string = getContext().getString(R.string.addProduct);
        assertEquals("+ product", string);

        string = getContext().getString(R.string.total);
        assertEquals("Total:", string);

        string = getContext().getString(R.string.pay);
        assertEquals("Pay", string);

        string = getContext().getString(R.string.transactionSave);
        assertEquals("Saving transaction...", string);

        string = getContext().getString(R.string.menu_productsCreate);
        assertEquals("Creating products...", string);

        string = getContext().getString(R.string.menu_productsClear);
        assertEquals("Products cleared.", string);

        string = getContext().getString(R.string.menu_orderCreate);
        assertEquals("Creating order...", string);

        string = getContext().getString(R.string.dialog_payer);
        assertEquals("Pay products", string);
    }

    public void testLangueFrancais()
    {
        setLocale("fr", "CA");
        String string = getContext().getString(R.string.scanner);
        assertEquals("Scanner Produit", string);

        string = getContext().getString(R.string.addProduct);
        assertEquals("+ produit", string);

        string = getContext().getString(R.string.total);
        assertEquals("Total:", string);

        string = getContext().getString(R.string.pay);
        assertEquals("Payer", string);

        string = getContext().getString(R.string.transactionSave);
        assertEquals("Sauvegarde de la transaction...", string);

        string = getContext().getString(R.string.menu_productsCreate);
        assertEquals("Création des produits...", string);

        string = getContext().getString(R.string.menu_productsClear);
        assertEquals("Produits supprimés.", string);

        string = getContext().getString(R.string.menu_orderCreate);
        assertEquals("Création d\'une commande...", string);

        string = getContext().getString(R.string.dialog_payer);
        assertEquals("Payer les produits", string);
    }
}
