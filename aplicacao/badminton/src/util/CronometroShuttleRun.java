//CronometroShuttlerun.java
package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * @author 5102011212
 */
public class CronometroShuttleRun extends Thread {

    private int mm, se, mi;
    private final int rowIndex;
    private final controle.ShuttleRun shuttleRun;
    private final modelo.Atleta umAtleta;
    private boolean continuar;

    public CronometroShuttleRun(controle.ShuttleRun shuttleRun, modelo.Atleta umAtleta, int rowIndex) {
        mm = 0;
        se = 0;
        mi = 0;
        this.rowIndex = rowIndex;
        this.shuttleRun = shuttleRun;
        this.umAtleta = umAtleta;
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
            if (se == 59) {
                se = 0;
                mm++;
                shuttleRun.tableModelOnline.setValueAt("" + (mm + 1), rowIndex, 1);
            } else if (mi == 999) {
                mi = 0;
                se++;
            }
            mi++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            shuttleRun.tableModelOnline.setValueAt(time(), rowIndex, 2);
        }
    }

    private String time() {
        String time = "";
        //minutos
        if (mm < 10) {
            time += "0" + mm;
        } else {
            time += mm;
        }
        //segundos
        if (se < 10) {
            time += ":0" + se;
        } else {
            time += ":" + se;
        }
        //miléssimos
        if (mi < 10) {
            time += ":00" + mi;
        } else if (mi < 100) {
            time += ":0" + mi;
        } else {
            time += ":" + mi;
        }
        return time;
    }

    private void beep() {
        try {
            File file = new File("src/visao/audio/beep-2.wav");
            AudioClip clip = Applet.newAudioClip(file.toURI().toURL());
            clip.play();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
}
