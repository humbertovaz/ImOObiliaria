package src;


/**
 * Excepçao atirada quando se tenta registar um utilizador que ja existe no sistema.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UtilizadorExistenteException extends Exception
{
    public UtilizadorExistenteException(String m)
    {
        super(m);
    }
}
