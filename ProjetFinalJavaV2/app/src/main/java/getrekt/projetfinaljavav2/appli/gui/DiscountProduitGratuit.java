package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import getrekt.projetfinaljavav2.R;

/**
 * Created by Joel on 5/16/2015.
 */
public class DiscountProduitGratuit extends DialogFragment {
    DialogResult mDialogResult; // the callback

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product_add_dialog, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.discountDialog_title));




        return v;
    }
}
