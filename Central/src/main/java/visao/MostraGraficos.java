/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.CandidatoDao;
import javax.swing.JPanel;
import modelo.Candidato;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author João Paulo e  Leandro
 */
public class MostraGraficos extends javax.swing.JFrame {

    
    private CandidatoDao candidatoDao;
    private int votoBranco;
    /**Construtor do Frame
     *@param candidatoDao, instancia do Dao da classe de candidatos
     *@param votoBranco, valor inteiro contendo a quantidade de votos em branco
     *@version 4.0
     */
    public MostraGraficos(CandidatoDao candidatoDao,int votoBranco) {
        this.candidatoDao = candidatoDao;
        this.votoBranco = votoBranco;
        initComponents();
        this.setContentPane(createDemoPanel());
        this.setLocationRelativeTo(null);
        this.setTitle("Gráfico Eleições");
    }
    /**Metodo responsavel por gerar grafico com base nas informacoes dos candidatos
     *@return PieDataset, instancia do objeto de grafico gerado
     *@version 4.0
     */
    public PieDataset criaGrafico() {
        DefaultPieDataset dataset = new DefaultPieDataset();//cria objeto responsavel por gear o grafico
        for (Candidato candidato : candidatoDao.retornaCandidatos()){//varre o vetor de candidatos
            dataset.setValue(candidato.getNome(), candidato.getQuantidadeVotos());//seta as informacoes no grafico
        }
        dataset.setValue("Votos brancos", this.votoBranco);//seta quantidade de votos brancos no grafico
        return dataset;//retorna o grafico
    }
    /**
     *@param dataset instancia do objeto de PieDataset, contendo as informaceos do Grafico
     *@return JFreeChart, instancia do objeto de grafico gerado
     *@version 4.0
     */
    public JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Gráfico Eleições Presidenciais", // titulo do grafico 
                dataset, // informacoes do grafico    
                true, // legenda   
                true,
                false);

        return chart;
    }

    /**
     *@return JPanel, Painel contendo o grafico gerado
     *@version 4.0
     */
    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(criaGrafico());//gera o grafico
        return new ChartPanel(chart);//retorna o painel
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
