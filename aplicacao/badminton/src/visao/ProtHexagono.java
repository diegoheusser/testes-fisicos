// ProtHexagono.java
package visao;

/**
 * @author Diego Heusser
 */
public class ProtHexagono extends javax.swing.JPanel {

    public ProtHexagono() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbLink = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Teste do Hexágono"));

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\"> Topend Sport. Hexagon Agility Test. Disponível em:<br>  <a href=\"http://www.topendsports.com/testing/tests/hexagon.htm \" target=_blank>http://www.topendsports.com/testing/tests/hexagon.htm</a><br>  Acesso em : 14/08/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/hexagono.png"))); // NOI18N

        jLabel1.setText("<html>\n<b>Equipamentos:</b>\n<br><br>Fita métrica, giz ou fita adesiva para marcar terreno, cronômetro.\n<br><br><b>Descrição:</b>\n<br><br>Usando fita atlética, marcar um hexágono (seis forma unilateral) no chão. O comprimento de cada lado deve ser de 24 polegadas (60.5 cm), e cada ângulo deve trabalhar-se 120 graus. A pessoa a ser testada começa com os dois pés juntos no meio do hexágono de frente para a linha da frente. Ao iniciar, eles pulam na frente do outro lado da linha, então para trás ao longo da mesma linha no meio do hexágono. Então, continuando a olhar para frente com os pés juntos, saltar sobre o próximo lado e volta para o hexágono. Continue este padrão por três voltas completas. Realizar o teste tanto no sentido horário e anti-horário.\n<br><br><b>Pontuação:</b>\n<br><br>A pontuação atletas é o tempo necessário para completar três voltas completas. O melhor resultado de dois circuitos é registrado. A comparação das direções anti-horária e horária vai mostrar se existem desequilíbrios entre habilidades de movimento para a esquerda e para a direita.\n</html>");

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addComponent(lbLink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE))
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
