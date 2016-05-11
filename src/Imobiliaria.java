package src;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import java.io.Serializable;

public class Imobiliaria implements Serializable
{
    private Map<String, Utilizador> utilizadores;
    private Map<String, Imovel> imoveis;
    private Utilizador logged;

    public Imobiliaria()
    {
        utilizadores = new HashMap<>();
        imoveis = new HashMap<>();
        logged = null;
    }

    /*
     * VENDEDORES
     */
    /*para quando um vendedor esta loggado, vai acrescentar
      o imovel ao seu set de imoveis, e temos de acrescentar
      a este set tambem */
    public void registaImovel(Imovel im) throws SemAutorizacaoException, ImovelExisteException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem registar imoveis");
        
        if(imoveis.containsKey(im.getId()))
            throw new ImovelExisteException("Imovel ja se encontra na base de dados");

        imoveis.put(im.getId(), im.clone());
        Vendedor v = (Vendedor) logged;
        v.registaImovel(im.getId());
    }

    public List<Consulta> getConsultas() throws SemAutorizacaoException
    {
        final int CONSULTAS = 10;
        
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores tem acesso as consultas");
        
        Vendedor v = (Vendedor) logged;
        Set<String> emVenda = v.getImoveisEmVenda();
        
        /*Incluir tambem para os imoveis vendidos!?*/
        
        List<Consulta> consultas = new ArrayList<>();
        
        for(String id: emVenda)
        {
            Imovel i = imoveis.get(id);
            consultas.addAll(i.getConsultas());
        }
        
        Collections.sort(consultas);
        
        int to = consultas.size();
        
        int from = to >= CONSULTAS ? to-CONSULTAS : 0;
        
        consultas = consultas.subList(from, to);
        return consultas;
    }

    public void setEstado(String idImovel, String estado) throws SemAutorizacaoException, ImovelInexistenteException, EstadoInvalidoException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem mudar o estado de um imovel");
        
        if(!(imoveis.containsKey(idImovel)))
            throw new ImovelInexistenteException("Imovel com id: "+idImovel+" nao existe");

        if(!(Imovel.validaEstado(estado)))
            throw new EstadoInvalidoException("Estado invalido!");

        Vendedor v = (Vendedor) logged;
        
        imoveis.get(idImovel).setEstado(estado);
        
        if(Imovel.vendido(estado))
        {
            v.setVendido(idImovel);
            /*Retirar do map de imoveis !???*/
        }
        
    }

    public Set<String> getTopImoveis(int n) throws SemAutorizacaoException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem ver o top");

        Vendedor v = (Vendedor) logged;
        Set<String> ids = v.getImoveisEmVenda();

        /*Ordenar por numero de consultas (Maior para o menor)*/
        return ids.stream().filter(i -> imoveis.get(i).getNumConsultas() >= n).collect(Collectors.toSet());
    }

    /*
     * COMPRADORES
     */

    public void setFavorito(String idImovel) throws SemAutorizacaoException, ImovelInexistenteException
    {
        if(!(logged instanceof Comprador))
            throw new SemAutorizacaoException("So compradores podem realizar esta acçao");
        
        if(!(imoveis.containsKey(idImovel)))
            throw new ImovelInexistenteException("Imovel com id:"+idImovel+" nao existe");

        Comprador c = (Comprador) logged;
        c.setFavorito(idImovel);
    }

    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException
    {
        if(!(logged instanceof Comprador))
            throw new SemAutorizacaoException("So compradores podem realizar esta acçao");

        Comprador c = (Comprador) logged;
        Set<String> ids = c.getFavoritos();

        return ids.stream().filter(i -> imoveis.containsKey(i))
                           .map(i -> imoveis.get(i))
                           .collect(Collectors.toCollection(TreeSet::new));
    }
    
    /*
     * TODOS OS UTILIZADORES
     */

    public List<Imovel> getImovel(String classe, int preco)
    {
       // List<Imovel> ims = (List<String>) imoveis.values();

        return imoveis.values().stream().filter(i -> (i.getPrecoPedido() <= preco) && (i.getClass().getName().equals("src."+classe)))
                                        .map(i -> {if(logged instanceof Vendedor) 
                                                      return i.clone();
                                                   else 
                                                   {
                                                      String email = (logged != null) ? logged.getEmail() : "nao registado";
                                                      i.registaConsulta(email, new GregorianCalendar()); 
                                                      return i.clone();
                                                   }})
                                        .collect(Collectors.toList());
    }

    /*So acrescento uma consulta se for um comprador a realizar a acçao*/
    public List<Habitavel> getHabitavel(int preco)
    {

        return imoveis.values().stream().filter(i -> (i.getPrecoPedido() <= preco) && (i instanceof Habitavel))
                           .map(i -> {if(logged instanceof Vendedor) 
                                         return (Habitavel) i.clone();
                                      else 
                                      {
                                         String email = (logged != null) ? logged.getEmail() : "nao registado";
                                         i.registaConsulta(email, new GregorianCalendar()); 
                                         return (Habitavel) i.clone();
                                      }})
                           .collect(Collectors.toList());
    }

    public Set<Vendedor> getVendedores()
    {   
        return utilizadores.values().stream()
                                    .filter(v -> (v instanceof Vendedor))
                                    .map(v -> {return (Vendedor) v;})
                                    .collect(Collectors.toSet());
    }

    public Map<Imovel, Vendedor> getMapeamentoImoveis()
    {
         Map<Imovel, Vendedor> mapIm = new HashMap<>(imoveis.values().size());
         Set<Vendedor> sVend = getVendedores();
         Iterator<Vendedor> it = sVend.iterator();
         
         while(it.hasNext())
         {
             Vendedor v = it.next();
             Set<String> ids = v.getImoveisEmVenda();

             for(String id: ids)
             {
                if(logged instanceof Vendedor)
                    mapIm.put(imoveis.get(id).clone(), v);
                else
                {
                    Imovel im = imoveis.get(id);
                    String email = logged != null ? logged.getEmail() : "nao registado";
                    im.registaConsulta(email, new GregorianCalendar());
                    mapIm.put(im, v);
                }
             }
         }

        return mapIm;
    }

    public static Imobiliaria initApp() throws IOException, ClassNotFoundException, ClassCastException
    {
       return leObj("Imobiliaria.ser");
    }
    
    public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException
    {
        if(utilizadores.containsKey(utilizador.getEmail()))
            throw new UtilizadorExistenteException("Utilizador ja existente");

        utilizadores.put(utilizador.getEmail(), utilizador.clone());
    }
    
    /*Ao fazer este método apercebi-me que se calhar deviamos ter uma 
      lista de Utilizadores apenas, não sei, tenho que pensar*/
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException, UtilizadorInexistenteException
    {/*
        if(logged != null)
            throw new SessaoJaIniciadaException("Ja tem sessao iniciada");
       */ 
        Utilizador u;
        if((u = utilizadores.get(email)) == null)
            throw new UtilizadorInexistenteException("Utilizador com email:"+email+" nao existe");
        else
        {
            if(u.verificaPassword(password))
                logged = u; /*Clone??*/
            else
                throw new SemAutorizacaoException("Password incorrecta");
        }
    }

    public void fechaSessao() 
    {
        logged = null;
    }

    public void gravaObj(String fich) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public static Imobiliaria leObj(String fich) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));

        Imobiliaria im = (Imobiliaria) ois.readObject();
        ois.close();
        return im;
    }

    public void log(String f, boolean ap) throws IOException
    {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n------------- LOG ----- LOG ----- LOG -------------\n");
        fw.write(this.toString());
        fw.write("\n------------- LOG ----- LOG ----- LOG -------------\n");
        fw.flush();
        fw.close();
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        for(Utilizador u: utilizadores.values())
            str.append(u.toString());
        str.append("\n");
        for(Imovel i: imoveis.values())
            str.append(i.toString());

        return str.toString();
    }
    
    public boolean isVendedorLogged()
    {
        return logged != null ? logged instanceof Vendedor : false;
    }
    
    public boolean isCompradorLogged()
    {
        return logged != null ? logged instanceof Comprador : false;
	}
}
