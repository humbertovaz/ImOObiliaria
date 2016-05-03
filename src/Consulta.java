package src;

import java.util.GregorianCalendar;

public class Consulta
{
    private Imovel imovel;
    private String email;
    private GregorianCalendar data;
    
    public Consulta()
    {
        imovel = null;
        email = "";
        data = new GregorianCalendar();
    }
    
    public Consulta(Imovel im, String email, GregorianCalendar d)
    {
        imovel = im.clone();
        this.email = email;
        data = d;
    }
    
    public Consulta(Consulta c)
    {
        this(c.getImovel(), c.getEmail(), c.getData());
    }
    
    public Imovel getImovel() { return imovel.clone(); }
    public String getEmail() { return email; }
    public GregorianCalendar getData() { return data; }

    public void setImovel(Imovel i) { imovel = i.clone(); }
    public void setEmail(String e) { email = e; }
    public void setData(GregorianCalendar d) { data = d; }
    
    public Consulta clone()
    {
        return new Consulta(this);
    }
    
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if((o == null) || (this.getClass() !=  o.getClass())) return false;
        
        Consulta obj = (Consulta) o;
        return imovel.equals(obj.getImovel()) &&
               email.equals(obj.getEmail()) &&
               data.getTime().equals(obj.getData().getTime());               
    }
}
