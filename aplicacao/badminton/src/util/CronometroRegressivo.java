//CronometroRegressivo.java
package util;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import modelo.Atleta;

/**
 * @author 5102011212
 */
public class CronometroRegressivo {

    private Timer cronometro;
    private DateFormat formato = new SimpleDateFormat("mm:ss");
    private Calendar calendario = Calendar.getInstance();
    private final byte contagem;
    private Atleta athlete;

    public Atleta getAthlete() {
        return athlete;
    }

    public void setAthlete(Atleta athlete) {
        this.athlete = athlete;
    }

    public CronometroRegressivo(int ano, int mes, int dia, int horas, int minutos, int segundos, byte tipoContagem) {
        this.cronometro = new Timer();
        calendario.set(ano, mes, dia, horas, minutos, segundos);
        this.contagem = tipoContagem;
    }

    public void cronometro(final controle.AgilidadeEmQuadra agilidadeEmQuadra, final Atleta a, final controle.Execucao execucao) {
        setAthlete(a);
        final int index = execucao.model.indexRow(a);
        final Color old = a.getCor();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                String tempo = getTime();
                a.setTempo(tempo);
                execucao.model.fireTableCellUpdated(index, 1);
                if (tempo.equals("00:00")) {
                    agilidadeEmQuadra.atletasIntervaloDescansoMinimo.remove(a);
                    a.setCor(old);
                    a.setTempo(null);
                    execucao.model.updateAtleta(a);
                    //execucao.listaIntervalo.remove(a);
                    this.cancel();
                }
            }
        };
        cronometro.scheduleAtFixedRate(tarefa, 0, 1000);
        this.cronometro = null;
    }

    public String getTime() {
        calendario.add(Calendar.SECOND, contagem);
        String result = formato.format(calendario.getTime());
        return result;
    }
}
