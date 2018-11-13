package modelo;

/**
 *
 * @author João Paulo e Leandro
 */
public class Partido {
    private int numero;//Numero do partido
    private String nome;//Nome do partido

    /** 
     * @return int Um valor int contendo o numero do partido.
    */ 
    public int getNumero() {
        return numero;
    }
    /**
     * @param numero Um valor do tipo int, para ser inserido o numero do partido.
     *  .
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    /** 
     * @return String Um valor String contendo o nome do partido.
    */
    public String getNome() {
        return nome;
    }
    /**
     * @param nome Um valor do tipo String, para ser inserido o nome do partido.
     *  .
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
