package src;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Comprador extends Actor{

	Set<Imovel> catalogo;
	Set<String> favoritos;

	public Comprador()
	{
		super();
		catalogo = new TreeSet<>();
		favoritos = new TreeSet<>();
	}

	public Comprador(String email, String nome, String password, String morada, GregorianCalendar dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		catalogo = new TreeSet<>();
		favoritos = new TreeSet<>();
	}

	public Comprador(Comprador c)
	{
		super(c.getEmail(), c.getNome(), c.getPassword(), c.getMorada(), c.getDataNascimento());
		this.catalogo = c.getCatalogo();
		this.favoritos = c.getFavoritosS();
	}

	public void setFavorito(String idImovel)
	{
		favoritos.add(idImovel);
	}

	public void addImovelCat(Imovel i)
	{
		catalogo.add(i.clone());
	}

	public void setFavoritos(TreeSet<String> favs)
	{
		favoritos.clear();
		favs.forEach(i -> this.favoritos.add(i));
	}

/*
	Teremos que incluir qualquer coisa para podermos ir buscar os imoveis
	correspondentes aos id's do nosso set
*/

	public TreeSet<Imovel> getFavoritos()
	{
		return catalogo.stream().filter(i -> favoritos.contains(i.getId())).collect(Collectors.toCollection(TreeSet::new));
	}

	public TreeSet<Imovel> getCatalogo()
	{
		return catalogo.stream().map(i -> i.clone()).collect(Collectors.toCollection(TreeSet::new));
	}

	public TreeSet<String> getFavoritosS()
	{
		/*Não é preciso fazer deep copy por que é um TreeSet de strings, e as strings são imutáveis*/
		return new TreeSet<>(favoritos);
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

}
