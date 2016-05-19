package src;


import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.GregorianCalendar;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public abstract class Utilizador implements Comparable, Serializable {
	
	private String email; /*Identifica o utilizador*/
	private String nome;
	private String password;
	private String morada;
    private GregorianCalendar dataNascimento;
    
    /**
     * Utilizador Constructor
     *
     */
    public Utilizador (){
		email = nome = password = morada = "";
        this.dataNascimento = new GregorianCalendar();
    }
    
    /**
     * Utilizador Constructor
     *
     * @param email A parameter
     * @param nome A parameter
     * @param password A parameter
     * @param morada A parameter
     * @param dataNascimento A parameter
     */
    public Utilizador(String email, String nome, String password, String morada, GregorianCalendar dataNascimento) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = (GregorianCalendar) dataNascimento.clone();
    }

    /**
     * Utilizador Constructor
     *
     * @param a A parameter
     */
    public Utilizador(Utilizador a){
        this(a.getEmail(), a.getNome(), a.getPassword(), a.getMorada(), a.getDataNascimento());
    }
    
    /**
     * Getters e Setters
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

    /**
     * Metodo changePassword
     *
     * @param email email do user
     * @param oldPassword password antiga
     * @param newPassword password nova
     * @return se foi ou nao mudada a password
     */
    public boolean changePassword(String email, String oldPassword, String newPassword)
    {
        boolean result = email.equals(this.email) && oldPassword.equals(password);
        if(result)
            password = newPassword;
        return result;
    }
    
    /**
     * Metodo verificaPassword
     *
     * @param pass password a verificar
     * @return se e ou nao a password deste user
     */
    public boolean verificaPassword(String pass)
	{
		return password.equals(pass);
	}
    
	
	public int compareTo(Object o)
	{
	    Utilizador u = (Utilizador) o;
		return email.compareTo(u.getEmail());
	}

	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("\nEmail: ").append(email); 
		str.append("\nNome: ").append(nome); 
		str.append("\nMorada: ").append(morada);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
		str.append("\nData de nascimento (dd/MM/yyyy): ").append(sdf.format(dataNascimento.getTime()));
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
			   this.dataNascimento.equals(obj.getDataNascimento());
	}
}
