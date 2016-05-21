package src;


/**
 * Excepçao atirada quando e referido um utilizador que nao existe.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UtilizadorInexistenteException extends Exception
{
    public UtilizadorInexistenteException(String m)
    {
        super(m);
    }
}
