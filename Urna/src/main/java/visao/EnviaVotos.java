/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.VotoDao;
import java.util.ArrayList;
import uteis.Arquivo;
import conexao.ConexaoDrive;
import javax.swing.JOptionPane;

/**
 *
 * @author João Paulo e Leandro
 */
public class EnviaVotos extends javax.swing.JFrame {

    /**
     * Creates new form EnviaVotos
     */
    
    VotoDao votoDao;
    /*Construtor do Frame*/
    public EnviaVotos(VotoDao votoDao) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Envia votos");
        this.votoDao = votoDao;
    }
    /**Método para enviar os votos para o Google Drive
     * @author João Paulo e Leandro
     * @version 1.0
     * @return void
     */
    public void enviarVotos(){
        /*verifica se o array de votos está vazio*/
        if(votoDao.retornaVotos().isEmpty()){
            /*se estiver, mostra mensagem avisando que não houve nenhum voto ainda*/
            JOptionPane.showMessageDialog(this, "Não foi realizado nenhum voto, arquivo não pode ser enviado!");
        }else{
            /*se houver votos, chama o método para criar o arquivo JSON*/
            Arquivo.criaArquivoJSON((ArrayList<Object>)(Object)votoDao.retornaVotos(),"votação.json");
            /*chama a instancia da conexão com o Drive*/
            ConexaoDrive.getInstance();
            /*chama a função de enviar o arquivo para o Drive*/
            ConexaoDrive.criaArquivo("votação.json","votação.json");
            /*mostra mensagem de sucesso*/
            JOptionPane.showMessageDialog(this, "Dados de votação enviados com sucesso!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botaoEnvia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Envia Votos");

        botaoEnvia.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        botaoEnvia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enviarNuvem.png"))); // NOI18N
        botaoEnvia.setLabel("Envia Votos");
        botaoEnvia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(botaoEnvia, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(botaoEnvia)
                .addGap(110, 110, 110))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEnviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviaActionPerformed
        enviarVotos();
    }//GEN-LAST:event_botaoEnviaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEnvia;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
