/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImOObiliaria.src;

/**
 *
 * 
Uma moradia representa um imóvel para habitação familiar. Estas possuem diversas características relevantes, devendo ser consideradas, no mínimo:
• o tipo (isolada, geminada, banda, gaveto) • a área de implantação
• a área total coberta
• a área do terreno envolvente
• o número de quartos e de WCs • o número da porta

 */
public class Moradia extends Imovel {
    private int tipo; // 0 -isolada, 1- geminada, 2- banda, 3 - gaveto
    private int area;
    private int areaTerreno;
    private int nrQuartos;

    public Moradia(int tipo, int area, int areaTerreno, int nrQuartos) {
        this.tipo = tipo;
        this.area = area;
        this.areaTerreno = areaTerreno;
        this.nrQuartos = nrQuartos;
    }
    
    public Moradia(){
        super("", 0, 0);
        this.tipo = 0;
        this.area = 0;
        this.areaTerreno=0;
        this.nrQuartos = 0;
    }
    
     public Moradia (Moradia o){
        super(o.getRua(), o.getPrecoPedido(), o.getPrecoAceite());
        this.tipo = o.getTipo();
        this.area = o.getArea();
        this.areaTerreno=o.getAreaTerreno();
        this.nrQuartos = o.getNrQuartos();
       
    }
    
    
    // gets e sets
    public int getTipo() {
        return tipo;
    }

    public int getArea() {
        return area;
    }

    public int getAreaTerreno() {
        return areaTerreno;
    }

    public int getNrQuartos() {
        return nrQuartos;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setAreaTerreno(int areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public void setNrQuartos(int nrQuartos) {
        this.nrQuartos = nrQuartos;
    }

    @Override
    public String toString() {
        return "Moradia " + "tipo=" + tipo + ", area=" + area + ", areaTerreno=" + areaTerreno + ", nrQuartos=" + nrQuartos;
    }
    
    
    
}
