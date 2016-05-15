package src;

import java.io.Serializable;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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

    public void iniciaLeilao(Imovel im, int horas) throws InterruptedException, IOException
    {
        /*Horas -> Minutos*/
        terminado = false;
        paraVenda = im.getId();
        precoBase = im.getPrecoAceite();
        long time = System.currentTimeMillis();
        finalLeilao = time + (horas * 60 * 1000);
        
        for(Tuplo<Licitador, Long> t: licitadores.keySet())
        {
            t.setSnd(new Long(time));
            licitadores.put(t, new Double(precoBase));
        }
        
        simulaLeilao(System.out);
    }
    
    public void adicionaComprador(String idComprador, double limite, double incrementos, double minutos)
    {
        Licitador l = new Licitador(idComprador, limite, incrementos, minutos);
        Tuplo<Licitador, Long> tuplo = new Tuplo<>(l, null);
        licitadores.put(tuplo, null);
    }
    
    public void simulaLeilao(OutputStream arg) throws IOException, InterruptedException
    {
        double maiorLicit = precoBase;
        Licitador winner = null;
        arg.write(inicio().getBytes());
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
                            arg.write(passaLicit(l).getBytes());
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
                            arg.write(licitacao(l, maiorLicit).getBytes());
                        }
                    }
                }
            }
            Thread.sleep(500);
        }
    }
    
    public void simulaLeilaoV2(Imovel im, int horas)
    {
        terminado = false;
        paraVenda = im.getId();
        precoBase = im.getPrecoAceite();
        long time = horas*60;
        Licitador winner = null;
        double maiorLicit = precoBase;
        for(Tuplo<Licitador, Long> t: licitadores.keySet())
        {
            t.setSnd(new Long((long)t.fst().getMinutos()));
            licitadores.put(t, new Double(precoBase));
        }
        
        for(int i = 0; i < time; i++)
        {
            for(Tuplo<Licitador, Long> t: licitadores.keySet())
            {
                if((int) t.snd().longValue() == i)
                {
                    if(maiorLicit < t.fst().getLimite())
                    {
                        double lastLicit = licitadores.get(t);
                        if(t.fst().equals(winner))
                        {
                            long nextLicit = (long) i + (long) t.fst().getMinutos();
                            t.setSnd(new Long(nextLicit));
                            licitadores.put(t, lastLicit);
                        }
                        else
                        {
                            double newLicit;
                            if(lastLicit == maiorLicit)
                            {
                                newLicit = lastLicit + t.fst().getIncrementos();
                            }
                            else{
                                int nmrInc = (int) Math.ceil((maiorLicit - lastLicit) / t.fst().getIncrementos());
                                newLicit = lastLicit + ((nmrInc+1) * t.fst().getIncrementos());
                            }
                            
                            if(newLicit > t.fst().getLimite()) 
                                newLicit = t.fst().getLimite(); 
                            
                            maiorLicit = newLicit;
                            winner = t.fst();
                            
                            long nextLicit = (long) i + (long) t.fst().getMinutos();
                            t.setSnd(new Long(nextLicit));
                            licitadores.put(t, newLicit);

                        }
                    }
                }
            }
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
        str.append(l.getIdComprador()).append(" ja esta a vencer.");
        return str.toString();
    }
    
}
