import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ImOObiliaria;

/**
 *
 * @author humbertovaz
 */
public class Comprador extends Actor{
    private ArrayList <Imovel> imoveisFavoritos;
    
    /*
    Construtores
    */
    public Comprador (){
        super();
        this.imoveisFavoritos=new ArrayList<Imovel>();
    }
    public Comprador(ArrayList<Imovel> imoveisFavoritos) {
        super();
        this.imoveisFavoritos = imoveisFavoritos;
    }
    public Comprador (Comprador c){
        super(c);
        this.imoveisFavoritos= new ArrayList<Imovel> ();
        
        for(Imovel i : c.getImoveisFavoritos()){
            this.imoveisFavoritos.add(i);
        }
    }
    
    
    
    
    /*
    Getters e Setters
    */
    public ArrayList<Imovel> getImoveisFavoritos() {
        return imoveisFavoritos;
    }
    public void setImoveisFavoritos(ArrayList <Imovel> imoveisFavoritos) {
        this.imoveisFavoritos = imoveisFavoritos;
    }

    
    
    /*
    Equals clone e toString
    */
    public boolean equals(Comprador c ){
        if (this==c) return true;
        if (c==null || c.getClass()!=this.getClass()) return false;
        
        return (c.getEmail().equals(this.getEmail()) 
                && c.getNome().equals(this.getNome()) 
                && c.getPassword().equals(this.getPassword()) 
                && c.getMorada().equals(this.getMorada())
                && c.getDataNascimento().equals(this.getDataNascimento())
                && c.getImoveisFavoritos().equals(this.getImoveisFavoritos())); 
                
    }
    public Comprador clone(){
        return new Comprador(this);
    }
    
    public String toString(){
    StringBuilder sb= new StringBuilder();
       sb.append("Nome: ").append(this.getNome()).append("\n");
       sb.append("Email: ").append(this.getEmail()).append("\n");
       sb.append("Morada: ").append(this.getMorada()).append("\n");
       sb.append("Data de Nascimento: ").append(this.getDataNascimento()).append("\n");
       sb.append("Password: ").append(this.getPassword()).append("\n");
       
       return sb.toString();
    }
    


}
