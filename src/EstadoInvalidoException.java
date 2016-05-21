package src;


/**
 * Excepçao atirada quando se tenta mudar o estado de um imovel para um estado invalido
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EstadoInvalidoException extends Exception
{
    public EstadoInvalidoException(String m){
        super(m);
    }
}
