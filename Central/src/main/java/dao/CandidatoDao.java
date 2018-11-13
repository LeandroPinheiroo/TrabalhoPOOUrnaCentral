//Dao de candidatos, essa classe sera responsavel por executar toda a persistencia dos dados
package dao;

import modelo.Candidato;
import java.util.ArrayList;

/**
 *
 * @author João Paulo e Leandro
 */
public class CandidatoDao {
    
    private ArrayList<Candidato> candidatos;//array list de candidatos

    public CandidatoDao() {//Construtor da classe
        candidatos = new ArrayList();//instancia o array-list para que seja possivel 
        //utiliza-lo para armazenar os candidatos cadastrados
    }
    /** 
     * @param candidato Um objeto do tipo Candidato, para ser inserido no arraylist.
     *  .
    */
    public void cadastraCandidato(Candidato candidato){
        this.candidatos.add(candidato);//insere candidato no array
    }
    /** 
     * @param candidato Um objeto do tipo Candidato, para ser removido do arraylist.
     *  .
    */
    public void removeCandidato(Candidato candidato){
        for (Candidato c : candidatos) {//Varre o arraylist de candidatos
            if(c.getNumero() == candidato.getNumero()){//ao encontrar o numero do candidato
                candidatos.remove(c);//remove ele do array
            }
        }
    }
    /** 
     * @return ArrayList contendo todos os candidatos cadastrados
    */
    public ArrayList<Candidato> retornaCandidatos(){
        return this.candidatos;
    }

    /** 
     * @param candidatos Um arraylist do tipo Candidato, para ser inserido no arraylist de candidatos que foram cadastrados.
     *  .
    */
    public void appendArrayList(ArrayList<Candidato> candidatos){
        for (Candidato candidato : candidatos) {//varre o arraylist recebido de parametro
            this.candidatos.add(candidato);//a cada objeto insere no arraylist da classe
        }
    }
}
