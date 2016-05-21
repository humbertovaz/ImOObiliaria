package src;

/**
 * Excepçao atirada quando se tenta realizar qualquer coisa sobre um leilao e nao existe nenhum.
 */

public class LeilaoInexistenteException extends Exception
{
    public LeilaoInexistenteException(String msg)
    {
        super(msg);
    }
}
