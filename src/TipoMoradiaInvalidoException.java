package src;


/**
 * Excepçao atirada quando e tentado atribuir um tipo invalido a uma moradia.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TipoMoradiaInvalidoException extends Exception
{
    public TipoMoradiaInvalidoException(String msg)
    {
        super(msg);
    }
}
