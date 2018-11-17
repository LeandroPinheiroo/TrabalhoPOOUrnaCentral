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
public class CpfInvalidoExpection extends Exception{
    public CpfInvalidoExpection(){
        super("CPF inválido!");
    }
}
