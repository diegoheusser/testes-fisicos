/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visao;

/**
 *
 * @author 5102011212
 */
public class ProtSentarEAlcancar extends javax.swing.JPanel {

    /**
     * Creates new form ProtSentarEAlcancar
     */
    public ProtSentarEAlcancar() {
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

        lbLink = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Teste Sentar e Alcançar"));

        lbLink.setText("<html><p style=\"font-size: 11px;\"><b>Referências:</b></p><p style=\" text-align:justify;\"> Badminton Australia. National Junior Program Fitness Testing Protocols. Jan 2008. Disponível em:<br>  <a href=\"http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ \" target=_blank> http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CDIQFjAA&url=%3Cbr%3Ehttp%3A%2F%2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3D256038&ei=n45cU ba2H%3Cbr%3EMPl0gHQsIDQDg&usg=AFQjCNHisJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ</a><br>  Acesso em : 04/07/2013.</p></html>");
        lbLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visao/imagens/sentarealcancar.png"))); // NOI18N

        jLabel1.setText("<html>\n<b>Equipamentos:</b>\n<br><br>Uma caixa de sentar e alcançar (ou pode ser usada uma régua sobre as pernas para fazer a medição).\n<br><br><b>Descrição:</b>\n<br><br>Este teste envolve sentar-se no chão com as pernas para frente. Com os pés sem sapatos, onde são colocados com a sola plana contra a caixa, na largura dos ombros. Ambos os joelhos estão na posição horizontal contra o chão pelo testador. Com as mãos em cima uns dos outros e as palmas viradas para baixo, o sujeito chega para frente ao longo da linha de medição, tanto quanto possível. Depois de três trechos de prática, a quarta é mantida por pelo menos dois segundos, enquanto o é gravado. Certifique-se que não há nenhum movimento irregular e que as pontas dos dedos permanecem no nível, e as pernas planas.\n<br><br><b>Pontuação:</b>\n<br><br>A pontuação é registrada para o centímetro mais próximo que a distância antes (negativo) ou além (positivo) dos dedos.\n</html>");

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