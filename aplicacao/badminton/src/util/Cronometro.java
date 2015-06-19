//Cronometro.java
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author 5102011212
 */
public class Cronometro {

    private Timer timer;
    private Date atualTime;
    private Date initialTime;
    private Date previousTime;
    private JTextField tf;
    private boolean atulizaMinuto, cancel;

    public boolean getCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isAtulizaMinuto() {
        return atulizaMinuto;
    }

    public void setAtulizaMinuto(boolean atulizaMinuto) {
        this.atulizaMinuto = atulizaMinuto;
    }

    public Cronometro() {
        initialTime = new Date();
        previousTime = new Date(0);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public JTextField getTf() {
        return tf;
    }

    public void setTf(JTextField tf) {
        this.tf = tf;
    }

    public void cronometro(JTextField tf) {
        setTimer(new Timer());
        setTf(tf);
        final SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        this.timer.schedule(new TimerTask() {
            int cont = 0;

            public void run() {
                try {
                    atualTime = new Date();
                    cont++;
                    getTf().setText(format.format(new Date((atualTime.getTime() - initialTime.getTime()) + previousTime.getTime())));
                    if (cont == 57000) {
                        cont = 0;
                        setAtulizaMinuto(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1);
    }

    //método para parar o cronômetro
    public void stop() {
        this.timer.cancel();
        final SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        try {
            previousTime = format.parse(getTf().getText());
        } catch (ParseException ex) {
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //método para iniciar o cronômetro
    public void start() {
        initialTime = new Date();
        cronometro(getTf());
    }

    //método para cancelar o cronômetro
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
        initialTime = new Date();
        previousTime = new Date(0);
    }
}
