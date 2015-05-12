package getrekt.projetfinaljavav2.models;

import java.util.Date;
import java.util.List;

/**
 * Created by 1387434 on 2015-04-24.
 */
public class Transaction {
    private Date m_date;

    public Date getDate()
    {
        return m_date;
    }

    public void setDate(Date pDate)
    {
        m_date = pDate;
    }

    private Long id;

    public Long getId()
    {
        return id;
    }

    public void setId(Long pId)
    {
        id = pId;
    }

    private List<TransactionItem> lstTransItems;

    public List<TransactionItem> getTransItems()
    {
        return lstTransItems;
    }

    public void setTransItems(List<TransactionItem> pList)
    {
        lstTransItems = pList;
    }

    private double totalMontant;

    public double getTotalMontant()
    {
        return totalMontant;
    }

    public void setTotalMontant(double pTotal)
    {
        totalMontant = pTotal;
    }

    public Transaction(List<TransactionItem> pList, Date pDate)
    {
        lstTransItems = pList;
        m_date = pDate;


        double total = 0;

        for(TransactionItem item : pList)
        {
            Product p = item.getProduct();

            if(p != null)
            {
                total += p.getPrice() * item.getQty();
            }
        }

        totalMontant = total;
    }
}
