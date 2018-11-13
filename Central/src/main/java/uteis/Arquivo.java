package uteis;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author João Paulo e Leandro
 */
public class Arquivo {

    /**
     * @param caminho Um valor do tipo String, contendo o caminho onde a imagem se encontra.
     * @return Integer[][], uma matriz contendo as informacoes da imagem.
    */
    public Integer[][] leImagemMatriz(String caminho) {
        Integer matriz[][] = null;//Declara matriz e inicia como NULL
        try {
            FileReader arq = new FileReader(caminho);//Cria objeto para ler o arquivo passado 
            BufferedReader lerArq = new BufferedReader(arq);//objeto de buffer para armazenar conteudo lido
            String linha = lerArq.readLine(); // lê a primeira linha e salva em uma string
            linha = lerArq.readLine(); // lê a segunda linha contendo o tamanho da matriz
            String vetor_string[] = linha.split(" ");//quebra a string em 2 para pegar o tamanho de linhas e colunas
            int x = 0;//inicialmente linhas e colunas igual a 0 
            int y = 0;
            if (vetor_string != null) {//caso nao tenha ocorrido erro em pegar a quantidade de linhas e colunas
                x = Integer.parseInt(vetor_string[1]);//Na posicao 1 do array se encontra a quantidade de linhas
                y = 3 * Integer.parseInt(vetor_string[0]);// a quantidade de colunas eh 3 vezes maior do lido pois PPM utiliza RGB ou seja 3 valores para cada celula da matriz
            }
            linha = lerArq.readLine(); // Le a terceira linha do arquivo 
            matriz = new Integer[x][y];//intancia a matriz na memoria com a quantidade de linhas e colunas corretas
            for (int i = 0; i < x; i++) {
                linha = lerArq.readLine();// le da quarta linha em diante
                String vet_imagem[] = linha.split(" ");//quebra a linha para pegar cada elemento
                for (int j = 0; j < y; j++) {
                    matriz[i][j] = Integer.parseInt(vet_imagem[j]);//joga o valor do elemento na matriz
                }
            }
            arq.close();//fecha o arquivo para evitar erro
        } catch (IOException e) {//captura o erro de Entrada ou saida
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return matriz;//Retorna matriz preenchida 
    }
    /**
     * @param conteudo Um valor do tipo String, contendo o conteudodo arquivo a ser criado.
     * @param caminho Um valor do tipo String, contendo o caminho onde o arquivo se 
     @throws java.io.IOException Caso nao encontre o arquivo a ser lido mostra erroencontra.
     *  .
    */
    public static void criaArquivo(String conteudo, String caminho) throws IOException {
        FileWriter arquivo;//objeto do tipo FileWriter, para escrever no arquivo
        try {
            arquivo = new FileWriter(new File(caminho));//instancia o objeto e passa o caminho de onde sera criado o mesmo
            arquivo.write(conteudo);//escreve o conteudo
            arquivo.close();//fecha o arquivo
        } catch (IOException e) {//captura erro de IO
            e.getMessage();
        } catch (Exception e) {
            e.getMessage();
        }
    }
     /**
     * @param lista Um arraylist do tipo Object, os objetos que serao inseridos no arquivo JSON.
     * @param caminho Um valor do tipo String, contendo o caminho onde o arquivo sera cirado.
     *  .
    */
    public static void criaArquivoJSON(ArrayList<Object> lista, String caminho) {
        Gson gson = new Gson();//Cria objeto do tipo Gson para q seja possivel criar o arquivo Json
        FileWriter arq = null;//objeto de escrita
        try {
            arq = new FileWriter(caminho);//tenta abrir o arrquivo para escrita
        } catch (IOException ex) {//captura erro de IO
            ex.getMessage();
        }
        PrintWriter gravarArq = new PrintWriter(arq);
        for (Object l : lista) {//varre o arraylist de objeto que foi passado
            gravarArq.printf("%s\n", gson.toJson(l));//escreve em formato Json no arquivo cada objeto da lista
        }
        try {
            arq.close();//fecha o arquivo
        } catch (IOException ex) {
            ex.getMessage();//captura erro de IO
        }
    }

}
