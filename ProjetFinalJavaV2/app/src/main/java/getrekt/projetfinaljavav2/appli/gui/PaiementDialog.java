package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import getrekt.projetfinaljavav2.R;


public class PaiementDialog extends DialogFragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.paiement_dialog, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.pay));

        // Identifying data to send back
        final EditText txtProductName = (EditText) v.findViewById(R.id.txt_productName);
        final EditText txtDesc = (EditText) v.findViewById(R.id.txt_desc);
        final EditText txtPrice = (EditText) v.findViewById(R.id.txt_price);
        final EditText txtBarcode = (EditText) v.findViewById(R.id.txt_barcode);



        // Watch for button clicks.
        Button buttonAdd = (Button) v.findViewById(R.id.btnPayer);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(),null,Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonCancel = (Button)v.findViewById(R.id.btn_cancel);

//        buttonCancel.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                dismiss();
//            }
//        });

        return v;
    }

}
