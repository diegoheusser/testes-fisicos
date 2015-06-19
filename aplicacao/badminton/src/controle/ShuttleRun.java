package controle;

import dao.ExecucaoDAO;
import dao.ShuttleRunDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import modelo.Atleta;
import modelo.Status;
import util.CronometroShuttleRun;
import visao.ExecucaoShuttleRun;
import visao.modelosComponentes.ShuttleRunTableModelOffline;
import visao.modelosComponentes.ShuttleRunTableModelOnline;

/**
 * @author Diego Heusser
 */
public class ShuttleRun {

    private List<util.CronometroShuttleRun> cronos;
    public visao.Execucao execucao;
    public visao.ExecucaoShuttleRun execShuttleRun;
    public visao.modelosComponentes.ShuttleRunTableModelOnline tableModelOnline;
    public visao.modelosComponentes.ShuttleRunTableModelOffline tableModelOffline;
    private List<modelo.Atleta> atletas;
    private util.BeepShuttleRun beep;
    private controle.Execucao controleExecucao;
    private boolean alterar;

    public ShuttleRun(visao.Execucao execucao, controle.Execucao controleExecucao) {
        this.controleExecucao = controleExecucao;
        alterar = false;
        this.execucao = execucao;
        execShuttleRun = new ExecucaoShuttleRun();
        cronos = new ArrayList<>();

        tableModelOnline = new visao.modelosComponentes.ShuttleRunTableModelOnline();
        execShuttleRun.tbShuttleRun.setModel(tableModelOnline);
        execShuttleRun.tbShuttleRun.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        util.BotaoColuna buttonColumn = new util.BotaoColuna(execShuttleRun.tbShuttleRun, 3);
        tabelaTamanhoColunas();
        ouvintes();
        inicializarComponentes();
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public boolean isAlterar() {
        return alterar;
    }

    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

    private void colunasFormatadas() {
        try {
            MaskFormatter mask = new MaskFormatter("##:##:###");
            mask.setPlaceholderCharacter('0');
            MaskFormatter mask2 = new MaskFormatter("##:##:##");
            mask2.setPlaceholderCharacter('0');
            MaskFormatter mask3 = new MaskFormatter("##/##/####");
            mask3.setPlaceholderCharacter('1');
            JFormattedTextField ft = new JFormattedTextField(mask);
            JFormattedTextField ft2 = new JFormattedTextField(mask2);
            JFormattedTextField ft3 = new JFormattedTextField(mask3);
            TableColumn colTempo = execShuttleRun.tbShuttleRun.getColumn("Tempo");
            colTempo.setCellEditor(new DefaultCellEditor(ft));
            TableColumn colHora = execShuttleRun.tbShuttleRun.getColumn("Hora");
            colHora.setCellEditor(new DefaultCellEditor(ft2));
            TableColumn colData = execShuttleRun.tbShuttleRun.getColumn("Data");
            colData.setCellEditor(new DefaultCellEditor(ft3));
        } catch (ParseException ex) {
            Logger.getLogger(ShuttleRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ouvintes() {
        execShuttleRun.btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });
        execShuttleRun.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        execShuttleRun.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    salvar();
                } catch (ParseException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(execucao, ex.getMessage());
                }
            }
        });
        execShuttleRun.rbOnline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (execShuttleRun.rbOnline.isSelected()) {
                    tableOnline();
                    execShuttleRun.btIniciar.setEnabled(true);
                }
            }
        });
        execShuttleRun.rbOffline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (execShuttleRun.rbOffline.isSelected()) {
                    tableOffline();
                    execShuttleRun.btIniciar.setEnabled(false);
                }
            }
        });
        execShuttleRun.tbShuttleRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (execShuttleRun.rbOnline.isSelected()) {
                    tabelaOnline(e);
                }
            }
        });
    }

    private void salvar() throws ParseException, SQLException {
        for (int i = 0; i < execShuttleRun.tbShuttleRun.getRowCount(); i++) {
            modelo.ShuttleRun shuttleRun = new modelo.ShuttleRun();
            shuttleRun.setAtleta((modelo.Atleta) execShuttleRun.tbShuttleRun.getValueAt(i, 0));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            shuttleRun.setData(new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(execShuttleRun.tbShuttleRun.getValueAt(i, 1))));
            shuttleRun.setHora(String.valueOf(execShuttleRun.tbShuttleRun.getValueAt(i, 2)));
            shuttleRun.setNivel(Integer.parseInt(String.valueOf(execShuttleRun.tbShuttleRun.getValueAt(i, 3))
                    .replaceAll(" ", "")));
            shuttleRun.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
            shuttleRun.setStatus(Status.CONCLUIDO);
            shuttleRun.setSuperficie(String.valueOf(execucao.cbSuperficie.getSelectedItem()));
            shuttleRun.setTemperatura((int) execucao.spTemperatura.getValue());
            shuttleRun.setTempo(String.valueOf(execShuttleRun.tbShuttleRun.getValueAt(i, 4)));
            dao.ShuttleRunDAO dao = new ShuttleRunDAO();
            if (alterar) {
                shuttleRun.setId(((modelo.Execucao) dao.execucao((modelo.Agenda) execucao.cbData.getSelectedItem(),
                        shuttleRun.getAtleta(), shuttleRun.getProtocolo())).getId());
                shuttleRun.setShuttleRunId(((modelo.ShuttleRun) dao.shuttleRun(
                        (modelo.Agenda) execucao.cbData.getSelectedItem(), shuttleRun)).getShuttleRunId());
                dao.alterarExecucao(shuttleRun);
                dao.alterar(shuttleRun);
            } else {
                dao.salvarExeucao(shuttleRun, (modelo.Agenda) execucao.cbData.getSelectedItem());
                shuttleRun.setId(((modelo.Execucao) dao.execucao((modelo.Agenda) execucao.cbData.getSelectedItem(),
                        shuttleRun.getAtleta(), shuttleRun.getProtocolo())).getId());
                dao.salvar(shuttleRun);
                modelo.Atleta umAtleta = shuttleRun.getAtleta();
                umAtleta.setCor(Color.green);
                ((visao.modelosComponentes.ExecucaoTableModel)execucao.tbExecution.getModel()).updateAtleta(umAtleta);
            }
            atletas.remove(shuttleRun.getAtleta());
        }
        tableOffline();
        controleExecucao.preencheCaixaDeCombinacaoComProtocolos(
                (modelo.Agenda)execucao.cbData.getSelectedItem());
    }


    private void excluir() {
        int[] linhas = execShuttleRun.tbShuttleRun.getSelectedRows();
        if (linhas.length > 0) {
            dao.ShuttleRunDAO dao = new dao.ShuttleRunDAO();
            try {
                for (int i = 0; i < linhas.length; i++) {
                    atletas.remove((modelo.Atleta) execShuttleRun.tbShuttleRun.getValueAt(linhas[i], 0));
                    modelo.Atleta umAtleta = (modelo.Atleta) execShuttleRun.tbShuttleRun.getValueAt(linhas[i], 0);
                    umAtleta.setCor(Color.white);
                    ((visao.modelosComponentes.ExecucaoTableModel)execucao.tbExecution.getModel()).updateAtleta(umAtleta);
                    dao.removerExecucao(
                            (modelo.Atleta) execShuttleRun.tbShuttleRun.getValueAt(linhas[i], 0),
                            (modelo.Protocolo) execucao.cbProtocol.getSelectedItem(),
                            (modelo.Agenda) execucao.cbData.getSelectedItem());
                }
                tableOffline();
            } catch (SQLException |ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(execucao, "Selecione uma linha!");
        }
    }

    private void inicializarComponentes() {
        execShuttleRun.rbOnline.setSelected(true);
        execShuttleRun.tbShuttleRun.getTableHeader().setReorderingAllowed(false);
        execShuttleRun.tbShuttleRun.setRowHeight(40);
        execShuttleRun.tbShuttleRun.setFillsViewportHeight(true);
    }

    public void executar() {
        execucao.pExecution.removeAll();
        execucao.pExecution.add(execShuttleRun);
        execucao.pExecution.validate();
    }

    public void tableOnline() {
        List<modelo.ShuttleRun> shuttleRun = new ArrayList<>();
        if (atletas != null) {
            for (modelo.Atleta umAtleta : atletas) {
                modelo.ShuttleRun newRun = new modelo.ShuttleRun();
                newRun.setAtleta(umAtleta);
                shuttleRun.add(newRun);
            }
        }
        tableModelOnline = new ShuttleRunTableModelOnline(shuttleRun);
        execShuttleRun.tbShuttleRun.setModel(tableModelOnline);
        util.BotaoColuna buttonColumn = new util.BotaoColuna(execShuttleRun.tbShuttleRun, 3);
        botoes(false);
    }

    public void tableOffline() {
        try {
            List<modelo.ShuttleRun> shuttleRun = new ArrayList<>();
            if (alterar) {
                dao.ShuttleRunDAO dao = new dao.ShuttleRunDAO();
                shuttleRun = dao.listar(atletas, (modelo.Protocolo) execucao.cbProtocol.getSelectedItem(),
                        (modelo.Agenda) execucao.cbData.getSelectedItem());
            } else {
                if (atletas != null) {
                    for (modelo.Atleta umAtleta : atletas) {
                        modelo.ShuttleRun newRun = new modelo.ShuttleRun();
                        newRun.setAtleta(umAtleta);
                        shuttleRun.add(newRun);
                    }
                }
            }
            tableModelOffline = new ShuttleRunTableModelOffline(shuttleRun);
            execShuttleRun.tbShuttleRun.setModel(tableModelOffline);
            botoes(true);
            colunasFormatadas();
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botoes(boolean b) {
        execShuttleRun.btIniciar.setEnabled(!b);
        execShuttleRun.btExcluir.setEnabled(b);
        execShuttleRun.btSalvar.setEnabled(b);
    }

    public void iniciar() {
        if (execShuttleRun.rbOnline.isSelected()) {
            if (execShuttleRun.tbShuttleRun.getModel().getRowCount() > 0) {
                execucao.tbExecution.setEnabled(false);
                execShuttleRun.btIniciar.setEnabled(false);
                int rowIndex = 0;
                for (modelo.Atleta umAtleta : atletas) {
                    util.CronometroShuttleRun crono
                            = new util.CronometroShuttleRun(this, umAtleta, rowIndex);
                    crono.start();
                    cronos.add(crono);
                    rowIndex++;
                }
                System.out.println("size: " + cronos.size());
                radioButtons(false);
                salvarExecucao(atletas);
                beep = new util.BeepShuttleRun(8.5, 20);
                beep.start();
            } else {
                JOptionPane.showMessageDialog(execucao, "Adicione atletas antes de iniciar!",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                execucao.tbExecution.requestFocus();
            }
        } else {
            System.out.println("Offline");
        }
    }

    public void radioButtons(boolean b) {
        execShuttleRun.rbOffline.setEnabled(b);
        execShuttleRun.rbOnline.setEnabled(b);
    }

    private void tabelaTamanhoColunas() {
        execShuttleRun.tbShuttleRun.getColumnModel().getColumn(2).setMaxWidth(100);
        execShuttleRun.tbShuttleRun.getColumnModel().getColumn(2).setMinWidth(100);
        execShuttleRun.tbShuttleRun.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(100);
        execShuttleRun.tbShuttleRun.getTableHeader().getColumnModel().getColumn(2).setMinWidth(100);
    }

    private void tabelaOnline(MouseEvent e) {
        int column = execShuttleRun.tbShuttleRun.getColumnModel().getColumnIndexAtX(e.getX()); // obtem a coluna do botão
        int row = e.getY() / execShuttleRun.tbShuttleRun.getRowHeight(); //obtem a linha do botão
        if (column == 3 && !cronos.isEmpty()) {
            if (((String) tableModelOnline.getValueAt(row, column)).equals("Finalizar")) {
                cronos.get(row).setContinuar(false);
                tableModelOnline.setValueAt("Fechar", row, column);
                salvar((modelo.Atleta) tableModelOnline.getValueAt(row, 0),
                        (int) tableModelOnline.getValueAt(row, 1),
                        String.valueOf(tableModelOnline.getValueAt(row, 2)));
            } else {
                tableModelOnline.remove(row);
            }
            if (tableModelOnline.getRowCount() == 0) {
                execucao.tbExecution.setEnabled(true);
                execShuttleRun.btIniciar.setEnabled(true);
            }
            if (state(cronos)) {
                beep.setContinuar(false);
            }
            if (tableModelOnline.getRowCount() == 0) {
                cronos.clear();
            }
        }
    }

    private void tabelaOffline(MouseEvent e) {
        int column = execShuttleRun.tbShuttleRun.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY() / execShuttleRun.tbShuttleRun.getRowHeight();
        if (column == 5) {
            modelo.ShuttleRun newRun = new modelo.ShuttleRun();
            newRun.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
            newRun.setStatus(Status.CONCLUIDO);
            newRun.setSuperficie(String.valueOf(execucao.cbSuperficie.getSelectedItem()));
            newRun.setTemperatura((int) execucao.spTemperatura.getValue());
            newRun.setAtleta((modelo.Atleta) tableModelOffline.getValueAt(row, 0));
            newRun.getAtleta().setCor(Color.green);
            try {
                newRun.setData(new SimpleDateFormat("dd/MM/yyyy").parse((String) tableModelOffline.getValueAt(row, 1)));
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(execucao, "Data inválida!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
            newRun.setHora((String) tableModelOffline.getValueAt(row, 2));
            newRun.setNivel((int) tableModelOffline.getValueAt(row, 3));
            newRun.setTempo((String) tableModelOffline.getValueAt(row, 4));
            try {
                if (alterar) {

                } else {
                    new dao.ExecucaoDAO().salvarExeucao(newRun, (modelo.Agenda) execucao.cbData.getSelectedItem());
                    newRun.setId(((modelo.Execucao) new dao.ExecucaoDAO().execucao(
                            (modelo.Agenda) execucao.cbData.getSelectedItem(), newRun.getAtleta(),
                            (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getId());
                    new dao.ShuttleRunDAO().salvar(newRun);
                    ((visao.modelosComponentes.ExecucaoTableModel) execucao.tbExecution.getModel()).updateAtleta(newRun.getAtleta());
                    radioButtons(false);
                }
            } catch (SQLException | ParseException ex) {
                ex.printStackTrace();
            }
            tableModelOffline.remove(row);
        }
    }

    private void salvarExecucao(List<modelo.Atleta> atletas) {
        try {
        dao.ExecucaoDAO dao = new ExecucaoDAO();
        modelo.Execucao umaExecucao = new modelo.Execucao();
        umaExecucao.setData(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        umaExecucao.setHora(sdf.format(umaExecucao.getData()));
        umaExecucao.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
        umaExecucao.setStatus(Status.NÃO_CONCLUIDO);
        umaExecucao.setSuperficie(String.valueOf(execucao.cbSuperficie.getSelectedItem()));
        umaExecucao.setTemperatura((int) execucao.spTemperatura.getValue());
        
            for (modelo.Atleta umAtleta : atletas) {
                umaExecucao.setAtleta(umAtleta);
                dao.salvarExeucao(umaExecucao, (modelo.Agenda) execucao.cbData.getSelectedItem());
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void salvar(modelo.Atleta umAtleta, int nivel, String tempo) {
        dao.ShuttleRunDAO dao = new ShuttleRunDAO();
        modelo.ShuttleRun umShuttleRun = new modelo.ShuttleRun();
        umShuttleRun.setNivel(nivel);
        umShuttleRun.setTempo(tempo);
        try {
            umShuttleRun.setId(((modelo.Execucao) dao.execucao(
                    (modelo.Agenda) execucao.cbData.getSelectedItem(), umAtleta,
                    (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getId());
            umShuttleRun.setAtleta(umAtleta);
            umShuttleRun.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
            umShuttleRun.setSuperficie(((modelo.Execucao) dao.execucao(
                    (modelo.Agenda) execucao.cbData.getSelectedItem(), umAtleta,
                    (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getSuperficie());
            umShuttleRun.setTemperatura(((modelo.Execucao) dao.execucao(
                    (modelo.Agenda) execucao.cbData.getSelectedItem(), umAtleta,
                    (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getTemperatura());
            umShuttleRun.setData(((modelo.Execucao) dao.execucao(
                    (modelo.Agenda) execucao.cbData.getSelectedItem(), umAtleta,
                    (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getData());
            umShuttleRun.setHora(((modelo.Execucao) dao.execucao(
                    (modelo.Agenda) execucao.cbData.getSelectedItem(), umAtleta,
                    (modelo.Protocolo) execucao.cbProtocol.getSelectedItem())).getHora());
            dao.salvar(umShuttleRun);
            umShuttleRun.setStatus(Status.CONCLUIDO);
            umAtleta.setCor(Color.green);
            dao.alterarExecucao(umShuttleRun);
            ((visao.modelosComponentes.ExecucaoTableModel) execucao.tbExecution.getModel()).updateAtleta(umAtleta);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(ShuttleRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean state(List<CronometroShuttleRun> cronos) {
        for (CronometroShuttleRun obj : cronos) {
            if (obj.isAlive()) {
                return false;
            }
        }
        return true;
    }
}
