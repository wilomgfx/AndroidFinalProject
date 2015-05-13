package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.monnayeur.CashException;
import getrekt.projetfinaljavav2.monnayeur.Change;
import getrekt.projetfinaljavav2.monnayeur.NotEnoughMoneyException;
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
        final EditText nbr100 = (EditText) v.findViewById(R.id.nbr100d);
        final EditText nbr50 = (EditText) v.findViewById(R.id.nbr50d);
        final EditText nbr20 = (EditText) v.findViewById(R.id.nbr20d);
        final EditText nbr10 = (EditText) v.findViewById(R.id.nbr10d);
        final EditText nbr5 = (EditText) v.findViewById(R.id.nbr5d);
        final EditText nbr2 = (EditText) v.findViewById(R.id.nbr2d);
        final EditText nbr1 = (EditText) v.findViewById(R.id.nbr1d);
        final EditText nbr25c = (EditText) v.findViewById(R.id.nbr25c);
        final EditText nbr10c = (EditText) v.findViewById(R.id.nbr10c);
        final EditText nbr5c = (EditText) v.findViewById(R.id.nbr5c);
        final EditText nbr1c = (EditText) v.findViewById(R.id.nbr1c);

        final Double total = Double.parseDouble(nbr100.getText().toString()) + Double.parseDouble(nbr50.getText().toString()) + Double.parseDouble(nbr20.getText().toString()) +
                Double.parseDouble(nbr10.getText().toString()) + Double.parseDouble(nbr5.getText().toString()) + Double.parseDouble(nbr2.getText().toString()) +
                Double.parseDouble(nbr1.getText().toString()) + Double.parseDouble(nbr25c.getText().toString()) + Double.parseDouble(nbr10c.getText().toString())
                + Double.parseDouble(nbr5c.getText().toString())  + + Double.parseDouble(nbr1c.getText().toString());

        Integer debugI = Integer.parseInt(nbr100.getText().toString());
        Integer debugItimes100 = Integer.parseInt(nbr100.getText().toString())*100;


        // Watch for button clicks.
        Button buttonPayer = (Button) v.findViewById(R.id.btnPayer);

        buttonPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            //TODO making it work properly, but the principle is there.
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"total "+ total.toString(),Toast.LENGTH_SHORT).show();

                //the values times the value in money.
                Integer debugItimes100 = Integer.parseInt(nbr100.getText().toString())*100;

                try {
                    //Log.i("DebuggingPaiementDialog",  "After " + debugI.toString() + " " + debugItimes100.toString());
                    Change changeResult;
                    changeResult =  moneyService.PayerAvecLaCaisse(debugItimes100.doubleValue());
                    Log.i("DebuggingPaiementDialog", moneyService.PrettyPrintChange(changeResult));

                } catch (NotEnoughMoneyException e) {
                    Toast.makeText(getActivity().getApplicationContext(),e.getMessage() ,Toast.LENGTH_LONG).show();
                } catch (CashException e) {
                    Toast.makeText(getActivity().getApplicationContext(),e.getMessage() ,Toast.LENGTH_LONG).show();
                }

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
