package src;

public class Actor{
	
	private String email; /*Identifica o utilizador*/
	private String nome;
	private String password;
	private String morada;
	private String dataNasc; /*Mudar para Date, ou GregorianCalendar*/

	public Actor()
	{
		email = nome = password = morada = dataNasc = "";
	}

	public Actor(String email, String nome, String password, String morada, String dataNasc)
	{
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.morada = morada;
		this.dataNasc = dataNasc;
	}

	public Actor(Actor a)
	{
		this(a.getEmail(), a.getNome(), a.getPassword(), a.getMorada(), a.getDataNasc());
	}

	public String getEmail() { return email; }
	public String getNome() { return nome; }
	public String getPassword() { return password; }
	public String getMorada() { return morada; }
	public String getDataNasc() { return dataNasc; }

	public void setEmail(String email) { this.email = email; }
	public void setNome(String nome) { this.nome = nome; }
	public void setPassword(String password) { this.password = password;}
	public void setMorada(String morada) { this.morada = morada; }
	public void setDataNasc(String dataNasc) { this.dataNasc = dataNasc;}

	public String toString()
	{
		StringBuilder str = new StringBuilder("==========Actor==========");
		str.append("Email: ");
		str.append(email); str.append("\nNome: ");
		str.append(nome); str.append("\nMorada: ");
		str.append(morada); str.append("\nData de nascimento: ");
		str.append(dataNasc);
		str.append("=========================");
		return str.toString();
	}

	public boolean equals(Object o)
	{
		if(this == o) return true;
		if((o == null) || (this.getClass() != o.getClass())) return false;
		Actor obj = (Actor) o;
		/*Se calhar é só preciso ver se os emails são iguais visto que "identifica o utilizar" como diz no enunciado*/
		return this.email.equals(obj.getEmail()) &&
			   this.nome.equals(obj.getNome()) &&
			   this.password.equals(obj.getPassword()) &&
			   this.morada.equals(obj.getMorada()) &&
			   this.dataNasc.equals(obj.getDataNasc());
	}

	public Actor clone()
	{
		return new Actor(this);
	}
}
