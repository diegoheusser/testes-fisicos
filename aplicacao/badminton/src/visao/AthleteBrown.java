/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import javax.swing.SwingWorker;

/**
 *
 * @author Diego
 */
public class AthleteBrown extends SwingWorker<Void, Void> {

    AnimationShuttleRun shuttleRun;
    public int x;//variavel referente a posição x do boneco no plano cartesiano
    public int y;//variavel referente a posição y do boneco no plano cartesiano
    public int velocidade = 44;//variavel para a velocidade em que o boneco vai se mover
    public boolean stop = false;//variavel para ver se está pausado
    public double distancia = 0;//variável referente a distância percorrida pelo boneco

    public AthleteBrown(AnimationShuttleRun tp, int x, int y) {
        this.shuttleRun = tp;
        this.x = x;
        this.y = y;

    }

    @Override
    protected Void doInBackground() throws Exception {// metodo do movimento do boneco
        while (!stop) {
            while (x < 870 && !stop) {//cone 1 para o cone 2
                x += 5;
                distancia += 0.1149425287356322;
                do {
                    Thread.sleep(velocidade);//tempo de espera
                } while (!shuttleRun.atualizaInterface(x, y, false, distancia, 1));//atualiza a interface e verifica se está pausado
            }
            beep();//método para emitir um sinal sonoro
            while (x > 0 && !stop) {//cone 2 para o cone 1
                x -= 5;
                distancia += 0.1149425287356322;
                do {
                    Thread.sleep(velocidade);//tempo de espera
                } while (!shuttleRun.atualizaInterface(x, y, true, distancia, 1));//atualiza a interface e verifica se está pausado
            }
            beep();//método para emitir um sinal sonoro
        }
        return null;
    }

    private void beep() {//método para emitir um sinal sonoro
        shuttleRun.elimina++;//variavel para mostrar a quantidade de beeps
        try {
            File file = new File("src/visao/audio/beep-2.wav");
            AudioClip clip = Applet.newAudioClip(file.toURI().toURL());
            clip.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
