package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.content.Context;
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
    public UpdateMethod mDialogResult; // the callback
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.discount_manager, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.discountDialog_title));

        final Button btn2Pour1 = (Button)v.findViewById(R.id.btn_rabais2pour1);
        final Button btnProduitGratuit = (Button)v.findViewById(R.id.btn_rabaisProduitGratuit);
        final Button btnSeuilSansTaxes = (Button)v.findViewById(R.id.btn_rabaisSeuilSansTaxes);

        btn2Pour1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discount2Pour1 d = new Discount2Pour1();
                d.context = context;
                d.show(getFragmentManager(), "Rabais 2 pour 1");
            }
        });

        btnProduitGratuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountProduitGratuit d = new DiscountProduitGratuit();
                d.context = context;
                d.show(getFragmentManager(), "Produit Gratuit");
            }
        });

        btnSeuilSansTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dialogue pour le seuil sans taxes.
            }
        });

        final Button btnBack = (Button)v.findViewById(R.id.btn_rabaisBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogResult.launch();
                dismiss();
            }
        });

        return v;
    }
}
