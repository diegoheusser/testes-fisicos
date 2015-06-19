package controle;

import dao.AgilidadeEmQuadraDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.Status;
import modelo.Valido;
import util.CronometroRegressivo;

/**
 * * @author Diego Heusser
 */
public class AgilidadeEmQuadra {

    public String[] motivo = new String[3];
    private String[] preMotivos = new String[3];
    public int rodCont;
    public Date dataExecucao;
    //
    private controle.Execucao controle;
    //modelo
    public modelo.Atleta umAtleta;
    public List<modelo.Atleta> atletasIntervaloDescansoMinimo;
    //dao
    private dao.AgilidadeEmQuadraDAO agilidadeEmQuadraDAO;
    //visao
    private util.CronometroExecucao cronometro;
    public visao.Execucao execucao;
    public visao.AgilidadeEmQuadra agilidadeEmQuadra;
    public visao.Imagem imagem;
    public visao.RodadasOffline rodadasOffline;
    public visao.RodadasOnline rodadasOnline;

    public AgilidadeEmQuadra(visao.Execucao execucao, controle.Execucao controle) {
        //Controller
        this.controle = controle;
        //model
        umAtleta = null;
        atletasIntervaloDescansoMinimo = new ArrayList<>();
        //DAO
        agilidadeEmQuadraDAO = new dao.AgilidadeEmQuadraDAO();
        //view
        this.execucao = execucao;
        agilidadeEmQuadra = new visao.AgilidadeEmQuadra();
        agilidadeEmQuadra.rbOnline.setSelected(true);
        agilidadeEmQuadra.separadorEsquerda.setBackground(Color.red);
        agilidadeEmQuadra.separadorDireita.setForeground(Color.red);
        rodadasOffline = new visao.RodadasOffline();
        rodadasOnline = new visao.RodadasOnline();
        imagem = new visao.Imagem();
        //
        preMotivos[0] = "Não colocou um pé na caixa de transição";
        preMotivos[1] = "Derrubou os tubos";
        preMotivos[2] = "Outro";
        rodCont = 0;

        ouvintes();
    }

