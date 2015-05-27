package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.repo.RepoCaisse;
import getrekt.projetfinaljavav2.monnayeur.CashRegister;
import getrekt.projetfinaljavav2.monnayeur.LajoieCorriveauReg;
import getrekt.projetfinaljavav2.service.MonnayeurService;

/**
 * Created by William on 2015-05-26.
 */
public class CaisseManagerDialog extends DialogFragment {

    MonnayeurService monnayeurService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        monnayeurService = new MonnayeurService(getActivity().getApplicationContext());
        View v = inflater.inflate(R.layout.caissemanager_dialog, container, false);

        // Sets title area
        getActivity().setTitle(getString(R.string.caisse_manager));

        final TextView txtViewEtat = (TextView)v.findViewById(R.id.textView2);


        CashRegister reg = monnayeurService.getRegister();

        txtViewEtat.setText(monnayeurService.prettyPrintReg(reg));


        return v;
    }
}
