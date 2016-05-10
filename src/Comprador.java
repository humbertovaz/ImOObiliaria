package src;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.stream.Collectors;

import java.io.Serializable;

public class Comprador extends Utilizador implements Serializable{

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
		super(c);
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
		/*Não é preciso fazer deep copy por que é um TreeSet de strings, e as strings são imutáveis*/
		return new TreeSet<>(favoritos);
	}

	public Comprador clone()
	{
		return new Comprador(this);
	}

	public boolean equals(Object o)
	{
		if(this == o) return true;
		if((o == null) || (this.getClass() != o.getClass())) return false;
		
		Comprador c = (Comprador) o;
		/*testar também o set de favoritos!?*/
		return super.equals(c);
	}

	public String toString()
	{
		StringBuilder str = new StringBuilder("\n==========Comprador==========\n");
		str.append(super.toString());
		str.append("\nImoveis Favoritos (id):\n");
		for(String id: favoritos)
			str.append(id+"\n");
		str.append("\n=============================\n");
		return str.toString();
	}
	
	public int hashCode()
	{
		int hash = super.hashCode();
		
		return hash*31 + ((favoritos == null) ? 0 : favoritos.hashCode());
	}

}
