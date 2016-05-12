package src;

import java.io.Serializable;

/**
 * Write a description of class Licitador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Licitador implements Serializable
{
    private String idComprador;
    private double limite;
    private double incrementos;
    private double minutos;
    
    public Licitador()
    {
        idComprador = "";
        limite = incrementos = minutos = 0;
    }
    
    public Licitador(String idComprador, double limite, double incrementos, double minutos)
    {
        this.idComprador = idComprador;
        this.limite = limite;
        this.incrementos = incrementos;
        this.minutos = minutos;
    }
    
    public Licitador(Licitador l)
    {
        this(l.getIdComprador(), l.getLimite(), l.getIncrementos(), l.getMinutos());
    }
    
    public String getIdComprador() { return idComprador; }
    public double getLimite() { return limite; }
    public double getIncrementos() { return incrementos; }
    public double getMinutos() { return minutos; }
    
    public void setIdComprador(String id) { idComprador = id; }
    public void setLimite(double lim) { limite = lim; }
    public void setIncrementos(double inc) { incrementos = inc; }
    public void setMinutos(double min) { minutos = min; }

    public long proxLicitacao(long t)
    {
        return 0;
    }
    
    public Licitador clone()
    {
        return new Licitador(this);
    }
    
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        
        Licitador obj = (Licitador) o;
        
        return obj.getIdComprador().equals(idComprador)
            && obj.getLimite() == limite
            && obj.getIncrementos() == incrementos
            && obj.getMinutos() == minutos;
    }
    
    public String toString()
    {
        StringBuilder str = new StringBuilder("\nLicitador");
        str.append("\nID: ").append(idComprador);
        str.append("\nLimite: ").append(limite);
        str.append("\nIncrementos: ").append(incrementos);
        str.append("\nMinutos: ").append(minutos);
        str.append("\n");
        return str.toString();
    }
    
    public int hashCode()
    {
        int hash = 7;
        long aux;
        
        hash = 31*hash + idComprador.hashCode();
        aux = Double.doubleToLongBits(limite);
        hash = 31*hash + (int)(aux^(aux >>> 32));
		aux = Double.doubleToLongBits(incrementos);
		hash = 31*hash + (int)(aux^(aux >>> 32));
		aux = Double.doubleToLongBits(minutos);
		hash = 31*hash + (int)(aux^(aux >>> 32));

        return hash;
    }
}
