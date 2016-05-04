package src;

/**
 *  Um apartamento representa um imóvel inserido num prédio, como tal sem jardim. São consideradas no mínimo, as seguintes características:
 * • o tipo (Simples, Duplex, Triplex) • a área total
 * • o número de quartos e WCs
 * • o número da porta e o andar • se possui, ou não, garagem
 */

public class Apartamento extends Imovel implements Habitavel {
    private int tipo; // (1 -Simples,2- Duplex,3- Triplex)
    private int area;
    private int nrQuartos;
    private int nrWC;
    private int nrPorta;
    private int andar;
    private boolean garagem;

    public Apartamento(){
        super();
        this.tipo = 0;
        this.area = 0;
        this.nrQuartos = 0;
        this.nrWC = 0;
        this.nrPorta = 0;
        this.andar = 0;
        this.garagem = false;
        
    }
    
    public Apartamento(String id,String rua,String estado, int precoPedido, int precoAceite, int tipo, int area, int nrQuartos, int nrWC, int nrPorta, int andar, boolean garagem) {
        super(id,rua, estado, precoPedido, precoAceite);
        this.tipo = tipo;
        this.area = area;
        this.nrQuartos = nrQuartos;
        this.nrWC = nrWC;
        this.nrPorta = nrPorta;
        this.andar = andar;
        this.garagem = garagem;
        
    }
    public Apartamento (Apartamento o){
        super(o); // rua precoPedido e precoAceite
        tipo = this.getTipo();
        area = this.getArea();
        nrQuartos = this.getNrQuartos();
        nrWC = this.getNrWC();
        nrPorta = this.getNrPorta();
        andar = this.getAndar();
        this.garagem = this.temGaragem();
    }

    public final int getTipo() { return tipo; }
    public int getArea() { return area; }
    public int getNrQuartos() { return nrQuartos; }
    public int getNrWC() { return nrWC; }
    public int getNrPorta() { return nrPorta; }
    public int getAndar() { return andar; }
    public boolean temGaragem() { return garagem; }

    public void setTipo(int tipo) { this.tipo = tipo; }
    public void setArea(int area) { this.area = area; }
    public void setNrQuartos(int nrQuartos) { this.nrQuartos = nrQuartos; }
    public void setNrWC(int nrWC) { this.nrWC = nrWC; }
    public void setNrPorta(int nrPorta) { this.nrPorta = nrPorta; }
    public void setAndar(int andar) { this.andar = andar; }
    public void setGaragem(boolean garagem) { this.garagem = garagem; }
    
	/*
        Equals clone e toString
    */
    
    @Override
    public boolean equals (Object o){
    if (this==o) return true;
    if (o==null || o.getClass()!=this.getClass()) return false;
    Apartamento ap = (Apartamento) o;
    
    return (super.equals(ap)
            && ap.getTipo()==this.getTipo() 
            && ap.getArea()==this.getArea() 
            && ap.getNrQuartos()==this.getNrQuartos() 
            && ap.getNrWC()==this.getNrWC() 
            && ap.getNrPorta()==this.getNrPorta() 
            && ap.getAndar()==this.getAndar());
    }
    
	@Override
    public Apartamento clone(){
        return new Apartamento(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Apartamento ");
        str.append("tipo "); str.append(tipo); 
        str.append("area "); str.append(area);
        str.append("nrQuartos "); str.append(nrQuartos);
        str.append("nrWC "); str.append(nrWC);
        str.append("nrPorta "); str.append(nrPorta);
        str.append("andar "); str.append(andar);
        str.append("garagem "); str.append(garagem);
        return str.toString();
    }

}
