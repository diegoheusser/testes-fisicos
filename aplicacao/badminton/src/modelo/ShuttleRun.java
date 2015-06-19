//ShuttleRun.java
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Diego Heusser
 */
public class ShuttleRun extends Execucao {
    
    private int shuttleRunId;
    private String tempo;
    private int nivel;

    public ShuttleRun() {
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
        nivel = 1;
        tempo = "00:00:000";
        data = new Date();
        hora = sdfHora.format(new Date());
    }
    
    public int getShuttleRunId() {
        return shuttleRunId;
    }

    public void setShuttleRunId(int shuttleRunId) {
        this.shuttleRunId = shuttleRunId;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

}
