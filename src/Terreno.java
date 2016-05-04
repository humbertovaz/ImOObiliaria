package src;

/**
 *
 * Um terreno representa um espaço com área disponível para construção. É de notar
* que existem terrenos apropriados para construção de habitação, ou apenas para
* construção de armazéns. É ainda importante saber qual o diâmetro das canalizações
* (em milímetros), assim como os kWh máximo suportados pela rede elétrica, se
* instalados, bem como se existe acesso à rede de esgotos.
 */

public class Terreno extends Imovel{
    int area ;
    int construcao; // 1 - Habitacao 2 - Armazem
    double diametro;
    double kWhmax; 
    int redeEsgotos;// 1-Sim 0-Não
    
    /*
        Contrutores
    */
    public Terreno(){
        super();
        this.area=0;
        this.construcao=0;
        this.diametro=0.0;
        this.kWhmax=0.0;
        this.redeEsgotos=0;
    }
    
    public Terreno(String id,String rua,String estado, int precoPedido, int precoAceite, int area, int construcao, double diametro, double kWhmax, int redeEsgotos) {
        super(id,rua, estado, precoPedido,precoAceite);
        this.area = area;
        this.construcao = construcao;
        this.diametro = diametro;
        this.kWhmax = kWhmax;
        this.redeEsgotos = redeEsgotos;
    }
    
    public Terreno(Terreno t){
        super(t);
        area = t.getArea();
        construcao = t.getConstrucao();
        diametro = t.getDiametro();
        kWhmax = t.getkWhmax();
        redeEsgotos = t.getRedeEsgotos();
    }
    
    
    /*
    getters  e setters
    */

    public int getArea() { return area; }
    public int getConstrucao() { return construcao; }
    public double getDiametro() { return diametro; }
    public double getkWhmax() { return kWhmax; }
    public int getRedeEsgotos() { return redeEsgotos; }

    public void setArea(int area) { this.area = area; }
    public void setConstrucao(int construcao) { this.construcao = construcao; }
    public void setDiametro(double diametro) { this.diametro = diametro; }
    public void setkWhmax(double kWhmax) { this.kWhmax = kWhmax; }
    public void setRedeEsgotos(int redeEsgotos) { this.redeEsgotos = redeEsgotos; }
    
    /*
        Equals clone e toString
    */
    
    /*
    int area ;
    int construcao; // 1 - Habitacao 2 - Armazem
    double diametro;
    double kWhmax; 
    int redeEsgotos;// 1-Sim 0-Não
    */
    public boolean equals(Object o){
        if (o==this) return true;
        if (o==null || o.getClass()!=this.getClass())return false;
 
        Terreno t = (Terreno) o;
        return (super.equals(t)
               && t.getArea()==this.getArea()
               && t.getConstrucao()==this.getConstrucao()
               && t.getDiametro()==this.getDiametro()
               && t.getkWhmax()==this.getkWhmax()
               && t.getRedeEsgotos()==this.getRedeEsgotos());
    }
    public Terreno clone(){
        return new Terreno(this);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder("Terreno\n");
        sb.append("Area: ").append(area).append("\n");
        sb.append("Construcao: ").append(construcao).append("\n");
        sb.append("Diametro Canalizacoes: ").append(diametro).append("\n");
        sb.append("kWh Maximo: ").append(kWhmax).append("\n");
        sb.append("Rede Esgotos: ").append(redeEsgotos).append("\n");
      
        return sb.toString();
    }    
}

