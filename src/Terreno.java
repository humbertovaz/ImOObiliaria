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
    
    public Terreno(String id,String rua, int precoPedido, int precoAceite, int area, int construcao, double diametro, double kWhmax, int redeEsgotos) {
        super(id,rua,precoPedido,precoAceite);
        this.area = area;
        this.construcao = construcao;
        this.diametro = diametro;
        this.kWhmax = kWhmax;
        this.redeEsgotos = redeEsgotos;
    }
    
    public Terreno(Terreno t){
        super(t);
        this.area = t.getArea();
        this.construcao = t.getConstrucao();
        this.diametro = t.getDiametro();
        this.kWhmax = t.getkWhmax();
        this.redeEsgotos = t.getRedeEsgotos();
    }
    
    
    /*
    gets  e sets
    */

    public int getArea() {
        return area;
    }

    public int getConstrucao() {
        return construcao;
    }

    public double getDiametro() {
        return diametro;
    }

    public double getkWhmax() {
        return kWhmax;
    }

    public int getRedeEsgotos() {
        return redeEsgotos;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setConstrucao(int construcao) {
        this.construcao = construcao;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public void setkWhmax(double kWhmax) {
        this.kWhmax = kWhmax;
    }

    public void setRedeEsgotos(int redeEsgotos) {
        this.redeEsgotos = redeEsgotos;
    }
    
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
               && t.getRua().equals(this.getRua())
               && t.getPrecoPedido()==this.getPrecoPedido()
               && t.getPrecoAceite()==this.getPrecoAceite()
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
        StringBuilder sb= new StringBuilder();
        sb.append("Rua : ").append(this.getRua()).append("\n");
        sb.append("Preco Pedido: ").append(this.getPrecoPedido()).append("\n");
        sb.append("Preco Aceite: ").append(this.getPrecoAceite()).append("\n");
        sb.append("Area: ").append(this.getArea()).append("\n");
        sb.append("Construcao: ").append(this.getConstrucao()).append("\n");
        sb.append("Diametro Canalizacoes: ").append(this.getDiametro()).append("\n");
        sb.append("kWh Maximo: ").append(this.getkWhmax()).append("\n");
        sb.append("Rede Esgotos: ").append(this.getRedeEsgotos()).append("\n");
      
        return sb.toString();
    }
    
    

}

