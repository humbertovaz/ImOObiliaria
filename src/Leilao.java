package src;

import java.io.Serializable;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


// Considerar horas como minutos e minutos como segundos

public class Leilao implements Serializable
{
    boolean terminado;
    private long finalLeilao;
    private String leiloeiro;
    private String paraVenda;
    private double precoBase;
    private Map<Tuplo<Licitador, Long>, Double> licitadores; // (id,min,proxLicitacao) -> valorLicitaçao
     
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

    public void iniciaLeilao(Imovel im, int horas) throws InterruptedException
    {
        /*Horas -> Minutos*/
        terminado = false;
        paraVenda = im.getId();
        precoBase = im.getPrecoAceite();
        long time = System.currentTimeMillis();
        finalLeilao = time + (horas * 60 * 1000);
        
        for(Tuplo<Licitador, Long> t: licitadores.keySet())
        {
            t.setSnd(time);
            licitadores.put(t, new Double(precoBase));
        }
        
        simulaLeilao();
    }
    
    public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos)
    {
        Licitador l = new Licitador(idComprador, limite, incrementos, minutos);
        Tuplo<Licitador, Long> tuplo = new Tuplo<>(l, null);
        licitadores.put(tuplo, null);
    }
    
    public void simulaLeilao() throws InterruptedException
    {
        while(System.currentTimeMillis() <= finalLeilao)
        {
            for(Tuplo<Licitador, Long> t: licitadores.keySet())
            {
                Licitador l = t.fst();
                long next = t.snd();
                double lastLicit = licitadores.get(t).doubleValue();
                if(next <= System.currentTimeMillis())
                {
                    if(lastLicit + l.getIncrementos() <= l.getLimite())
                    {
                        long nextLicit = System.currentTimeMillis() + (long)(l.getMinutos()*1000);
                        t.setSnd(new Long(nextLicit));
                        licitadores.put(t, new Double(lastLicit + l.getIncrementos()));
                    }
                }
            }
            Thread.sleep(500);
        }
    }
    
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
    
}
