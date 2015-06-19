package visao;

/**
 * @author 5102011212
 */
public class ProtFlexibilidadeDeOmbroEPunho extends javax.swing.JPanel {

    public ProtFlexibilidadeDeOmbroEPunho() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTexto = new javax.swing.JLabel();
        lbLink = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Teste de Flexibilidade de Ombor e Punho"));

        lbTexto.setText("<html> <b>Objetivo:</b> \n<br>• O objetivo deste teste é para acompanhar o desenvolvimento do ombro do atleta e flexibilidade pulso. \n<br><b>Descriçãol:</b> \n<br>• O atleta aquece por 10 minutos\n<br>• O atleta segura a corda com as mãos a frente do corpo e esticadas na largura dos ombros\n<br>• O atleta estende os braços por cima da cabeça e em seguida vai abaixando os braços em direção a parte inferior das costas (permitindo que as mãos deslizem pela corda)\n<br>• O assistente registra a medida em polegadas, entre os polegares do atleta.\n<br>• O assistente registra a medida em polegadas dos ombros do atleta\n<br>• O assistente subtrai a medida do ombro com a dos polegares (essa é a medida utilizada para avaliação)\n<br>• O teste é repetido 3 vezes e medida mais longa, é utilizada para avaliação do atleta.\n</html>");
        lbTexto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\">Static Flexibility Test – Shoulder. Disponível em:\n<br>  <a href=\"http://www.brianmac.co.uk/flextest4.htm \" target=_blank> http://www.brianmac.co.uk/flextest4.htm</a><br>  Acesso em : 14/08/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/fop.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
            .addComponent(lbLink, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLink, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel lbLink;
    private javax.swing.JLabel lbTexto;
    // End of variables declaration//GEN-END:variables
}
