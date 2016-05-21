package src;


/**
 * Excepçao atirada quando queremos inserir no sistema um imovel que ja existe.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImovelExisteException extends Exception
{
    public ImovelExisteException(String m)
    {
        super(m);
    }
}
