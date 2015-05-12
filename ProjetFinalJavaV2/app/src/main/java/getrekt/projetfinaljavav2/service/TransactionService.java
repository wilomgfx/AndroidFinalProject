package getrekt.projetfinaljavav2.service;

/**
 * Created by 1387434 on 2015-05-12.
 */
import android.content.Context;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import getrekt.projetfinaljavav2.models.Product;
import getrekt.projetfinaljavav2.models.Transaction;
import getrekt.projetfinaljavav2.models.TransactionItem;
import getrekt.projetfinaljavav2.models.repo.RepositoryTransac;

/**
 * Created by 1387434 on 2015-05-05.
 */
public class TransactionService {
    private Context context;

    private RepositoryTransac repoTrans;

    public TransactionService(Context pContext)
    {
        this.context = pContext;

        repoTrans = RepositoryTransac.get(context);
    }

    public void createStorage()
    {
        repoTrans.createStorage();
    }

    public void deleteStorage()
    {
        repoTrans.deleteStorage();
    }

    public long save(Transaction a)
    {
        Long id = repoTrans.save(a);

        DecimalFormat df = new DecimalFormat("#.00");

        Log.i("Transaction", "===================================================");
        Log.i("Transaction", "TRANSACTION COURANTE :");
        Log.i("Transaction", "DATE: " + a.getDate().toString() + ", TOTAL AMOUNT: " + df.format(a.getTotalMontant()));
        for(TransactionItem item : a.getTransItems()) {
            Log.i("Transaction", Integer.toString(item.getQty()) + " " + item.getProduct().getProductName() + " " + Double.toString(item.getProduct().getPrice()));
        }
        Log.i("Transaction", "===================================================");
        Log.i("Transaction", "TRANSACTIONS PRÉCÉDENTES :");
        for(Transaction t : repoTrans.getAll())
        {
            Log.i("Transaction", "-------------------------------------------------------");
            Log.i("Transaction", "TransactionID: " + Long.toString(t.getId()));
            Log.i("Transaction", "DATE: " + t.getDate().toString() + ", TOTAL AMOUNT: " + df.format(t.getTotalMontant()));

            for(TransactionItem item : t.getTransItems())
            {
                Log.i("Transaction", Integer.toString(item.getQty()) + " " + item.getProduct().getProductName() + " " + Double.toString(item.getProduct().getPrice()));
            }
        }
        Log.i("Transaction", "===================================================");

        return id;
    }

    public void saveMany(Iterable<Transaction> list)
    {
        repoTrans.saveMany(list);
    }

    public void saveMany(Transaction... list)
    {
        repoTrans.saveMany(list);
    }

    public Transaction getById(Long id)
    {
        return repoTrans.getById(id);
    }

    public List<Transaction> getAll() {
        return repoTrans.getAll();
    }

    public void deleteOne(Long o) {
        repoTrans.deleteOne(o);
    }

    public void deleteOne(Transaction o) {
        repoTrans.deleteOne(o);
    }

    public void deleteAll() {
        repoTrans.deleteAll();
    }

    public TransactionItem generateRandomTransItem()
    {
        ProductService prodService = new ProductService(context);

        List<Product> lstProds = prodService.getAll();

        Random r = new Random();

        int indexRandom = r.nextInt(lstProds.size());
        Product p = lstProds.get(indexRandom);
        int qty = r.nextInt(10) + 1;

        return new TransactionItem(qty, p);

    }

    public void printReceipt(List<TransactionItem> lstItems)
    {
        Log.i("Facture", "=======================================FACTURE=======================================");
        Log.i("Facture","=====================================================================================");

        for(TransactionItem transItem : lstItems)
        {
            Log.i("Facture","| " + transItem.getProduct().getBarCode());

            DecimalFormat df = new DecimalFormat("#0.00");
            StringBuilder line = new StringBuilder("                                                       ");
            if(transItem.getQty() > 1)
            {
                Log.i("Facture", "| " + StringUtils.leftPad(transItem.getProduct().getProductName(), 10));
                StringBuilder product = new StringBuilder(transItem.getQty() + " @ " + "$" + df.format(transItem.getProduct().getPrice()) + " ch.");
                StringBuilder total = new StringBuilder(df.format(transItem.getQty() * transItem.getProduct().getPrice()));

                line.replace(0, product.length() - 1, product.toString());
                line.replace(line.length() - total.length(), line.length() - 1, total.toString());
            }
            else
            {
                String product = transItem.getProduct().getProductName();
                String total = df.format(transItem.getProduct().getPrice());

                line.replace(0, product.length() - 1, product.toString());
                line.replace(line.length() - total.length(), line.length() - 1, total.toString());
            }

            Log.i("Facture","|   " + line.toString());
            Log.i("Facture", "|");
        }


        Log.i("Facture","=====================================================================================");
        Log.i("Facture","=====================================================================================");
    }

    /***
     * Ajoute la tax sur le prix total.
     * @param montantTotal montantTotal de la facture
     * @return le total avec la taxe appliquee
     */
    public Double addTax(Double montantTotal){
        //http://www.calculconversion.com/calcul-taxes-tps-tvq.html
        Double montantAvecTaxe = 0.00;
        Double tps = 5.00;
        Double tvq = 9.975;
        Double montantTps = montantTotal * (tps/100);
        Double montantTvq = montantTotal * (tvq/100);
        montantAvecTaxe =  montantTotal + (montantTps + montantTvq);

        double rounded = (double) Math.round(montantAvecTaxe * 100.0) / 100.0;

        return rounded;
    }
}