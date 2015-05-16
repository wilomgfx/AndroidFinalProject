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
import getrekt.projetfinaljavav2.models.TransactionItem;

public class MyAdapter extends ArrayAdapter<TransactionItem>
{
    private List<TransactionItem> list_items;
    UpdateMethod m_method;

    public MyAdapter(Context context, List<TransactionItem> objects, UpdateMethod m)
    {
        super(context, R.layout.list_item, objects);
        list_items = objects;
        m_method = m;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // transformer le fichier XML en objets Java : gonfler
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.list_item, parent, false);

        // Retourne une transactionItem de la liste
        final TransactionItem item = getItem(position);

        row.findViewById(R.id.item_btn_plus).setTag(item);

        Button bPlus = (Button)row.findViewById(R.id.item_btn_plus);

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setQty(item.getQty() + 1);
                notifyDataSetChanged();
                m_method.launch();
            }
        });

        Button bMinus = (Button)row.findViewById((R.id.item_btn_minus));
        row.findViewById(R.id.item_btn_minus).setTag(item);

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getQty() == 1)
                {
                    list_items.remove(position);
                    m_method.launch();
                }
                else
                {
                    item.setQty(item.getQty() - 1);
                    m_method.launch();
                }
                notifyDataSetChanged();
            }
        });
        TextView qty_item = (TextView) row.findViewById(R.id.item_qty);
        row.findViewById(R.id.item_qty).setTag(item);

        TextView product_name = (TextView) row.findViewById(R.id.item_name);
        row.findViewById(R.id.item_name).setTag(item);

        product_name.setText(item.getProduct().getProductName());

        String quanti = getContext().getString(R.string.listItem_shownQty) + " " + Integer.toString(item.getQty());
        qty_item.setText(quanti);

        return row;
    }
}
