package src;

import java.io.Serializable;

public class LojaHabitavel extends Loja implements Habitavel, Serializable
{
    private Apartamento apartamento;

    /**
     * LojaHabitavel Constructor
     */
    public LojaHabitavel()
    {
        super();
        apartamento = new Apartamento();
    }

    /**
     * LojaHabitavel Constructor
     *
     * @param id A parameter
     * @param rua A parameter
     * @param estado A parameter
     * @param precoPedido A parameter
     * @param precoAceite A parameter
     * @param area A parameter
     * @param wc A parameter
     * @param tipoNegocio A parameter
     * @param numeroDePorta A parameter
     * @param ap A parameter
     */
    public LojaHabitavel(String id, String rua, String estado, double precoPedido, double precoAceite, int area, boolean wc, String tipoNegocio, int numeroDePorta, Apartamento ap)
    {
        super(id, rua, estado, precoPedido, precoAceite, area, wc, tipoNegocio, numeroDePorta);
        apartamento = ap.clone();
    }

    /**
     * LojaHabitavel Constructor
     *
     * @param lh A parameter
     */
    public LojaHabitavel(LojaHabitavel lh)
    {
        super(lh);
        apartamento = lh.getApartamento();
    }
    
    /**
     * LojaHabitavel Constructor
     *
     * @param l A parameter
     * @param ap A parameter
     */
    public LojaHabitavel(Loja l, Apartamento ap)
    {
        super(l);
        apartamento = ap.clone();
    }

    /**
     * Getters e Setters
     */
    
    public Apartamento getApartamento() { return apartamento.clone(); }
    
    public void setApartamento(Apartamento ap) { apartamento = ap.clone(); }

    /**
     * clone, toString, equals, hashCode
     */
    
    public LojaHabitavel clone()
    {
        return new LojaHabitavel(this);
    }

    public boolean equals(Object o)
    {
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        LojaHabitavel obj = (LojaHabitavel) o;

        return super.equals(obj) && apartamento.equals(obj.getApartamento());
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder(super.toString());
        str.append(apartamento.toStringInc());
        
        return str.toString();
    }

    public int hashCode()
    {
        int hash = super.hashCode();

        hash = 31*hash + apartamento.hashCode();

        return hash;
    }
    
}
