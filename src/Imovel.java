package src;

import java.util.*;
//package ImOObiliaria;

/**
 *
 * Todos os imóveis terão a si associadas a rua em que se situam, o preço pedido e o preço mínimo aceite pelo proprietário (que não deverá ser apresentado aos compradores).
 */


public class Imovel implements Comparable {
    private String id;
    private String rua;
	private String estado; // (emVenda, reservado, vendido)
    private int precoPedido;
    private int precoAceite;

    
    public Imovel(){
        this.id = this.rua = this.estado = "";
        this.precoPedido=0;
        this.precoAceite=0;
        
    }
    
    public Imovel(String id, String rua, String estado, int precoPedido, int precoAceite) {
        this.id = id;
        this.rua = rua;
		this.estado = estado;
        this.precoPedido = precoPedido;
        this.precoAceite = precoAceite;
    }
    
    public Imovel (Imovel o){
        id = o.getId();
        rua= o.getRua();
		estado = o.getEstado();
        precoPedido= o.getPrecoPedido();
        precoAceite= o.getPrecoAceite();
    }
	
	//getters e setters
    
    public String getId() { return id;}
    public String getRua() { return rua; }
	public String getEstado() { return estado; }
    public int getPrecoPedido() { return precoPedido; }
    public int getPrecoAceite() { return precoAceite; }
   
    public void setId(String id) { this.id = id; }
    public void setRua(String rua) { this.rua = rua; }
    public void setEstado(String estado) { this.estado = estado; }
	public void setPrecoPedido(int precoPedido) { this.precoPedido = precoPedido; }
    public void setPrecoAceite(int precoAceite) { this.precoAceite = precoAceite; }
    
	public int compareTo(Object o) 
    { 
        Imovel i = (Imovel) o;
        return id.compareTo(i.getId()); 
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
           precoAceite==i.getPrecoAceite());
    }
    
    @Override
    public Imovel clone (){
        return new Imovel(this);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Imovel ");
        str.append("id: "); str.append(id);
        str.append("rua: "); str.append(rua);
        str.append("precoPedido: "); str.append(precoPedido);
        str.append("precoAceite: "); str.append( precoAceite);
        return str.toString();
    }
}