    private void ouvintes() {
        agilidadeEmQuadra.lbImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abreImagem();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                agilidadeEmQuadra.lbImage.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                agilidadeEmQuadra.lbImage.setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        agilidadeEmQuadra.rbOffline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agilidadeEmQuadra.rbOffline.isSelected()) {
                    panelOffline();
                }
            }
        });
        agilidadeEmQuadra.rbOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agilidadeEmQuadra.rbOnline.isSelected()) {
                    panelOnline();
                }
            }
        });
        rodadasOffline.btSaveOffline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offline();
            }
        });
        rodadasOffline.ckInvalidarRod1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOffline.ckInvalidarRod1, 1, null);
            }
        });
        rodadasOffline.ckInvalidarRod2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOffline.ckInvalidarRod2, 2, null);
            }
        });
        rodadasOffline.ckInvalidarRod3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOffline.ckInvalidarRod3, 3, null);
            }
        });
        rodadasOffline.ftRod1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validaCampo(rodadasOffline.ftRod1);
            }
        });
        rodadasOffline.ftRod2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validaCampo(rodadasOffline.ftRod2);
            }
        });
        rodadasOffline.ftRod3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validaCampo(rodadasOffline.ftRod3);
            }
        });
        agilidadeEmQuadra.btExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                online();
            }
        });
        rodadasOnline.ckRod1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOnline.ckRod1, 1, rodadasOnline.lbRod1);
            }
        });
        rodadasOnline.ckRod2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOnline.ckRod2, 2, rodadasOnline.lbRod2);
            }
        });
        rodadasOnline.ckRod3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidar(rodadasOnline.ckRod3, 3, rodadasOnline.lbRod3);
            }
        });
    }

    public void executar() {
        execucao.pExecution.removeAll();
        execucao.pExecution.add(this.agilidadeEmQuadra);
        execucao.pExecution.validate();
    }

    private void abreImagem() {
        imagem.setVisible(true);
    }

    //valida os campos de tempo usando expressões regulares
    public void validaCampo(JFormattedTextField campoFormatado) {
        /*Expressão regular utilizada
         * para formatar o tempo em: 
         * minutos:segundos:milisegundos
         *foi: [0-9]{2}:[012345][0-9]:[0-9]{3}*/
        Pattern padrao = Pattern.
                compile("[0-9]{2}:[012345][0-9]:[0-9]{3}");
        Matcher pesquisa = padrao.matcher(campoFormatado.getText());
        if (pesquisa.matches()) {
            campoFormatado.setForeground(Color.black);
        } else {
            campoFormatado.setForeground(Color.red);
        }
    }

    private void invalidar(JCheckBox caixaDeSelecao, int rodada, JLabel rotulo) {
        try {
            if (caixaDeSelecao.isSelected()) {
                motivo[rodada - 1] = (String) JOptionPane.showInputDialog(execucao, "Qual foi o Motivo?",
                        "Invalidar Rodada: " + rodada, JOptionPane.PLAIN_MESSAGE, new ImageIcon(
                        getClass().getResource("/view/imagens/invalidar.png")), preMotivos, preMotivos[0]);
                if (motivo[rodada - 1] != null) {
                    if (motivo[rodada - 1].equals("Outro")) {
                        motivo[rodada - 1] = (String) JOptionPane.showInputDialog(execucao, "Qual foi o Motivo?",
                                "Invalidar Rodada: " + rodada, JOptionPane.PLAIN_MESSAGE);
                    }
                }
                if (rotulo != null) {
                    rotulo.setForeground(Color.red);
                    dao.AgilidadeEmQuadraDAO dao = new AgilidadeEmQuadraDAO();
                    modelo.AgilidadeEmQuadra umaAgilidadeEmQuadra = new modelo.AgilidadeEmQuadra();
                    umaAgilidadeEmQuadra.setId(dao.maxExecucaoId(umAtleta.getId()));
                    umaAgilidadeEmQuadra.setAgilidadeEmQuadraID(
                            dao.agilidadeEmQuadraId(umaAgilidadeEmQuadra.getId(), rodada));
                    umaAgilidadeEmQuadra.setValido(Valido.INVALIDO);
                    umaAgilidadeEmQuadra.setMotivo(motivo[rodada-1]);
                    dao.invalida(umaAgilidadeEmQuadra);
                }
            } else {
                if (rotulo != null) {
                    rotulo.setForeground(Color.black);
                                        dao.AgilidadeEmQuadraDAO dao = new AgilidadeEmQuadraDAO();
                    modelo.AgilidadeEmQuadra umaAgilidadeEmQuadra = new modelo.AgilidadeEmQuadra();
                    umaAgilidadeEmQuadra.setId(dao.maxExecucaoId(umAtleta.getId()));
                    umaAgilidadeEmQuadra.setAgilidadeEmQuadraID(
                            dao.agilidadeEmQuadraId(umaAgilidadeEmQuadra.getId(), rodada));
                    umaAgilidadeEmQuadra.setValido(Valido.VALIDO);
                    umaAgilidadeEmQuadra.setMotivo(motivo[rodada-1]);
                    dao.invalida(umaAgilidadeEmQuadra);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(execucao, "Não foi possivel invalidar a rodada "+rodada,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void offline() {
        if (!rodadasOffline.ftRod1.getForeground().equals(Color.black)
                && !rodadasOffline.ftRod2.getForeground().equals(Color.black)
                && !rodadasOffline.ftRod3.getForeground().equals(Color.black)) {
            JOptionPane.showMessageDialog(execucao, "Campo inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            modelo.AgilidadeEmQuadra umaAgilidadeEmQuadra = new modelo.AgilidadeEmQuadra();
            JFormattedTextField[] lista = new JFormattedTextField[]{
                rodadasOffline.ftRod1, rodadasOffline.ftRod2, rodadasOffline.ftRod3};
            JCheckBox[] listaCaixa = new JCheckBox[]{
                rodadasOffline.ckInvalidarRod1, rodadasOffline.ckInvalidarRod2, rodadasOffline.ckInvalidarRod3};
            switch (rodCont) {
                case 0:
                    lacoRepeticao(umaAgilidadeEmQuadra, lista, listaCaixa, rodCont);
                    break;
                case 1:
                    lacoRepeticao(umaAgilidadeEmQuadra, lista, listaCaixa, rodCont);
                    break;
                default:
                    lacoRepeticao(umaAgilidadeEmQuadra, lista, listaCaixa, rodCont);
                    break;
            }
        }
    }

    private void lacoRepeticao(modelo.AgilidadeEmQuadra umaAgilidadeEmQuadra,
            JFormattedTextField[] camposFormatados, JCheckBox[] caixasDeSelecoes, int indice) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
            umaAgilidadeEmQuadra.setAtleta(umAtleta);
            umaAgilidadeEmQuadra.setData((Date) rodadasOffline.spDataHora.getValue());
            umaAgilidadeEmQuadra.setHora(sdf.format(rodadasOffline.spDataHora.getValue()));
            umaAgilidadeEmQuadra.setSuperficie((String) execucao.cbSuperficie.getSelectedItem());
            umaAgilidadeEmQuadra.setTemperatura((int) (execucao.spTemperatura.getValue()));
            umaAgilidadeEmQuadra.setValido(Valido.VALIDO);
            umaAgilidadeEmQuadra.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
            umaAgilidadeEmQuadra.setStatus(Status.NÃO_CONCLUIDO);
            for (int i = indice; i < camposFormatados.length; i++) {
                umaAgilidadeEmQuadra.setParcial(i + 1);
                umaAgilidadeEmQuadra.setTempo(camposFormatados[i].getText());
                if (i == 0) {
                    dao.save(umaAgilidadeEmQuadra, ((modelo.Agenda) execucao.cbData.getSelectedItem()).getId());
                }
                umaAgilidadeEmQuadra.setId(dao.maxExecucaoId(umAtleta.getId()));
                if (caixasDeSelecoes[i].isSelected()) {
                    umaAgilidadeEmQuadra.setValido(Valido.INVALIDO);
                    umaAgilidadeEmQuadra.setMotivo(motivo[i]);
                } else {
                    umaAgilidadeEmQuadra.setValido(Valido.VALIDO);
                    umaAgilidadeEmQuadra.setMotivo(null);
                }
                dao.save(umaAgilidadeEmQuadra);
                if (i == 2) {
                    umaAgilidadeEmQuadra.setStatus(Status.CONCLUIDO);
                    umaAgilidadeEmQuadra.setId(dao.maxExecucaoId(umAtleta.getId()));
                    umaAgilidadeEmQuadra.setAgilidadeEmQuadraID(dao.agilidadeEmQuadraId(
                            umaAgilidadeEmQuadra.getId(), 3));
                    umAtleta.setCor(Color.green);
                    ((visao.modelosComponentes.ExecucaoTableModel) execucao.tbExecution.getModel()).updateAtleta(umAtleta);
                    dao.update(umaAgilidadeEmQuadra.getId(), umaAgilidadeEmQuadra.getStatus());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(AgilidadeEmQuadra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void panelOffline() {
        agilidadeEmQuadra.pRodadas.removeAll();
        agilidadeEmQuadra.pRodadas.add(rodadasOffline);
        agilidadeEmQuadra.pRodadas.repaint();
        limparCampos();
        verificaCamposOnlineOffline();
        abilitaCronometro(false);
    }

    public void panelOnline() {
        agilidadeEmQuadra.pRodadas.removeAll();
        agilidadeEmQuadra.pRodadas.add(rodadasOnline);
        agilidadeEmQuadra.pRodadas.repaint();
        limparCampos();
        verificaCamposOnlineOffline();
        abilitaCronometro(true);
    }

    private void abilitaCronometro(boolean b) {
        agilidadeEmQuadra.separadorDireita.setVisible(b);
        agilidadeEmQuadra.separadorEsquerda.setVisible(b);
        agilidadeEmQuadra.tfCrono.setVisible(b);
        agilidadeEmQuadra.btExecutar.setVisible(b);
    }

    private void limparCampos() {
        //offline
        rodadasOffline.ckInvalidarRod1.setSelected(false);
        rodadasOffline.ckInvalidarRod2.setSelected(false);
        rodadasOffline.ckInvalidarRod3.setSelected(false);
        rodadasOffline.ftRod1.setText(null);
        rodadasOffline.ftRod2.setText(null);
        rodadasOffline.ftRod3.setText(null);
        rodadasOffline.ftRod1.setValue(null);
        rodadasOffline.ftRod2.setValue(null);
        rodadasOffline.ftRod3.setValue(null);
        //online
        rodadasOnline.ckRod1.setSelected(false);
        rodadasOnline.ckRod2.setSelected(false);
        rodadasOnline.ckRod3.setSelected(false);
        rodadasOnline.lbRod1.setText("Rodada 1:");
        rodadasOnline.lbRod2.setText("Rodada 2:");
        rodadasOnline.lbRod3.setText("Rodada 3:");
    }

    private void verificaCamposOnlineOffline() {
        try {
            dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
            List<modelo.AgilidadeEmQuadra> listaAgilidadeEmQuadra = dao.rodadas(
                    dao.maxExecucaoId(umAtleta.getId()),
                    ((modelo.Protocolo) execucao.cbProtocol.getSelectedItem()).getId());

            JCheckBox[] caixasDeSelecoesOff = new JCheckBox[]{rodadasOffline.ckInvalidarRod1,
                rodadasOffline.ckInvalidarRod2, rodadasOffline.ckInvalidarRod3};
            JCheckBox[] caixasDeSelecoesOn = new JCheckBox[]{rodadasOnline.ckRod1,
                rodadasOnline.ckRod2, rodadasOnline.ckRod3};
            JLabel[] rotulos = new JLabel[]{rodadasOnline.lbRod1, rodadasOnline.lbRod2,
                rodadasOnline.lbRod3};
            JFormattedTextField[] camposFormatados = new JFormattedTextField[]{
                rodadasOffline.ftRod1, rodadasOffline.ftRod2, rodadasOffline.ftRod3};
            for (int i = 0; i < listaAgilidadeEmQuadra.size(); i++) {
                rotulos[i].setText("Rodada " + listaAgilidadeEmQuadra.get(i).getParcial()
                        + ":    " + listaAgilidadeEmQuadra.get(i).getTempo());
                camposFormatados[i].setText(listaAgilidadeEmQuadra.get(i).getTempo());
                if (listaAgilidadeEmQuadra.get(i).getValido() == Valido.VALIDO) {
                    rotulos[i].setForeground(Color.black);
                    caixasDeSelecoesOn[i].setSelected(false);
                    caixasDeSelecoesOff[i].setSelected(false);
                } else {
                    rotulos[i].setForeground(Color.red);
                    caixasDeSelecoesOn[i].setSelected(true);
                    caixasDeSelecoesOff[i].setSelected(true);
                    motivo[i] = listaAgilidadeEmQuadra.get(i).getMotivo();
                }
                rodCont++;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void online() {
        if (umAtleta == null) {
            JOptionPane.showMessageDialog(execucao, "Selecione um atleta antes de executar!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!atletasIntervaloDescansoMinimo.contains(umAtleta)) {
            if (agilidadeEmQuadra.btExecutar.getText().equals("Executar")) {
                agilidadeEmQuadra.btExecutar.setText("Gravar");
                cronometro = new util.CronometroExecucao();
                cronometro.cronometro(agilidadeEmQuadra.tfCrono);
                if (rodCont == 0) {
                    dataExecucao = new Date();
                }
            } else {
                if (rodCont != 3) {
                    agilidadeEmQuadra.btExecutar.setText("Executar");
                    cronometro.stop();
                    marcaRodada();
                    rodCont++;
                } else {
                    rodCont = 0;
                }
            }
        } else {
            JOptionPane.showMessageDialog(execucao, umAtleta.getNome()
                    + " ainda está no período minimo de descanso, de 2 minutos", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void marcaRodada() {
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
        dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
        modelo.AgilidadeEmQuadra umaAgilidadeEmQuadra = new modelo.AgilidadeEmQuadra();
        //generalização
        int id = 0;
        try {
            id = dao.maxExecucaoId(umAtleta.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (id != 0) {
            umaAgilidadeEmQuadra.setId(id);
        }
        umaAgilidadeEmQuadra.setAtleta(umAtleta);
        try {
            umaAgilidadeEmQuadra.setData(dataExecucao);
        } catch (ParseException ex) {
            Logger.getLogger(AgilidadeEmQuadra.class.getName()).log(Level.SEVERE, null, ex);
        }
        umaAgilidadeEmQuadra.setHora(sdfHora.format(dataExecucao));
        umaAgilidadeEmQuadra.setStatus(Status.NÃO_CONCLUIDO);
        umaAgilidadeEmQuadra.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
        umaAgilidadeEmQuadra.setSuperficie((String) execucao.cbSuperficie.getSelectedItem());
        umaAgilidadeEmQuadra.setTemperatura((int) execucao.spTemperatura.getValue());
        //Especialização
        umaAgilidadeEmQuadra.setTempo(agilidadeEmQuadra.tfCrono.getText());
        umaAgilidadeEmQuadra.setValido(Valido.VALIDO);
        umaAgilidadeEmQuadra.setMotivo(null);
        umaAgilidadeEmQuadra.setParcial(rodCont + 1);
        JCheckBox[] caixasDeSelecoes = new JCheckBox[]{rodadasOnline.ckRod1,
            rodadasOnline.ckRod2, rodadasOnline.ckRod3};
        JLabel[] rotulos = new JLabel[]{rodadasOnline.lbRod1, rodadasOnline.lbRod2,
            rodadasOnline.lbRod3};
        for (int i = rodCont; i <= rodCont; i++) {
            rotulos[i].setText("Rodada " + (i + 1) + ":    "
                    + agilidadeEmQuadra.tfCrono.getText());
            caixasDeSelecoes[i].setEnabled(true);
            if (caixasDeSelecoes[i].isSelected()) {
                umaAgilidadeEmQuadra.setValido(Valido.INVALIDO);
                umaAgilidadeEmQuadra.setMotivo(motivo[i]);
            }
            try {
                if (i == 0) {
                    dao.save(umaAgilidadeEmQuadra, ((modelo.Agenda) execucao.cbData.getSelectedItem()).getId());
                    umaAgilidadeEmQuadra.setId(dao.maxId());
                    dao.save(umaAgilidadeEmQuadra);
                } else {
                    dao.save(umaAgilidadeEmQuadra);
                }
                if (rodCont == 2) {
                    umaAgilidadeEmQuadra.setStatus(Status.CONCLUIDO);
                    umaAgilidadeEmQuadra.setId(dao.maxExecucaoId(umAtleta.getId()));
                    umaAgilidadeEmQuadra.setAgilidadeEmQuadraID(dao.agilidadeEmQuadraId(
                            umaAgilidadeEmQuadra.getId(), 3));
                    umAtleta.setCor(Color.green);
                    ((visao.modelosComponentes.ExecucaoTableModel) execucao.tbExecution.getModel()).updateAtleta(umAtleta);
                    dao.update(umaAgilidadeEmQuadra.getId(), umaAgilidadeEmQuadra.getStatus());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (!atletasIntervaloDescansoMinimo.contains(umAtleta)) {
            atletasIntervaloDescansoMinimo.add(umAtleta);
            byte a = -1;
            util.CronometroRegressivo regressivo = new CronometroRegressivo(0, 0, 0, 0, 2, 0, a);
            regressivo.cronometro(this, umAtleta, controle);
            umAtleta.setCor(Color.yellow);
            controle.model.updateAtleta(umAtleta);
        }
    }
}
