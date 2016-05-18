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

    /**
     * Apartamento Constructor
     *
     */
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
    
    /**
     * Apartamento Constructor
     *
     * @param id A parameter
     * @param rua A parameter
     * @param estado A parameter
     * @param precoPedido A parameter
     * @param precoAceite A parameter
     * @param tipo A parameter
     * @param area A parameter
     * @param nrQuartos A parameter
     * @param nrWC A parameter
     * @param nrPorta A parameter
     * @param andar A parameter
     * @param garagem A parameter
     */
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
    /**
     * Apartamento Constructor
     *
     * @param o A parameter
     */
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

    /**
     * Method validaTipo
     *
     * @param tipo A parameter
     * @return The return value
     */
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
        str.append("\nTipo "); str.append(tipo); 
        str.append("\nArea "); str.append(area);
        str.append("\nNumero de quartos "); str.append(nrQuartos);
        str.append("\nNumero de WC "); str.append(nrWC);
        str.append("\nNumero de Porta "); str.append(nrPorta);
        str.append("\nAndar "); str.append(andar);
        str.append("\nTem garagem? "); 
        if(garagem)
            str.append("Sim\n");
        else
            str.append("Nao\n");

        return str.toString();
    }
    
    public String toStringInc()
    {
        StringBuilder str = new StringBuilder("parte habitavel: ");
        str.append("\nTipo "); str.append(tipo); 
        str.append("\nArea "); str.append(area);
        str.append("\nNumero de quartos "); str.append(nrQuartos);
        str.append("\nNumero de WC "); str.append(nrWC);
        str.append("\nNumero de Porta "); str.append(nrPorta);
        str.append("\nAndar "); str.append(andar);
        str.append("\nTem garagem? "); 
        if(garagem)
            str.append("Sim\n");
        else
            str.append("Nao\n");
            
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
