package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import getrekt.projetfinaljavav2.R;

/**
 * Created by William on 2015-05-18.
 */
public class MonnayeurResultDialog extends DialogFragment {

    private String monnayeurPrint;

    public void setMonnayeurPrint(String monnayeurPrint){
        this.monnayeurPrint = monnayeurPrint;
    }

    private String total;

    public void setTotal(String total){
        this.total = total;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.monnayeur_result_dialog, container, false);

        if(total.equals("0.0")) {
            TextView textResultMonnayeur = (TextView) v.findViewById(R.id.txtChangeResult);
            TextView textTotal = (TextView) v.findViewById(R.id.txt_total);
            textResultMonnayeur.setText(getString(R.string.no_change_to_give));
            //textTotal.setText(total);
        }
        else{
            TextView textResultMonnayeur = (TextView) v.findViewById(R.id.txtChangeResult);
            TextView textTotal = (TextView) v.findViewById(R.id.txt_total);
            textResultMonnayeur.setText(monnayeurPrint);
            textTotal.setText(total);
        }

        return  v;
    }
}

