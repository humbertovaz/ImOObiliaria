package src;

import java.util.Iterator;

import java.io.Serializable;

/**
* Uma moradia representa um imóvel para habitação familiar. Estas possuem diversas características relevantes, devendo ser consideradas, no mínimo:
* • o tipo (isolada, geminada, banda, gaveto) • a área de implantação
* • a área total coberta
* • a área do terreno envolvente
* • o número de quartos e de WCs • o número da porta
*/
public class Moradia extends Imovel implements Habitavel, Serializable {
    private static String[] tipos = {"isolada", "geminada", "banda", "gaveto"};
    private String tipo;
    private int areaImplantacao;
    private int areaTotalCoberta;
    private int areaTerrenoEnv;
    private int nrQuartos;

    /**
     * Moradia Constructor
     *
     * @param id A parameter
     * @param rua A parameter
     * @param estado A parameter
     * @param precoPedido A parameter
     * @param precoAceite A parameter
     * @param tipo A parameter
     * @param areaImplantacao A parameter
     * @param areaTotalCoberta A parameter
     * @param areaTerrenoEnv A parameter
     * @param nrQuartos A parameter
     */
    public Moradia(String id, String rua, String estado, double precoPedido, double precoAceite, String tipo, int areaImplantacao, int areaTotalCoberta, int areaTerrenoEnv, int nrQuartos){
        super(id, rua, estado, precoPedido,precoAceite);
        this.areaImplantacao = areaImplantacao;
        this.areaTotalCoberta = areaTotalCoberta;
        this.areaTerrenoEnv = areaTerrenoEnv;
        this.nrQuartos = nrQuartos;
        this.tipo = tipo;
    }
    
    
    /**
     * Moradia Constructor
     *
     */
    public Moradia(){
        super();
        tipo = "";
        areaImplantacao = 0;
        areaTotalCoberta = 0;
        areaTerrenoEnv=0;
        nrQuartos = 0;
    }
    
     /**
      * Moradia Constructor
      *
      * @param o A parameter
      */
     public Moradia (Moradia o){
        super(o);
        tipo = o.getTipo();
        areaImplantacao = o.getAreaImplantacao();
        areaTotalCoberta = o.getAreaTotalCoberta();
        areaTerrenoEnv =o.getAreaTerrenoEnv();
        nrQuartos = o.getNrQuartos();
       
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
    
    // getters e setters
    public String getTipo() { return tipo; }
    public int getAreaImplantacao() { return areaImplantacao; }
    public int getAreaTotalCoberta() { return areaTotalCoberta;}
    public int getAreaTerrenoEnv() { return areaTerrenoEnv; }
    public int getNrQuartos() { return nrQuartos; }
    
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setArea(int a) { areaImplantacao = a; }
    public void setAreaTotalCoberta(int a) { areaTotalCoberta = a;}
    public void setAreaTerreno(int a) { areaTerrenoEnv = a; }
    public void setNrQuartos(int nrQuartos) { this.nrQuartos = nrQuartos; }
    
    /*
        equals clone e toString
    */
    
    @Override
    public boolean equals( Object o )
    {
        if (this== o) return true;
        if (this== null || o.getClass()!=this.getClass()) return false;
     
        Moradia moradia = (Moradia) o;
     
        return (super.equals(moradia)
                && moradia.getTipo().equals(tipo) 
                && moradia.getAreaImplantacao()==areaImplantacao
                && moradia.getAreaTotalCoberta()==areaTotalCoberta
                && moradia.getAreaTerrenoEnv()==areaTerrenoEnv
                && moradia.getNrQuartos()==nrQuartos);
    }
    
    @Override
    public Moradia clone(){
        return new Moradia(this);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Moradia: ");
        str.append(super.toString());
        str.append("\nTipo: ").append(tipo);
        str.append("\nArea implantaçao: ").append(areaImplantacao);
        str.append("\nArea Total coberta: ").append(areaTotalCoberta);
        str.append("\nArea Terreno envolvente: ").append(areaTerrenoEnv);
        str.append("\nNumero de Quartos: ").append(nrQuartos);
        return str.toString();
    }

    public int hashCode()
    {
        int hash = super.hashCode();

        hash = 31*hash + tipo.hashCode();
        hash = 31*hash + areaImplantacao;
        hash = 31*hash + areaTotalCoberta;
        hash = 31*hash + areaTerrenoEnv;
        hash = 31*hash + nrQuartos;

        return hash;
    }
}
