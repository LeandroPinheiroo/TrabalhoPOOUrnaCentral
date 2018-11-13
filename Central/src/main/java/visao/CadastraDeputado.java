/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.CandidatoDao;
import dao.PartidoDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Deputado;
import modelo.Partido;
import static uteis.Verifica.validaCPF;

/**
 *
 * @author João Paulo e Leandro
 */
public class CadastraDeputado extends javax.swing.JFrame {

    /**
     * Creates new form CadastraEleitor
     */
    private CandidatoDao candidatoDao;
    private ArrayList<Partido> arrayPartido;
    private PartidoDao partidoDao;

    /**Construtor do Frame
     *@param candidatoDao, instancia do Dao da classe de candidatos
     *@param partidoDao, instancia do Dao da classe de partidos
     *@version 4.0
     */
    public CadastraDeputado(CandidatoDao candidatoDao, PartidoDao partidoDao) {
        this.partidoDao = partidoDao;//pega os dados do partido
        this.candidatoDao = candidatoDao;//candidato
        arrayPartido = partidoDao.retornaPartidos();//retorna os partidos
        if (arrayPartido.isEmpty()) {//caso array de partidos venha vazio
            this.mostra_erro();//mostra erro que nao eh possivel cadastrar candidato sem partido
            this.setVisible(false);//desabilita o frame
            this.dispose();//fecha o frame
            return;//retorna
        }
        initComponents();//inicia componentes
        this.setLocationRelativeTo(null);//coloca no centro
        jCpf.setEditable(true);//edita CPF
        this.setTitle("Cadastra Deputado");//coloca Titulo
        preenchePartidoCombo(arrayPartido);//preenche o compo de partido com base nos cadastrados

    }
    /**Metodo reponsavel por mostrar mensagem de erro por falta de partidos
     * 
     *@version 1.0
     */
    public void mostra_erro() {
        JOptionPane.showConfirmDialog(this, "Não existe partidos cadastrados, cadaste um partido para continuar", "Erro Partido", JOptionPane.ERROR_MESSAGE);
    }
    /**Metodo reponsavel por preencher o compo de partidos
     * 
     *@version 2.0
     *@param arrayPartido array de partido com os dados preenchidos
     */
    public void preenchePartidoCombo(ArrayList<Partido> arrayPartido) {
        for (Partido partido : arrayPartido) {
            jComboPartido.addItem(partido.getNome());
        }
    }
    /**Metodo por validar os campos do candidato
     *@return String, com os possiveis erros encontrados
     *@version 4.0
     */
    public String validaCandidato() {
        String erros = "";//erros inicia vazio
        //caso algum campo esteja vazio insere o erro
        if (jtfNome.getText().equals("")) {
            erros += "Insira o nome do deputado\n";
        }
        if (jtfNumero.getText().equals("")) {
            erros += "Insira o número do deputado\n";
        }
        if (jComboPartido.getSelectedIndex() == -1) {
            erros += "Insira o partido do deputado\n";
        }
        if (jComboEstado.getSelectedIndex() == -1) {
            erros += "Insira o estado pelo qual o deputado está concorrendo\n";
        }
        if (jCpf.getText().equals("   .   .   -  ")) {
            erros += "Insira o CPF do deputado\n";
            return erros;
        }
        if (validaCPF(jCpf.getText()) == false) {
            erros += "CPF Inválido\n";
        }
        return erros;//retorna os erros
    }
    /**Metodo reponsavel gerar um novo deputado com base nas informacoes recebidas nos inputs
     *@return deputado, um objeto de candidato contendo todas as informacoes do mesmo
     *@version 1.0
     */
    public Deputado novoDeputado() {
        Deputado deputado = new Deputado();
        deputado.setCpf(jCpf.getText());
        deputado.setNome(jtfNome.getText());
        deputado.setNumero(Integer.parseInt(jtfNumero.getText()));
        deputado.setPartido(this.partidoDao.retornaPartido(jComboPartido.getSelectedItem().toString()));
        deputado.setEstado(jComboEstado.getSelectedItem().toString());
        return deputado;
    }

