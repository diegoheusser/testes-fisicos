//Rel.SelecionaAtletas.java
//Tela de seleção de atletas
package visao;

/**
 * @author Diego Heusser
 */
public class RelSelecionaAtletas extends javax.swing.JDialog {

    public RelSelecionaAtletas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        cbNivel = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbLateralidade = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btAdicionar = new javax.swing.JButton();
        ckTodos = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleção de Atletas");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(tfNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 28, 202, -1));

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","Masculino", "Feminino" }));
        getContentPane().add(cbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, 202, -1));

        jLabel2.setText("Gênero");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, -1));

        lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Atletas"));
        jScrollPane2.setViewportView(lista);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 11, 338, 228));

        cbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","Iniciante", "Intermediário", "Avançado" }));
        getContentPane().add(cbNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 202, -1));

        jLabel3.setText("Nível");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 109, -1, -1));

        cbLateralidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","Canhoto", "Destro", "Anbidestro" }));
        getContentPane().add(cbLateralidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 178, 202, -1));

        jLabel4.setText("Lateralidade");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 158, -1, -1));

        btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/lupa.png"))); // NOI18N
        btPesquisar.setText("Pesquisar");
        btPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 216, -1, -1));

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/cancel.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 216, 100, 30));

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/ok.png"))); // NOI18N
        btAdicionar.setText("OK");
        btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 250, -1, -1));

        ckTodos.setText("Todos");
        getContentPane().add(ckTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        setSize(new java.awt.Dimension(594, 322));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAdicionar;
    public javax.swing.JButton btCancelar;
    public javax.swing.JButton btPesquisar;
    public javax.swing.JComboBox cbGenero;
    public javax.swing.JComboBox cbLateralidade;
    public javax.swing.JComboBox cbNivel;
    public javax.swing.JCheckBox ckTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JList lista;
    public javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
