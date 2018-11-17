/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import com.google.gson.Gson;
import conexao.ConexaoDrive;
import dao.CandidatoDao;
import dao.EleitorDao;
import dao.PartidoDao;
import excecoes.SemConexaoComIntenetException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.ws.http.HTTPException;
import modelo.Candidato;
import modelo.Deputado;
import modelo.Eleitor;
import modelo.Partido;
import modelo.Presidente;
import uteis.Arquivo;
import uteis.VerificaInternet;

/**
 *
 * @author Jõao Paulo e Leandro
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    private PartidoDao partidoDao = new PartidoDao();
    private EleitorDao eleitorDao = new EleitorDao();
    private CandidatoDao candidatoDao = new CandidatoDao();

    /**
     * Construtor do Frame
     *
     * @version 3.0
     */
    public Menu() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);//cria FRAME FULL SCREAM
        setLocationRelativeTo(null);
        setTitle("Central");
    }

    /**
     * Metodo reponsavel por as informacoes contidas no drive e nao localmente
     *
     * @version 1.0
     */
    public void atualizaDados() {
        criaArquivoCandidatos();//cria arquivos de Candidatos partidos e eleitores
        criaArquivoPartidos();
        criaArquivoEleitores();
        //Insere as informacoes nos array sem apagar as anteriores
        candidatoDao.appendArrayList(geraObjetoCandidato());
        partidoDao.appendArrayList(geraObjetoPartido());
        eleitorDao.appendArrayList(geraObjetoEleitores());
        JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo reponsavel por criar arquivo de eleitores localmente
     *
     * @version 1.0
     */
    public void criaArquivoEleitores() {
        ConexaoDrive.getInstance();
        List<com.google.api.services.drive.model.File> lista_arquivos = ConexaoDrive.listaArquivos();
        if (lista_arquivos == null) {
            JOptionPane.showMessageDialog(this, "Erro, confira sua conexão com a internet", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (com.google.api.services.drive.model.File lista_arquivo : lista_arquivos) {
            if (lista_arquivo.getName().equals("eleitores.json")) {
                try {
                    String conteudo = ConexaoDrive.leArquivoGD(lista_arquivo.getId());
                    Arquivo.criaArquivo(conteudo, "eleitores.json");
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HTTPException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }

    /**
     * Metodo reponsavel por criar arquivo de candidatos localmente
     *
     * @version 1.0
     */
    public void criaArquivoCandidatos() {
        ConexaoDrive.getInstance();
        List<com.google.api.services.drive.model.File> lista_arquivos = ConexaoDrive.listaArquivos();
        if (lista_arquivos == null) {
            JOptionPane.showMessageDialog(this, "Erro, confira sua conexão com a internet", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (com.google.api.services.drive.model.File lista_arquivo : lista_arquivos) {
            if (lista_arquivo.getName().equals("candidatos.json")) {
                try {
                    String conteudo = ConexaoDrive.leArquivoGD(lista_arquivo.getId());
                    Arquivo.criaArquivo(conteudo, "candidatos.json");

                    return;
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HTTPException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }

    /**
     * Metodo reponsavel por criar arquivo de partidos localmente
     *
     * @version 1.0
     */
    public void criaArquivoPartidos() {
        ConexaoDrive.getInstance();
        List<com.google.api.services.drive.model.File> lista_arquivos = ConexaoDrive.listaArquivos();
        if (lista_arquivos == null) {
            JOptionPane.showMessageDialog(this, "Erro, confira sua conexão com a internet", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (com.google.api.services.drive.model.File lista_arquivo : lista_arquivos) {
            if (lista_arquivo.getName().equals("partidos.json")) {
                try {
                    String conteudo = ConexaoDrive.leArquivoGD(lista_arquivo.getId());
                    Arquivo.criaArquivo(conteudo, "partidos.json");
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (HTTPException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
        }
    }

    /**
     * Metodo reponsavel por gerar objeto de candidatos com base no arquivo
     * local
     *
     * @return ArrayList, um array contendo todos os candidatos cadastrados
     * @version 1.0
     */
    public ArrayList<Candidato> geraObjetoCandidato() {
        Gson gson = new Gson();
        FileInputStream arquivoEntrada;
        ArrayList<Candidato> candidatos = null;
        try {
            arquivoEntrada = new FileInputStream("candidatos.json");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivoEntrada));
            candidatos = new ArrayList();
            String strLine;
            while ((strLine = leitor.readLine()) != null) {
                Deputado deputado = gson.fromJson(strLine, Deputado.class);
                if (deputado.getEstado() == null) {
                    Presidente presidente = gson.fromJson(strLine, Presidente.class);
                    candidatos.add(presidente);
                } else {
                    candidatos.add(deputado);
                }
            }
            leitor.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro, arquivo não localizado!");
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatos;
    }

    /**
     * Metodo reponsavel por gerar objeto de partidos com base no arquivo local
     *
     * @return ArrayList, um array contendo todos os Partidos cadastrados
     * @version 1.0
     */
    public ArrayList<Partido> geraObjetoPartido() {
        Gson gson = new Gson();
        FileInputStream arquivoEntrada;
        ArrayList<Partido> partidos = null;
        try {
            arquivoEntrada = new FileInputStream("partidos.json");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivoEntrada));
            partidos = new ArrayList();
            String strLine;
            while ((strLine = leitor.readLine()) != null) {
                partidos.add(gson.fromJson(strLine, Partido.class));
            }
            leitor.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partidos;
    }

    /**
     * Metodo reponsavel por gerar objeto de eleitores com base no arquivo local
     *
     * @return ArrayList, um array contendo todos os eleitores cadastrados
     * @version 1.0
     */
    public ArrayList<Eleitor> geraObjetoEleitores() {
        Gson gson = new Gson();
        FileInputStream arquivoEntrada;
        ArrayList<Eleitor> eleitores = null;
        try {
            arquivoEntrada = new FileInputStream("eleitores.json");
            BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivoEntrada));
            eleitores = new ArrayList();
            String strLine;
            while ((strLine = leitor.readLine()) != null) {
                eleitores.add(gson.fromJson(strLine, Eleitor.class));
            }
            leitor.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro, arquivo não localizado!");
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eleitores;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Cadastros = new javax.swing.JMenu();
        cadEleitor = new javax.swing.JMenuItem();
        cadCandidato = new javax.swing.JMenuItem();
        cadCandidato1 = new javax.swing.JMenuItem();
        cadPartido = new javax.swing.JMenuItem();
        jMenuUpload = new javax.swing.JMenu();
        driveEleitor = new javax.swing.JMenuItem();
        driveCandidato = new javax.swing.JMenuItem();
        drivePartido = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        driveVotos = new javax.swing.JMenuItem();
        driveAtualiza = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eleicoes.png"))); // NOI18N

        Cadastros.setText("Cadastros");
        Cadastros.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        cadEleitor.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        cadEleitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/eleitor.png"))); // NOI18N
        cadEleitor.setText("Eleitor");
        cadEleitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadEleitorActionPerformed(evt);
            }
        });
        Cadastros.add(cadEleitor);

        cadCandidato.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        cadCandidato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/candidato.png"))); // NOI18N
        cadCandidato.setText("Deputado");
        cadCandidato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCandidatoActionPerformed(evt);
            }
        });
        Cadastros.add(cadCandidato);

        cadCandidato1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        cadCandidato1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/candidato.png"))); // NOI18N
        cadCandidato1.setText("Presidente");
        cadCandidato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadCandidato1ActionPerformed(evt);
            }
        });
        Cadastros.add(cadCandidato1);

        cadPartido.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        cadPartido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/partido.png"))); // NOI18N
        cadPartido.setText("Partido");
        cadPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadPartidoActionPerformed(evt);
            }
        });
        Cadastros.add(cadPartido);

        jMenuBar1.add(Cadastros);

        jMenuUpload.setText("Drive Upload");
        jMenuUpload.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        driveEleitor.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        driveEleitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enviarNuvem.png"))); // NOI18N
        driveEleitor.setText("Eleitores");
        driveEleitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driveEleitorActionPerformed(evt);
            }
        });
        jMenuUpload.add(driveEleitor);

        driveCandidato.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        driveCandidato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enviarNuvem.png"))); // NOI18N
        driveCandidato.setText("Candidatos");
        driveCandidato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driveCandidatoActionPerformed(evt);
            }
        });
        jMenuUpload.add(driveCandidato);

        drivePartido.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        drivePartido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/enviarNuvem.png"))); // NOI18N
        drivePartido.setText("Partidos");
        drivePartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drivePartidoActionPerformed(evt);
            }
        });
        jMenuUpload.add(drivePartido);

        jMenuBar1.add(jMenuUpload);

        jMenu1.setText("Drive Download");
        jMenu1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        driveVotos.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        driveVotos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/baixarNuvem.png"))); // NOI18N
        driveVotos.setText("Votos");
        driveVotos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driveVotosActionPerformed(evt);
            }
        });
        jMenu1.add(driveVotos);

        driveAtualiza.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        driveAtualiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/baixarNuvem.png"))); // NOI18N
        driveAtualiza.setText("Atualiza Dados");
        driveAtualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driveAtualizaActionPerformed(evt);
            }
        });
        jMenu1.add(driveAtualiza);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadEleitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadEleitorActionPerformed
        new CadastraEleitor(eleitorDao).setVisible(true);
    }//GEN-LAST:event_cadEleitorActionPerformed

    private void cadCandidatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCandidatoActionPerformed
        new CadastraDeputado(candidatoDao, partidoDao).setVisible(true);
    }//GEN-LAST:event_cadCandidatoActionPerformed

    private void cadPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadPartidoActionPerformed
        new CadastraPartido(partidoDao).setVisible(true);
    }//GEN-LAST:event_cadPartidoActionPerformed

    private void drivePartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drivePartidoActionPerformed
        new EnviaPartidos(partidoDao).setVisible(true);
    }//GEN-LAST:event_drivePartidoActionPerformed

    private void driveEleitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driveEleitorActionPerformed
        new EnviaEleitores(eleitorDao).setVisible(true);
    }//GEN-LAST:event_driveEleitorActionPerformed

    private void driveCandidatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driveCandidatoActionPerformed
        new EnviaCandidatos(candidatoDao).setVisible(true);
    }//GEN-LAST:event_driveCandidatoActionPerformed

    private void driveVotosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driveVotosActionPerformed
        new BuscaVotos(candidatoDao).setVisible(true);
    }//GEN-LAST:event_driveVotosActionPerformed

    private void driveAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driveAtualizaActionPerformed
        if (VerificaInternet.verificaConexao("https://www.google.com/") == false) {
            JOptionPane.showMessageDialog(this, new SemConexaoComIntenetException().getMessage());
            return;
        }
        this.atualizaDados();
    }//GEN-LAST:event_driveAtualizaActionPerformed

    private void cadCandidato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadCandidato1ActionPerformed
        new CadastraPresidente(candidatoDao, partidoDao).setVisible(true);
    }//GEN-LAST:event_cadCandidato1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Cadastros;
    private javax.swing.JMenuItem cadCandidato;
    private javax.swing.JMenuItem cadCandidato1;
    private javax.swing.JMenuItem cadEleitor;
    private javax.swing.JMenuItem cadPartido;
    private javax.swing.JMenuItem driveAtualiza;
    private javax.swing.JMenuItem driveCandidato;
    private javax.swing.JMenuItem driveEleitor;
    private javax.swing.JMenuItem drivePartido;
    private javax.swing.JMenuItem driveVotos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jMenuUpload;
    // End of variables declaration//GEN-END:variables
}
