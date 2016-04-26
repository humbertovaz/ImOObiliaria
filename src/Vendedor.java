package src;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ImOObiliaria;

/**
 *
 * O vendedor é a entidade responsável pela gestão dos anúncios de imóveis para
* venda. O comprador deverá, assim, conter a seguinte informação adicional:
*  Portfólio de imóveis em venda;
*  Histórico de imóveis vendidos;
 */
public class Vendedor extends Actor{
    Set<Imovel> imoveisEmVenda;
    List<Imovel> imoveisVendidos;
    List<Consulta> consultas;

    /*
        Construtores
    */

    public Vendedor()
	{
		super();
		consultas = new LinkedList<>();
		imoveisEmVenda = new TreeSet<>();
	}

	public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		consultas = new LinkedList<>();
		imoveisEmVenda = new TreeSet<>();
	}

	public Vendedor(Vendedor v)
	{
		super(v);
		consultas = v.getConsultas();
		imoveisEmVenda = v.getImoveis();
	}

	public LinkedList<Consulta> getConsultas()
	{
		return consultas.stream().map(c -> c.clone()).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public TreeSet<Imovel> getImoveis()
	{
		return imoveisEmVenda.stream().map(i -> i.clone()).collect(Collectors.toCollection(TreeSet::new));
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
		String s = this.toString();
		/* TODO */
		return s;
	}
}
