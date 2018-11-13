/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import conexao.ConexaoDrive;
import dao.EleitorDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import uteis.Arquivo;

/**
 *
 * @author Jõao Paulo e Leandro
 */
public class EnviaEleitores extends javax.swing.JFrame {

    /**
     * Creates new form EnviaPartidos
     */
    private EleitorDao eleitorDao;
    /**Construtor do Frame
     *@param eleitorDao, instancia do Dao da classe de eleitores
     *@version 4.0
     */
    public EnviaEleitores(EleitorDao eleitorDao) {
        this.eleitorDao = eleitorDao;
        initComponents();
        this.setTitle("Envia Eleitores");
        this.setLocationRelativeTo(null);
    }
    /**Metodo reponsavel por gerar json de todos os eleitores cadastrados
     * 
     *@version 1.0
     */
    public void geraJson(){
        ArrayList<Object> l = (ArrayList<Object>)(Object)eleitorDao.retornaEleitores();
        Arquivo.criaArquivoJSON(l, "eleitores.json");
    }
    /**Metodo reponsavel por enviar Json para o Drive
     * 
     *@version 1.0
     */
    public void enviaDrive(){
        ConexaoDrive.getInstance();//Cria conexao com o drive
        ConexaoDrive.criaArquivo("eleitores.json", "eleitores.json");//Cria arquivo de eleitores
        JOptionPane.showMessageDialog(this, "Dados dos eleitores enviados com sucesso!\n");//mostra mensagem de sucesso
    }
    /**Metodo reponsavel por apagar arquivo do drive
     * 
     *@version 1.0
     */
    public void apagaDrive(){
        ConexaoDrive.getInstance();//Cria conexao com o drive
        List<com.google.api.services.drive.model.File> lista_arquivos = ConexaoDrive.listaArquivos();//lista todos os arquivos do drive
        for (com.google.api.services.drive.model.File lista_arquivo : lista_arquivos) {
            if (lista_arquivo.getName().equals("eleitores.json")) {//ao encotrar de eleitores
                ConexaoDrive.removerArquivo(lista_arquivo.getId());//apaga o mesmo
            }
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

        jlabelEleitor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jenviaEleitores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlabelEleitor.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jlabelEleitor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelEleitor.setText("Eleitores - Drive");
        jlabelEleitor.setToolTipText("");

        jenviaEleitores.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jenviaEleitores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enviarNuvem.png"))); // NOI18N
        jenviaEleitores.setText("Enviar Eleitores");
        jenviaEleitores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenviaEleitoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jenviaEleitores)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jenviaEleitores)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelEleitor, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jenviaEleitoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenviaEleitoresActionPerformed
        this.geraJson();
        this.apagaDrive();
        this.enviaDrive();
    }//GEN-LAST:event_jenviaEleitoresActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jenviaEleitores;
    private javax.swing.JLabel jlabelEleitor;
    // End of variables declaration//GEN-END:variables
}