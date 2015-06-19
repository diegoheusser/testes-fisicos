//ProtCegonha.java
package visao;

/**
 * @author Diego Heusser
 */
public class ProtCegonha extends javax.swing.JPanel {

    public ProtCegonha() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLink = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Teste da Cegonha"));

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\">Topend Sports. Stork Balance Stand Test Disponível em:<br>  <a href=\"http://www.topendsports.com/testing/tests/balance-stork.htm \" target=_blank> http://www.topendsports.com/testing/tests/balance-stork.htm</a><br>  Acesso em : 14/08/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/cegonha.png"))); // NOI18N

        jLabel1.setText("<html>\n<b>Equipamentos:</b>\n<br><br>Superfície plana e antiderrapante, cronômetro, papel e lápis.\n<br><br><b>Descrição:</b>\n<br><br>Retirar os sapatos e colocar as mãos nos quadris, em seguida, posicione o pé não sustentável contra o joelho de dentro da perna de apoio. O sujeito é dado um minuto para praticar o equilíbrio. O sujeito levanta o calcanhar para equilibrar sobre a ponta do pé. O cronômetro é iniciado quando o calcanhar é levantado do chão. O cronômetro é parado se:\n<br>- Se qualquer uma das mãos saírem dos quadris.\n<br>- Se o pé de apoio mover ou saltar em qualquer direção.\n<br>- Se o pé não sustentável perde o contato com o joelho.\n<br>- Se o calcanhar do pé de apoio tocar o chão.\n<br><br><b>Pontuação:</b>\n<br><br>O tempo total em segundos é gravado. A pontuação é a melhor de três tentativas.\n</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addComponent(lbLink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLink, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel lbLink;
    // End of variables declaration//GEN-END:variables
}
