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
 * Created by Joel on 4/15/2015.
 */
public class CustomDialog extends DialogFragment {

    DialogResult mDialogResult; // the callback
    public String m_preAssignedBarCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product_add_dialog, container, false);

        // Sets title area
        getDialog().setTitle("Ajouter un produit");

        // Identifying data to send back
        final EditText txtProductName = (EditText) v.findViewById(R.id.txt_productName);
        final EditText txtDesc = (EditText) v.findViewById(R.id.txt_desc);
        final EditText txtPrice = (EditText) v.findViewById(R.id.txt_price);
        final EditText txtBarcode = (EditText) v.findViewById(R.id.txt_barcode);

        // Preassigned BarCode

        if(m_preAssignedBarCode != null)
        {
            txtBarcode.setText(m_preAssignedBarCode);
        }

        // Watch for button clicks.
        Button buttonAdd = (Button) v.findViewById(R.id.btn_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDialogResult != null)
                {
                    // Sending data
                    mDialogResult.finish(txtProductName.getText().toString(),
                            txtDesc.getText().toString(),
                            Double.parseDouble(txtPrice.getText().toString()),
                            txtBarcode.getText().toString());
                    dismiss();
                }
            }
        });

        Button buttonCancel = (Button)v.findViewById(R.id.btn_cancel);

        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                dismiss();
            }
        });

        return v;
    }

    public void setDialogResult(DialogResult dialogResult)
    {
        mDialogResult = dialogResult;
    }
}