    /**Metodo reponsavel por limpar os campos
     * 
     *@version 1.0
     */
    public void limparCampos() {
        jCpf.setText("");
        jtfNome.setText("");
        jtfNumero.setText("");
        jComboEstado.setSelectedIndex(-1);
        jComboPartido.setSelectedIndex(-1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jlabelEleitor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jLabelNumero = new javax.swing.JLabel();
        jtfNumero = new javax.swing.JTextField();
        jLabelCpf = new javax.swing.JLabel();
        jCpf = new javax.swing.JFormattedTextField();
        LabelPartido = new javax.swing.JLabel();
        jComboPartido = new javax.swing.JComboBox<>();
        LabelEstado = new javax.swing.JLabel();
        jComboEstado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        ButttonLimpar = new javax.swing.JButton();
        ButtonCadastrar = new javax.swing.JButton();
        ButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlabelEleitor.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jlabelEleitor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabelEleitor.setText("Deputado");
        jlabelEleitor.setToolTipText("");

        jLabelNome.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabelNome.setText("Nome:");

        jLabelNumero.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabelNumero.setText("Número:");

        jtfNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumeroActionPerformed(evt);
            }
        });

        jLabelCpf.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabelCpf.setText("CPF:");

        jCpf.setEditable(false);
        try {
            jCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCpfActionPerformed(evt);
            }
        });

        LabelPartido.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        LabelPartido.setText("Partido:");

        jComboPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPartidoActionPerformed(evt);
            }
        });

        LabelEstado.setText("Estado: ");

        jComboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MT", "MS", "PA", "PB", "PR", "PE", "PI", "RR", "RO", "RJ", "RN", "RS", "SC", "SP", "SE", "TO" }));
        jComboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNumero)
                            .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(LabelEstado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelCpf)
                                .addComponent(LabelPartido))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(jCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboPartido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jtfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCpf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEstado)
                    .addComponent(jComboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPartido, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jComboPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        ButttonLimpar.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        ButttonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/limpar.png"))); // NOI18N
        ButttonLimpar.setText("Limpar");
        ButttonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButttonLimparActionPerformed(evt);
            }
        });

        ButtonCadastrar.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        ButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/adicionar.png"))); // NOI18N
        ButtonCadastrar.setText("Cadastrar");
        ButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCadastrarActionPerformed(evt);
            }
        });

        ButtonCancelar.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        ButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cancelar.png"))); // NOI18N
        ButtonCancelar.setText("Cancelar");
        ButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(ButtonCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(ButttonLimpar)
                .addGap(99, 99, 99)
                .addComponent(ButtonCancelar)
                .addGap(70, 70, 70))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButttonLimpar)
                    .addComponent(ButtonCadastrar)
                    .addComponent(ButtonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelEleitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelEleitor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCpfActionPerformed

    private void ButttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButttonLimparActionPerformed
        this.limparCampos();
    }//GEN-LAST:event_ButttonLimparActionPerformed

    private void ButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCadastrarActionPerformed
        String erro = validaCandidato();
        if (!erro.equals("")) {
            JOptionPane.showMessageDialog(this, erro, "Erro ao cadastrar Deputado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        candidatoDao.cadastraCandidato(this.novoDeputado());
        JOptionPane.showMessageDialog(this, "Deputado cadastrado com sucesso", "Deputado Cadastrado", JOptionPane.INFORMATION_MESSAGE);
        this.limparCampos();
    }//GEN-LAST:event_ButtonCadastrarActionPerformed

    private void ButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_ButtonCancelarActionPerformed

    private void jComboPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPartidoActionPerformed

    }//GEN-LAST:event_jComboPartidoActionPerformed

    private void jtfNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumeroActionPerformed

    private void jComboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEstadoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCadastrar;
    private javax.swing.JButton ButtonCancelar;
    private javax.swing.JButton ButttonLimpar;
    private javax.swing.JLabel LabelEstado;
    private javax.swing.JLabel LabelPartido;
    private javax.swing.JComboBox<String> jComboEstado;
    private javax.swing.JComboBox<String> jComboPartido;
    private javax.swing.JFormattedTextField jCpf;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabelCpf;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNumero;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlabelEleitor;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfNumero;
    // End of variables declaration//GEN-END:variables
}
