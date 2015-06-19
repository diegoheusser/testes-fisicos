//CronometroExecucao.java
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;

/**
 *
 * @author 5102011212
 */
public class CronometroExecucao {

    private Timer timer;
    private JTextField tf;
    private Date initialTime;
    private Date atualTime;
    
    public CronometroExecucao() {
        this.initialTime = new Date();
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

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public Date getAtualTime() {
        return atualTime;
    }

    public void setAtualTime(Date atualTime) {
        this.atualTime = atualTime;
    }

    public void cronometro(JTextField tf) {
        setTimer(new Timer());
        setTf(tf);
        final SimpleDateFormat format = new SimpleDateFormat("mm:ss:SSS");
        this.timer.schedule(new TimerTask() {
            int cont = 0;

            @Override
            public void run() {
                try {
                    atualTime = new Date();
                    cont++;
                    getTf().setText(format.format(new Date((atualTime.getTime() - initialTime.getTime()))));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1);
    }
    
    public void stop(){
        this.timer.cancel();
    }
}
