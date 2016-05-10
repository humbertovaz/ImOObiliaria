package src;


import java.io.Serializable;

/**
 *
 * Uma loja representa um espaço destinado a diferentes tipos de negócio. Deve ficar
registada informação quanto à sua área, se possuem, ou não, WC, qual o tipo de
negócio viável na loja, número da porta.
Existem, no entanto, algumas lojas que possuem parte habitacional. Para estas
deverá ser registada a informação guardada para os apartamentos.
 */
public class Loja extends Imovel implements Serializable {
    int area;
    boolean wc; //1 -Sim, 0-Nao;
    String tipoNegocio;
    int numeroDePorta;

    public Loja(){
        super();
        area = 0;
        wc = false;
        tipoNegocio = "";
        numeroDePorta = 0;
    }
    
    public Loja(String id, String rua, String estado, double precoPedido, double precoAceite, int area, boolean wc, String tipoNegocio, int numeroDePorta) {
        super(id, rua,estado, precoPedido, precoAceite);
        this.area = area;
        this.wc = wc;
        this.tipoNegocio = tipoNegocio;
        this.numeroDePorta = numeroDePorta;
    }
    
    public Loja (Loja l){
        super(l);
        area=l.getArea();
        wc = l.getWc();
        tipoNegocio = l.getTipoNegocio();
        numeroDePorta =l.getNumeroDePorta();
    }
   
    /*
    	getters e setters
    */
    

    public int getArea() { return area; }
    public boolean getWc() { return wc; }
    public String getTipoNegocio() { return tipoNegocio; }
    public int getNumeroDePorta() { return numeroDePorta; }
    
	public void setArea(int area) { this.area = area; }
    public void setWc(boolean wc) { this.wc = wc; }
    public void setTipoNegocio(String tipoNegocio) { this.tipoNegocio = tipoNegocio; }
    public void setNumeroDePorta(int numeroDePorta) { this.numeroDePorta = numeroDePorta; }
    
    public boolean equals(Object o){
        if (o==this) return true;
        if (o==null || o.getClass()!=this.getClass())return false;
        
        Loja l = (Loja) o;
        return (super.equals(l)
               && l.getArea()==area
               && l.getWc()==wc
               && l.getTipoNegocio().equals(tipoNegocio)
               && l.getNumeroDePorta()==numeroDePorta);
    }
    public Loja clone(){
        return new Loja(this);
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder("Loja: \n");
        sb.append(super.toString());
        sb.append("Area: ").append(area).append("\n");
        sb.append("Wc: ").append(wc).append("\n");
        sb.append("Tipo de Negocio: ").append(tipoNegocio).append("\n");
        sb.append("Numero de Porta : ").append(numeroDePorta).append("\n");
      
        return sb.toString();
    }

	public int hashCode()
	{
		int hash = super.hashCode();

		hash = 31*hash + area;
		hash = 31*hash + (wc ? 1 : 0);
		hash = 31*hash + tipoNegocio.hashCode();
		hash = 31*hash + numeroDePorta;

		return hash;
	}
    
}
