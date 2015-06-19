// CronometroTeste.java
package util;

import controle.Teste;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Diego Heusser
 */
public class CronometroTeste extends Thread{

    private final int linha, coluna;
    private int t;
    private final controle.Teste teste;
    private boolean continuar;

    public CronometroTeste(int linha, int coluna, Teste teste) {
        this.linha = linha;
        this.coluna = coluna;
        this.teste = teste;
        t = 0;
        continuar = true;
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }
    
    @Override
    public void run() {
        while (continuar) {
            teste.tableModel.setValueAt(""+t,linha, coluna);
            t++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CronometroTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
