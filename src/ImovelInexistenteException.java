package src;


/**
 * Excepçao atirada quando e referido um imovel que nao existe.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImovelInexistenteException extends Exception 
{
    public ImovelInexistenteException(String m)
    {
        super(m);
    }
}
