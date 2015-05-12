package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import getrekt.projetfinaljavav2.R;

/**
 * Created by 1387434 on 2015-05-12.
 */
public class DiscountDialog extends DialogFragment {
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
