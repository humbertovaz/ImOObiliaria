package src;

import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import java.io.Serializable;
/**
 *
 * Todos os imóveis terão a si associadas a rua em que se situam, o preço pedido e o preço mínimo aceite pelo proprietário (que não deverá ser apresentado aos compradores).
 */


public abstract class Imovel implements Comparable<Imovel>, Serializable{
    private static String[] estados = {"venda", "reservado", "vendido"};
    private String id;
    private String rua;
    private String estado; // (emVenda, reservado, vendido) enum!?
    private double precoPedido;
    private double precoAceite;
    private int numConsultas;
    private List<Consulta> consultas;

    
    public Imovel(){
        id = rua = estado = "";
        precoPedido=0;
        precoAceite=0;
        numConsultas = 0;
        consultas = new ArrayList<>();
    }
    
    public Imovel(String id, String rua, String estado, double precoPedido, double precoAceite) {
        this.id = id;
        this.rua = rua;
        this.estado = estado;
        this.precoPedido = precoPedido;
        this.precoAceite = precoAceite;
        numConsultas = 0;
        consultas = new ArrayList<>();
    }
    
    public Imovel (Imovel o){
        id = o.getId();
        rua = o.getRua();
        estado = o.getEstado();
        precoPedido = o.getPrecoPedido();
        precoAceite = o.getPrecoAceite();
        numConsultas = o.getNumConsultas();
        consultas = o.getConsultas();
    }
    
    public static boolean validaEstado(String estado)
    {
        boolean valido = false;
        
        for(String e: estados)
        {
            if(estado.equals(e))
            {
                valido = true;
                break;
            }
        }
        
        return valido;
    }
    
    public static boolean vendido(String estado)
    {
        return estado.equals(estados[0]);
    }
    
    //getters e setters
    
    public String getId() { return id;}
    public String getRua() { return rua; }
    public String getEstado() { return estado; }
    public double getPrecoPedido() { return precoPedido; }
    public double getPrecoAceite() { return precoAceite; }
    public int getNumConsultas() { return numConsultas; }
    public List<Consulta> getConsultas()
    {
        return consultas.stream().map(c -> c.clone()).collect(Collectors.toList());
    }

    public void setId(String id) { this.id = id; }
    public void setRua(String rua) { this.rua = rua; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setPrecoPedido(double precoPedido) { this.precoPedido = precoPedido; }
    public void setPrecoAceite(double precoAceite) { this.precoAceite = precoAceite; }
    public void setConsultas(List<Consulta> c)
    {
        consultas.clear();
    //  c.stream().map(cs -> consultas.add(cs.clone()));
        consultas = c.stream().map(cs -> cs.clone()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void registaConsulta(String email, GregorianCalendar data)
    {
        consultas.add(new Consulta(id , email, data));
        incNumConsultas();
    }

    public void incNumConsultas()
    {
        numConsultas += 1;
    }

    public int compareTo(Imovel i) 
    { 
        return precoPedido < i.getPrecoPedido() ? -1
              :precoPedido > i.getPrecoPedido() ? 1
              : 0;
    }

    /*
     Equals clone e toString
    */

    @Override
    public boolean equals(Object o){
    if (this==o) return true;
    if (o==null || o.getClass()!=this.getClass()) return false;
    
    Imovel i = (Imovel) o;
    
    return(id.equals(i.getId()) && 
           rua.equals(i.getRua()) && 
           estado.equals(i.getEstado()) &&
           precoPedido==i.getPrecoPedido() && 
           precoAceite==i.getPrecoAceite() &&
           numConsultas == i.getNumConsultas());
    }
    
    @Override
    public abstract Imovel clone ();
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nid: "); str.append(id);
        str.append("\nrua: "); str.append(rua);
        str.append("\nprecoPedido: "); str.append(precoPedido);
        return str.toString();
    }
    
    public int hashCode()
    {
        int hash = 7;
        long aux;
        
        hash = 31*hash + id.hashCode();
        hash = 31*hash + rua.hashCode();
        hash = 31*hash + estado.hashCode();
        aux = Double.doubleToLongBits(precoPedido);
        hash = 31*hash + (int) (aux^(aux >>> 32));
        aux = Double.doubleToLongBits(precoAceite);
        hash = 31*hash + (int) (aux^(aux >>> 32));
        hash = 31*hash + numConsultas;

        return hash;
    }
}
