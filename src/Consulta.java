package src;

import java.util.GregorianCalendar;

import java.io.Serializable;

public class Consulta implements Comparable<Consulta>, Serializable
{
    private String imovel;
    private String email;
    private GregorianCalendar data;
    
    public Consulta()
    {
        imovel = "";
        email = "";
        data = new GregorianCalendar();
    }
    
    public Consulta(String im, String email, GregorianCalendar d)
    {
        imovel = im;
        this.email = email;
        data =(GregorianCalendar) d.clone();
    }
    
    public Consulta(Consulta c)
    {
        this(c.getImovel(), c.getEmail(), c.getData());
    }
    
    public String getImovel() { return imovel; }
    public String getEmail() { return email; }
    public GregorianCalendar getData() { return (GregorianCalendar) data.clone(); }

    public void setImovel(String i) { imovel = i; }
    public void setEmail(String e) { email = e; }
    public void setData(GregorianCalendar d) { data = (GregorianCalendar) d.clone(); }
    
	public int compareTo(Consulta c)
	{
		return data.compareTo(c.getData());
	}

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
               data.equals(obj.getData());               
    }

	public String toString()
	{
		StringBuilder str = new StringBuilder("Consulta\n");
		str.append("Imovel (id): "); str.append(imovel);
		str.append("Utilizador (email): "); str.append(email);
		str.append("Data de consulta: ");str.append(data.getTime());

		return str.toString();
	}
}
