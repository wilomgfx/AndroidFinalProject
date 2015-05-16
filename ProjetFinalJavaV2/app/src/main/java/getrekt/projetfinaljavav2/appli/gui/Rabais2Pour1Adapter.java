package getrekt.projetfinaljavav2.appli.gui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.TransactionItem;

/**
 * Created by Joel on 5/16/2015.
 */
public class Rabais2Pour1Adapter extends ArrayAdapter<Product>{
    private List<Product> list_items;
    UpdateMethod m_method;

    public Rabais2Pour1Adapter(Context context, List<Product> objects, UpdateMethod m)
    {
        super(context, R.layout.list_item, objects);
        list_items = objects;
        m_method = m;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // transformer le fichier XML en objets Java : gonfler
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.rabais2pour1_list_item, parent, false);

        // Retourne une transactionItem de la liste
        final Product item = getItem(position);

        Button bMinus = (Button)row.findViewById((R.id.rabais2pour1_item_btn_minus));
        row.findViewById(R.id.rabais2pour1_item_btn_minus).setTag(item);

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_items.remove(position);
                m_method.launch();
                notifyDataSetChanged();
            }
        });

        TextView product_name = (TextView) row.findViewById(R.id.rabais2pour1_item_name);
        row.findViewById(R.id.rabais2pour1_item_name).setTag(item);

        product_name.setText(item.getProductName());
        return row;
    }
}
