package src;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import java.io.Serializable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * O vendedor é a entidade responsável pela gestão dos anúncios de imóveis para
* venda. O comprador deverá, assim, conter a seguinte informação adicional:
*  Portfólio de imóveis em venda;
*  Histórico de imóveis vendidos;
 */
public class Vendedor extends Utilizador implements Serializable{
    private Set<String> imoveisEmVenda; //id's dos imoveis à venda
    private Set<String> imoveisVendidos; //id's dos imoveis vendidos

    /*
        Construtores
    */

    public Vendedor()
    {
        super();
        imoveisEmVenda = new TreeSet<>();
        imoveisVendidos = new TreeSet<>();
    }

    public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNasc)
    {
        super(email, nome, password, morada, dataNasc);
        imoveisEmVenda = new TreeSet<>();
        imoveisVendidos = new TreeSet<>();
    }

    public Vendedor(Vendedor v)
    {
        super(v);
        imoveisEmVenda = v.getImoveisEmVenda();
        imoveisVendidos = v.getImoveisVendidos();
    }

   /* public List<Consulta> getConsultas()
    {
         return consultas.stream().skip(Math.max(0, consultas.size()-10)).map(c -> c.clone()).collect(Collectors.toList());
    }*/
    
    public TreeSet<String> getImoveisEmVenda()
    {
        return new TreeSet<>(imoveisEmVenda);
    }
    
    public TreeSet<String> getImoveisVendidos()
    {
        return new TreeSet<>(imoveisVendidos);
    }
    
    public void setImoveisEmVenda(TreeSet<String> im)
    {
        imoveisEmVenda = new TreeSet<>(im);
    }
    
    public void setImoveisVendidos(TreeSet<String> im)
    {
        imoveisVendidos = new TreeSet<>(im);
    }

    public void registaImovel(String id)
    {
        imoveisEmVenda.add(id);
    }
    
    public boolean setVendido(String idImovel)
    {
        boolean success = imoveisEmVenda.remove(idImovel);
        
        if(success)
            imoveisVendidos.add(idImovel);
        
        return success;
    }
    
    public boolean equals(Object o)
    {
        return this.equals(o);
    }

    public Vendedor clone()
    {
        return new Vendedor(this);
    }
    
    public String toString()
    {
		StringBuilder str = new StringBuilder("\n==========Vendedor==========\n");
		str.append(super.toString());
		str.append("\n");
		str.append("\nImoveis à venda (id):\n");
		for(String id: imoveisEmVenda)
			str.append(id+"\n");
		str.append("\nImoveis vendidos (id):\n");
		for(String id: imoveisVendidos)
			str.append(id+"\n");
		str.append("\n============================\n");
		return str.toString();
	}
	
	public int hashCode()
	{
	    int hash = 7;
	    
	    hash = 31*hash + imoveisEmVenda.hashCode();
	    hash = 31*hash + imoveisVendidos.hashCode();
	    
	    return hash;
	}
}
