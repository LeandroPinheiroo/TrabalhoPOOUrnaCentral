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
public class SemConexaoComIntenetException extends Exception{
    public SemConexaoComIntenetException() {
        super("Não foi possível realizar a conexão, por favor verifique sua conexão ou ligue para o provedor de internet");
    }
}
