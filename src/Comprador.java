<<<<<<< HEAD
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
    
=======
package src;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class Comprador extends Actor{

	/*Set<Imovel> favoritos;*/
	Set<String> favoritos;

	public Comprador()
	{
		super();
		favoritos = new TreeSet<>();
	}
	
	public Comprador(String email, String nome, String password, String morada, String dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		favoritos = new TreeSet<>();
	}

	public Comprador(Comprador c)
	{
		super(c.getEmail(), c.getNome(), c.getPassword(), c.getMorada(), c.getDataNasc());
		this.favoritos = c.getFavoritos();
	}

	public void setFavorito(String idImovel)
	{
		favoritos.add(idImovel);
	}

	public void setFavoritos(TreeSet<String> favs)
	{
		favoritos.clear();
		favs.forEach(i -> this.favoritos.add(i));
	}

	public TreeSet<String> getFavoritos()
	{
		TreeSet<String> nova = new TreeSet<>();
		Iterator<String> it = favoritos.iterator();
		while(it.hasNext())
			nova.add(it.next());

		return nova;
	}

	public Comprador clone()
	{
		return new Comprador(this);
	}

	public boolean equals(Object o)
	{
		boolean res = this.equals(o);
		/*Ver se é preciso verificar se os TreeSets são os mesmos*/
		return res;
	}

	public String toString()
	{
		String s = this.toString();
		/*ver se é preciso passar o treeSet para string também*/
		return s;
	}

>>>>>>> origin/master
}
