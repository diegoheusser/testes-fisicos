//Relatorio.java
//Tela para configuração dos protocolos
package visao;

/**
 * @author Diego Heusser
 */
public class Relatorio extends javax.swing.JPanel {

    public Relatorio() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgAgrupar = new javax.swing.ButtonGroup();
        bgTipo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liAtleta = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        liProtocolo = new javax.swing.JList();
        btAddAtleta = new javax.swing.JButton();
        btDelAtleta = new javax.swing.JButton();
        btAddProtocolo = new javax.swing.JButton();
        btDelProtocolo = new javax.swing.JButton();
        dcInicio = new com.toedter.calendar.JDateChooser();
        dcFim = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbInstituicao = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btGerar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rbAtleta = new javax.swing.JRadioButton();
        rbProtocolo = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        rbRAT = new javax.swing.JRadioButton();
        rbRET = new javax.swing.JRadioButton();
        rbHT = new javax.swing.JRadioButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        liAtleta.setBorder(javax.swing.BorderFactory.createTitledBorder("Atletas"));
        jScrollPane1.setViewportView(liAtleta);

        liProtocolo.setBorder(javax.swing.BorderFactory.createTitledBorder("Protocolos"));
        jScrollPane2.setViewportView(liProtocolo);

        btAddAtleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/add.png"))); // NOI18N
        btAddAtleta.setText("Adicionar");
        btAddAtleta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btDelAtleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/del.png"))); // NOI18N
        btDelAtleta.setText("Remover");
        btDelAtleta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btAddProtocolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/add.png"))); // NOI18N
        btAddProtocolo.setText("Adicionar");
        btAddProtocolo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btDelProtocolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/del.png"))); // NOI18N
        btDelProtocolo.setText("Remover");
        btDelProtocolo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAddAtleta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDelAtleta)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAddProtocolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btDelProtocolo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddAtleta, btAddProtocolo, btDelAtleta, btDelProtocolo});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddAtleta)
                    .addComponent(btDelAtleta)
                    .addComponent(btAddProtocolo)
                    .addComponent(btDelProtocolo))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddAtleta, btAddProtocolo, btDelAtleta, btDelProtocolo});

        jLabel1.setText("Data de início");

        jLabel2.setText("Data de fim");

        cbInstituicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInstituicaoActionPerformed(evt);
            }
        });

        jLabel3.setText("Instituição");

        btGerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/impressora.png"))); // NOI18N
        btGerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agrupar por:"));

        bgAgrupar.add(rbAtleta);
        rbAtleta.setText("Atleta");

        bgAgrupar.add(rbProtocolo);
        rbProtocolo.setText("Protocolo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAtleta)
                    .addComponent(rbProtocolo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(rbAtleta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbProtocolo))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Relatório"));

        bgTipo.add(rbRAT);
        rbRAT.setText("Relação Analítica de Testes");

        bgTipo.add(rbRET);
        rbRET.setText("Resumo de Evolução de Testes");

        bgTipo.add(rbHT);
        rbHT.setText("Histórico de Testes");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbRAT)
                        .addGap(29, 29, 29)
                        .addComponent(rbHT))
                    .addComponent(rbRET))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbRAT)
                    .addComponent(rbHT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbRET))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(dcFim, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btGerar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbInstituicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btGerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel2, jPanel3});

    }// </editor-fold>//GEN-END:initComponents

    private void cbInstituicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInstituicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInstituicaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgAgrupar;
    private javax.swing.ButtonGroup bgTipo;
    public javax.swing.JButton btAddAtleta;
    public javax.swing.JButton btAddProtocolo;
    public javax.swing.JButton btDelAtleta;
    public javax.swing.JButton btDelProtocolo;
    public javax.swing.JButton btGerar;
    public javax.swing.JComboBox cbInstituicao;
    public com.toedter.calendar.JDateChooser dcFim;
    public com.toedter.calendar.JDateChooser dcInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JList liAtleta;
    public javax.swing.JList liProtocolo;
    public javax.swing.JRadioButton rbAtleta;
    public javax.swing.JRadioButton rbHT;
    public javax.swing.JRadioButton rbProtocolo;
    public javax.swing.JRadioButton rbRAT;
    public javax.swing.JRadioButton rbRET;
    // End of variables declaration//GEN-END:variables
}
