package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.service.MonnayeurService;
import getrekt.projetfinaljavav2.service.TransactionService;


public class PaiementDialog extends DialogFragment
{
    TransactionService transacService;
    MonnayeurService moneyService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        transacService = new TransactionService(getActivity().getApplicationContext());
        moneyService = new MonnayeurService(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.paiement_dialog, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.pay));
        //TODO verify if the amount added is enough to pay the bill
        // Identifying data to send back
        final EditText txt100 = (EditText) v.findViewById(R.id.txt_100d);
        final EditText txt50 = (EditText) v.findViewById(R.id.txt_50d);
        final EditText txt20 = (EditText) v.findViewById(R.id.txt_20d);
        final EditText txt10 = (EditText) v.findViewById(R.id.txt_10d);
        final EditText txt5 = (EditText) v.findViewById(R.id.txt_5d);
        final EditText txt2 = (EditText) v.findViewById(R.id.txt_2d);
        final EditText txt1 = (EditText) v.findViewById(R.id.txt_1d);
        final EditText txt25c = (EditText) v.findViewById(R.id.txt_25c);
        final EditText txt10c = (EditText) v.findViewById(R.id.txt_5c);
        final EditText txt5c = (EditText) v.findViewById(R.id.txt_5c);
        final EditText txt1c = (EditText) v.findViewById(R.id.txt_1c);

        final Double total = Double.parseDouble(txt100.getText().toString()) + Double.parseDouble(txt50.getText().toString()) + Double.parseDouble(txt20.getText().toString()) +
                Double.parseDouble(txt10.getText().toString()) + Double.parseDouble(txt5.getText().toString()) + Double.parseDouble(txt2.getText().toString()) +
                Double.parseDouble(txt1.getText().toString()) + Double.parseDouble(txt25c.getText().toString()) + Double.parseDouble(txt10c.getText().toString())
                + Double.parseDouble(txt5c.getText().toString())  + + Double.parseDouble(txt1c.getText().toString());

        // Watch for button clicks.
        Button buttonPayer = (Button) v.findViewById(R.id.btnPayer);

        buttonPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //moneyService.PayerAvecLaCaisse()
                Toast.makeText(getActivity().getApplicationContext(),"total "+ total.toString(),Toast.LENGTH_SHORT).show();
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
