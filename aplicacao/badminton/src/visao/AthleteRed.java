/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import javax.swing.SwingWorker;

/**
 *
 * @author Diego
 */
public class AthleteRed extends SwingWorker<Void, Void> {

    AnimationShuttleRun shuttleRun;
    public int x;//variavel referente a posição x do boneco no plano cartesiano
    public int y;//variavel referente a posição y do boneco no plano cartesiano
    public int velocidade = 69;//variavel para a velocidade em que o boneco vai se mover
    public boolean stop = false;//variavel para ver se está pausado
    public boolean elimina = false;
    public double distancia = 0;//variável referente a distância percorrida pelo boneco

    public AthleteRed(AnimationShuttleRun tp, int x, int y) {
        this.shuttleRun = tp;
        this.x = x;
        this.y = y;

    }

    @Override
    protected Void doInBackground() throws Exception {// metodo do movimento do boneco
        while (!stop) {
            while (x < 750 && !stop && !elimina) {//cone 1 para o cone 2
                x += 5;
                distancia += 0.1149425287356322;
                do {
                    Thread.sleep(velocidade);//tempo de espera
                } while (!shuttleRun.atualizaInterface(x, y, false, distancia, 3));//atualiza a interface e verifica se está pausado
            }
            while (x > 190 && !stop && !elimina) {//cone 2 para o cone 1
                x -= 5;
                distancia += 0.1149425287356322;
                do {
                    Thread.sleep(velocidade);//tempo de espera
                } while (!shuttleRun.atualizaInterface(x, y, true, distancia, 3));//atualiza a interface e verifica se está pausado
            }
            while (x < 1200 && elimina) {
                x += 5;
                do {
                    Thread.sleep(velocidade);//tempo de espera
                } while (!shuttleRun.atualizaInterface(x, y, false, distancia, 3));//atualiza a interface e verifica se está pausado
            }
        }
        return null;
    }
}
