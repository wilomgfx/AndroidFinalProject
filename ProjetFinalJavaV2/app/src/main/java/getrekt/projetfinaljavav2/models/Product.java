package getrekt.projetfinaljavav2.models;

public class Product {
    private Long m_id;

    public Long getID() { return m_id; }

    public void setID(Long pId) { this.m_id = pId; }

    private String m_productName;

    public String getProductName() {
        return m_productName;
    }

    public void setProductName(String pProductName) {
        if(pProductName != null)
        {
            this.m_productName = pProductName;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private Double m_price;

    public Double getPrice()
    {
        return m_price;
    }

    public void setPrice(Double pPrice)
    {
        if(pPrice >= 0)
        {
            m_price = pPrice;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private String m_desc;

    public String getDesc()
    {
        return m_desc;
    }

    public void setDesc(String pDesc)
    {
        if(pDesc != null)
        {
            m_desc = pDesc;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    private String m_barCode;

    public String getBarCode()
    {
        return m_barCode;
    }

    public void setBarCode(String pBarCode)
    {
        if(pBarCode != null)
        {
            m_barCode = pBarCode;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public Product(String pProductName, String pDesc, Double pPrice, String pBarCode)
    {
        if(pProductName != null)
        {
            m_productName = pProductName;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        if(pBarCode != null)
        {
            m_barCode = pBarCode;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        if(pDesc != null)
        {
            m_desc = pDesc;
        }
        else
        {
            throw new IllegalArgumentException();
        }
        if(pPrice >= 0)
        {
            m_price = pPrice;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public Product(Product pOldProd)
    {
        m_productName = pOldProd.getProductName();
        m_barCode = pOldProd.getBarCode();
        m_desc = pOldProd.getDesc();
        m_price = pOldProd.getPrice();
    }
}
