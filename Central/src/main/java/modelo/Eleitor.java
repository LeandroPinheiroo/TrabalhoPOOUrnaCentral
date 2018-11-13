package modelo;

/**
 *
 * @author João Paulo e Leandro
 */
public class Eleitor {
    private String titulo_eleitor; //Titulo de eleitor 
    private String nome; //Nome do eleitor
    private String cpf; //CPF do eleitor
    private Integer matriz_imagem[][]; //Matriz para armazenar a imagem PPM do eleitor
    private int numero_urna; //Numero da Urna que o eleitor Vota

    public Eleitor() {
        this.votou = false;
    }
    
    private String estado;
    private boolean votou;

    public boolean isVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /** 
     * @return String Um valor String contendo o titulo de eleitor do eleitor.
    */    
    public String getTitulo_eleitor() {
        return titulo_eleitor;
    }
    /**
     * @param titulo_eleitor Um valor do tipo String, para ser inserido o titulo de eleitor.
     *  .
    */
    public void setTitulo_eleitor(String titulo_eleitor) {
        this.titulo_eleitor = titulo_eleitor;
    }
    /** 
     * @return String Um valor String contendo o nome do eleitor.
    */ 
    public String getNome() {
        return nome;
    }
    /** 
     * @param nome Um valor do tipo String, para ser inserido o nome do eleitor.
     *  .
    */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /** 
     * @return String Um valor String contendo o CPF do eleitor.
    */ 
    public String getCpf() {
        return cpf;
    }
    /** 
     * @param cpf Um valor do tipo String, para ser inserido o CPF do eleitor.
     *  .
    */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    /** 
     * @return Integer[][] Uma matiriz de Integer contendo a imagem PPM do eleitor.
    */ 
    public Integer[][] getMatriz_imagem() {
        return matriz_imagem;
    }
    /** 
     * @param matriz_imagem Uma matriz do tipo Integer, para ser inserido a imagem PPM do eleitor.
     *  .
    */
    public void setMatriz_imagem(Integer[][] matriz_imagem) {
        this.matriz_imagem = matriz_imagem;
    }
    /** 
     * @return int Uma valor de int contendo o numero da urna do eleitor.
    */
    public int getNumero_urna() {
        return numero_urna;
    }
    /** 
     * @param numero_urna Um valor do tipo int, para ser inserido a numeroda urna do eleitor.
     *  .
    */
    public void setNumero_urna(int numero_urna) {
        this.numero_urna = numero_urna;
    }    
}
