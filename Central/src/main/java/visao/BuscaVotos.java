package visao;

import com.google.gson.Gson;
import conexao.ConexaoDrive;
import dao.CandidatoDao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.http.HTTPException;
import modelo.Candidato;
import modelo.Voto;
import uteis.Arquivo;

/**
 *
 * @author João Paulo e Leandro
 */
public class BuscaVotos extends javax.swing.JFrame {

    /**
     * Creates new form BuscaVotos
     */
    private CandidatoDao candidatoDao;//Objeto de candidato para receber os dados cadastrados
    private int votoBranco = 0;//contabilizador de votos brancos
    /**Construtor do Frame
     *@param candidatoDao, instancia do Dao da classe de candidatos
     *@version 4.0
     */
    public BuscaVotos(CandidatoDao candidatoDao) {
        this.candidatoDao = candidatoDao;
        initComponents();
        botaoGeraGrafico.setEnabled(false);
        this.setTitle("Busca Votos");
        this.setLocationRelativeTo(null);
    }
    /**Responsavel por criar arquivos de votos
     *@version 4.0
     * 
     */
    public void criaArquivoVotos() {
        ConexaoDrive.getInstance();//gerar uma conexao com o drive
        List<com.google.api.services.drive.model.File> lista_arquivos = ConexaoDrive.listaArquivos();//lista de arquivo para receber todos arquivos existentes no drive
        if(lista_arquivos == null){
            JOptionPane.showMessageDialog(this, "Erro, confira sua conexão com a internet","Erro",JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (com.google.api.services.drive.model.File lista_arquivo : lista_arquivos) {//varre a lista
            if (lista_arquivo.getName().equals("votação.json")) {//caso encontre o arquivo que deseja
                try {
                    String conteudo = ConexaoDrive.leArquivoGD(lista_arquivo.getId());//pega o conteudo do mesmo
                    Arquivo.criaArquivo(conteudo, "votação.json");//cria um arquivo local com as mesmas informacoes
                    return;//retorna
                } catch (IOException ex) {//captura erro de IO
                    Logger.getLogger(BuscaVotos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HTTPException ex) {
                    Logger.getLogger(BuscaVotos.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }

    /**Metodo responsavel por gerar um objeto com base no arquivo local
     *@version 4.0
     *@return ArrayList
     * 
     */
    public ArrayList<Voto> geraObjetoVotacao() {
        Gson gson = new Gson();//Instancia de Gson para ler arquivo Json
        FileInputStream arquivoEntrada;//arquivo de entrada
        ArrayList<Voto> votos = null;//ArrayList inicialmente vazio
        try {
            arquivoEntrada = new FileInputStream("votação.json");//tenta abrir o arquivo
            BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivoEntrada));//objeto de Buffer para armazenar dados lidos do arquivo
            votos = new ArrayList();//cria o arraylist
            String strLine;//stringpara receber o conteudo da linha
            while ((strLine = leitor.readLine()) != null) {//atribui o conteudo da linha e equanto nao retorne null indica que existe dados e devve ser lido
                votos.add(gson.fromJson(strLine, Voto.class));//armazena os dados no arraylist
            }
            leitor.close();//fecha o arquvio
        } catch (FileNotFoundException ex) {//captura erro caso o arquivo nao exista
            JOptionPane.showMessageDialog(null, "Erro, arquivo não localizado!");//mostra mensagem de erro
            this.dispose();//fecha o Frame
        } catch (IOException ex) {//captura erro de IO
            Logger.getLogger(BuscaVotos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votos;
    }

    /**Responsavel por contabilizar votos
     *@version 4.0
     * 
     */
    public void contabilizaVotos() {
        ArrayList<Voto> votos = this.geraObjetoVotacao();//gera objeto de votos e armazena todos no arraylist
        this.votoBranco = 0;//contador de votos branco inicia em 0
        if(!votos.isEmpty()){//Verifica se o Arraylist de votos nao esta vazio
            for (Voto voto : votos) {//varre o vetor de votos
                if (voto.getCandidato() != null) {//caso tenha candidato armazenado no voto
                    for (Candidato candidato : candidatoDao.retornaCandidatos()) {//varre os candidatos cadastrados
                        if (voto.getCandidato().getNumero() == candidato.getNumero()) {//ao encontrar o numero igual
                            candidato.setQuantidadeVotos(candidato.getQuantidadeVotos() + 1);//contabiliza os votos
                        }
                    }
                } else {//caso nao tenha candidato armazenado no voto indica que o voto esta em branco
                    this.votoBranco ++;//soma no contador
                }
            }
        }
        Collections.sort(candidatoDao.retornaCandidatos());//ordena o array
        DefaultTableModel model = (DefaultTableModel) tabelaVotos.getModel();//model para inserir dados na tabela
        model.addRow(new Object[]{"Votos em Branco", "", this.votoBranco });//adiciona a primeira linha com votos em branco
        for (Candidato candidato : candidatoDao.retornaCandidatos()) {//varre o vetor de candidatos
            model.addRow(new Object[]{candidato.getNome(), candidato.getPartido().getNome(), candidato.getQuantidadeVotos()});//insere na tabela seus partidos nomes e quantidade de votos
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVotos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        botaoGeraGrafico = new javax.swing.JButton();
        botaoBaixaVotos1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        tabelaVotos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome Candidato", "Partido", "Número Votos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaVotos);
        if (tabelaVotos.getColumnModel().getColumnCount() > 0) {
            tabelaVotos.getColumnModel().getColumn(0).setResizable(false);
            tabelaVotos.getColumnModel().getColumn(1).setResizable(false);
            tabelaVotos.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recebimento de Votos");

        botaoGeraGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/grafico.png"))); // NOI18N
        botaoGeraGrafico.setText("Gerar Gráfico");
        botaoGeraGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGeraGraficoActionPerformed(evt);
            }
        });

        botaoBaixaVotos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/baixarNuvem.png"))); // NOI18N
        botaoBaixaVotos1.setText("Receber Votos");
        botaoBaixaVotos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBaixaVotos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoGeraGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoBaixaVotos1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoBaixaVotos1)
                        .addGap(18, 18, 18)
                        .addComponent(botaoGeraGrafico)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoGeraGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGeraGraficoActionPerformed
        new MostraGraficos(candidatoDao,this.votoBranco ).setVisible(true);
    }//GEN-LAST:event_botaoGeraGraficoActionPerformed

    private void botaoBaixaVotos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBaixaVotos1ActionPerformed
        this.criaArquivoVotos();
        this.contabilizaVotos();
        JOptionPane.showMessageDialog(this, "Dados dos votos baixados com sucesso!\n");
        if (tabelaVotos.getRowCount() > 0) {
            botaoGeraGrafico.setEnabled(true);
        }
    }//GEN-LAST:event_botaoBaixaVotos1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBaixaVotos1;
    private javax.swing.JButton botaoGeraGrafico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVotos;
    // End of variables declaration//GEN-END:variables
}
