package src;

/**
 * Excepçao atirada quando se tenta iniciar o leilao sem licitadores.
 */

public class LeilaoSemLicitadoresException extends Exception
{
    // instance variables - replace the example below with your own
    public LeilaoSemLicitadoresException(String msg)
    {
        super(msg);
    }
}
