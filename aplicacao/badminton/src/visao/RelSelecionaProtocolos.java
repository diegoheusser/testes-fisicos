//RelSelecionaProtocolos.java
//Tela para selecionar os protcolos
package visao;

/**
 * @author Diego Heusser
 */
public class RelSelecionaProtocolos extends javax.swing.JDialog {

    public RelSelecionaProtocolos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btAdicionar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        tfNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btPesquisar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        ckTodos = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleção de Protocolos");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/ok.png"))); // NOI18N
        btAdicionar.setText("OK");
        getContentPane().add(btAdicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(491, 250, -1, -1));

        lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Protocolos"));
        jScrollPane2.setViewportView(lista);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 11, 338, 228));
        getContentPane().add(tfNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 28, 210, -1));

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
            "","Agilidade", "Coordenação e Tempo de Reação","Equilíbrio","Flexibilidade","Flexibilidade e Força","Força","Resistência Anaeróbica","Resistência Anaeróbica e velocidade","Tempo de Reação" }));
getContentPane().add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, 210, -1));

jLabel2.setText("Tipo");
getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 57, -1, -1));

btPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/lupa.png"))); // NOI18N
btPesquisar.setText("Pesquisar");
getContentPane().add(btPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/cancel.png"))); // NOI18N
btCancelar.setText("Cancelar");
getContentPane().add(btCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 100, 30));

ckTodos.setText("Todos");
getContentPane().add(ckTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

setSize(new java.awt.Dimension(599, 324));
setLocationRelativeTo(null);
}// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAdicionar;
    public javax.swing.JButton btCancelar;
    public javax.swing.JButton btPesquisar;
    public javax.swing.JComboBox cbTipo;
    public javax.swing.JCheckBox ckTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JList lista;
    public javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
