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
public class UsuarioNaoEncontradoException extends Exception{
    public UsuarioNaoEncontradoException(){
        super("Usuário não cadastrado ou Login e senha incorretos!");
    }
}
