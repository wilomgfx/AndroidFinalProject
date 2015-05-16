package getrekt.projetfinaljavav2.appli.gui;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.Rabais2Pour1;
import getrekt.projetfinaljavav2.models.TransactionItem;
import getrekt.projetfinaljavav2.service.ProductService;
import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by Joel on 5/16/2015.
 */
public class Discount2Pour1 extends DialogFragment{
    List<Product> m_currentProducts;
    Rabais2Pour1Adapter m_adapter;
    ListView m_lsv_items;
    public Context context;
    ProductService prodService;
    TransactionService transService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.discount_2pour1, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.rabais2pour1));

        prodService = new ProductService(context);
        transService = new TransactionService(context);

        m_currentProducts = prodService.getRabais2Pour1().getProduitsRabais();

        final Rabais2Pour1Adapter adapter = new Rabais2Pour1Adapter(context, m_currentProducts, new UpdateMethod() {
            @Override
            public void launch() {
            }
        });

        ListView list = (ListView)v.findViewById(R.id.lsv_2pour1_items);
        list.setAdapter(adapter);
        m_adapter = adapter;
        m_lsv_items = list;

        final Button btn_AddRabais = (Button)v.findViewById(R.id.rabais2pour1_btn_add);
        final EditText codeBarreRabais = (EditText)v.findViewById(R.id.rabais2pour1_codeBarre);

        btn_AddRabais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product prod = prodService.getByCodeBarre(codeBarreRabais.getText().toString());

                if(prod == null)
                    Toast.makeText(context, getString(R.string.productNotFound), Toast.LENGTH_SHORT).show();
                else
                {
                    boolean contained = false;

                    for(Product item : m_currentProducts)
                    {
                        if(item.getBarCode().equals(prod.getBarCode()))
                            contained = true;
                    }

                    if(!contained)
                        m_currentProducts.add(prod);
                    else
                        Toast.makeText(context, getString(R.string.rabais2pour1_alreadyThere), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                }
            }
        });

        final Button btnBack = (Button)v.findViewById(R.id.rabais2pour1_btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodService.save(prodService.getRabais2Pour1());
                dismiss();
            }
        });

        return v;
    }
}
