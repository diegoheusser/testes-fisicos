package visao;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Diego Heusser
 */
public class RodadasOffline extends javax.swing.JPanel {

    public RodadasOffline() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        ftRod1 = new javax.swing.JFormattedTextField();
        ckInvalidarRod1 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        ftRod2 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        ckInvalidarRod2 = new javax.swing.JCheckBox();
        ftRod3 = new javax.swing.JFormattedTextField();
        ckInvalidarRod3 = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        spDataHora = new javax.swing.JSpinner();
        btSaveOffline = new javax.swing.JButton();

        jLabel17.setText("Rodada 1:");

        try {
            ftRod1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftRod1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftRod1.setToolTipText("minutos : segundos : milisegundos");

        ckInvalidarRod1.setText("inválido");

        jLabel18.setText("Rodada 2:");

        try {
            ftRod2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftRod2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftRod2.setToolTipText("minutos : segundos : milisegundos");

        jLabel19.setText("Rodada 3:");

        ckInvalidarRod2.setText("inválido");

        try {
            ftRod3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftRod3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ftRod3.setToolTipText("minutos : segundos : milisegundos");

        ckInvalidarRod3.setText("inválido");

        jLabel20.setText("Data:");

        spDataHora.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1378147141932L), null, null, java.util.Calendar.MINUTE));

        btSaveOffline.setText("Salvar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftRod2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftRod3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftRod1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckInvalidarRod1)
                            .addComponent(ckInvalidarRod2)
                            .addComponent(ckInvalidarRod3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btSaveOffline, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(spDataHora)))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btSaveOffline, ftRod1, ftRod2, ftRod3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(ftRod1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckInvalidarRod1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(ftRod2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckInvalidarRod2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(ftRod3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckInvalidarRod3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(spDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSaveOffline)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btSaveOffline;
    public javax.swing.JCheckBox ckInvalidarRod1;
    public javax.swing.JCheckBox ckInvalidarRod2;
    public javax.swing.JCheckBox ckInvalidarRod3;
    public javax.swing.JFormattedTextField ftRod1;
    public javax.swing.JFormattedTextField ftRod2;
    public javax.swing.JFormattedTextField ftRod3;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JSpinner spDataHora;
    // End of variables declaration//GEN-END:variables
}
