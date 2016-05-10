package src;


import java.io.Serializable;

/**
 *
 * Um terreno representa um espaço com área disponível para construção. É de notar
* que existem terrenos apropriados para construção de habitação, ou apenas para
* construção de armazéns. É ainda importante saber qual o diâmetro das canalizações
* (em milímetros), assim como os kWh máximo suportados pela rede elétrica, se
* instalados, bem como se existe acesso à rede de esgotos.
 */


public class Terreno extends Imovel implements Serializable{
    private static String[] tiposConstr = {"habitacional", "armazens"};
    private int area;
    private String tipoConstrucao; // 1 - Habitacao 2 - Armazem mudar para enum
    private double diametro;
    private double kWhmax; 
    private boolean redeEsgotos;
    
    /*
        Contrutores
    */
   
    public Terreno(){
        super();
        this.area=0;
        this.tipoConstrucao="";
        this.diametro=0.0;
        this.kWhmax=0.0;
        this.redeEsgotos=false;
    }
    
    public Terreno(String id,String rua,String estado, double precoPedido, double precoAceite, int area, String tipoConstrucao, double diametro, double kWhmax, boolean redeEsgotos) {
        super(id,rua, estado, precoPedido,precoAceite);
        this.area = area;
        this.tipoConstrucao = tipoConstrucao;
        this.diametro = diametro;
        this.kWhmax = kWhmax;
        this.redeEsgotos = redeEsgotos;
    }
    
    public Terreno(Terreno t){
        super(t);
        area = t.getArea();
        tipoConstrucao = t.getTipoConstrucao();
        diametro = t.getDiametro();
        kWhmax = t.getkWhmax();
        redeEsgotos = t.getRedeEsgotos();
    }
    
    public static boolean validaTipoConstrucao(String tipo)
    {
        boolean valido = false;
        
        for(String t: tiposConstr)
        {
            if(tipo.equals(t))
            {
                valido = true;
                break;
            }
        }
        
        return valido;
    }
    
    /*
    getters  e setters
    */

    public int getArea() { return area; }
    public String getTipoConstrucao() { return tipoConstrucao; }
    public double getDiametro() { return diametro; }
    public double getkWhmax() { return kWhmax; }
    public boolean getRedeEsgotos() { return redeEsgotos; }

    public void setArea(int area) { this.area = area; }
    public void setTipoConstrucao(String tipoConstrucao) { this.tipoConstrucao = tipoConstrucao; }
    public void setDiametro(double diametro) { this.diametro = diametro; }
    public void setkWhmax(double kWhmax) { this.kWhmax = kWhmax; }
    public void setRedeEsgotos(boolean redeEsgotos) { this.redeEsgotos = redeEsgotos; }
    
    /*
        Equals clone e toString
    */

    public boolean equals(Object o){
        if (o==this) return true;
        if (o==null || o.getClass()!=this.getClass())return false;
 
        Terreno t = (Terreno) o;
        return (super.equals(t)
               && t.getArea()== area
               && t.getTipoConstrucao()== tipoConstrucao
               && t.getDiametro()== diametro
               && t.getkWhmax()== kWhmax
               && t.getRedeEsgotos()== redeEsgotos);
    }
    public Terreno clone(){
        return new Terreno(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Terreno:\n");
        sb.append(super.toString());
        sb.append("Area: ").append(area).append("\n");
        sb.append("Tipo de Construcao: ").append(tipoConstrucao).append("\n");
        sb.append("Diametro Canalizacoes: ").append(diametro).append("\n");
        sb.append("kWh Maximo: ").append(kWhmax).append("\n");
        sb.append("Rede Esgotos: ").append(redeEsgotos).append("\n");
      
        return sb.toString();
    }    
    
    public int hashCode()
    {
        int hash = super.hashCode();
        long aux;
        
        hash = 31*hash + area;
        hash = 31*hash + tipoConstrucao.hashCode();
        aux = Double.doubleToLongBits(diametro);
        hash = 31*hash + (int)(aux^(aux >>> 32));
		aux = Double.doubleToLongBits(kWhmax);
		hash = 31*hash + (int)(aux^(aux >>> 32));
		hash = 31*hash + (redeEsgotos ? 1 : 0);
        
        return hash;
    }
}

