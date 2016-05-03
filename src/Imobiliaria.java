package src;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Iterator;

public class Imobiliaria
{
    private Set<Imovel> imoveis;
    private Set<Comprador> compradores;
    private Set<Vendedor> vendedores;
    private String emailLogged; /*Talvez mudar para utilizador, mas 
								só iamos ter as infos passadas no login ou seja o email e a pass, 
								logo acho que serve assim*/
	
	public Comprador getComprador(String email)
	{
		Iterator<Comprador> it = compradores.iterator();
		Comprador c = null;
		boolean found = false;

		while(it.hasNext() && !found)
		{
			c = it.next();
			if(c.getEmail().equals(email))
				found = true;
		}

		if(found)
			return c.clone();
		else
			return null;
	}

	public Vendedor getVendedor(String email)
	{
		Iterator<Vendedor> it = vendedores.iterator();
		Vendedor v = null;
		boolean found = false;

		while(it.hasNext() && !found)
		{
			v = it.next();
			if(v.getEmail().equals(email))
				found = true;
		}

		if(found)
			return v.clone();
		else
			return null;
	}
    
    public boolean containsIt(String k)
    {
        Iterator<Imovel> it = imoveis.iterator();
        boolean found = false;

        while(it.hasNext() && !found)
        {
            if(it.next().getId().equals(k))
                found = true;
        }

        return found;
    }

	/*
	 * VENDEDORES (VERIFICACOES AINDA SEM TRY{}CATCH)
	 */
	/*para quando um vendedor esta loggado, vai acrescentar
	  o imovel ao seu set de imoveis, e temos de acrescentar
	  a este set tambem */
	public void registaImovel(Imovel im)
	{
		Vendedor v = getVendedor(emailLogged);
		if(v == null) return;

		imoveis.add(im.clone());
		v.registaImovel(im); /* a Vendedor já faz clone */
	}

	public List<Consulta> getConsultas()
	{
		Vendedor v = getVendedor(emailLogged);
		if(v == null) return null;

		return v.getConsultas(); 
	}

	public void setEstado(String idImovel, String estado) {}

	public Set<String> getTopImoveis(int n) {return null;}

	/*
	 * COMPRADORES (VERIFICACOES AINDA SEM TRY{}CATCH)
	 */

	public void setFavorito(String idImovel)
    {
		if(containsIt(idImovel))
		{
			Comprador c = getComprador(emailLogged);
			
			if(c != null)
				c.setFavorito(idImovel);
		}
    }

	public TreeSet<Imovel> getFavoritos()
    {
		Comprador c = getComprador(emailLogged);
        if(c == null) return null;

		Set<String> ids = c.getFavoritos();

        return imoveis.stream().filter(i -> ids.contains(i.getId()))
                                .map(Imovel::clone)
                                .collect(Collectors.toCollection(TreeSet::new));
    }
	
	/*
	 * TODOS OS UTILIZADORES
	 */

	public List<Imovel> getImovel(String classe, int preco)
	{
		return imoveis.stream().filter(i -> (i.getPrecoPedido() <= preco) && (i.getClass().getName().equals(classe)))
								.map(Imovel::clone)
								.collect(Collectors.toList());
	}

	public List<Habitavel> getHabitavel(int preco)
	{
		return imoveis.stream().filter(i -> (i.getPrecoPedido() <= preco) && (i instanceof Habitavel))
		              .map(i -> {return (Habitavel) i;})
					  .collect(Collectors.toList());
	}

    public static void initApp() {}
    
    public void registarUtilizador(Utilizador utilizador) {}
    
    public void iniciaSessao(String email, String password) {}
    
    public void fechaSessao() {}
}
