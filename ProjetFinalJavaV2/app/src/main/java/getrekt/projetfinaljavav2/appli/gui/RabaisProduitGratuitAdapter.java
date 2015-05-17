package getrekt.projetfinaljavav2.appli.gui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;

/**
 * Created by Joel on 5/16/2015.
 */
public class RabaisProduitGratuitAdapter extends ArrayAdapter<ProduitGratuit> {
    private List<ProduitGratuit> list_items;
    UpdateMethod m_method;

    public RabaisProduitGratuitAdapter(Context context, List<ProduitGratuit> objects, UpdateMethod m)
    {
        super(context, R.layout.list_item, objects);
        list_items = objects;
        m_method = m;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // transformer le fichier XML en objets Java : gonfler
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.rabaisproduitgratuit_list_item, parent, false);

        // Retourne une transactionItem de la liste
        final ProduitGratuit item = getItem(position);

        Button bMinus = (Button)row.findViewById((R.id.rabaisProduitGratuit_btnMinus));
        row.findViewById(R.id.rabaisProduitGratuit_btnMinus).setTag(item);

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_items.remove(position);
                m_method.launch();
                notifyDataSetChanged();
            }
        });

        TextView product_name = (TextView) row.findViewById(R.id.rabaisProduitGratuit_item_prodName);
        row.findViewById(R.id.rabaisProduitGratuit_item_prodName).setTag(item);

        product_name.setText(item.getProd().getProductName());

        TextView prodSeuil = (TextView) row.findViewById(R.id.rabaisProduitGratuit_item_seuil);
        row.findViewById(R.id.rabaisProduitGratuit_item_seuil).setTag(item);

        DecimalFormat df = new DecimalFormat("#.00");
        String seuilFormat = df.format(item.getSeuil());

        prodSeuil.setText(seuilFormat);
        return row;
    }
}
