package getrekt.projetfinaljavav2.appli;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import getrekt.projetfinaljavav2.R;
import getrekt.projetfinaljavav2.appli.gui.CustomDialog;
import getrekt.projetfinaljavav2.appli.gui.DialogResult;
import getrekt.projetfinaljavav2.appli.gui.DiscountDialog;
import getrekt.projetfinaljavav2.appli.gui.MyAdapter;
import getrekt.projetfinaljavav2.appli.gui.PaiementDialog;
import getrekt.projetfinaljavav2.appli.gui.UpdateMethod;
import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.Transaction;
import getrekt.projetfinaljavav2.models.TransactionItem;
import getrekt.projetfinaljavav2.service.ProductService;
import getrekt.projetfinaljavav2.service.TransactionService;


public class ProjetFinalMain extends ActionBarActivity {

    List<TransactionItem> m_currentProducts = new ArrayList<TransactionItem>();
    MyAdapter m_adapter;
    ListView m_lsv_items;
    String test;
    ProductService serviceProducts;
    TransactionService serviceTransacs;
    List<TransactionItem> m_freeProdsCurrent = new ArrayList<TransactionItem>();
    //RepositoryProduitsFichiers repoFich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_final_main);

        MyAdapter adapter = new MyAdapter(ProjetFinalMain.this, m_currentProducts, new UpdateMethod() {
            @Override
            public void launch() {
                ActualizeList();
            }
        });
        ListView list = (ListView)findViewById(R.id.lsv_items);
        list.setAdapter(adapter);
        m_adapter = adapter;
        m_lsv_items = list;

        Button boutonScanner = (Button) findViewById(R.id.btn_scanner);
        Button boutonAddProduit = (Button) findViewById(R.id.btn_addProduct);
        Button boutonPayer = (Button) findViewById(R.id.btn_pay);

        boutonScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Scanner app
                IntentIntegrator integrator = new IntentIntegrator(ProjetFinalMain.this);
                integrator.initiateScan();
            }
        });

        boutonAddProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCustomDialog();
            }
        });

        //TODO ajouter le printreceipt dans le dialog paiementDialog pour effectuer la transaction.
        boutonPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), getString(R.string.transactionSave), Toast.LENGTH_SHORT).show();
                Transaction newTrans = new Transaction(m_currentProducts, new Date());

                //serviceTransacs.save(newTrans);
                PaiementDialog pDialog = new PaiementDialog();
                pDialog.setTotal(calculerTotal());
                //TODO Changer en I18N
                pDialog.show(getFragmentManager(),"Paiement d");
                //serviceTransacs.printReceipt(m_currentProducts);
            }
        });

        serviceProducts = new ProductService(this);
        serviceTransacs = new TransactionService(this);


        // TESTS
        //m_products.add(new Product("Blabla2", "Ceci est la desc de Blabla2.", 30.00, "123456789104"));
        //m_currentProducts.add(new TransactionItem(3, m_products.get(0)));

        //m_products.add(new Product("Blabla1","Ceci est la desc de Blabla1.", 10.00, "022334545453"));
        // m_currentProducts.add(new TransactionItem(1, m_products.get(1)));

        // repo.saveMany(m_products.get(0), m_products.get(1));
    }

    public void ShowCustomDialog()
    {
        CustomDialog d = new CustomDialog();
        // initialization stuff, blah blah
        d.setDialogResult(new DialogResult(){
            public void finish(String pProductName, String pDesc, double pPrice, String pBarCode){
                // now you can use the 'result' on your activity
                // Here is what happens after the activity sends the Finish method call.

                Product newProd = new Product(pProductName, pDesc, pPrice, pBarCode);

                // Adding the data to the collections
                //TODO Validation de produit
                if(!serviceProducts.exists(newProd))
                {
                    serviceProducts.save(newProd);
                }

                // Actual new product

                boolean existsFront = false;
                for(TransactionItem item : m_currentProducts)
                {
                    Product prod = item.getProduct();
                    if(prod.getBarCode().equals(newProd.getBarCode()))
                    {
                        existsFront = true;
                        item.setQty(item.getQty() + 1);
                    }
                }
                if(!existsFront)
                    m_currentProducts.add(new TransactionItem(1, newProd));

                m_adapter.notifyDataSetChanged();
                TextView txtTotal = (TextView)findViewById(R.id.txt_total);
                txtTotal.setText(getString(R.string.total) + calculerTotal() + "$");
            }
        });
        //TODO Changer en I18N
        d.show(getFragmentManager(),"Ajouter un produit");
    }

    public void ShowCustomDialog(String pNewBarCode)
    {
        CustomDialog d = new CustomDialog();
        d.m_preAssignedBarCode = pNewBarCode;
        // initialization stuff, blah blah
        d.setDialogResult(new DialogResult(){
            public void finish(String pProductName, String pDesc, double pPrice, String pBarCode){
                // now you can use the 'result' on your activity
                // Here is what happens after the activity sends the Finish method call.
                Product newProd = new Product(pProductName, pDesc, pPrice, pBarCode);

                // TODO Validation de produit
                if(!serviceProducts.exists(newProd))
                    serviceProducts.save(newProd);

                // Actual new product

                boolean existsFront = false;
                for(TransactionItem item : m_currentProducts)
                {
                    Product prod = item.getProduct();
                    if(prod.getBarCode().equals(newProd.getBarCode()))
                    {
                        existsFront = true;
                        item.setQty(item.getQty() + 1);
                    }
                }
                if(!existsFront)
                {
                    m_currentProducts.add(new TransactionItem(1, newProd));
                }

                m_adapter.notifyDataSetChanged();
                TextView txtTotal = (TextView)findViewById(R.id.txt_total);
                txtTotal.setText(getString(R.string.total) + calculerTotal() + "$");
            }
        });

        d.show(getFragmentManager(),"Ajouter un produit");
    }

    public void ShowDiscountDialog()
    {
        //TODO Gerer le dialogue de gestion des rabais et son retour ici.
        DiscountDialog d = new DiscountDialog();
        d.context = this;

        d.mDialogResult = new UpdateMethod() {
            @Override
            public void launch() {
                if(m_currentProducts.size() != 0)
                {
//                    double currentTotal = calculerTotalRawDouble();
//
//                    double newTotal2pour1 = serviceTransacs.Appliquer2Pour1(m_currentProducts, currentTotal);
//                    //serviceTransacs.AppliquerProduitGratuit(m_currentProducts, newTotal2pour1);
//
//                    m_adapter.notifyDataSetChanged();
//
//
//                    TextView txtTotal = (TextView) findViewById(R.id.txt_total);
//
//                    DecimalFormat df = new DecimalFormat("#.00");
//                    String totalString = df.format(newTotal2pour1);
//
//                    txtTotal.setText(getString(R.string.total) + totalString + "$");
                    ActualizeList();
                }
            }
        };

        d.show(getFragmentManager(), "Gérer les rabais");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanResult != null)
        {
            String barCode = scanResult.getContents();

            Product produitScanne = null;

            try
            {
                produitScanne = serviceProducts.getByCodeBarre(barCode);

                boolean exists = false;
                for(TransactionItem item : m_currentProducts)
                {
                    Product prod = item.getProduct();
                    if(prod.getBarCode().equals(produitScanne.getBarCode()))
                    {
                        item.setQty(item.getQty() + 1);
                        exists = true;
                    }
                }

                if(!exists)
                {
                    m_currentProducts.add(new TransactionItem(1, produitScanne));
                }

                m_adapter.notifyDataSetChanged();
                TextView txtTotal = (TextView)findViewById(R.id.txt_total);
                txtTotal.setText(getString(R.string.total) + calculerTotal() + "$");
            }
            catch(Exception e)
            {
                ShowCustomDialog(scanResult.getContents());
            }

            Log.i("Main", "Scan result from scanner is  " + scanResult.getContents());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projet_final_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.action_create_prod) {
            Toast.makeText(getApplicationContext(), getString(R.string.menu_productsCreate), Toast.LENGTH_SHORT).show();

            serviceProducts.generateProducts();

            return true;
        }
        if (id == R.id.action_clear_prod) {
            m_currentProducts.clear();
            serviceProducts.deleteAll();
            serviceProducts.getRabais2Pour1().deleteAllRabais();
            serviceProducts.getRabaisProduitGratuit().deleteAllRabais();

            m_adapter.notifyDataSetChanged();
            TextView txtTotal = (TextView)findViewById(R.id.txt_total);
            txtTotal.setText(getString(R.string.total) + calculerTotal() + "$");
            Toast.makeText(getApplicationContext(), getString(R.string.menu_productsClear), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_create_order) {
            Toast.makeText(getApplicationContext(), getString(R.string.menu_orderCreate), Toast.LENGTH_SHORT).show();

            try {
                m_currentProducts.clear();

                Random r = new Random();

                int nbrOfProducts = r.nextInt(10) + 1;

                for(int i = 0; i < nbrOfProducts; i++)
                    m_currentProducts.add(serviceTransacs.generateRandomTransItem());

                ActualizeList();

                return true;
            }
            catch(Exception e)
            {
                Log.e("Error", "Could not create order. No products available.");
            }
        }
        if (id == R.id.action_manageDiscounts) {
            ShowDiscountDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String calculerTotal()
    {
        DecimalFormat df = new DecimalFormat("#.00");

        double total = 0;

        for(TransactionItem transItem : m_currentProducts)
        {
            total += transItem.getQty() * transItem.getProduct().getPrice();
        }

        return df.format(total);
    }

    private double calculerTotalRawDouble()
    {
        double total = 0;

        for(TransactionItem transItem : m_currentProducts)
        {
            total += transItem.getQty() * transItem.getProduct().getPrice();
        }

        return total;
    }

    // Calculates the total of the transaction, processes discounts, formats the total...
    private void ActualizeList()
    {
        TextView txtTotal = (TextView)findViewById(R.id.txt_total);

        double currentTotal = calculerTotalRawDouble();

        double newTot = serviceTransacs.Appliquer2Pour1(m_currentProducts, currentTotal);

        List<TransactionItem> result = serviceTransacs.AppliquerProduitGratuit(m_currentProducts, newTot);

        List<TransactionItem> copyList = new ArrayList<TransactionItem>(m_currentProducts);

        for(int i = 0; i < m_currentProducts.size(); i++)
        {
            if(m_currentProducts.get(i).getProduct().getPrice().equals(0.00))
            {
                copyList.remove(i);
            }
        }

        m_freeProdsCurrent = result;

        for(TransactionItem item : result)
        {
            copyList.add(item);
        }

        // Refilling the list
        m_currentProducts.clear();

        for(TransactionItem item : copyList)
        {
            if(item.getQty() != 0)
                m_currentProducts.add(item);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String totalString = df.format(newTot);

        txtTotal.setText(getString(R.string.total) + totalString + "$");

        m_adapter.notifyDataSetChanged();

        // OLD CODE


//        if(m_freeProdsCurrent.size() != 0)
//        {
//            List<TransactionItem> copyList = new ArrayList<TransactionItem>(m_currentProducts);
//
//            for(TransactionItem itemFree : m_freeProdsCurrent)
//            {
//                for(int i = 0; i < m_currentProducts.size(); i++)
//                {
//                    TransactionItem items = m_currentProducts.get(i);
//
//                    if(items.getProduct().getBarCode().equals(itemFree.getProduct().getBarCode()))
//                    {
//                        copyList.get(i).setQty(items.getQty() - itemFree.getQty());
//                        if(copyList.get(i).getQty() == 0)
//                            copyList.remove(i);
//                    }
//                }
//            }
//        }
//
//        m_freeProdsCurrent = result;
//
//        // Setting up a flag
//        Long flag = Long.parseLong(Integer.toString(-5));
//
//        for(TransactionItem curItem : m_currentProducts)
//        {
//            for(TransactionItem freeItem : m_freeProdsCurrent)
//            {
//                if(freeItem.getProduct().getBarCode().equals(curItem.getProduct().getBarCode()))
//                {
//                    curItem.setQty(curItem.getQty() + freeItem.getQty());
//
//                    // Flag the free item
//                    freeItem.setId(flag);
//                }
//            }
//        }
//
//        double freeValue = 0.00;
//
//        for(TransactionItem item : m_freeProdsCurrent)
//        {
//            if(!item.getId().equals(flag))
//            {
//                m_currentProducts.add(item);
//            }
//
//            freeValue += item.getQty() * item.getProduct().getPrice();
//        }
//
//        // Removing the value of the free products, because the client does not pay for them.
//        newTot -= freeValue;
    }
}

