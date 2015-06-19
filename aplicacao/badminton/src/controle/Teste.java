// Teste.java
//Classe de controle do teste: Sentar e Alcançar
package controle;

import dao.TesteDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import modelo.FabricaTeste;
import modelo.Status;
import util.Coluna;
import util.CronometroTeste;
import visao.ExecucaoOfflineCrono;
import visao.modelosComponentes.TableCellRenderer;

/**
 * @author Diego Heusser
 */
public class Teste {

    private visao.ExecucaoOffline panel;
    private visao.ExecucaoOfflineCrono panelCrono;
    private final visao.Execucao execucao;
    private final controle.Execucao controleExecucao;
    private final dao.TesteDAO dao;
    private List<modelo.Atleta> atletas;
    public visao.modelosComponentes.TableModelGeneric tableModel;
    private util.Teste teste;
    private util.CronometroTeste[] crono;

    public Teste(visao.Execucao execucao, controle.Execucao controleExecucao,
            util.Teste teste, String tabela, String obj) {
        this.teste = teste;
        this.execucao = execucao;
        this.controleExecucao = controleExecucao;
        this.dao = new TesteDAO(tabela, obj, FabricaTeste.getTeste(teste));
        atletas = new ArrayList<>();
        if (teste.getValor() == 2) {
            this.panelCrono = new ExecucaoOfflineCrono();
            ouvintesCrono();
        } else if (teste.getValor() == 6) {
            this.panelCrono = new ExecucaoOfflineCrono();
            ouvintesCrono();
            panelCrono.btIniciar.setEnabled(false);
        } else {
            this.panel = new visao.ExecucaoOffline();
            ouvintes();
        }
    }

