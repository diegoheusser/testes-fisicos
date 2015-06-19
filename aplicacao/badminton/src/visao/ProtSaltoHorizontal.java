package visao;

/**
 * @author Diego Heusser
 */
public class ProtSaltoHorizontal extends javax.swing.JPanel {

    public ProtSaltoHorizontal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbLink = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Teste  Salto Horizontal"));
        setPreferredSize(new java.awt.Dimension(631, 526));

        jLabel1.setText("<html><b>Equipamentos:</b>\n<br><br>Fita métrica para medir a distância do salto, piso antiderrapante para a decolagem, e área de <br>pouso suave. A linha de decolagem deve ser claramente identificada.\n<br><br><b>Descrição:</b>\n<br><br>O atleta fica atrás de uma linha marcada no chão, com os pés ligeiramente afastados. Com <br>balanço dos braços e flexão dos joelhos o atleta se salta para frente. As tentativas para <br>saltar do atleta, tanto quanto possível, no pouso pode ser em um ou ambos os pés. Três <br>tentativas são permitidas.\n<br><br><b>Pontuação:</b>\n<br><br>A medição é feita a partir da linha de decolagem até o ponto mais próximo de contato no <br>patamar com a parte de trás dos calcanhares. Grave a maior distância do salto entre as três <br>tentativas.\n</html>");

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\"> Badminton Australia. National Junior Program Fitness Testing Protocols. Jan 2008. Disponível em:<br>  <a href=\"http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ \" target=_blank> http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=%3Cbr%3Ehttp%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cU ba2H%3Cbr%3EMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ</a><br>  Acesso em : 04/07/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/saltoHorizontal.png"))); // NOI18N

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
                    .addComponent(jLabel1)
                    .addComponent(lbLink, javax.swing.GroupLayout.PREFERRED_SIZE, 616, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
