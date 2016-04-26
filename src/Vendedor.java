<<<<<<< HEAD
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ImOObiliaria;

/**
 *
 * O vendedor é a entidade responsável pela gestão dos anúncios de imóveis para
venda. O comprador deverá, assim, conter a seguinte informação adicional:
 Portfólio de imóveis em venda;
 Histórico de imóveis vendidos;
 */
public class Vendedor extends Actor{
    ArrayList <Imovel> imoveisEmVenda;
    ArrayList <Imovel> imoveisVendidos;
    
    /*
        Construtores
    */

    public Vendedor (){
        super();
        this.imoveisEmVenda=new ArrayList<Imovel>();
        this.imoveisVendidos=new ArrayList<Imovel>();
    }
    public Vendedor(ArrayList <Imovel> imoveisEmVenda, ArrayList<Imovel> imoveisVendidos) {
        super();
        this.imoveisEmVenda= new ArrayList <Imovel>();
        for (Imovel i : imoveisEmVenda){
            this.imoveisEmVenda.add(i);
        }
        this.imoveisVendidos= new ArrayList <Imovel>();
        for (Imovel i : imoveisVendidos){
            this.imoveisVendidos.add(i);
            
        }
    }
    public Vendedor(Vendedor v){
        super(v);
        this.imoveisEmVenda=v.getImoveisEmVenda();
        this.imoveisVendidos=v.getImoveisVendidos();
    }
    
    /*
        getters e setters
    */

    public ArrayList<Imovel> getImoveisEmVenda() {
        return imoveisEmVenda;
    }

    public ArrayList<Imovel> getImoveisVendidos() {
        return imoveisVendidos;
    }

    public void setImoveisEmVenda(ArrayList<Imovel> imoveisEmVenda) {
        this.imoveisEmVenda = imoveisEmVenda;
    }

    public void setImoveisVendidos(ArrayList<Imovel> imoveisVendidos) {
        this.imoveisVendidos = imoveisVendidos;
    }
    
    
    /*
        equals clone e toString
    */
    /*
     String email ;
    String nome;
    String password;
    String morada;
    GregorianCalendar dataNascimento;
    */

    @Override
    public boolean equals (Object o){
        if (this==o) return true;
        if (o==null || o.getClass()!=this.getClass()) return false;
        
        Vendedor v = (Vendedor) o;
        
        return (v.getEmail().equals(this.getEmail()) 
                && v.getNome().equals(this.getNome()) 
                && v.getPassword().equals(this.getPassword()) 
                && v.getMorada().equals(this.getMorada())
                && v.getDataNascimento().equals(this.getDataNascimento())
                && v.getImoveisEmVenda().equals(this.getImoveisEmVenda()) 
                && v.getImoveisVendidos().equals(this.getImoveisVendidos()));
    }
    
    public Vendedor clone (){
        return new Vendedor(this);
    }

    @Override
    public String toString() {
       StringBuilder sb= new StringBuilder();
       sb.append("Nome: ").append(this.getNome()).append("\n");
       sb.append("Email: ").append(this.getEmail()).append("\n");
       sb.append("Morada: ").append(this.getMorada()).append("\n");
       sb.append("Data de Nascimento: ").append(this.getDataNascimento()).append("\n");
       sb.append("Password: ").append(this.getPassword()).append("\n");
       
       return sb.toString();
       
    }
    
     
=======
package src;

import java.util.ArrayList;

public class Vendedor extends Actor{

>>>>>>> origin/master
}
