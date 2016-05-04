package src;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;


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
public class Vendedor extends Utilizador{
   	private static String[] estados = {"emVenda", "reservado", "vendido"}; // por enquanto fica aqui
	private Set<Imovel> imoveisEmVenda;
    private List<Imovel> imoveisVendidos;
    private List<Consulta> consultas;

    /*
        Construtores
    */

    public Vendedor()
	{
		super();
		consultas = new ArrayList<>();
		imoveisEmVenda = new TreeSet<>();
	}

	public Vendedor(String email, String nome, String password, String morada, GregorianCalendar dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		consultas = new ArrayList<>();
		imoveisEmVenda = new TreeSet<>();
	}

	public Vendedor(Vendedor v)
	{
		super(v);
		consultas = v.getConsultas();
		imoveisEmVenda = v.getImoveis();
	}

	public List<Consulta> getConsultas()
	{
		 return consultas.stream().skip(Math.max(0, consultas.size()-10)).map(c -> c.clone()).collect(Collectors.toList());
	}
	
	public TreeSet<Imovel> getImoveis()
	{
		return imoveisEmVenda.stream().map(i -> i.clone()).collect(Collectors.toCollection(TreeSet::new));
	}

	public void registaImovel(Imovel im)
	{
		imoveisEmVenda.add(im.clone());
	}

	public void setEstado(String idImovel, String estado)
	{
		Iterator<Imovel> it = imoveisEmVenda.iterator();
		Imovel i = null;
		boolean found = false;
		boolean vendido = estado.equals(estados[2]);

		while(it.hasNext() && !found)
		{
			i = it.next();
			if(i.getId().equals(idImovel))
			{
				i.setEstado(estado);
				if(vendido)
				{
					imoveisVendidos.add(i);
					it.remove();
				}
				found = true;
			}
		}
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
		String s = super.toString();
		/* TODO */
		return s;
	}
}
