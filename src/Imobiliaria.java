package src;

/**
 * Classe Imobiliaria.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;

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
import java.util.Collection;

import java.io.Serializable;

public class Imobiliaria implements Serializable
{
    private Map<String, Utilizador> utilizadores;
    private Map<String, Imovel> imoveis;
    private Utilizador logged;
    private Leilao leilao;

    /** Construtor vazio */
    public Imobiliaria()
    {
        utilizadores = new HashMap<>();
        imoveis = new HashMap<>();
        logged = null;
    }

    /**Funçao que retorna o email do utilizador loggado no sistema.*/
    public String loggedEmail() throws SemAutorizacaoException
    {
        if(logged == null)
            throw new SemAutorizacaoException("Nao esta loggado");
        
        return logged.getEmail();
    }
    
    /*
     * VENDEDORES
     */

    /**Funçao que retorna o Imovel correspondente a um id
      *@param id String id de um possivel imovel
      *@return Imovel correspondente a id
      */
    public Imovel getImovelFromId(String id)
    {
        return imoveis.get(id);
    }
    
    /**
     * Funçao que possibilita a um vendedor criar um leilao
     * @trows SemAutorizacaoException
     */
    public void criaLeilao(Imovel im) throws SemAutorizacaoException, ImovelInexistenteException
    {
        if(logged == null || !(logged instanceof Vendedor))
            throw new SemAutorizacaoException("Nao tem autorizacao para criar um leilao!");
        
        Vendedor v = (Vendedor) logged;
        
        if(im == null)
            throw new ImovelInexistenteException("Imovel inexistente!");
        
        if(!(imoveis.containsKey(im.getId())))
            throw new ImovelInexistenteException("Imovel inexistente!");
       
        if(!(v.emVenda(im.getId())))
            throw new ImovelInexistenteException("Imovel nao e seu para vender!");
       
        if((im.estaReservado()) || (im.foiVendido()))
            throw new SemAutorizacaoException("Nao pode leiloar este imovel, verifique o seu estado!");
            
        leilao = new Leilao(logged.getEmail(), im);
    }
    
    /**
     * Funçao que inicia/arranca o leilao criado pelo vendedor loggado no sistema
     * @throws ImovelInexistenteException, SemAutorizacaoException, InterruptedException, IOException, LeilaoSemLicitadoresException, LeilaoInexistenteException
     */
    public void iniciaLeilao(int horas, OutputStream arg) throws ImovelInexistenteException, SemAutorizacaoException, InterruptedException, IOException, LeilaoSemLicitadoresException, LeilaoInexistenteException
    {
        if(leilao == null)
            throw new LeilaoInexistenteException("Ainda nao foi criado um leilao!");
        
        if(logged == null || !(logged instanceof Vendedor))
           throw new SemAutorizacaoException("Nao tem autorizaçao para iniciar um Leilao");
        
        Vendedor v = (Vendedor) logged;
           
        if(!(imoveis.containsKey(leilao.getParaVenda())) || !(v.emVenda(leilao.getParaVenda())))
            throw new ImovelInexistenteException("Imovel inexistente ou nao e seu para vender!");
        
        if(!(logged.getEmail().equals(leilao.getLeiloeiro())))
           throw new SemAutorizacaoException("Nao e o seu leilao");
           
        leilao.iniciaLeilao(horas);
        leilao.simulaLeilao(arg);
    }
    
    public String imovelEmLeilao()
    {
        return leilao.getParaVenda();
    }
    
    public String getInfoLeilao() throws LeilaoInexistenteException
    {
        if(leilao == null)
            throw new LeilaoInexistenteException("Nao existem leiloes!");
        return leilao.infoLeilao();
    }
    
    public void apagaLeilao() { leilao = null; }
    
    /**
     * Funçao que permite ao comprador loggado no sistema entrar no leilao criado, caso houver algum.
     * @throws LeilaoTerminadoException, SemAutorizacaoException
     */
    public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos) throws LeilaoTerminadoException, SemAutorizacaoException
    {
        if(logged==null || !(logged instanceof Comprador)) throw new SemAutorizacaoException("Nao pode entrar no leilao");
        
        if(leilao.getTerminado()) throw new LeilaoTerminadoException("O leilao ja terminou");
   
        leilao.adicionaComprador(idComprador, limite, incrementos, minutos);
    }

    /**
     * Funçao que permite ao vendedor encerrar o leilao.
     */
    public Comprador encerraLeilao()
    {
        return (Comprador) utilizadores.get(leilao.encerraLeilao());
    }
    
    /**
     * registaImovel e uma funçao dos vendedores e regista um Imovel no sistema
     * @throws SemAutorizacaoException, ImovelExisteException
     */
    public void registaImovel(Imovel im) throws SemAutorizacaoException, ImovelExisteException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem registar imoveis");
        
        if(imoveis.containsKey(im.getId()))
            throw new ImovelExisteException("Imovel ja se encontra na base de dados");

        imoveis.put(im.getId(), im); //.clone()
        Vendedor v = (Vendedor) logged;
        v.registaImovel(im.getId());
    }

    /**
     * getConsultas e uma funçao dos vendedores e filtra as 10 ultimas consultas feitas aos imoveis que um vendedor tem para venda
     * @throws SemAutorizacaoException
     * @return Lista de Consulta, com as 10 ultimas consultas feitas aos imoveis em venda do vendedor
     */
    public List<Consulta> getConsultas() throws SemAutorizacaoException
    {
        final int CONSULTAS = 10;
        
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores tem acesso as consultas");
        
        Vendedor v = (Vendedor) logged;
        Set<String> emVenda = v.getImoveisEmVenda();
        
        List<Consulta> consultas = new ArrayList<>();
        
        for(String id: emVenda)
        {
            Imovel i = imoveis.get(id);
            if(i.getEstado().equals("venda"))
                consultas.addAll(i.getConsultas());
        }
        
        Collections.sort(consultas);
        
        int to = consultas.size();
        
        int from = to >= CONSULTAS ? to-CONSULTAS : 0;
        
        consultas = consultas.subList(from, to);
        return consultas;
    }

    /**
     * setEstado e uma funçao dos vendedores e permite-lhes modificar o estado de um qualquer imovel que tenham a venda
     * @trows SemAutorizacaoException, ImovelInexistenteException, EstadoInvalidoException
     */
    public void setEstado(String idImovel, String estado) throws SemAutorizacaoException, ImovelInexistenteException, EstadoInvalidoException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem mudar o estado de um imovel");
        
        if(!(imoveis.containsKey(idImovel)))
            throw new ImovelInexistenteException("Imovel com id: "+idImovel+" nao existe");

        if(!(Imovel.validaEstado(estado)))
            throw new EstadoInvalidoException("Estado invalido!");

        Vendedor v = (Vendedor) logged;

        if(!(v.emVenda(idImovel)) && !(v.vendido(idImovel)))
            throw new SemAutorizacaoException("Imovel com id: "+idImovel+" nao e seu!");
        
        imoveis.get(idImovel).setEstado(estado);
        
        if(Imovel.vendido(estado))
            v.setVendido(idImovel);
    }

    /**
     * cria um conjunto com os codigos dos imoveis mais consultados (com mais de n consultas)
     * @param n numero de consultas
     * @return Conjunto com os codigos dos imoveis com mais de n consultas
     * @throws SemAutorizacaoException
     */
    public Set<String> getTopImoveis(int n) throws SemAutorizacaoException
    {
        if(!(logged instanceof Vendedor))
            throw new SemAutorizacaoException("So vendedores podem ver o top");

        Vendedor v = (Vendedor) logged;
        Set<String> ids = v.getImoveisEmVenda();
        ids.addAll(v.getImoveisVendidos());

        /*Ordenar por numero de consultas (Maior para o menor)*/
        return ids.stream().filter(i -> imoveis.get(i).getNumConsultas() >= n).collect(Collectors.toSet());
    }

    /*
     * COMPRADORES
     */

    /**
     * marca um imovel como favorito
     * @param idImovel id do imovel a marcar como favorito
     * @throws SemAutorizacaoException, ImovelInexistenteException
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

    /**
     * cria um conjunto com os imoveis favoritos de um comprador ordenados por preço
     * @throws SemAutorizacaoException
     * @return Conjunto dos imoveis favoritos do comprador loggado ordenados por preço
     */
    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException
    {
        if(!(logged instanceof Comprador))
            throw new SemAutorizacaoException("So compradores podem realizar esta acçao");

        Comprador c = (Comprador) logged;
        Set<String> ids = c.getFavoritos();
        
        TreeSet<Imovel> ts = new TreeSet<>(new Comparator<Imovel>(){
            public int compare(Imovel i1, Imovel i2)
            {
                return i1.getId().compareTo(i2.getId());
            }
        });
        
        for(String id: ids) 
        {
            Imovel im = imoveis.get(id);
            im.registaConsulta(logged.getEmail(), new GregorianCalendar());
            ts.add(im);
        }
        //Ordem natural dos Imoveis e por preço
       /* TreeSet<Imovel> ts = ids.stream().map(i -> {Imovel imv = imoveis.get(i); imv.registaConsulta(logged.getEmail(), new GregorianCalendar()); return imv; })
                           .collect(Collectors.toCollection(TreeSet::new));*/
      
       return ts;
    }
    
    /*
     * TODOS OS UTILIZADORES
     */

    /**
     * constroi uma lista com os imoveis de um certo tipo ate um dado preço
     * @param classe Tipo do imovel
     * @param preco Preço de referencia
     * @return Lista com os imoveis do tipo classe ate um preço preco
     */
    public List<Imovel> getImovel(String classe, int preco)
    {
        return imoveis.values().stream().filter(i -> (i.getPrecoPedido() <= preco) && (i.getClass().getSimpleName().equals(classe)))
                                        .map(i -> { String email = (logged != null) ? logged.getEmail() : "nao registado";
                                                    i.registaConsulta(email, new GregorianCalendar()); 
                                                    return i.clone();
                                                  })
                                        .collect(Collectors.toList());
    }

    /**
     * constroi uma lista com todos os imoveis habitaveis do sistema ate um certo preço
     * @param preco Preço de referencia
     * @return Lista de todos os imoveis habitaveis ate um preço preco
     */
    public List<Habitavel> getHabitavel(int preco)
    {

        return imoveis.values().stream().filter(i -> (i.getPrecoPedido() <= preco) && (i instanceof Habitavel))
                           .map(i -> { String email = (logged != null) ? logged.getEmail() : "nao registado";
                                       i.registaConsulta(email, new GregorianCalendar()); 
                                       return (Habitavel) i.clone();
                                     })
                           .collect(Collectors.toList());
    }

    /**
     * Cria um conjunto com todos os Vendedores do sistema
     * @return Conjunto de todos os vendedores do sistema
     */
    public Set<Vendedor> getVendedores()
    {   
        return utilizadores.values().stream()
                                    .filter(v -> (v instanceof Vendedor))
                                    .map(v -> {return (Vendedor) v;})
                                    .collect(Collectors.toSet());
    }

    /**
     * constroi um mapeamento entre todos os imoveis do sistema e os respectivos vendedores
     * @return Map de Imovel para Vendedor, de todos os imoveis do sistema
     */
    public Map<Imovel, Vendedor> getMapeamentoImoveis()
    {
         Map<Imovel, Vendedor> mapIm = new HashMap<>(imoveis.values().size());
         Set<Vendedor> sVend = getVendedores();
         Iterator<Vendedor> it = sVend.iterator();
         
         while(it.hasNext())
         {
             Vendedor v = it.next();
             Set<String> ids = v.getImoveisEmVenda();
             ids.addAll(v.getImoveisVendidos());

             for(String id: ids)
             {
                 Imovel im = imoveis.get(id);
                 String email = logged != null ? logged.getEmail() : "nao registado";
                 im.registaConsulta(email, new GregorianCalendar());
                 mapIm.put(im, v);
             }
         }

        return mapIm;
    }

    /**
     * Inicia a aplicaçao lendo o estado anterior do ficheiro Imobiliaria.ser
     * @return Imobiliaria lida do ficheiro
     */
    public static Imobiliaria initApp() throws IOException, ClassNotFoundException, ClassCastException
    {
       return leObj("Imobiliaria.ser");
    }
    
    /**
     * Regista um utilizador no sistema, ou comprador ou vendedor
     * @throws UtilizadorExistenteException
     */
    public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException
    {
        if(utilizadores.containsKey(utilizador.getEmail()))
            throw new UtilizadorExistenteException("Utilizador ja existente");

        utilizadores.put(utilizador.getEmail(), utilizador);/*.clone()*/
    }

    /**
     * inicia sessao no sistema
     * @param email Email de um possivel utilizador
     * @param password Password de um possivel utilizador
     * @throws SemAutorizacaoException, UtilizadorInexistenteException
     */
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException
    {/*
        if(logged != null)
            throw new SessaoJaIniciadaException("Ja tem sessao iniciada");
       */ 
        Utilizador u;
        if((u = utilizadores.get(email)) == null)
            throw new SemAutorizacaoException("Email incorrecto!");
        else
        {
            if(u.verificaPassword(password))
                logged = u;
            else
                throw new SemAutorizacaoException("Password incorrecta!");
        }
    }

    /**
     * Fecha sessao no sistema
     */
    public void fechaSessao() 
    {
        logged = null;
    }

    /**
     * grava o estado atual do sistema para um ficheiro
     * @param fich Fichero para onde queremos guardar
     * @throws IOException
     */
    public void gravaObj(String fich) throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    /**
     * Le o estado de um ficheiro
     * @param fich Ficheiro de onde queremos ler o estado
     * @return Imobiliaria com o estado lido do ficheiro
     * @throws IOException, ClassNotFoundException
     */
    public static Imobiliaria leObj(String fich) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));

        Imobiliaria im = (Imobiliaria) ois.readObject();
        ois.close();
        return im;
    }

    /**
     * Escreve um log para um ficheiro
     * @param f Ficheiro onde queremos escrever o log
     * @param ap
     * @throws IOException
     */
    public void log(String f, boolean ap) throws IOException
    {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n------------- LOG ----- LOG ----- LOG -------------\n");
        fw.write(this.toString());
        fw.write("\n------------- LOG ----- LOG ----- LOG -------------\n");
        fw.flush();
        fw.close();
    }
    
    /**
     * Passa uma instancia desta classe para String
     * @return String da instancia
     */
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
    
    /**
     * verifica se o utilizador loggado e vendedor
     * @return boolean com o resultado
     */
    public boolean isVendedorLogged()
    {
        return logged != null ? logged instanceof Vendedor : false;
    }
    
    /**
     * verifica se o utilizador loggado e comprador
     * @return boolean com o resultado
     */
    public boolean isCompradorLogged()
    {
        return logged != null ? logged instanceof Comprador : false;
    }
}
