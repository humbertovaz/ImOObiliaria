package src;

import java.io.Serializable;

/**
 *  Um apartamento representa um imóvel inserido num prédio, como tal sem jardim. São consideradas no mínimo, as seguintes características:
 * • o tipo (Simples, Duplex, Triplex) • a área total
 * • o número de quartos e WCs
 * • o número da porta e o andar • se possui, ou não, garagem
 */

public class Apartamento extends Imovel implements Habitavel, Serializable {
    private static String[] tipos = {"simples", "duplex", "triplex"};
    private String tipo;
    private int area;
    private int nrQuartos;
    private int nrWC;
    private int nrPorta;
    private int andar;
    private boolean garagem;

    public Apartamento(){
        super();
        tipo = "";
        area = 0;
        nrQuartos = 0;
        nrWC = 0;
        nrPorta = 0;
        andar = 0;
        garagem = false;
        
    }
    
    public Apartamento(String id,String rua,String estado, double precoPedido, double precoAceite, String tipo, int area, int nrQuartos, int nrWC, int nrPorta, int andar, boolean garagem) {
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
        super(o);
        tipo = o.getTipo();
        area = o.getArea();
        nrQuartos = o.getNrQuartos();
        nrWC = o.getNrWC();
        nrPorta = o.getNrPorta();
        andar = o.getAndar();
        garagem = o.getGaragem();
    }

    public static boolean validaTipo(String tipo)
    {
        boolean valido = false;
        
        for(String t: tipos)
        {
            if(t.equals(tipo))
            {
                valido = true;
                break;
            }
        }
        return valido;
    }
    
    public String getTipo() { return tipo; }
    public int getArea() { return area; }
    public int getNrQuartos() { return nrQuartos; }
    public int getNrWC() { return nrWC; }
    public int getNrPorta() { return nrPorta; }
    public int getAndar() { return andar; }
    public boolean getGaragem() { return garagem; }

    public void setTipo(String tipo) { this.tipo = tipo; }
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
            && ap.getTipo().equals(tipo) 
            && ap.getArea()==area 
            && ap.getNrQuartos()==nrQuartos
            && ap.getNrWC()==nrWC
            && ap.getNrPorta()==nrPorta
            && ap.getAndar()==andar
            && ap.getGaragem()==garagem);
    }
    
    @Override
    public Apartamento clone(){
        return new Apartamento(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Apartamento: ");
        str.append(super.toString());
        str.append("\ntipo "); str.append(tipo); 
        str.append("\narea "); str.append(area);
        str.append("\nnrQuartos "); str.append(nrQuartos);
        str.append("\nnrWC "); str.append(nrWC);
        str.append("\nnrPorta "); str.append(nrPorta);
        str.append("\nandar "); str.append(andar);
        str.append("\ngaragem "); str.append(garagem);
        str.append("\n");
        return str.toString();
    }
    
    public int hashCode()
    {
        int hash = super.hashCode();

        hash = 31*hash + tipo.hashCode();
        hash = 31*hash + area;
        hash = 31*hash + nrQuartos;
        hash = 31*hash + nrWC;
        hash = 31*hash + nrPorta;
        hash = 31*hash + andar;
        hash = 31*hash + (garagem ? 1 : 0);
    
        return hash;
    }
}
