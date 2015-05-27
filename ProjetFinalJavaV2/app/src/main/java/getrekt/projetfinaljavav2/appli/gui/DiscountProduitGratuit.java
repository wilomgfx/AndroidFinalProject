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
import android.widget.Toast;

import java.util.List;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.ProduitGratuit;
import getrekt.projetfinaljavav2.models.RabaisProduitGratuit;
import getrekt.projetfinaljavav2.service.ProductService;
import getrekt.projetfinaljavav2.service.TransactionService;

/**
 * Created by Joel on 5/16/2015.
 */
public class DiscountProduitGratuit extends DialogFragment {
    List<ProduitGratuit> m_currentProducts;
    RabaisProduitGratuitAdapter m_adapter;
    ListView m_lsv_items;
    public Context context;
    ProductService prodService;
    TransactionService transService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.discount_produitgratuit, container, false);

        // Sets title area
        getDialog().setTitle(getString(R.string.rabaisProduitGratuit));

        prodService = new ProductService(context);
        transService = new TransactionService(context);

        m_currentProducts = prodService.getRabaisProduitGratuit().getProduitsGratuits();

        final RabaisProduitGratuitAdapter adapter = new RabaisProduitGratuitAdapter(context, m_currentProducts, new UpdateMethod() {
            @Override
            public void launch() {
            }
        });

        ListView list = (ListView)v.findViewById(R.id.lsv_prodGrat_items);
        list.setAdapter(adapter);
        m_adapter = adapter;
        m_lsv_items = list;

        final Button btn_AddRabais = (Button)v.findViewById(R.id.rabaisProduitGratuit_btnAdd);
        final EditText codeBarreRabais = (EditText)v.findViewById(R.id.rabaisProduitGratuit_inputBarCode);
        final EditText pSeuil = (EditText)v.findViewById(R.id.rabaisProduitGratuit_Seuil);

        btn_AddRabais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product prod = prodService.getByCodeBarre(codeBarreRabais.getText().toString());
                double seuil = Double.parseDouble(pSeuil.getText().toString());

                if(prod == null)
                    Toast.makeText(context, getString(R.string.productNotFound), Toast.LENGTH_SHORT).show();
                else
                {
                    boolean contained = false;

                    for(ProduitGratuit item : m_currentProducts)
                    {
                        if(item.getProd().getBarCode().equals(prod.getBarCode()))
                            contained = true;
                    }

                    if(!contained)
                    {
                        ProduitGratuit newProdGrat = new ProduitGratuit(prod, seuil);
                        m_currentProducts.add(newProdGrat);
                    }
                    else
                        Toast.makeText(context, getString(R.string.rabaisProduitGratuit_alreadyThere), Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                }
            }
        });

        final Button btnBack = (Button)v.findViewById(R.id.rabaisProduitGratuit_btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodService.save(prodService.getRabaisProduitGratuit());
                dismiss();
            }
        });

        return v;
    }
}
