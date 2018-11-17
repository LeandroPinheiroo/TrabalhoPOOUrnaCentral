/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

/**
 *
 * @author weth
 */
public class DadoIncosistenteExpection{
    public class DadoNuloExpection extends Exception{
        public DadoNuloExpection(){
            super("Entrada vazia, por favor preencha corretamente!");
        }
    }
    
    public class DadoInvalidoExpection extends Exception{
        public DadoInvalidoExpection(){
            super("Entrada inválida, preencha o campo de acordo com o solicitado!");
        }
    }
}
