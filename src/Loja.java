import java.util.*;

//package ImOObiliaria;

/**
 *
 * Uma loja representa um espaço destinado a diferentes tipos de negócio. Deve ficar
registada informação quanto à sua área, se possuem, ou não, WC, qual o tipo de
negócio viável na loja, número da porta.
Existem, no entanto, algumas lojas que possuem parte habitacional. Para estas
deverá ser registada a informação guardada para os apartamentos.
 */
public class Loja extends Imovel {
    int area;
    int wc; //1 -Sim, 0-Nao;
    String tipoNegocio;
    int numeroDePorta;

    public Loja(){
        super();
        this.area = 0;
        this.wc = 0;
        this.tipoNegocio = "";
        this.numeroDePorta = 0;
    }
    
    public Loja(String rua,int precoPedido, int precoAceite, int area, int wc, String tipoNegocio, int numeroDePorta) {
        super(rua,precoPedido,precoAceite);
        this.area = area;
        this.wc = wc;
        this.tipoNegocio = tipoNegocio;
        this.numeroDePorta = numeroDePorta;
    }
    
    public Loja (Loja l){
        super(l);
        this.area=l.getArea();
        this.wc = l.getWc();
        this.tipoNegocio = l.getTipoNegocio();
        this.numeroDePorta =l.getNumeroDePorta();
    }
    
   
    
    /*
    getters e setters
    */
    

    public int getArea() {
        return area;
    }

    public int getWc() {
        return wc;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public int getNumeroDePorta() {
        return numeroDePorta;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setWc(int wc) {
        this.wc = wc;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public void setNumeroDePorta(int numeroDePorta) {
        this.numeroDePorta = numeroDePorta;
    }
    
    
    /*
    int area;
    int wc; //1 -Sim, 0-Nao;
    String tipoNegocio;
    int numeroDePorta;

    */
    public boolean equals(Loja l){
        if (l==this) return true;
        if (l==null || l.getClass()!=this.getClass())return false;
        return (l.getRua().equals(this.getRua())
               && l.getPrecoPedido()==this.getPrecoPedido()
               && l.getPrecoAceite()==this.getPrecoAceite()
               && l.getArea()==this.getArea()
               && l.getWc()==this.getWc()
               && l.getTipoNegocio().equals(this.getTipoNegocio())
               && l.getNumeroDePorta()==this.getNumeroDePorta());
    }
    public Loja clone(){
        return new Loja(this);
    }
    
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append("Rua : ").append(this.getRua()).append("\n");
        sb.append("Preco Pedido: ").append(this.getPrecoPedido()).append("\n");
        sb.append("Preco Aceite: ").append(this.getPrecoAceite()).append("\n");
        sb.append("Wc: ").append(this.getWc()).append("\n");
        sb.append("Tipo de Negocio: ").append(this.getTipoNegocio()).append("\n");
        sb.append("Numero de Porta : ").append(this.getNumeroDePorta()).append("\n");
      
        return sb.toString();
    }
    
}
