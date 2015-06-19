/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 5102011212
 */
public class Atleta extends javax.swing.JPanel {

    public Atleta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        tfNome = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        btExcluir = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btNovoAtleta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAtleta = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        tfNomePesquisaAtleta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btMostraTudo = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ftEstatura = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        ftMassaCorporal = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tfEnvergadura = new javax.swing.JFormattedTextField();
        cbNivel = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ftTempoPratica = new javax.swing.JFormattedTextField();
        jLabel25 = new javax.swing.JLabel();
        ftPraticaSemana = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox();
        cbLateralidade = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        cbInstituicao = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        dcNascimento = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taHistorico = new javax.swing.JTextArea();

        tfNome.setEnabled(false);

        tfEmail.setEnabled(false);

        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/excluir.png"))); // NOI18N
        btExcluir.setToolTipText("Excluir");

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/Alterar.png"))); // NOI18N
        btEditar.setToolTipText("Editar");

        btNovoAtleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/a.png"))); // NOI18N
        btNovoAtleta.setToolTipText("Novo Atleta");

        tbAtleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbAtleta.setFillsViewportHeight(true);
        tbAtleta.getTableHeader().setResizingAllowed(false);
        tbAtleta.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbAtleta);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setText("Nome do Atleta:");

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/lupa.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");

        btMostraTudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/cancel.png"))); // NOI18N
        btMostraTudo.setText("Cancelar");
        btMostraTudo.setEnabled(false);

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Nome Completo:");

        jLabel4.setText(" E-mail:");

        jLabel22.setText("Data de Nascimento:");

        try {
            ftEstatura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftEstatura.setEnabled(false);

        jLabel29.setText("Estatura:(m)");

        try {
            ftMassaCorporal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftMassaCorporal.setEnabled(false);

        jLabel26.setText("Massa Corporal:(Kg)");

        jLabel17.setText("Envergadura(Cm):");

        try {
            tfEnvergadura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfEnvergadura.setEnabled(false);

        cbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Iniciante", "Intermediário", "Avançado" }));
        cbNivel.setEnabled(false);

        jLabel21.setText("Nível:");

        jLabel23.setText("Tempo de Prática:(AA/MM)");

        try {
            ftTempoPratica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftTempoPratica.setEnabled(false);

        jLabel25.setText("Horas de prática por semana:");

        try {
            ftPraticaSemana.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftPraticaSemana.setEnabled(false);

        jLabel18.setText("Gênero:");

        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));
        cbGenero.setEnabled(false);

        cbLateralidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Canhoto", "Destro", "Anbidestro" }));
        cbLateralidade.setEnabled(false);

        jLabel19.setText("Lateralidade:");

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/cancelar.png"))); // NOI18N
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setEnabled(false);

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/salvar.png"))); // NOI18N
        btSalvar.setToolTipText("Salvar");
        btSalvar.setEnabled(false);

        cbInstituicao.setEnabled(false);

        jLabel1.setText("Instituição:");

        dcNascimento.setEnabled(false);

        jLabel2.setText("Histórico de lesões e doenças:");

        taHistorico.setColumns(20);
        taHistorico.setRows(5);
        taHistorico.setEnabled(false);
        jScrollPane2.setViewportView(taHistorico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ftEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ftMassaCorporal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(tfEnvergadura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btSalvar)
                                .addGap(18, 18, 18)
                                .addComponent(btCancelar)
                                .addGap(65, 65, 65))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(cbLateralidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(81, 81, 81)
                                        .addComponent(jLabel19))
                                    .addComponent(jLabel30)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(ftTempoPratica, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ftPraticaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbInstituicao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(jLabel22)
                                    .addComponent(dcNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(149, 149, 149)
                                        .addComponent(jLabel17)))
                                .addGap(10, 10, 10)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(btNovoAtleta)
                                        .addGap(17, 17, 17)
                                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(btExcluir))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(tfNomePesquisaAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btPesquisar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btMostraTudo)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel30)
                                .addGap(6, 6, 6)
                                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(6, 6, 6)
                                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addGap(4, 4, 4)
                                .addComponent(dcNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel17))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftEstatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftMassaCorporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfEnvergadura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel1))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(6, 6, 6)
                                        .addComponent(ftTempoPratica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(6, 6, 6)
                                        .addComponent(ftPraticaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbLateralidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(tfNomePesquisaAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btPesquisar)
                                            .addComponent(btMostraTudo))))
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btExcluir, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btNovoAtleta, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSalvar))))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btCancelar;
    public javax.swing.JButton btEditar;
    public javax.swing.JButton btExcluir;
    public javax.swing.JButton btMostraTudo;
    public javax.swing.JButton btNovoAtleta;
    public javax.swing.JButton btPesquisar;
    public javax.swing.JButton btSalvar;
    public javax.swing.JComboBox cbGenero;
    public javax.swing.JComboBox cbInstituicao;
    public javax.swing.JComboBox cbLateralidade;
    public javax.swing.JComboBox cbNivel;
    public com.toedter.calendar.JDateChooser dcNascimento;
    public javax.swing.JFormattedTextField ftEstatura;
    public javax.swing.JFormattedTextField ftMassaCorporal;
    public javax.swing.JFormattedTextField ftPraticaSemana;
    public javax.swing.JFormattedTextField ftTempoPratica;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextArea taHistorico;
    public javax.swing.JTable tbAtleta;
    public javax.swing.JTextField tfEmail;
    public javax.swing.JFormattedTextField tfEnvergadura;
    public javax.swing.JTextField tfNome;
    public javax.swing.JTextField tfNomePesquisaAtleta;
    // End of variables declaration//GEN-END:variables
}
