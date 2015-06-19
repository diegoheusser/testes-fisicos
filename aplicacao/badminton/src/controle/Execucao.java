//Execucao.java
//Classe para controlar as execuções
package controle;

import dao.AgendaDAO;
import dao.AgilidadeEmQuadraDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Protocolo;
import modelo.Agenda;
import modelo.Atleta;
import visao.modelosComponentes.ComboBoxRenderer;
import visao.AnimationAgilidadeEmQuadra;
import visao.modelosComponentes.ExecucaoCellRenderer;

/**
 * @author Diego Heusser
 */
public class Execucao {

    private final visao.MenuPrincipal mp;
    public visao.Execucao execucao;
    private visao.AgilidadeEmQuadra exeAgilidadeEmQuadra;
    public Agenda scheduling;
    private DefaultListModel listModelScheduling;
    public List<modelo.Atleta> listaIntervalo;
    public visao.modelosComponentes.ExecucaoTableModel model;
    private controle.AgilidadeEmQuadra aeq;
    private controle.ShuttleRun sr;
    private controle.Teste t;

    public Execucao(visao.MenuPrincipal mp) {
        this.mp = mp;
        execucao = new visao.Execucao();
        ouvintes();
        components();
    }

    private void ouvintes() {
        execucao.cbData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (execucao.cbData.getItemCount() > 0) {
                    spDate();
                }
            }
        });
        execucao.cbProtocol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (execucao.cbProtocol.getItemCount() > 0) {
                    cbProtocols();
                }
            }
        });
        execucao.cbProtocol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                preencheCaixaDeCombinacaoComProtocolos(scheduling);
            }
        });
        execucao.btAnimacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAnimacao();
            }
        });
        execucao.btVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btVisualizar();
            }
        });
        execucao.btConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scheduling != null) {
                    confirmar();
                } else {
                    JOptionPane.showMessageDialog(mp, "Não há nenhum agendamento cadastrado!",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        execucao.tbExecution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modelo.Atleta umAtleta = (modelo.Atleta) execucao.tbExecution.getValueAt(
                        execucao.tbExecution.getSelectedRow(), 0);
                if (umAtleta != null) {
                    mudaVariaveisAmbiente(umAtleta);
                }
            }
        });

    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(execucao);
        mp.jPanel.validate();
    }

    public void spDate() {
        this.scheduling = (Agenda) execucao.cbData.getSelectedItem();
        if (scheduling != null) {
            preencheCaixaDeCombinacaoComProtocolos(scheduling);
            atualizaTabela();
        }
    }

    public void atualizaTabela() {
        dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
        modelo.Protocolo umProtocolo = (modelo.Protocolo) execucao.cbProtocol.getSelectedItem();
        if (umProtocolo != null) {
            try {
                model = new visao.modelosComponentes.ExecucaoTableModel(dao.verificaCor(scheduling.getAtleta(), scheduling.getId(), umProtocolo.getId()));
            } catch (SQLException ex) {
                Logger.getLogger(Execucao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            model = new visao.modelosComponentes.ExecucaoTableModel();
        }
        preencheTabelaComTodosAtletas(model);
    }

    private void cbProtocols() {
        modelo.Protocolo umProtocolo = (modelo.Protocolo) execucao.cbProtocol.getSelectedItem();
        switch (umProtocolo.getId()) {
            case 1://Agilidade em quadra
                aeq = new controle.AgilidadeEmQuadra(execucao, this);
                aeq.executar();
                break;
            case 2://ShuttleRun
                sr = new ShuttleRun(execucao, this);
                sr.executar();
                break;
            case 3://flexibilidade de Ombro e Punho
                t = new Teste(execucao, this, util.Teste.FLEXIBILIDADE_DE_OMBRO_E_PUNHO,
                        "flexibilidade_de_ombro_e_punho", "distancia");
                t.executar();
                break;
            case 4://Teste de Flexibilidade das Pernas
                execucao.pExecution.removeAll();
                execucao.pExecution.validate();
                execucao.pExecution.repaint();
                break;
            case 5://Lançamentos de bolas na parede
                t = new Teste(execucao, this, util.Teste.LANÇAMENTO_DE_BOLAS_NA_PAREDE,
                        "lancamento_de_bolas_na_parede", "quantidade");
                t.executar();
                break;
            case 6://repetição de agachamentos
                t = new Teste(execucao, this, util.Teste.REPETIÇÃO_DE_AGACHAMENTO,
                        "repeticao_de_agachamento", "quantidade");
                t.executar();
                break;
            case 7://Sprint Fatigue Test
                execucao.pExecution.removeAll();
                execucao.pExecution.validate();
                execucao.pExecution.repaint();
                break;
            case 8://Teste da Cegonha
                t = new Teste(execucao, this, util.Teste.CEGONHA, "cegonha", "tempo");
                t.executar();
                break;
            case 9://Musculatura do Core
                t = new Teste(execucao, this, util.Teste.MUSCULATURA_DO_CORE,
                        "musculatura_do_core", "completo");
                t.executar();
                break;
            case 10://Salto Vertical
                t = new Teste(execucao, this, util.Teste.SALTO_VERTICAL,
                        "salto_vertical", "distancia");
                t.executar();
                break;
            case 11://Salto Horizontal
                t = new Teste(execucao, this, util.Teste.SALTO_HORIZONTAL,
                        "salto_horizontal", "distancia");
                t.executar();
                break;
            case 12://Texte do Hexágono
                t = new Teste(execucao, this, util.Teste.HEXÁGONO,
                        "hexagono", "tempo");
                t.executar();
                break;
            case 13://Tempo de reação Régua
                t = new Teste(execucao, this, util.Teste.TEMPO_DE_REAÇÃO_RÉGUA,
                        "tempo_reacao_regua", "milisegundos");
                t.executar();
                break;
            case 14://Agachar e Pegar a Peteca
                execucao.pExecution.removeAll();
                execucao.pExecution.validate();
                execucao.pExecution.repaint();
                break;
            case 15://Sentar e Alcançar
                t = new Teste(execucao, this, util.Teste.SENTAR_E_ALCAÇAR,
                        "sentar_alcancar", "distancia");
                t.executar();
                break;

        }
        atualizaTabela();
    }

    private void btAnimacao() {
        modelo.Protocolo umProtocolo = (modelo.Protocolo) execucao.cbProtocol.getSelectedItem();
        if (umProtocolo == null) {
            JOptionPane.showMessageDialog(mp, "Não há nenhum protocolo selecionado!");
            return;
        }
        switch (umProtocolo.getId()) {
            case 1://Agilidade em quadra
                AnimationAgilidadeEmQuadra animation = new AnimationAgilidadeEmQuadra();
                animation.setVisible(true);
                break;

            case 2://ShuttleRun
                visao.AnimationShuttleRun run = new visao.AnimationShuttleRun();
                run.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(mp, "Não há animação para esse protocolo");
                break;

        }
    }

    private void btVisualizar() {
        try {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();//Usado para abrir arquivos
            modelo.Protocolo umProtocolo = (modelo.Protocolo) execucao.cbProtocol.getSelectedItem();
            if (umProtocolo == null) {
                JOptionPane.showMessageDialog(mp, "Não há nenhum protocolo selecionado!");
                return;
            }
            switch (umProtocolo.getId()) {
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
                    desktop.open(new File(getClass().getResource("/visao/protocolos/.pdf").getFile()));//abre o pdf
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
                    desktop.open(new File(getClass().getResource("/visao/protocolos/.pdf").getFile()));//abre o pdf
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
                    desktop.open(new File(getClass().getResource("/visao/protocolos/.pdf").getFile()));//abre o pdf
                    break;
                case 15:
                    //Sentar e Alcançar
                    desktop.open(new File(getClass().getResource("/visao/protocolos/testesentarealcancar.pdf").getFile()));//abre o pdf
                    break;
            }
        } catch (HeadlessException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao abrir PDF: " + e.getMessage());//mensagem que aparece se aconter algum erro
        }
    }

    private void components() {
        this.listModelScheduling = new DefaultListModel();
        preencheCaixaDeCombinacaoComAgendamentos();
    }

    private void preencheCaixaDeCombinacaoComAgendamentos() {
        try {
            ArrayList<Agenda> array = (ArrayList<Agenda>) scheduling();
            for (Agenda obj : array) {
                execucao.cbData.addItem(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Não foi possível carregar os agendamentos!");
            e.printStackTrace();
        }
    }

    public void preencheCaixaDeCombinacaoComProtocolos(Agenda sc) {
        try {
            int old = 0;
            old = execucao.cbProtocol.getSelectedIndex();
            dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
            Object[] lista = dao.verificaFimProt(
                    scheduling.getProtocolo(), scheduling.getId(), scheduling.getAtleta().size());

            execucao.cbProtocol.removeAllItems();
            for (Object obj : lista) {
                execucao.cbProtocol.addItem(obj);
            }

            visao.modelosComponentes.ComboBoxRenderer renderer = new ComboBoxRenderer();
            renderer.setPreferredSize(new Dimension(247, 20));
            execucao.cbProtocol.setRenderer(renderer);
            if (old >= 0) {
                execucao.cbProtocol.setSelectedIndex(old);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public void preencheTabelaComTodosAtletas(visao.modelosComponentes.ExecucaoTableModel model1) {
        ExecucaoCellRenderer e = new ExecucaoCellRenderer(listaIntervalo);
        execucao.tbExecution.setDefaultRenderer(Object.class, e);
        //seta o modelo da tabela
        execucao.tbExecution.setModel(model1);
        //seta o tamanho da coluna do tempo
        execucao.tbExecution.getColumnModel().getColumn(1).setMaxWidth(50);
        execucao.tbExecution.getColumnModel().getColumn(1).setMinWidth(50);
        execucao.tbExecution.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(50);
        execucao.tbExecution.getTableHeader().getColumnModel().getColumn(1).setMinWidth(50);
        //repinta a tabela
        execucao.tbExecution.repaint();
    }

    //retorna uma lsita de agendamentos  
    public List<Agenda> scheduling() throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.scheduling("", "", null, null, "");
    }

    //retorna uma lista de protocolos conforme o id do agendamento
    public List<Protocolo> protocol(int schedulingId) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.protocol(schedulingId);
    }

    //retorna uma lista de atletas conforme o id do agendamento
    public List<modelo.Atleta> athlete(int schedulingId) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.athlete(schedulingId);
    }

    private void agilidadeEmQuadra(modelo.Atleta umAtleta) {
        try {
            dao.AgilidadeEmQuadraDAO dao = new dao.AgilidadeEmQuadraDAO();
            List<modelo.AgilidadeEmQuadra> listaAgilidadeEmQuadra = dao.rodadas(
                    dao.maxExecucaoId(umAtleta.getId()),
                    ((modelo.Protocolo) execucao.cbProtocol.getSelectedItem()).getId());
            aeq.umAtleta = umAtleta;
            if (listaAgilidadeEmQuadra.size() > 0) {
                aeq.dataExecucao = listaAgilidadeEmQuadra.get(0).getData();
            }
            long ano = 31536000000l;
            long calc = (new Date().getTime() - umAtleta.getDataNascimento().getTime());
            calc = (calc / ano);
            String info = "<html>Nome: " + umAtleta.getNome() + "<br><br>"
                    + "Idade: " + (int) calc + "<br><br>"
                    + "Nível: " + umAtleta.getNivel() + "<br><br>"
                    + "Massa Corporal: " + umAtleta.getMassaCorporal() + " Kg<br><br>"
                    + "Envergadura: " + umAtleta.getEnvergadura() + " Cm<br><br>"
                    + "Estatura: " + umAtleta.getEstatura() + " m<br><br>"
                    + "</html>";
            aeq.agilidadeEmQuadra.lbInfo.setText(info);
            if (aeq.agilidadeEmQuadra.rbOnline.isSelected()) {
                aeq.panelOnline();
            } else {
                aeq.panelOffline();
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void mudaVariaveisAmbiente(modelo.Atleta umAtleta) {
        try {
            dao.AgilidadeEmQuadraDAO dao = new AgilidadeEmQuadraDAO();
            modelo.Execucao umaExecucao = null;
            umaExecucao = dao.execucao(((modelo.Agenda) execucao.cbData.getSelectedItem()).getId(),
                    umAtleta.getId(), ((modelo.Protocolo) execucao.cbProtocol.getSelectedItem()).getId());
            if (umaExecucao.getId() == 0) {
                execucao.cbSuperficie.setSelectedIndex(0);
                execucao.spTemperatura.setValue(0);
            } else {
                execucao.cbSuperficie.setSelectedItem(umaExecucao.getSuperficie());
                execucao.spTemperatura.setValue(umaExecucao.getTemperatura());
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void shuttleRun(List<modelo.Atleta> atletas) {
        sr.setAtletas(atletas);
        sr.radioButtons(true);
        if (sr.execShuttleRun.rbOffline.isSelected()) {
            sr.tableOffline();
        } else {
            sr.tableOnline();
        }
    }

    private void confirmar() {
        modelo.Protocolo umProtocolo = (modelo.Protocolo) execucao.cbProtocol.getSelectedItem();
        List<modelo.Atleta> atletasNovos;
        List<modelo.Atleta> atletasVelhos;
        int[] linhas;

        linhas = execucao.tbExecution.getSelectedRows();
        atletasNovos = new ArrayList<>();
        atletasVelhos = new ArrayList<>();
        String mensagem = "";
        for (int i = 0; i < linhas.length; i++) {
            modelo.Atleta umAtleta = (modelo.Atleta) execucao.tbExecution.getValueAt(linhas[i], 0);
            if (umAtleta.getCor().equals(Color.green)) {
                mensagem += umAtleta.getNome() + "\n";
                atletasVelhos.add(umAtleta);
            } else {
                atletasNovos.add(umAtleta);
            }
        }
        switch (umProtocolo.getId()) {
            case 1://Agilidade em Quadra
                modelo.Atleta a = (modelo.Atleta) execucao.tbExecution.getValueAt(execucao.tbExecution.getSelectedRow(), 0);
                if (a != null) {
                    agilidadeEmQuadra(a);
                } else {
                    JOptionPane.showMessageDialog(mp, "Selecionene um atleta da lista!",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                execucao.tbExecution.clearSelection();
                break;
            case 2://ShuttleRun
                linhas = execucao.tbExecution.getSelectedRows();
                List<modelo.Atleta> atletas = new ArrayList<>();
                String atletasConcluidos = "";
                for (int i = 0; i < linhas.length; i++) {
                    modelo.Atleta umAtleta = (modelo.Atleta) execucao.tbExecution.getValueAt(linhas[i], 0);
                    if (umAtleta.getCor().equals(Color.green)) {
                        atletasConcluidos += umAtleta.getNome() + "\n";
                        atletas.add(umAtleta);
                    } else {
                        atletas.add(umAtleta);
                    }
                }
                if (!atletasConcluidos.equals("")) {
                    JOptionPane.showMessageDialog(mp, "Os atletas: " + atletasConcluidos + "já concluiram o teste.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    sr.execShuttleRun.rbOffline.setSelected(true);
                    sr.setAlterar(true);
                }
                shuttleRun(atletas);
                execucao.tbExecution.clearSelection();
                break;
            case 4://Teste de Flexibilidade das Pernas

                break;
             case 7://Sprint Fatigue Test

                break;
            case 14://Agachar e Pegar a Peteca

                break;
            default:
                if (!mensagem.equals("")) {
                    JOptionPane.showMessageDialog(mp, "Os atletas: " + mensagem + "já concluiram o teste.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    teste(atletasVelhos);
                } else {
                    teste(atletasNovos);
                }
                break;
        }
        execucao.tbExecution.clearSelection();
    }

    private void teste(List<Atleta> atletas) {
        t.setAtletas(atletas);
        t.tabela();
    }
}