    public List<modelo.Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<modelo.Atleta> atletas) {
        this.atletas = atletas;
    }

    public util.Teste getTeste() {
        return teste;
    }

    public void setTeste(util.Teste teste) {
        this.teste = teste;
    }

    public void executar() {
        if (teste.getValor() == 2 || teste.getValor() == 6) {
            execucao.pExecution.removeAll();
            execucao.pExecution.add(panelCrono);
            execucao.pExecution.validate();
        } else {
            execucao.pExecution.removeAll();
            execucao.pExecution.add(panel);
            execucao.pExecution.validate();
        }
    }

    private void ouvintes() {
        panel.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel.tabela);
            }
        });
        panel.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salva();
            }
        });
    }

    public void tabela() {

        List<modelo.Teste> lista = new ArrayList<>();
        String superficie = (String) execucao.cbSuperficie.getSelectedItem();
        int temperatura = (int) execucao.spTemperatura.getValue();

        for (modelo.Atleta umAtleta : atletas) {

            modelo.Teste t = FabricaTeste.getTeste(teste);

            if (umAtleta.getCor().equals(Color.green)) {
                try {
                    t = dao.teste((modelo.Agenda) execucao.cbData.getSelectedItem(),
                            umAtleta, (modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                t.setTemperatura(temperatura);
                t.setSuperficie(superficie);
            }
            t.setStatus(Status.CONCLUIDO);
            t.setProtocolo((modelo.Protocolo) execucao.cbProtocol.getSelectedItem());
            t.setAtleta(umAtleta);

            lista.add(t);
        }
        
        tableModel = new visao.modelosComponentes.TableModelGeneric(lista);
        TableCellRenderer cellRenderer = new TableCellRenderer(tableModel);
        if (teste.getValor() == 2 || teste.getValor() == 6) {
            panelCrono.tabela.setDefaultRenderer(Object.class, cellRenderer);
            panelCrono.tabela.setRowHeight(50);
            panelCrono.tabela.setModel(tableModel);
            if (!lista.isEmpty()) {

                util.BotaoColuna buttonColumn
                        = new util.BotaoColuna(
                                panelCrono.tabela, colunaDoBotao(lista.get(0).getClass()));
                colunasFormatadas(panelCrono.tabela);
                crono = new CronometroTeste[lista.size()];
            }
        } else {
            panel.tabela.setDefaultRenderer(Object.class, cellRenderer);
            panel.tabela.setRowHeight(50);
            panel.tabela.setModel(tableModel);
            if (!lista.isEmpty()) {
                colunasFormatadas(panel.tabela);
            }
        }

    }

    private int colunaDoBotao(Class<?> c) {
        Class<?> classe = c;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                Coluna anotacao = metodo.getAnnotation(Coluna.class);
                if (anotacao.nome().equals("Ação")) {
                    return anotacao.posicao();
                }
            }
        }
        return 0;
    }

    private void colunasFormatadas(JTable tb) {
        try {
            MaskFormatter hora = new MaskFormatter("##:##:##");
            MaskFormatter data = new MaskFormatter("##/##/####");

            JFormattedTextField ftHora = new JFormattedTextField(hora);
            JFormattedTextField ftData = new JFormattedTextField(data);

            TableColumn colHora = tb.getColumn("Hora");
            TableColumn colData = tb.getColumn("Data");

            colHora.setCellEditor(new DefaultCellEditor(ftHora));
            colData.setCellEditor(new DefaultCellEditor(ftData));

            if (tb.getColumnName(3).equals("Completado")) {
                String[] d = new String[]{"sim", "não"};
                JComboBox cb = new JComboBox(d);
                TableColumn colC = tb.getColumnModel().getColumn(3);
                colC.setCellEditor(new DefaultCellEditor(cb));
            }
        } catch (ParseException ex) {
            ex.getMessage();
            JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void remove(JTable tb) {
        int[] linhas = tb.getSelectedRows();
        try {
            List<modelo.Atleta> remover = new ArrayList<>();
            for (int i = 0; i < linhas.length; i++) {
                dao.remove((modelo.Agenda) execucao.cbData.getSelectedItem(),
                        atletas.get(linhas[i]), (modelo.Protocolo) execucao.cbProtocol.
                        getSelectedItem());

                remover.add(atletas.get(linhas[i]));
            }
            atletas.removeAll(remover);
            tabela();
            controleExecucao.atualizaTabela();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salva() {
        try {
            for (modelo.Teste t : tableModel.getLista()) {

                if (t.getAtleta().getCor().equals(Color.green)) {
                    dao.altera(t);
                } else {
                    dao.insere(t, (modelo.Agenda) execucao.cbData.getSelectedItem());
                }

            }
            JOptionPane.showMessageDialog(execucao, "Salvo");
            tabela();
            tableModel.clear();
            controleExecucao.atualizaTabela();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(execucao, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ouvintesCrono() {
        panelCrono.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panelCrono.tabela);
            }
        });
        panelCrono.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salva();
            }
        });
        panelCrono.btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    iniciar(i, 3);
                    if (teste.getValor() == 2) {
                        tableModel.setValueAt("Parar", i, 4);
                    }
                }
                panelCrono.btIniciar.setEnabled(false);
            }
        });
        panelCrono.tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int coluna = panelCrono.tabela.getColumnModel().getColumnIndexAtX(e.getX());
                int linha = e.getY() / panelCrono.tabela.getRowHeight();
                if (coluna == colunaDoBotao(tableModel.getLista().get(0).getClass())) {
                    if (tableModel.getValueAt(linha, coluna).equals("Iniciar")) {
                        iniciar(linha, (coluna - 2));
                        tableModel.setValueAt("Parar", linha, coluna);
                    } else if (tableModel.getValueAt(linha, coluna).equals("Parar")) {
                        crono[linha].setContinuar(false);
                        tableModel.setValueAt("", linha, coluna);
                    }
                }
            }
        });
    }

    public void iniciar(int linha, int coluna) {
        crono[linha] = new CronometroTeste(linha, coluna, this);
        crono[linha].start();
    }
}
