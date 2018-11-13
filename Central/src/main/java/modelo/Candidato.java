package modelo;

/**
 *
 * @author João Paulo e Leandro
 */
public class Candidato implements Comparable {//implements Comparable para ordenar Array

    private String nome;//Nome do candidato
    private int numero;//Numero do candidato
    private String cpf;//CPF do candidato
    private Partido partido;//Partido a qual o candidato pertence
    private int quantidadeVotos;//Quantidade de votos que o candidato possui

    public Candidato() {//Construtor da classe
        this.quantidadeVotos = 0;//ao instanciar o objeto de candidatos atribui sua quantidade de votos para 0
    }
    /** 
     * @return Int Um valor int contendo a quanidade de votos que o candidato possui.
    */
    public int getQuantidadeVotos() {
        return quantidadeVotos;
    }
    /** 
     * @param quantidadeVotos Um valor do tipo int, para ser inserido na quantidade de votos que o candidato possui.
     *  .
    */
    public void setQuantidadeVotos(int quantidadeVotos) {
        this.quantidadeVotos = quantidadeVotos;
    }
    /** 
     * @return String Um valor String contendo o nome do candidato.
    */
    public String getNome() {
        return nome;
    }
    /** 
     * @param nome Um valor do tipo String, para ser inserido no nome do candidato possui.
     *  .
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /** 
     * @return int Um valor int contendo o numero do candidato.
    */
    public int getNumero() {
        return numero;
    }
    /** 
     * @param numero Um valor do tipo int, para ser inserido o numero do candidato.
     *  .
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    /** 
     * @return String Um valor String contendo o CPF do candidato.
    */
    public String getCpf() {
        return cpf;
    }
    /** 
     * @param cpf Um valor do tipo String, para ser inserido o CPF do candidato.
     *  .
    */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    /** 
     * @return Partido Um objeto do tipo Partido contendo o Partido do candidato.
    */
    public Partido getPartido() {
        return partido;
    }
    /** 
     * @param partido Um Obejto do tipo Partido, para ser inserido o Partido do candidato.
     *  .
    */
    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    /** 
     * @param objeto Um Objeto do tipo Object que sera convertido para o tipo candidato, para ser comparado com base na quantidade de votos e assim ordenar o array de em ordem decrescente
     *  .
    */
    @Override
    public int compareTo(Object objeto) {
        Candidato candidato = (Candidato) objeto;//Faz um cast no objeto recebido para ser convertido para o tipo candidato
        if (this.getQuantidadeVotos() < candidato.getQuantidadeVotos()) {//caso a quantidade de votos seja menor
            return 1;//retorna 1
        }
        if (this.getQuantidadeVotos() > candidato.getQuantidadeVotos()) {//caso maior
            return -1;//retorna -1
        }
        return 0;//caso igual retorna 0
    }
}
