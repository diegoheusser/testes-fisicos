// BeepShuttleRun.java
// Calcula e emite os beeps do teste shuttle run
// em um determinado intervalo de tempo
package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 5102011212
 */
public class BeepShuttleRun extends Thread {

    private int tempo;
    private double velocidade;
    private int distancia;
    private int n, m;
    private File file;
    private AudioClip clip;
    private boolean continuar;

    public BeepShuttleRun(double velocidade, int distancia) {
        setTempo(distancia, velocidade);
        this.velocidade = velocidade;
        this.distancia = distancia;
        file = new File("src/visao/audio/beep-2.wav");
        try {
            clip = Applet.newAudioClip(file.toURI().toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(BeepShuttleRun.class.getName()).log(Level.SEVERE, null, ex);
        }
        n = 0;
        m = 0;
        continuar = true;
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public int getTempo() {
        return tempo;
    }

    private void setTempo(int distancia, double velocidade) {
        this.tempo = (int) ((distancia/((velocidade * 1000)/3600))*1000);
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    private void beep() {
        try {
            clip.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(continuar) {
            if(n == tempo){
                beep();
                n = 0;
            }
            if(m == 59999){
                m = 0;
                velocidade += 0.5;
                setTempo(distancia, velocidade);
            }
            n++;
            m++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(BeepShuttleRun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
