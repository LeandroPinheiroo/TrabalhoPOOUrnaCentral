/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

/**
 *
 * @author João Paulo e Leandro
 */
public class Verifica {


    /**
     * @param Cpf Um valor do tipo String, contendo o CPF que sera validado.
     * @return boolean, uma valor boolean dizendo se o CPF passado eh valido (TRUE) ou invalido(FALSE).
    */
    public static boolean validaCPF(String Cpf) {
        Cpf = Cpf.replaceAll("[\\.-]", "");//quebra a string deixando apenas numeros

        if ((Cpf.equals("00000000000")) || (Cpf.equals("11111111111")) || (Cpf.equals("22222222222")) || (Cpf.equals("33333333333"))
                || (Cpf.equals("44444444444")) || (Cpf.equals("55555555555")) || (Cpf.equals("66666666666")) || (Cpf.equals("77777777777"))
                || (Cpf.equals("88888888888")) || (Cpf.equals("99999999999"))) {//Como numeros inteiramente identicos sao validos eles devem ser capturados de forma pratica
            return false;//retorna false
        } else {//valida o CPF
        
            String s1, s2, s3, s4, s5, s6, s7, s8, s9, confere = "";
            int n1, n2, n3, n4, n5, n6, n7, n8, n9, verificador1, verificador2;

            s1 = Cpf.substring(0, 1);
            n1 = Integer.parseInt(s1);
            s2 = Cpf.substring(1, 2);
            n2 = Integer.parseInt(s2);
            s3 = Cpf.substring(2, 3);
            n3 = Integer.parseInt(s3);

            s4 = Cpf.substring(3, 4);
            n4 = Integer.parseInt(s4);
            s5 = Cpf.substring(4, 5);
            n5 = Integer.parseInt(s5);
            s6 = Cpf.substring(5, 6);
            n6 = Integer.parseInt(s6);

            s7 = Cpf.substring(6, 7);
            n7 = Integer.parseInt(s7);
            s8 = Cpf.substring(7, 8);
            n8 = Integer.parseInt(s8);
            s9 = Cpf.substring(8, 9);
            n9 = Integer.parseInt(s9);

            verificador1 = (n1 * 10 + n2 * 9 + n3 * 8 + n4 * 7 + n5 * 6 + n6 * 5 + n7 * 4 + n8 * 3 + n9 * 2);

            if ((verificador1 % 11) < 2) {
                verificador1 = 0;
            } else {
                verificador1 = 11 - (verificador1 % 11);
            }

            verificador2 = (n1 * 11 + n2 * 10 + n3 * 9 + n4 * 8 + n5 * 7 + n6 * 6 + n7 * 5 + n8 * 4 + n9 * 3 + verificador1 * 2);

            if ((verificador2 % 11) < 2) {
                verificador2 = 0;
            } else {
                verificador2 = 11 - (verificador2 % 11);
            }

            confere = (s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + verificador1 + verificador2);

            if (confere.equals(Cpf)) {//Caso o CPF seja valido
                return true;//retorna TRUE
            } else {//se nao
                return false;//retorna FALSE
            }
        }
    }
}
