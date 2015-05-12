package getrekt.projetfinaljavav2.models;

/**
 * Created by 1387434 on 2015-04-21.
 */
public class TransactionItem {
    public TransactionItem(int pQty, Product pProduct)
    {
        setQty(pQty);
        setProduct(pProduct);
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

    private int m_qty;

    public int getQty()
    {
        return m_qty;
    }

    public void setQty(int pQty)
    {
        m_qty = pQty;
    }

    private Product m_product;

    public Product getProduct()
    {
        return  m_product;
    }

    public void setProduct(Product pProduct)
    {
        m_product = pProduct;
    }
}
