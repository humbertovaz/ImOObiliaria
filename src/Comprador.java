package src;

import java.util.GregorianCalendar;
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

	public Comprador(String email, String nome, String password, String morada, GregorianCalendar dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		favoritos = new TreeSet<>();
	}

	public Comprador(Comprador c)
	{
		super(c.getEmail(), c.getNome(), c.getPassword(), c.getMorada(), c.getDataNascimento());
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
/*
	Teremos que incluir qualquer coisa para podermos ir buscar os imoveis
	correspondentes aos id's do nosso set

	public TreeSet<Imovel> getFavoritos()
	{
		TreeSet<String> nova = new TreeSet<>();
		Iterator<String> it = favoritos.iterator();
		while(it.hasNext())
			nova.add(it.next());

		return nova;
	}
*/
	
	public TreeSet<String> getFavoritos()
	{
		TreeSet<String> nova = new TreeSet<>(favoritos);
		return nova; /*Não precisamos de fazer deep copy porque strings são imutáveis*/
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
