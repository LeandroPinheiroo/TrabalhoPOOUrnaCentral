package modelo;

/**
 *
 * @author João Paulo e Leandro
 */
public class Voto {
    private Candidato candidato;//Obejto de Candidato para representar o voto que o candidato possui

    /** 
     * @return Candidato Um obejto Candidato contendo o candidato pelo seu voto.
    */
    public Candidato getCandidato() {
        return candidato;
    }
    /**
     * @param candidato Um objeto do tipo Candidato, para ser inserido o voto do candidato.
     *  .
    */
    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}
