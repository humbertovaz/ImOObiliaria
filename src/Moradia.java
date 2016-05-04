package src;
/**
* Uma moradia representa um imóvel para habitação familiar. Estas possuem diversas características relevantes, devendo ser consideradas, no mínimo:
* • o tipo (isolada, geminada, banda, gaveto) • a área de implantação
* • a área total coberta
* • a área do terreno envolvente
* • o número de quartos e de WCs • o número da porta
*/
public class Moradia extends Imovel implements Habitavel {
    private int tipo; // 0 -isolada, 1- geminada, 2- banda, 3 - gaveto
    private int area;
    private int areaTerreno;
    private int nrQuartos;

    public Moradia(String id, String rua, String estado, int precoPedido, int precoAceite, int tipo, int area, int areaTerreno, int nrQuartos) {
        super(id, rua, estado, precoPedido,precoAceite);
        this.tipo = tipo;
        this.area = area;
        this.areaTerreno = areaTerreno;
        this.nrQuartos = nrQuartos;
    }
    
    public Moradia(){
        super();
        this.tipo = 0;
        this.area = 0;
        this.areaTerreno=0;
        this.nrQuartos = 0;
    }
    
     public Moradia (Moradia o){
        super(o);
        this.tipo = o.getTipo();
        this.area = o.getArea();
        this.areaTerreno=o.getAreaTerreno();
        this.nrQuartos = o.getNrQuartos();
       
    }
    
    
    // getters e setters
    public int getTipo() { return tipo; }
    public int getArea() { return area; }
    public int getAreaTerreno() { return areaTerreno; }
    public int getNrQuartos() { return nrQuartos; }
    
	public void setTipo(int tipo) { this.tipo = tipo; }
    public void setArea(int area) { this.area = area; }
    public void setAreaTerreno(int areaTerreno) { this.areaTerreno = areaTerreno; }
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
             	&& moradia.getTipo()==this.getTipo() 
             	&& moradia.getArea()==this.getArea() 
             	&& moradia.getAreaTerreno()==this.getAreaTerreno() 
             	&& moradia.getNrQuartos()==this.getNrQuartos());
    }
    
	@Override
    public Moradia clone(){
        return new Moradia(this);
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Moradia ");
        str.append("tipo= ").append(tipo);
        str.append("\narea= ").append(area);
        str.append("\nareaTerreno= ").append(areaTerreno);
        str.append("\nnrQuartos= ").append(nrQuartos);
        return str.toString();
    }
}
