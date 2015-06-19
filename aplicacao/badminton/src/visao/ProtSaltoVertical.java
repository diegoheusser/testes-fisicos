//ProtSaltoVertical.java
package visao;

/**
 * @author Diego Heusser
 */
public class ProtSaltoVertical extends javax.swing.JPanel {

    public ProtSaltoVertical() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbLink = new javax.swing.JLabel();

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/saltoVertical.png"))); // NOI18N

        jLabel1.setText("<html>\n<b>Equipamentos:</b>\n<br><br>Fita métrica ou uma parede marcada, giz para marcação de parede. Ou Vertec equipamento é o preferido para este teste.\n<br><br><b>Descrição:</b>\n<br><br>O atleta fica de lado a uma parede, e atinge-se com o lado mais próximo da parede. Manter os pés no chão, o ponto em as pontas dos dedos alcança na parede sem pular é marcado. Isso é chamado de alcance de pé. Então o atleta se afasta da parede, e salta verticalmente tão elevado quanto possível, utilizando ambos os braços e pernas <br>para apoiar o corpo na projeção para cima. Na tentativa de tocar a parede no ponto mais alto do salto. A diferença de distância entre a altura, de pé e a altura do salto é a pontuação. O melhor de três tentativas é registrado.\n<br><br><b>Pontuação:</b>\n<br><br>A altura do salto vertical normalmente é registrada como a pontuação na distância.\n</html>");

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\"> Badminton Australia. National Junior Program Fitness Testing Protocols. Jan 2008. Disponível em:<br>  <a href=\"http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ \" target=_blank> http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=%3Cbr%3Ehttp%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cU ba2H%3Cbr%3EMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ</a><br>  Acesso em : 04/07/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                    .addComponent(lbLink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 616, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
