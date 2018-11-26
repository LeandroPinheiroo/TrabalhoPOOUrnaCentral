/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author João Paulo
 * 
 */
public class VerificaInternet {
    /**Método para checar se há conexão com a internet em um determinado endereço
     * @param endereco
     * @return Boolean conexão
     */
    public static boolean verificaConexao(String endereco) {
        try {
            URL url = new URL(endereco);
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
