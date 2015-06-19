package controle;

import dao.AgendaDAO;
import dao.GrupoDAO;
import dao.InstituicaoDAO;
import dao.ProtocoloDAO;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingWorker;
import modelo.Grupo;
import modelo.Instituicao;
import modelo.Protocolo;
import org.apache.commons.mail.EmailException;
import util.Email;
import visao.modelosComponentes.OrdenarLista;
import visao.modelosComponentes.AgendaCellRenderer;
import visao.modelosComponentes.AgendaTableModel;

/**
 * @author Diego Heusser
 */
public class Agenda {

    private Instituicao instituicao;
    private Grupo grupo;
    private modelo.Atleta atleta;
    private Protocolo protocolo;
    private modelo.Agenda agenda;
    private DefaultListModel<modelo.Atleta> listModelAtleta;
    private DefaultListModel<modelo.Protocolo> listModelProtocolo;
    private DefaultListModel<modelo.Atleta> listModelAgendaAtletas;
    private DefaultListModel<modelo.Protocolo> listModelAgendaProtocolos;
    private AgendaTableModel tableModel;
    private SpinnerModel spinnerModel;
    private boolean alterar, ouvinte;
    private visao.MenuPrincipal mp;
    private visao.Agenda cadAgenda;

    public Agenda() {
        cadAgenda = new visao.Agenda();
        componentes();
        ouvintes();
    }

    public Agenda(visao.MenuPrincipal mp) {
        this.mp = mp;
        cadAgenda = new visao.Agenda();
        componentes();
        ouvintes();
    }

