//Protocolo.java
//Classe de controle dos protocolos
package controle;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import visao.ProtAgilidadeEmQuadra;
import visao.ProtFlexibilidadeDeOmbroEPunho;
import visao.ProtShuttleRun;
import visao.AnimationAgilidadeEmQuadra;
import visao.AnimationShuttleRun;
import visao.ProtCegonha;
import visao.ProtHexagono;
import visao.ProtLancamentoDeBolasNaParede;
import visao.ProtMusculaturaDoCore;
import visao.ProtRepeticaoAgachamento;
import visao.ProtSaltoHorizontal;
import visao.ProtSaltoVertical;
import visao.ProtSentarEAlcancar;
import visao.ProtTempoReacaoRegua;

/**
 * * @author Diego Heusser
 */
public class Protocolo {

    private visao.Protocolo protocolo;
    private visao.MenuPrincipal mp;

    public Protocolo() {
        protocolo = new visao.Protocolo();
        listener();
        components();
    }

    public Protocolo(visao.MenuPrincipal mp) {
        this.mp = mp;
        protocolo = new visao.Protocolo();
        listener();
        components();
    }

    private void listener() {
        protocolo.cbProtocolos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openProtocol();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        protocolo.btPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPDF();
            }
        });
        protocolo.btAnimacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAnimation();
            }
        });
    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(protocolo);
        mp.jPanel.validate();
    }

    private void components() {
        //Abre o protocolo 
        ProtAgilidadeEmQuadra aq = new ProtAgilidadeEmQuadra();
        protocolo.jPanel.add(aq);
        protocolo.jPanel.validate();
        protocolo.jPanel.repaint();
        //Preenche o combo box com todos os protocolos
        fillComboBoxWithAllProtocol();
    }

    private void fillComboBoxWithAllProtocol() {
        try {
            List<modelo.Protocolo> arrayProtocol = new dao.ProtocoloDAO().protocol();
            modelo.Protocolo protocol = null;
            for (int i = 0; i < arrayProtocol.size(); i++) {
                protocol = arrayProtocol.get(i);
                protocolo.cbProtocolos.addItem(protocol);
            }
            protocolo.cbProtocolos.setSelectedIndex(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void openPDF() {
        try {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();//Usado para abrir arquivos
            modelo.Protocolo umProtocolo = (modelo.Protocolo) protocolo.cbProtocolos.getSelectedItem();
            switch (umProtocolo.getId()) {
                case 0:
                    System.out.println("0");
                    break;
                case 1:
                    //Agilidade em Quadra
                    desktop.open(new File(getClass().getResource("/visao/protocolos/agilidadeemquadra.pdf").getFile()));//abre o pdf 
                    break;
                case 2:
                    //ShuttleRun
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testede20metrosdecorrida.pdf").getFile()));//abre o pdf
                    break;
                case 3:
                    //Flexibilidade de Ombro e punho
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testedeflexibilidadedoombroepunho.pdf").getFile()));//abre o pdf
                    break;
                case 4:
                    //Teste de flexibilidade de pernas 
                    break;
                case 5:
                    //Lançamento de bolas na parede 
                    desktop.open(new File(getClass().getResource("/visao/protocolos/lancamentodebolasnaparede.pdf").getFile()));//abre o pdf
                    break;
                case 6:
                    //Repetição de agachamento 20 
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testederepeticaodeagachamento.pdf").getFile()));//abre o pdf
                    break;
                case 7:
                    //Sprint Fatigue Test 
                    break;
                case 8:
                    //Teste da cegonha 
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testedacegonha.pdf").getFile()));//abre o pdf
                    break;
                case 9:
                    //Musculatura do Core 
                    desktop.open(new File(getClass().getResource("/visao/protocolos/musculaturadocore.pdf").getFile()));//abre o pdf
                    break;
                case 10:
                    //Salto Vertical
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testesaltovertical.pdf").getFile()));//abre o pdf
                    break;
                case 11:
                    //Salto Horizontal
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testesaltohorizontal.pdf").getFile()));//abre o pdf
                    break;
                case 12:
                    //Teste do Hexágono
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testedohexagono.pdf").getFile()));//abre o pdf
                    break;
                case 13:
                    //Tempo de reação Régua
                    desktop.open(new File(getClass().getResource("/visao/protocolos/tempodereacaoregua.pdf").getFile()));//abre o pdf
                    break;
                case 14:
                    //Agachar e pegar peteca 
                    break;
                case 15:
                    //Sentar e Alcançar
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testesentarealcancar.pdf").getFile()));//abre o pdf
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mp, "Erro ao abrir PDF: " + e.getMessage());//mensagem que aparece se aconter algum erro
        }
    }

    private void openProtocol() throws Exception {
        modelo.Protocolo umProtocolo = (modelo.Protocolo) protocolo.cbProtocolos.getSelectedItem();
        switch (umProtocolo.getId()) {
            case 0:
                break;
            case 1:
                ProtAgilidadeEmQuadra aq = new ProtAgilidadeEmQuadra();
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(aq);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(true);
                break;
            case 2:
                ProtShuttleRun p20 = new ProtShuttleRun();
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(p20);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(true);
                break;
            case 3:
                ProtFlexibilidadeDeOmbroEPunho pFOP = new ProtFlexibilidadeDeOmbroEPunho();
                pFOP.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.brianmac.co.uk/flextest4.htm");
                    }

                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pFOP);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 4:
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 5:
                ProtLancamentoDeBolasNaParede pLBP = new ProtLancamentoDeBolasNaParede();
                pLBP.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.topendsports.com/testing/tests/wall-catch.htm");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pLBP);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 6:
                ProtRepeticaoAgachamento pRA = new ProtRepeticaoAgachamento();
                pRA.lbLink.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e ) {
                        link("http://customs.gov.au/webdata/resources/files/CT_FitnessProtocol.pdf");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pRA);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 7:
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 8:
                ProtCegonha pC = new ProtCegonha();
                pC.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.topendsports.com/testing/tests/balance-stork.htm");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pC);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 9:
                ProtMusculaturaDoCore pMC = new ProtMusculaturaDoCore();
                pMC.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&"
                                + "source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%"
                                + "2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3"
                                + "D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNH"
                                + "isJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pMC);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 10:
                ProtSaltoVertical pSV = new ProtSaltoVertical();
                pSV.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&"
                                + "source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%"
                                + "2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3"
                                + "D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNH"
                                + "isJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pSV);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 11:
                ProtSaltoHorizontal pSH = new ProtSaltoHorizontal();
                pSH.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&"
                                + "source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%"
                                + "2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3"
                                + "D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNH"
                                + "isJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pSH);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 12:
                ProtHexagono pH = new ProtHexagono();
                pH.lbLink.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e ) {
                        link("http://www.topendsports.com/testing/tests/hexagon.htm");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pH);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 13:
                ProtTempoReacaoRegua pTRR = new ProtTempoReacaoRegua();
                pTRR.lbLink.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e ) {
                        link("http://www.topendsports.com/testing/reactionmake.htm");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pTRR);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 14:
                protocolo.btAnimacao.setEnabled(false);
                break;
            case 15:
                ProtSentarEAlcancar pSA = new ProtSentarEAlcancar();
                pSA.lbLink.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        link("http://www.google.com.br/url?sa=t&rct=j&q=&esrc=s&"
                                + "source=web&cd=1&ved=0CDIQFjAA&url=http%3A%2F%"
                                + "2Fwww.sportingpulse.com%2Fget_file.cgi%3Fid%3"
                                + "D256038&ei=n45cUba2HMPl0gHQsIDQDg&usg=AFQjCNH"
                                + "isJnaVZZrKSJhW1PHmHuBZV8Q-w&bvm=bv.44697112,d.dmQ");
                    }
                });
                protocolo.jPanel.removeAll();
                protocolo.jPanel.add(pSA);
                protocolo.jPanel.validate();
                protocolo.jPanel.repaint();
                protocolo.btAnimacao.setEnabled(false);
                break;
        }
    }

    private void openAnimation() {
        switch (protocolo.cbProtocolos.getSelectedIndex()) {
            case 0:
                break;
            case 1:
                AnimationAgilidadeEmQuadra animacao = new AnimationAgilidadeEmQuadra();
                animacao.setVisible(true);
                break;
            case 2:
                AnimationShuttleRun animacao2 = new AnimationShuttleRun();
                animacao2.setVisible(true);
                break;
            default:
                break;
        }
    }

    private void link(String link) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(link);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(protocolo, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
