package getrekt.projetfinaljavav2.appli.gui;

/**
 * Created by William on 2015-05-19.
 */


import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.InvalidDataException;
import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by William on 2015-05-19.
 */
public class SeuilSansTaxeDialog extends DialogFragment {

    public Context context;
    TransactionService transService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.seuil_sans_taxe_dialog, container, false);

        transService = new TransactionService(context);

        Button btnSansTaxes = (Button)v.findViewById(R.id.buttonSansTaxes);

        final EditText txtSansTaxes = (EditText)v.findViewById(R.id.txt_seuil);
        getDialog().setTitle((getString(R.string.tax_freeAmount)));

        btnSansTaxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Double valeur = Double.parseDouble(txtSansTaxes.getText().toString());
                    transService.AppliquerSeuilSansTaxes(valeur);
                    Toast.makeText(getActivity().getApplicationContext(), (getString(R.string.tax_freeIsnow)) + valeur.toString(), Toast.LENGTH_LONG).show();
                    dismiss();
                } catch (InvalidDataException e) {
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                Log.i("sansTaxeDebug", transService.getNoTaxAmount().toString());

            }

        });


        return v;
    }
}