    private void ouvintes() {
        cadAgenda.ckProtocols.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxProtocolos();
            }
        });
        cadAgenda.ckAgendaProtocols.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAgendaProtocolos();
            }
        });
        cadAgenda.ckAthletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAtletas();
            }
        });
        cadAgenda.ckAgendaAthletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxAgendaAtletas();
            }
        });
        cadAgenda.btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar();
            }
        });
        cadAgenda.btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoCancelar();
            }
        });
        cadAgenda.cbInstitution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxInstituicao();
            }
        });
        cadAgenda.cbGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxGrupo();
            }
        });
        cadAgenda.btAddProtocols.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoAddProtocolos();
            }
        });
        cadAgenda.btDelProtocols.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoDelProtocolos();
            }
        });
        cadAgenda.btAddAthletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoAddAtletas();
            }
        });
        cadAgenda.btDelAthletes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoDelAtletas();
            }
        });
        cadAgenda.cbNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoFiltrar((modelo.Instituicao) cadAgenda.cbInstitution.getSelectedItem(),
                        (modelo.Grupo) cadAgenda.cbGroup.getSelectedItem(),
                        (String) cadAgenda.cbGenero.getSelectedItem(),
                        (String) cadAgenda.cbNivel.getSelectedItem());
            }
        });
        cadAgenda.cbGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoFiltrar((modelo.Instituicao) cadAgenda.cbInstitution.getSelectedItem(),
                        (modelo.Grupo) cadAgenda.cbGroup.getSelectedItem(),
                        (String) cadAgenda.cbGenero.getSelectedItem(),
                        (String) cadAgenda.cbNivel.getSelectedItem());
            }
        });
        cadAgenda.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoEditar();
            }
        });
        cadAgenda.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botaoRemover();
            }
        });
        cadAgenda.btPesquisafiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotaoPesquisaFiltrar(cadAgenda.tfAtleta.getText(),
                        cadAgenda.dsData.getDate(),
                        cadAgenda.tfPesquisaLocal.getText(),
                        (modelo.Protocolo) cadAgenda.cbProtocolos.getSelectedItem(),
                        (String) cadAgenda.cbSituation.getSelectedItem());
            }
        });

        //////////////////////////////////
        cadAgenda.liProtocols.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaDeProtocolos(e);
            }
        });
        cadAgenda.liAgendaProtocols.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaDeProtocolosAgendados(e);
            }
        });
        cadAgenda.liAthletes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaDeAtletas(e);
            }
        });
        cadAgenda.liAgendaAthletes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listaDeAtletasAgendados(e);
            }
        });
    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(cadAgenda);
        mp.jPanel.validate();
    }

    //método do evento do check Box ckProtocols
    private void checkBoxProtocolos() {
        cadAgenda.liProtocols.setSelectionInterval(0, cadAgenda.liProtocols.getModel().getSize() - 1);
        if (!cadAgenda.ckProtocols.isSelected()) {
            cadAgenda.liProtocols.clearSelection();
        }
    }

    //método do evento do check Box ckAgendaProtocols
    private void checkBoxAgendaProtocolos() {
        cadAgenda.liAgendaProtocols.setSelectionInterval(0, cadAgenda.liAgendaProtocols.getModel().getSize() - 1);
        if (!cadAgenda.ckAgendaProtocols.isSelected()) {
            cadAgenda.liAgendaProtocols.clearSelection();
        }
    }

    //método do evento do check Box ckAthletes
    private void checkBoxAtletas() {
        cadAgenda.liAthletes.setSelectionInterval(0, cadAgenda.liAthletes.getModel().getSize() - 1);
        if (!cadAgenda.ckAthletes.isSelected()) {
            cadAgenda.liAthletes.clearSelection();
        }
    }

    //método do evento do check Box ckAgendaAthletes
    private void checkBoxAgendaAtletas() {
        cadAgenda.liAgendaAthletes.setSelectionInterval(0, cadAgenda.liAgendaAthletes.getModel().getSize() - 1);
        if (!cadAgenda.ckAgendaAthletes.isSelected()) {
            cadAgenda.liAgendaAthletes.clearSelection();
        }
    }

    private void btSalvar() {
        final visao.BarraProgresso p = new visao.BarraProgresso();
        p.setVisible(true);
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                if (alterar) {
                    botaoAlterar();
                } else {
                    botaoSalvar();
                }
                return null;
            }

            @Override
            protected void done() {
                p.setVisible(false);
            }
        };
        worker.execute();
    }

    //método do evento do botão btCancel
    private void botaoCancelar() {
        limpaTela();
        this.alterar = false;
    }

    //método do evento do combo Box cbInstitution
    private void comboBoxInstituicao() {
        instituicao = (modelo.Instituicao) cadAgenda.cbInstitution.getSelectedItem();
        botaoFiltrar((modelo.Instituicao) cadAgenda.cbInstitution.getSelectedItem(),
                (modelo.Grupo) cadAgenda.cbGroup.getSelectedItem(),
                (String) cadAgenda.cbGenero.getSelectedItem(),
                (String) cadAgenda.cbNivel.getSelectedItem());
        if (instituicao != null) {
            preencheCaixaDeCombinacaoComTodosGrupos(instituicao.getId());
        } else {
            preencheCaixaDeCombinacaoComTodosGrupos(-1);
        }
    }

    //método do evento do combo Box cbGroup
    private void comboBoxGrupo() {
        grupo = (modelo.Grupo) cadAgenda.cbGroup.getSelectedItem();
        botaoFiltrar((modelo.Instituicao) cadAgenda.cbInstitution.getSelectedItem(),
                (modelo.Grupo) cadAgenda.cbGroup.getSelectedItem(),
                (String) cadAgenda.cbGenero.getSelectedItem(),
                (String) cadAgenda.cbNivel.getSelectedItem());
    }

    //método do evento do botão btAddProtocols
    private void botaoAddProtocolos() {
        //verifica se a seleção é vazia
        if (cadAgenda.liProtocols.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Selecione um protocolo da lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //adiciona os protocolos selecionados
        String message = "";
        for (Object list : cadAgenda.liProtocols.getSelectedValuesList()) {
            if (!listModelAgendaProtocolos.contains(list) && !verificaProtocolo((modelo.Protocolo) list)) {
                listModelAgendaProtocolos.addElement((Protocolo) list);
            } else {
                message += list.toString() + ",\n";
            }
        }
        if (!message.equals("")) {
            JOptionPane.showMessageDialog(mp, "O(s) Protocolo(s):\n" + message + "já estão na lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        cadAgenda.liAgendaProtocols.setModel(listModelAgendaProtocolos);
        //deseleciona a lista de Protocolos selecionados
        cadAgenda.liProtocols.clearSelection();
        cadAgenda.ckProtocols.setSelected(false);
    }

    //método do evento do botão btDelProtocols
    private void botaoDelProtocolos() {
        //verifica se a seleção é vazia
        if (cadAgenda.liAgendaProtocols.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Selecione um atleta da lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        //remove da lista os protocolos selecionados
        for (Object list : cadAgenda.liAgendaProtocols.getSelectedValuesList()) {
            listModelAgendaProtocolos.removeElement(list);
        }
        cadAgenda.liAgendaProtocols.setModel(listModelAgendaProtocolos);
        //deseleciona a lisra de Protocolos selecionados
        cadAgenda.liAgendaProtocols.clearSelection();
        cadAgenda.ckAgendaProtocols.setSelected(false);
    }

    //método do evento do botão btAddAthletes
    private void botaoAddAtletas() {
        //verifica se a seleção é vazia
        if (cadAgenda.liAthletes.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Selecione um atleta da lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        //adiciona os atletas selecionados
        String message = "";
        for (Object list : cadAgenda.liAthletes.getSelectedValuesList()) {
            if (!listModelAgendaAtletas.contains(list) && !verificaAtleta((modelo.Atleta) list)) {
                listModelAgendaAtletas.addElement((modelo.Atleta) list);
            } else {
                message += list.toString() + ",\n";
            }
        }
        if (!message.equals("")) {
            JOptionPane.showMessageDialog(mp, "O(s) Atleta(s):\n" + message + "já estão na lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        cadAgenda.liAgendaAthletes.setModel(listModelAgendaAtletas);

        //deseleciona a lista de atletas selecionados
        cadAgenda.liAthletes.clearSelection();
        cadAgenda.ckAthletes.setSelected(false);
    }

    //método do evento do botão btDelAthletes
    private void botaoDelAtletas() {
        //verifica se a seleção é vazia
        if (cadAgenda.liAgendaAthletes.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Selecione um atleta da lista!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        //remove da lista os atletas selecionados
        for (Object list : cadAgenda.liAgendaAthletes.getSelectedValuesList()) {
            listModelAgendaAtletas.removeElement(list);
        }
        cadAgenda.liAgendaAthletes.setModel(listModelAgendaAtletas);
        //deseleciona a lista de atletas selecionados
        cadAgenda.liAgendaAthletes.clearSelection();
        cadAgenda.ckAgendaAthletes.setSelected(false);
    }

    //método do evento da lista liProtocols
    private void listaDeProtocolos(MouseEvent evt) {
        cadAgenda.liAgendaProtocols.clearSelection();
        cadAgenda.liAgendaAthletes.clearSelection();
        cadAgenda.liAthletes.clearSelection();
        if (cadAgenda.ckProtocols.isSelected()) {
            cadAgenda.liProtocols.clearSelection();
            cadAgenda.ckProtocols.setSelected(false);
        }
        //quando for duplo click
        if (evt.getClickCount() == 2) {
            botaoAddProtocolos();
        }
    }

    //método do evento da lista liAgendaProtocols
    private void listaDeProtocolosAgendados(MouseEvent evt) {
        cadAgenda.liProtocols.clearSelection();
        cadAgenda.liAgendaAthletes.clearSelection();
        cadAgenda.liAthletes.clearSelection();
        if (cadAgenda.ckAgendaProtocols.isSelected()) {
            cadAgenda.liAgendaProtocols.clearSelection();
            cadAgenda.ckAgendaProtocols.setSelected(false);
        }
        //quando for duplo click
        if (evt.getClickCount() == 2) {
            botaoDelProtocolos();
        }
    }

    //método do evento da lista liAthletes
    private void listaDeAtletas(MouseEvent evt) {
        cadAgenda.liProtocols.clearSelection();
        cadAgenda.liAgendaProtocols.clearSelection();
        cadAgenda.liAgendaAthletes.clearSelection();
        if (cadAgenda.ckAthletes.isSelected()) {
            cadAgenda.liAthletes.clearSelection();
            cadAgenda.ckAthletes.setSelected(false);
        }
        //quando for duplo click
        if (evt.getClickCount() == 2) {
            botaoAddAtletas();
        }
    }

    //método do evento da lista liAgendaAthletes
    private void listaDeAtletasAgendados(MouseEvent evt) {
        cadAgenda.liProtocols.clearSelection();
        cadAgenda.liAgendaProtocols.clearSelection();
        cadAgenda.liAthletes.clearSelection();
        if (cadAgenda.ckAgendaAthletes.isSelected()) {
            cadAgenda.liAgendaAthletes.clearSelection();
            cadAgenda.ckAgendaAthletes.setSelected(false);
        }
        if (evt.getClickCount() == 2) {
            botaoDelAtletas();
        }
    }

    //método do botão filtrar
    private void botaoFiltrar(Instituicao institution, Grupo group, String genero, String nivel) {
        try {
            ArrayList<modelo.Atleta> arrayFilter = (ArrayList<modelo.Atleta>) atleta(institution, group, genero, nivel);
            listModelAtleta.clear();
            for (int i = 0; i < arrayFilter.size(); i++) {
                atleta = arrayFilter.get(i);
                listModelAtleta.addElement(atleta);
            }
            cadAgenda.liAthletes.setModel(listModelAtleta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método do botão editar
    private void botaoEditar() {
        try {
            this.alterar = true;
            int selectedRow = cadAgenda.tbAgenda.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mp, "Selecione uma linha!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = (int) cadAgenda.tbAgenda.getValueAt(selectedRow, 4);
                modelo.Agenda sc = agenda(id);
                setAgenda(sc);
                agenda = sc;
                cadAgenda.tpAgenda.setSelectedIndex(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método do botão excluir
    private void botaoRemover() {
        try {
            int size = cadAgenda.tbAgenda.getSelectedRows().length;
            int[] selectedRows = new int[size];
            selectedRows = cadAgenda.tbAgenda.getSelectedRows();

            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    int id = (int) cadAgenda.tbAgenda.getValueAt(selectedRows[i], 4);
                    remover(id);
                }
                JOptionPane.showMessageDialog(mp, "Excluído");
                //atualiza a tabela com os dados do banco
                tableModel = new AgendaTableModel(agenda("", "", null, null, ""));
                preencheTabelaComTodos(tableModel);
            } else {
                JOptionPane.showMessageDialog(mp, "Selecione uma linha", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método do botão pesquisaFiltrar
    private void BotaoPesquisaFiltrar(String atleta, Date data, String local, Protocolo protocolo, String situacao) {
        try {
            //atualiza a tabela
            tableModel = new AgendaTableModel(agenda(atleta, local, data, protocolo, situacao));
            preencheTabelaComTodos(tableModel);
            //limpa os campos de pesquisa
            cadAgenda.tfAtleta.setText(null);
            spinnerModel = new SpinnerDateModel();
            cadAgenda.dsData.setDate(null);
            cadAgenda.tfPesquisaLocal.setText(null);
            cadAgenda.cbProtocolos.setSelectedIndex(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método para inicializar os componentes
    private void componentes() {
        try {
            //inicializa o booleano
            this.alterar = false;
            this.ouvinte = true;
            //inicializa os objetos
            this.instituicao = new Instituicao();
            this.grupo = new Grupo();
            this.atleta = new modelo.Atleta();
            this.agenda = new modelo.Agenda();
            this.listModelAtleta = new DefaultListModel<>();
            this.listModelProtocolo = new DefaultListModel<>();
            this.listModelAgendaAtletas = new DefaultListModel<>();
            this.listModelAgendaProtocolos = new DefaultListModel<>();
            this.spinnerModel = new SpinnerDateModel();
            this.tableModel = new AgendaTableModel(agenda("", "", null, null, ""));
            //preeche os comboBoxs
            preencheCaixaDeCombinacaoComTodasInstituicoes();
            preencheCaixaDeCombinacaoComTodosGrupos(-1);
            preenchecaixaDeCombinacaoComTodosProtocolos();
            //preenche o lista com todos os atletas e protocolos
            preecheListaComTodosAtletas();
            preencheListaComTodosProtocolos();
            //método para preencher a tabela com agendamentos
            preencheTabelaComTodos(tableModel);
            //Cria os ouvintes
            cadAgenda.liAgendaProtocols.setModel(listModelAgendaProtocolos);
            MouseAdapter listener = new OrdenarLista(cadAgenda.liAgendaProtocols);
            cadAgenda.liAgendaProtocols.addMouseListener(listener);
            cadAgenda.liAgendaProtocols.addMouseMotionListener(listener);

            cadAgenda.liAgendaAthletes.setModel(listModelAgendaAtletas);
            MouseAdapter listener2 = new OrdenarLista(cadAgenda.liAgendaAthletes);
            cadAgenda.liAgendaAthletes.addMouseListener(listener2);
            cadAgenda.liAgendaAthletes.addMouseMotionListener(listener2);
            //barra de progresso
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método que retorna a altura da linha
    private int calculaAlturaDaLinha(int tamanhoAtletas, int tamanhoProtocolos) {
        if (tamanhoAtletas > tamanhoProtocolos) {
            return (int) (tamanhoAtletas * 16.667);
        } else {
            return (int) (tamanhoProtocolos * 16.667);
        }
    }

    //método para preencher a tabela com agendamentos
    private void preencheTabelaComTodos(AgendaTableModel modelo) {
        //seta o modelo da tabela com os seus respectivos dados
        cadAgenda.tbAgenda.setModel(modelo);
        //definindo a cor de cada linha
        AgendaCellRenderer custom = new AgendaCellRenderer(cadAgenda);
        cadAgenda.tbAgenda.setDefaultRenderer(Object.class, custom);
        //setando a altura de cada linha
        if (modelo.getRow().size() > 0) {
            int linha = 0;
            for (int i = 0; i < modelo.getRow().size(); i++) {
                linha = calculaAlturaDaLinha(modelo.getRow().get(i).getAtleta().size(),
                                modelo.getRow().get(i).getProtocolo().size());
                if (linha < 1) {
                    linha = 16;
                }
                cadAgenda.tbAgenda.setRowHeight(
                        i, linha);
            }
        }
        //esconde a coluna do ID
        cadAgenda.tbAgenda.getColumnModel().getColumn(4).setMaxWidth(0);
        cadAgenda.tbAgenda.getColumnModel().getColumn(4).setMinWidth(0);
        cadAgenda.tbAgenda.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        cadAgenda.tbAgenda.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        //esconde a coluna da situação
        cadAgenda.tbAgenda.getColumnModel().getColumn(5).setMaxWidth(0);
        cadAgenda.tbAgenda.getColumnModel().getColumn(5).setMinWidth(0);
        cadAgenda.tbAgenda.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        cadAgenda.tbAgenda.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
    }

    //método para preencher a  lista com todos os atletas cadastrados
    private void preecheListaComTodosAtletas() {
        try {
            ArrayList<modelo.Atleta> arrayAthlete = (ArrayList<modelo.Atleta>) (atleta(null, null, "", ""));
            listModelAtleta.clear();
            for (int i = 0; i < arrayAthlete.size(); i++) {
                atleta = arrayAthlete.get(i);
                listModelAtleta.addElement(atleta);
            }
            cadAgenda.liAthletes.setModel(listModelAtleta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método para preencher a lista com todos os protocolos do banco
    private void preencheListaComTodosProtocolos() {
        try {
            ArrayList<Protocolo> arrayProtocol = (ArrayList<Protocolo>) (protocolo());
            listModelProtocolo.clear();
            for (int i = 0; i < arrayProtocol.size(); i++) {
                protocolo = arrayProtocol.get(i);
                listModelProtocolo.addElement(protocolo);
            }
            cadAgenda.liProtocols.setModel(listModelProtocolo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método para preecher o combo Box cbInstitution com todas as instituições cadastradas
    private void preencheCaixaDeCombinacaoComTodasInstituicoes() {
        try {
            ArrayList<Instituicao> arrayInstitution = (ArrayList<Instituicao>) (instituicao());
            instituicao = null;
            cadAgenda.cbInstitution.addItem(instituicao);
            for (int i = 0; i < arrayInstitution.size(); i++) {
                instituicao = arrayInstitution.get(i);
                cadAgenda.cbInstitution.addItem(instituicao);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método para preencher o combo Box cbGroup com todos os grupos cadastrados
    private void preencheCaixaDeCombinacaoComTodosGrupos(int id) {
        //preenche com os grupos cadastrados
        if (id == -1) {
            try {
                cadAgenda.cbGroup.removeAllItems();
                ArrayList<modelo.Grupo> arrayGroup = (ArrayList<modelo.Grupo>) (grupo());
                grupo = null;
                cadAgenda.cbGroup.addItem(grupo);
                for (int i = 0; i < arrayGroup.size(); i++) {
                    grupo = (Grupo) arrayGroup.get(i);
                    cadAgenda.cbGroup.addItem(grupo);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            try {
                cadAgenda.cbGroup.removeAllItems();
                ArrayList<modelo.Grupo> arrayGroup = (ArrayList<modelo.Grupo>) (grupo(id));
                grupo = null;
                cadAgenda.cbGroup.addItem(grupo);
                for (int i = 0; i < arrayGroup.size(); i++) {
                    grupo = (Grupo) arrayGroup.get(i);
                    cadAgenda.cbGroup.addItem(grupo);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    //método para preencher o combo Box cbProtocol com todos os protocolos
    private void preenchecaixaDeCombinacaoComTodosProtocolos() {
        try {
            ArrayList<modelo.Protocolo> arrayProtocol = (ArrayList<modelo.Protocolo>) (protocolo());
            protocolo = null;
            cadAgenda.cbProtocolos.addItem(protocolo);
            for (int i = 0; i < arrayProtocol.size(); i++) {
                protocolo = arrayProtocol.get(i);
                cadAgenda.cbProtocolos.addItem(protocolo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método para verificar se o objeto já está na lista
    private boolean verificaProtocolo(Protocolo p) {
        boolean verifica = false;
        for (int i = 0; i < listModelAgendaProtocolos.getSize(); i++) {
            if (p.equals(listModelAgendaProtocolos.getElementAt(i))) {
                verifica = true;
            }
        }
        return verifica;
    }

    //método para verificar se o objeto já está na lista
    private boolean verificaAtleta(modelo.Atleta a) {
        boolean verifica = false;
        for (int i = 0; i < listModelAgendaAtletas.getSize(); i++) {
            if (a.equals(listModelAgendaAtletas.getElementAt(i))) {
                verifica = true;
            }
        }
        return verifica;
    }

    //método para limpar os campos da tela
    private void limpaTela() {
        //seta a posição inicial dos combo box e date chooser
        cadAgenda.cbInstitution.setSelectedIndex(0);
        cadAgenda.cbGroup.setSelectedIndex(0);
        cadAgenda.spDateTime.setValue(new Date());
        cadAgenda.tfLocal.setText(null);
        //esvazia as listas de agendamentos
        listModelAgendaAtletas.removeAllElements();
        listModelAgendaProtocolos.removeAllElements();
        cadAgenda.liAgendaProtocols.setModel(listModelAgendaProtocolos);
        cadAgenda.liAgendaAthletes.setModel(listModelAgendaAtletas);
        //desmarca os checks boxs
        cadAgenda.ckEmailNotice.setSelected(false);
        cadAgenda.ckAthletes.setSelected(false);
        cadAgenda.ckAgendaAthletes.setSelected(false);
        cadAgenda.ckProtocols.setSelected(false);
        cadAgenda.ckAgendaProtocols.setSelected(false);
        //deseleciona as listas de agendamentos
        cadAgenda.liAthletes.clearSelection();
        cadAgenda.liAgendaAthletes.clearSelection();
        cadAgenda.liProtocols.clearSelection();
        cadAgenda.liAgendaProtocols.clearSelection();
    }

    //método do botão alterar
    private void botaoAlterar() {
        Date data = (Date) cadAgenda.spDateTime.getValue();
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        //verifica se a data é válida
        if (data.before(new Date())) {
            JOptionPane.showMessageDialog(mp, "A data [ " + d.format(data) + " ] já passou!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int s = -1;
            int f = -1;
            String pr = "";
            //preenche um array com todos os protocolos da lista
            int sizeProtocols = cadAgenda.liAgendaProtocols.getModel().getSize();
            Protocolo[] arrayProtocols = new Protocolo[sizeProtocols];
            for (int i = 0; i < sizeProtocols; i++) {
                arrayProtocols[i] = (Protocolo) cadAgenda.liAgendaProtocols.getModel().getElementAt(i);
                Protocolo p = (Protocolo) cadAgenda.liAgendaProtocols.getModel().getElementAt(i);
                pr += p.toString() + "\n";
                if (arrayProtocols[i].getNome().equals("Shuttle Run")) {
                    s = i;
                }
                if (arrayProtocols[i].getNome().equals("Sprint Fatigue Test")) {
                    f = i;
                }
            }
            int opcao;
            if (s != -1 && f != -1) {
                if (s < (sizeProtocols - 2) && f == (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Shuttle Run', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                } else if (f < (sizeProtocols - 2) && s == (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Sprint Fatigue Test', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                } else if (f < (sizeProtocols - 1) && s < (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "Os testes de resistência 'Sprint Fatigue Test' e 'Shuttle Run', estão antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } else if (s != -1) {
                if (s != (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Shuttle Run', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } else if (f != -1) {
                if (f != (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Sprint Fatigue Test', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }
            //preenche um array com todos os atletas da lista
            int sizeAthletes = cadAgenda.liAgendaAthletes.getModel().getSize();
            modelo.Atleta[] arrayAthletes = new modelo.Atleta[sizeAthletes];
            for (int i = 0; i < sizeAthletes; i++) {
                arrayAthletes[i] = (modelo.Atleta) cadAgenda.liAgendaAthletes.getModel().getElementAt(i);
            }
            if (!verifica(arrayAthletes, arrayProtocols, cadAgenda.tfLocal.getText())) {
                return;
            }
            //passa os arrays para uma lista
            List<Protocolo> protocol = Arrays.asList(arrayProtocols);
            List<modelo.Atleta> athlete = Arrays.asList(arrayAthletes);
            //salva os dados do agendamento
            alterar(this.agenda.getId(), (Date) cadAgenda.spDateTime.getValue(), cadAgenda.tfLocal.getText(), "Em aberto", protocol, athlete);
            //se o check box estiver selecionado ele envia um email
            if (cadAgenda.ckEmailNotice.isSelected()) {
                enviaEmail(athlete, "Sistema de Avaliação Física para Badminton", pr);
            }
            JOptionPane.showMessageDialog(mp, "Salvo!");
            AgendaTableModel tbmd = new AgendaTableModel(agenda("", "", null, null, ""));
            preencheTabelaComTodos(tbmd);
            limpaTela();
            this.alterar = false;
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //método do evento do botão btSave
    private void botaoSalvar() {
        Date data = (Date) cadAgenda.spDateTime.getValue();
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        //verifica se a data é válida
        if (data.before(new Date())) {
            JOptionPane.showMessageDialog(mp, "A data [ " + d.format(data) + " ] já passou!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int s = -1;
            int f = -1;
            String pr = "";
            //preenche um array com todos os protocolos da lista
            int sizeProtocols = cadAgenda.liAgendaProtocols.getModel().getSize();
            Protocolo[] arrayProtocols = new Protocolo[sizeProtocols];
            for (int i = 0; i < sizeProtocols; i++) {
                arrayProtocols[i] = (Protocolo) cadAgenda.liAgendaProtocols.getModel().getElementAt(i);
                Protocolo p = (Protocolo) cadAgenda.liAgendaProtocols.getModel().getElementAt(i);
                pr += p.toString() + "\n";
                if (arrayProtocols[i].getNome().equals("Shuttle Run")) {
                    s = i;
                }
                if (arrayProtocols[i].getNome().equals("Sprint Fatigue Test")) {
                    f = i;
                }
            }
            int opcao;
            if (s != -1 && f != -1) {
                if (s < (sizeProtocols - 2) && f == (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Shuttle Run', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                } else if (f < (sizeProtocols - 2) && s == (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Sprint Fatigue Test', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                } else if (f < (sizeProtocols - 1) && s < (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "Os testes de resistência 'Sprint Fatigue Test' e 'Shuttle Run', estão antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } else if (s != -1) {
                if (s != (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Shuttle Run', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } else if (f != -1) {
                if (f != (sizeProtocols - 1)) {
                    opcao = JOptionPane.showConfirmDialog(mp, "O teste de resistência 'Sprint Fatigue Test', está antes dos demais.\n Deseja continuar?",
                            "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opcao == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            }

            //preenche um array com todos os atletas da lista
            int sizeAthletes = cadAgenda.liAgendaAthletes.getModel().getSize();
            modelo.Atleta[] arrayAthletes = new modelo.Atleta[sizeAthletes];
            for (int i = 0; i < sizeAthletes; i++) {
                arrayAthletes[i] = (modelo.Atleta) cadAgenda.liAgendaAthletes.getModel().getElementAt(i);
            }
            if (!verifica(arrayAthletes, arrayProtocols, cadAgenda.tfLocal.getText())) {
                return;
            }
            //passa os arrays para uma lista
            List<Protocolo> protocol = Arrays.asList(arrayProtocols);
            List<modelo.Atleta> athlete = Arrays.asList(arrayAthletes);
            //salva os dados do agendamento
            salvar((Date) cadAgenda.spDateTime.getValue(), cadAgenda.tfLocal.getText(), "Em aberto", protocol, athlete);
            //se o check box estiver selecionado ele envia um email
            if (cadAgenda.ckEmailNotice.isSelected()) {
                enviaEmail(athlete, "Sistema de Avaliação Física para Badminton - Agendamento", pr);
            }
            JOptionPane.showMessageDialog(mp, "Salvo!");
            AgendaTableModel tbmd = new AgendaTableModel(agenda("", "", null, null, ""));
            preencheTabelaComTodos(tbmd);
            limpaTela();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    //método que seta os campos do agendamento
    private void setAgenda(modelo.Agenda agenda) {
        //setando os protocolos para a lista de protocolos agendados
        listModelAgendaProtocolos.clear();
        for (Protocolo list : agenda.getProtocolo()) {
            listModelAgendaProtocolos.addElement(list);
        }
        cadAgenda.liAgendaProtocols.setModel(listModelAgendaProtocolos);
        //setando os atletas para a lista de atletas agendados
        listModelAgendaAtletas.clear();
        for (modelo.Atleta list : agenda.getAtleta()) {
            listModelAgendaAtletas.addElement(list);
        }
        cadAgenda.liAgendaAthletes.setModel(listModelAgendaAtletas);
        //setando o campo do local
        cadAgenda.tfLocal.setText(agenda.getLocal());
        //setando o campo da data
        cadAgenda.spDateTime.setValue(agenda.getData());
    }

    //método para enviar o email
    private void enviaEmail(List<modelo.Atleta> atleta, String assunto, String protocolos) {
        Email email = null;
        Instituicao t = null;
        controle.Atleta ac = new controle.Atleta();
        String message = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat shf = new SimpleDateFormat("HH:mm:ss");
        for (modelo.Atleta list : atleta) {
            try {
                t = ac.institutionObject(list.getInstituicao().getId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(mp, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            message += "Prezado atleta " + list.getNome() + "!\n\n"
                    + "A instituição '" + t.toString() + "' agendou "
                    + "um teste físico para ser realizado por você.\n\n"
                    + "O teste ocorrerá conforme as informações abaixo:\n\n"
                    + "Local: " + cadAgenda.tfLocal.getText() + ".\n"
                    + "Data: " + sdf.format(cadAgenda.spDateTime.getValue()) + ".\n"
                    + "Horário: " + shf.format(cadAgenda.spDateTime.getValue()) + ".\n\n"
                    + "Testes: \n"
                    + protocolos + "\n"
                    + "* Esse é um email automático enviado pelo 'Sistema de Avaliação Física para Badminton'"
                    + "(UDESC-CEAVI), não responda!\n\n"
                    + "Atenciosamente.\n"
                    + t.toString();
            email = new Email(list.getEmail(), list.getNome(), assunto, message);
            try {
                email.sendEmail();
            } catch (EmailException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mp, "Não foi possível enviar o email:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean verifica(modelo.Atleta[] atleta ,Protocolo[] protocolo, String local) {
        if (atleta.length == 0) {
            JOptionPane.showMessageDialog(mp, "Adicione um atleta antes de salvar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (protocolo.length == 0) {
            JOptionPane.showMessageDialog(mp, "Adicione um protocolo antes de salvar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (local.isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Digite um local antes de salvar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //método para salvar no banco de dados
    public void salvar(Date data, String local, String situacao, 
            List<Protocolo> protocolo, List<modelo.Atleta> atleta) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        modelo.Agenda scheduling = new modelo.Agenda();
        scheduling.setData(data);
        scheduling.setSituacao(situacao);
        scheduling.setAtleta(atleta);
        scheduling.setProtocolo(protocolo);
        scheduling.setLocal(local);
        dao.save(scheduling);
    }

    //método para alterar no banco de dados
    public void alterar(int agendaId, Date data, String local, String situacao,
            List<Protocolo> protocolo ,List<modelo.Atleta> atleta) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        modelo.Agenda scheduling = new modelo.Agenda();
        scheduling.setId(agendaId);
        scheduling.setData(data);
        scheduling.setLocal(local);
        scheduling.setSituacao(situacao);
        scheduling.setProtocolo(protocolo);
        scheduling.setAtleta(atleta);
        dao.update(scheduling);
    }

    //método para apagar no banco de dados
    public void remover(int id) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        dao.delete(id);
    }

    //método que retorna uma lista com todos os grupos cadastrados
    public List<Grupo> grupo() throws SQLException {
        GrupoDAO dao = new GrupoDAO();
        return dao.findGrupo();
    }

    //método que retorna uma lista conforme o id passado
    public List<Grupo> grupo(int id) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.group(id);
    }
    //método que retorna uma lista com todas as instituições cadastrados

    public List<Instituicao> instituicao() throws SQLException {
        InstituicaoDAO dao = new InstituicaoDAO();
        return dao.findByInstituicao();
    }

    //método que retorna uma lista de todos os protocolos
    public List<Protocolo> protocolo() throws SQLException {
        ProtocoloDAO dao = new ProtocoloDAO();
        return dao.protocol();
    }

    //método que retorna um objeto do tipo Agenda
    public modelo.Agenda agenda(int agendaId) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.scheduling(agendaId);
    }

    //método que retorna uma lista de agendamentos conforme os filtros passados
    public List<modelo.Agenda> agenda(String atleta, String local, Date data,
            Protocolo protocolo, String situacao) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.scheduling(atleta, local, data, protocolo, situacao);

    }

    //método que retorna uma lista de atletas conforme os filtros passados
    public List<modelo.Atleta> atleta(Instituicao instituicao ,Grupo grupo,
            String genero, String nivel) throws SQLException {
        AgendaDAO dao = new AgendaDAO();
        return dao.athlete(instituicao, grupo, genero, nivel);
    }
}
