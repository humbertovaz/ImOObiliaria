package src;

import java.io.Serializable;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Comparator;

import java.util.PriorityQueue;

import java.io.OutputStream;
import java.io.IOException;


// Considerar horas como minutos e minutos como segundos

public class Leilao implements Serializable
{
    boolean terminado;
    private long finalLeilao;
    private String leiloeiro;
    private String paraVenda;
    private double precoBase;
    private Map<Tuplo<Licitador, Long>, Double> licitadores;
     
    public Leilao()
    {
        terminado = false;
        leiloeiro = paraVenda = "";
        licitadores = new HashMap<>();
    }

    public Leilao(String leiloeiro)
    {
        terminado = false;
        this.leiloeiro = leiloeiro;
        licitadores = new HashMap<>();
    }
    
    public Leilao(String leiloeiro, Imovel im)
    {
        terminado = false;
        this.leiloeiro = leiloeiro;
        paraVenda = im.getId();
        precoBase = im.getPrecoAceite();
        licitadores = new HashMap<>();
    }

    public Leilao(Leilao l)
    {
        terminado = l.getTerminado();
        leiloeiro = l.getLeiloeiro();
        paraVenda = l.getParaVenda();
        licitadores = l.getLicitadores();
    }

    public boolean getTerminado() { return terminado; }
    public String getLeiloeiro() { return leiloeiro; }
    public String getParaVenda() { return paraVenda; }
    public HashMap<Tuplo<Licitador, Long>, Double> getLicitadores() { return new HashMap<>(licitadores);}

    public void setLeiloeiro(String l) { leiloeiro = l; }
    public void setParaVenda(String id) { paraVenda = id; }
    public void setLicitadores(HashMap<Tuplo<Licitador, Long>, Double> licit) 
    {
        licitadores.clear();
        licitadores = new HashMap<>(licit);
    }

    /**
     * Esta funçao inicia o leilao, determina o tempo em ms em que o leilao vai acabar e inicializa todos os valores iniciais ao precoBase
     * @param horas Duraçao do leilao
     * @throws LeilaoSemLicitadoresException
     */
    public void iniciaLeilao(int horas) throws InterruptedException, IOException, LeilaoSemLicitadoresException
    {
        long time = System.currentTimeMillis();
        finalLeilao = time + (horas * 60 * 1000);
        
        if(licitadores.isEmpty())
            throw new LeilaoSemLicitadoresException("Leilao ainda nao tem licitadores!");
        
        for(Tuplo<Licitador, Long> t: licitadores.keySet())
        {
            t.setSnd(new Long(time));
            licitadores.put(t, new Double(precoBase));
        }
    }
    
    /**
     * Funçao que adiciona um comprador ao leilao, transforma-o em licitador, insere-o num tuplo e de seguida na HashMap
     * @param idComprador EMail do comprador
     * @param limite Valor limite de que o comprador pretende licitar
     * @param incrementos Incrementos entre licitaçoes
     * @param minutos Intervalo entre licitaçoes
     */
    public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos)
    {
        Licitador l = new Licitador(idComprador, limite, incrementos, minutos);
        Tuplo<Licitador, Long> tuplo = new Tuplo<>(l, null);
        licitadores.put(tuplo, null);
    }
    
    /**
     * Simula o leilao. Enquanto nao for a hora de terminar o leilao percorremos os licitadores e vemos se esta na hora deles de licitar se estiver realizamos a nova licitaçao, ate acabar o tempo do leilao
     * @param arg OutputStream para onde queremos escrever as constantes licitaçoes que vao acontecendo no leilao
     */
    public void simulaLeilao(OutputStream arg) throws IOException, InterruptedException
    {
        double maiorLicit = precoBase;
        Licitador winner = null;
        while(System.currentTimeMillis() <= finalLeilao)
        {
            for(Tuplo<Licitador, Long> t: licitadores.keySet())
            {
                Licitador l = t.fst();
                long next = t.snd();
                double lastLicit = licitadores.get(t).doubleValue();
                if(next <= System.currentTimeMillis())
                {
                    if(maiorLicit < l.getLimite())
                    {    
                        if(l.equals(winner))
                        {
                            long nextLicit = System.currentTimeMillis() + (long)(l.getMinutos()*1000);
                            t.setSnd(new Long(nextLicit));
                            licitadores.put(t, lastLicit);
                        }
                        else
                        {
                            double newLicit;
                            if(lastLicit == maiorLicit)
                            {
                                newLicit = lastLicit + l.getIncrementos();
                            }
                            else{
                                int nmrInc = (int) Math.ceil((maiorLicit - lastLicit) / l.getIncrementos());
                                newLicit = lastLicit + ((nmrInc+1) * l.getIncrementos());
                            }
                            
                            if(newLicit > l.getLimite()) 
                                newLicit = l.getLimite(); 
                            
                            maiorLicit = newLicit;
                            winner = l;

                            long nextLicit = System.currentTimeMillis() + (long)(l.getMinutos()*1000);
                            t.setSnd(new Long(nextLicit));
                            licitadores.put(t, new Double(newLicit));
                            arg.write(licitacao(l, maiorLicit).getBytes()); arg.flush();
                        }
                    }
                }
            }
            Thread.sleep(500);
        }
    }
    
    /**
     * Funçao que encerra o leilao, percorre os licitadores para ver qual deles realizou a maior licitaçao
     * @return Email (id) do vencedor
     */
    public String encerraLeilao()
    {
        Iterator<Map.Entry<Tuplo<Licitador, Long>, Double>> it = licitadores.entrySet().iterator();
        String winner = "";
        double max = 0.0;
        
        while(it.hasNext())
        {
            Map.Entry<Tuplo<Licitador, Long>, Double> pair = (Map.Entry<Tuplo<Licitador, Long>, Double>) it.next();
            if(pair.getValue().doubleValue() > max)
            {
                max = pair.getValue().doubleValue();
                winner = pair.getKey().fst().getIdComprador();
            }
        }
        
        terminado = true;
        return winner;
    }
    
    /**
     * Uma funçao que da um bocado de informaçao sobre o leilao ao comprador antes de entrar no mesmo
     * @param informaçao sobre o leilao
     */
    public String infoLeilao()
    {
        StringBuilder str = new StringBuilder();
        str.append("\nLeiloeiro: ").append(leiloeiro);
        str.append("\nImovel em leilao: ").append(paraVenda);
        str.append("\nPreço base: ").append(precoBase);
        str.append("\nCompradores ja no leilao:\n");
        if(licitadores.isEmpty())
            str.append("Ainda nao entraram compradores no leilao!\n");
        else
        {
            for(Tuplo<Licitador, Long> t: licitadores.keySet())
                str.append(t.fst().getIdComprador()).append(";\n");
        }
        
        return str.toString();
    }
    
    private String inicio()
    {
        return "Inicio do leilao:\n";
    }
    
    private String licitacao(Licitador l, double licit)
    {
        StringBuilder str = new StringBuilder();
        str.append(l.getIdComprador()).append(" licitou ").append(licit);
        return str.toString()+"\n";
    }
    
    private String passaLicit(Licitador l)
    {
        StringBuilder str = new StringBuilder();
        str.append(l.getIdComprador()).append(" ja esta a vencer.\n");
        return str.toString();
    }
    
}
