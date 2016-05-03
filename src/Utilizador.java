package src;

import java.util.GregorianCalendar;
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
public class Utilizador {
	
	private String email; /*Identifica o utilizador*/
	private String nome;
	private String password;
	private String morada;
    GregorianCalendar dataNascimento;
    
    /*
        Construtures
    */

    public Utilizador (){
		email = nome = password = morada = "";
        this.dataNascimento = new GregorianCalendar();
    }
    
    public Utilizador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }

    public Utilizador(Utilizador a){
        this(a.getEmail(), a.getNome(), a.getPassword(), a.getMorada(), a.getDataNascimento());
    }
    
  

    /*
    gets e sets
     */
    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getPassword() { return password; }
    public String getMorada() { return morada; }
    public GregorianCalendar getDataNascimento() { return (GregorianCalendar) dataNascimento.clone(); }

    public void setEmail(String email) { this.email = email; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPassword(String password) { this.password = password; }
    public void setMorada(String morada) { this.morada = morada; }
    public void setDataNascimento(GregorianCalendar dataNascimento) { this.dataNascimento = (GregorianCalendar) dataNascimento.clone(); }

	public Utilizador clone()
	{
		return new Utilizador(this);
	}

	public String toString()
	{
		StringBuilder str = new StringBuilder("==========Actor==========");
		str.append("Email: ");
		str.append(email); str.append("\nNome: ");
		str.append(nome); str.append("\nMorada: ");
		str.append(morada); str.append("\nData de nascimento: ");
		str.append(dataNascimento.getTime());
		str.append("=========================");
		return str.toString();
	}

	public boolean equals(Object o)
	{
		if(this == o) return true;
		if((o == null) || (this.getClass() != o.getClass())) return false;
		Utilizador obj = (Utilizador) o;
		/*Se calhar é só preciso ver se os emails são iguais visto que "identifica o utilizar" como diz no enunciado*/
		return this.email.equals(obj.getEmail()) &&
			   this.nome.equals(obj.getNome()) &&
			   this.password.equals(obj.getPassword()) &&
			   this.morada.equals(obj.getMorada()) &&
			   this.dataNascimento.getTime().equals(obj.getDataNascimento().getTime());
	}
}
