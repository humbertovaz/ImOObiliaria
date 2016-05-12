package src;

import java.io.Serializable;

/**
 * Write a description of class Tuplo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tuplo<X, Y> implements Serializable
{
    private final X x;
    private Y y;
    
    public Tuplo(X x, Y y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Tuplo(Tuplo<X, Y> t)
    {
        x = t.fst();
        y = t.snd();
    }
    
    public X fst() { return x; }
    public Y snd() { return y; }
    
    public void setSnd(Y y) { this.y = y; }
    
    public Tuplo<X, Y> clone()
    {
        return new Tuplo(this);
    }
    
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        
        Tuplo<X, Y> obj = (Tuplo<X, Y>) o;
        return obj.fst() == x && obj.snd() == y;
    }
    
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("(").append(x).append(", ").append(y).append(")");
        return str.toString();
    }
    
    public int hashCode()
    {
        int hash = 7;
        
        hash = 31*hash + x.hashCode();
      /*hash = 31*hash + y.hashCode();*/
        return hash;
    }
}
