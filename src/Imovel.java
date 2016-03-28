package src;

import java.util.*;
 

/**
 *
 * Todos os imóveis terão a si associadas a rua em que se situam, o preço pedido e o preço mínimo aceite pelo proprietário (que não deverá ser apresentado aos compradores).

 */


public class Imovel {
    private String rua;
    private int precoPedido;
    private int precoAceite;

    public Imovel()
    {
        this.rua = "";
    }
    
    public Imovel(String rua, int precoPedido, int precoAceite) {
        this.rua = rua;
        this.precoPedido = precoPedido;
        this.precoAceite = precoAceite;
    }
    
    public Imovel (Imovel o){
        this.rua=o.getRua();
        this.precoPedido=o.getPrecoPedido();
        this.precoAceite=o.getPrecoAceite();
    }
//gets e sets
    public String getRua() {
        return rua;
    }

    public int getPrecoPedido() {
        return precoPedido;
    }

    public int getPrecoAceite() {
        return precoAceite;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setPrecoPedido(int precoPedido) {
        this.precoPedido = precoPedido;
    }

    public void setPrecoAceite(int precoAceite) {
        this.precoAceite = precoAceite;
    }

    @Override
    public String toString() {
        return "Imovel " + "rua=" + rua + " precoPedido=" + precoPedido + " precoAceite=" + precoAceite;
    }
    
    

    public boolean equals(Object o){
    if (this==o) return true;
    if (o==null || o.getClass()!=this.getClass()) return false;
    
    Imovel i;
        i = (Imovel) o;
    
    return(this.getRua().equals(i.getRua()) && this.getPrecoPedido()==i.getPrecoPedido() && this.getPrecoAceite()==i.getPrecoAceite());
    }
    
    @Override
    public Imovel clone (){
    Imovel novo = new Imovel(this);
    return novo;
    }
}