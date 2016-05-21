package src;


/**
 * Excepçao atirada quando se tenta realizar alguma acçao que nao faz parte do conjunto de funcionalidades de um determinado utilizador
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SemAutorizacaoException extends Exception
{
    public SemAutorizacaoException(String m){
        super(m);
    }
}
