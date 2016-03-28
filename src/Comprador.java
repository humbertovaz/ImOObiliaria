package src;

import java.util.ArrayList;

public class Comprador extends Actor{

	/*TreeSet !???*/	
	ArrayList<Imovel> favoritos;

	public Comprador()
	{
		super();
		favoritos = ArrayList<>();
	}
	
	public Comprador(String email, String nome, String password, String morada, String dataNasc)
	{
		super(email, nome, password, morada, dataNasc);
		favoritos = ArrayList<>();
	}

	public Comprador(Comprador c)
	{
		/*TODO*/
	}

}
