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
 * Propõe-se a existência de dois tipos distintos de actores no sistema, que partilham a
* seguinte informação:
*  email (que identifica o utilizador);
*  nome;
*  password;
*  morada;
*  data de nascimento.
 */
public class Actor {
    String email ;
    String nome;
    String password;
    String morada;
    GregorianCalendar dataNascimento;
    
    /*
        Construtures
    */

    public Actor (){
       this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = new GregorianCalendar();
    }
    
    public Actor(String email, String nome, String password, String morada, GregorianCalendar dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    public Actor (Actor a){
        this.email=a.getEmail();
        this.nome=a.getNome();
        this.password=a.getPassword();
        this.morada=a.getMorada();
        this.dataNascimento=a.getDataNascimento();
    }
    
  

    /*
    gets e sets
     */
    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getMorada() {
        return morada;
    }

    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setDataNascimento(GregorianCalendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    

}
