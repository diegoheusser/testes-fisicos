/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

/**
 *
 * @author 5102011212
 */
public class Protocolo extends javax.swing.JPanel {

    /**
     * Creates new form PProAgilidadeNaQuadra
     */
    public Protocolo() {
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

        cbProtocolos = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        btPDF = new javax.swing.JButton();
        btAnimacao = new javax.swing.JButton();

        jLabel33.setText("Protocolos:");

        jPanel.setLayout(new java.awt.CardLayout());

        btPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/pdf.png"))); // NOI18N
        btPDF.setText("Abrir");

        btAnimacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/play.png"))); // NOI18N
        btAnimacao.setText("Animação");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(cbProtocolos, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btAnimacao, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 305, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(btPDF)
                    .addComponent(cbProtocolos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAnimacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAnimacao;
    public javax.swing.JButton btPDF;
    public javax.swing.JComboBox cbProtocolos;
    private javax.swing.JLabel jLabel33;
    public javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables
}