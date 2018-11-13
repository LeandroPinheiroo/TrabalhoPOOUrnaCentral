/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Eleitor;
import java.util.ArrayList;


/**
 *
 * @author João Paulo e Leandro
 */
public class EleitorDao{
    private ArrayList<Eleitor> eleitores;//array list de eleitores

    public EleitorDao() {//construtor da classe
        eleitores = new ArrayList();//instancia o arraylist para ser utilizado e armazenar valores
    }
    /** 
     * @param eleitor Um objeto do tipo Eleitor, para ser inserido no arraylist.
     *  .
    */
    public void cadastraEleitor(Eleitor eleitor){
        this.eleitores.add(eleitor);//insere o eleitor no array
    }
    /** 
     * @param eleitor Um objeto do tipo Eleitor, para ser removido do arraylist.
     *  .
    */
    public void removeEleitor(Eleitor eleitor){
        for (Eleitor e : eleitores) {//varre o arraylis
            if(e.getCpf().equals(eleitor.getCpf())){//ao encontrar o CPF do eleitor
                eleitores.remove(e);//remove o mesmo
            }
        }
    }
    /** 
     * @return ArrayList.
    */
    public ArrayList<Eleitor> retornaEleitores(){
        return this.eleitores;//retorna o arraylist de eleitores cadastrados 
    }  
    /** 
     * @param eleitores Um arraylist do tipo Eleitor, para ser atribuido no arraylist da classse.
     *  .
    */
    public void appendArrayList(ArrayList<Eleitor> eleitores){
        for (Eleitor eleitor : eleitores) {//varre o arraylist recebido
            this.eleitores.add(eleitor);//atribui o objeto ao arraylist da classe
        }
    }
}
