//Grupo.java
package controle;

import dao.AtletaDAO;
import dao.GrupoDAO;
import dao.InstituicaoDAO;
import dao.PeriodoDAO;
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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.Instituicao;
import modelo.Periodo;
import visao.modelosComponentes.GrupoTableModel;

/**
 *
 * @author 5102011212
 */
public class Grupo {

    private modelo.Atleta atletaEditar;
    private modelo.Atleta atleta;
    private modelo.Atleta atleta2 = new modelo.Atleta();
    private boolean todos = false;
    private int idga = 0;
    private visao.MenuPrincipal mp;
    private visao.Grupo cadGrupo;
    private modelo.Instituicao inst;
    private DefaultListModel listaModel;
    private DefaultListModel listaModel2;
    private ArrayList<modelo.Atleta> array;
    private ArrayList<modelo.Atleta> arrayNew;
    private ArrayList<modelo.Atleta> arrayEditar;
    private boolean alterar;

    public Grupo() {
                cadGrupo = new visao.Grupo();
        alterar = false;
        listaModel = new DefaultListModel();
        listaModel2 = new DefaultListModel();
        array = new ArrayList<>();
        arrayNew = new ArrayList<>();
        arrayEditar = new ArrayList<>();
        GrupoTableModel model = new GrupoTableModel();
        try {
            setCbInstituicao();//adiciona todas instiuções no comboBox
            model = new GrupoTableModel(grupo(), listaGrupoAtleta());//insere duas lista para o model da tabela
           //atualiza a tabela
            setLiTodosAtletas();//adiciona todos os atletas a lista 
        } catch (SQLException e) {
            e.printStackTrace();
        }
         tabela(model);

        cadGrupo.btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        cadGrupo.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        cadGrupo.btDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                del();
            }
        });
        cadGrupo.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        cadGrupo.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        cadGrupo.btFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        cadGrupo.btMostraTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraTudo();
            }
        });
        cadGrupo.btNovoGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoGrupo();
            }
        });
        cadGrupo.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        cadGrupo.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        cadGrupo.cbInstituicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboInstituicao();
            }
        });
        cadGrupo.ckSelecionaAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdd();
            }
        });
        cadGrupo.ckSelecionaDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDel();
            }
        });
        cadGrupo.cbNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadGrupo.btFiltrar.setEnabled(true);
            }
        });
        cadGrupo.cbGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadGrupo.btFiltrar.setEnabled(true);
            }
        });

        cadGrupo.tbGrupo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    editar();
                } else {
                    limparTela();
                    abilita(false);
                }
            }
        });
        cadGrupo.liAtleta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    del();
                }
            }
        });
        cadGrupo.liTodosAtletas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    add();
                }
            }
        });
    }

    public Grupo(visao.MenuPrincipal mp) {
        this.mp = mp;
        cadGrupo = new visao.Grupo();
        alterar = false;
        listaModel = new DefaultListModel();
        listaModel2 = new DefaultListModel();
        array = new ArrayList<>();
        arrayNew = new ArrayList<>();
        arrayEditar = new ArrayList<>();
        GrupoTableModel model = new GrupoTableModel();
        try {
            setCbInstituicao();//adiciona todas instiuções no comboBox
            model = new GrupoTableModel(grupo(), listaGrupoAtleta());//insere duas lista para o model da tabela
           //atualiza a tabela
            setLiTodosAtletas();//adiciona todos os atletas a lista 
        } catch (SQLException e) {
            e.printStackTrace();
        }
         tabela(model);

        cadGrupo.btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        cadGrupo.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        cadGrupo.btDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                del();
            }
        });
        cadGrupo.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        cadGrupo.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        cadGrupo.btFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        cadGrupo.btMostraTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraTudo();
            }
        });
        cadGrupo.btNovoGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoGrupo();
            }
        });
        cadGrupo.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        cadGrupo.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        cadGrupo.cbInstituicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboInstituicao();
            }
        });
        cadGrupo.ckSelecionaAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdd();
            }
        });
        cadGrupo.ckSelecionaDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkDel();
            }
        });
        cadGrupo.cbNivel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadGrupo.btFiltrar.setEnabled(true);
            }
        });
        cadGrupo.cbGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadGrupo.btFiltrar.setEnabled(true);
            }
        });

        cadGrupo.tbGrupo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    editar();
                } else {
                    limparTela();
                    abilita(false);
                }
            }
        });
        cadGrupo.liAtleta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    del();
                }
            }
        });
        cadGrupo.liTodosAtletas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {//verifica se foi dado dois click com o mouse
                    add();
                }
            }
        });
    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(cadGrupo);
        mp.jPanel.validate();
    }

    private void setListaTodosAtleta(int id) throws SQLException {//metodo para preencher a lista conforme o id da instituição
        ArrayList<modelo.Atleta> array = (ArrayList<modelo.Atleta>) (listInstituicao(id));
        listaModel = new DefaultListModel();
        modelo.Atleta a = new modelo.Atleta();
        for (int i = 0; i < array.size(); i++) {
            a = (modelo.Atleta) array.get(i);
            listaModel.addElement(a);
        }
        cadGrupo.liTodosAtletas.setModel(listaModel);
    }

    private void setListaTodosAtletaFiltro(int instituicao, String genero, String nivel) {//metodo para preencher a lista conforme a institução, gênero e nível
        ArrayList<modelo.Atleta> array = new ArrayList<>();
        try {
            array = (ArrayList<modelo.Atleta>) (listInstituicaoGeneroNivel(instituicao, genero, nivel));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaModel = new DefaultListModel();
        modelo.Atleta a = new modelo.Atleta();
        for (int i = 0; i < array.size(); i++) {
            a = (modelo.Atleta) array.get(i);
            listaModel.addElement(a);
        }
        cadGrupo.liTodosAtletas.setModel(listaModel);
    }

    private void setLiTodosAtletas() throws SQLException {//metodo para preencher a lista com todos os atletas do banco
        ArrayList<modelo.Atleta> ar = (ArrayList<modelo.Atleta>) (atleta());
        listaModel = new DefaultListModel();
        modelo.Atleta a = new modelo.Atleta();
        for (int i = 0; i < ar.size(); i++) {
            a = (modelo.Atleta) ar.get(i);
            listaModel.addElement(a);
        }
        cadGrupo.liTodosAtletas.setModel(listaModel);
    }

    private void setCbInstituicao() {// metodo para preencher o comboBox com todas as intituições do banco
        try {
            ArrayList<Instituicao> ar = (ArrayList<Instituicao>) (instituicao());

            for (int i = 0; i < ar.size(); i++) {
                inst = (Instituicao) ar.get(i);
                cadGrupo.cbInstituicao.addItem(inst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void tabela(GrupoTableModel model) {
        cadGrupo.tbGrupo.setModel(model);//atualiza a tabela
        //esconde a coluna 0
        cadGrupo.tbGrupo.getColumnModel().getColumn(0).setMaxWidth(0);
        cadGrupo.tbGrupo.getColumnModel().getColumn(0).setMinWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        //esconde a coluna 2
        cadGrupo.tbGrupo.getColumnModel().getColumn(2).setMaxWidth(0);
        cadGrupo.tbGrupo.getColumnModel().getColumn(2).setMinWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
        //esconde a coluna 5
        cadGrupo.tbGrupo.getColumnModel().getColumn(5).setMaxWidth(0);
        cadGrupo.tbGrupo.getColumnModel().getColumn(5).setMinWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        cadGrupo.tbGrupo.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

    }

    private void limparTela() {//metodo para limpar a tela
        cadGrupo.tfNome.setText(null);
        cadGrupo.cbInstituicao.setSelectedIndex(-1);
        listaModel2.clear();
        cadGrupo.liAtleta.setModel(listaModel2);
        cadGrupo.liTodosAtletas.setModel(listaModel2);
        cadGrupo.ckSelecionaAdd.setSelected(false);
        cadGrupo.ckSelecionaDel.setSelected(false);
        cadGrupo.dcFinal.setDate(null);
        cadGrupo.dcInicio.setDate(null);
        arrayEditar.clear();
    }

    private void abilita(boolean b) {// metodo para abilitar os componentes da tela
        cadGrupo.tfNome.setEnabled(b);
        cadGrupo.cbInstituicao.setEnabled(b);
        cadGrupo.btAdd.setEnabled(b);
        cadGrupo.btCancelar.setEnabled(b);
        cadGrupo.btDel.setEnabled(b);
        cadGrupo.btSalvar.setEnabled(b);
        cadGrupo.liAtleta.setEnabled(b);
        cadGrupo.liTodosAtletas.setEnabled(b);
        cadGrupo.ckSelecionaAdd.setEnabled(b);
        cadGrupo.ckSelecionaDel.setEnabled(b);
        cadGrupo.dcFinal.setEnabled(b);
        cadGrupo.dcInicio.setEnabled(b);
        cadGrupo.cbGenero.setEnabled(b);
        cadGrupo.cbNivel.setEnabled(b);
        cadGrupo.btFiltrar.setEnabled(b);

    }

    private boolean validaCampos() { //metodo para validar os campos
        Date inicial = cadGrupo.dcInicio.getDate();
        Date dataFinal = cadGrupo.dcFinal.getDate();

        if (inicial.after(dataFinal)) {// verifica se a data inicial vem depois da data final
            JOptionPane.showMessageDialog(null, "Data inicial inválida!");
            cadGrupo.dcInicio.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    private void add() {
        if (cadGrupo.liTodosAtletas.getSelectedValuesList().isEmpty()) {//verifica se a seleção da lista está nula
            JOptionPane.showMessageDialog(null, "Selecione um atleta da lista!");
            return;
        }
        arrayNew.clear();
        try {
            arrayNew = (ArrayList<modelo.Atleta>) atleta();//preenche o array com todos os atletas do banco
            modelo.Atleta a = new modelo.Atleta();
            boolean contem = false;
            //adiciona o atleta selecionado a lista de atletas no grupo
            for (Object a2 : cadGrupo.liTodosAtletas.getSelectedValuesList()) {
                a = (modelo.Atleta) a2;
                for (int i = 0; i < arrayEditar.size(); i++) {
                    modelo.Atleta b = (modelo.Atleta) arrayEditar.get(i);
                    if (b.getId() == (a.getId())) {
                        contem = true;
                    }
                }
                //verifica se o atleta selecionado já está na lista
                if (listaModel2.contains(a2) || contem == true) {
                    JOptionPane.showMessageDialog(null, a2.toString() + " já está na lista!");
                } else {
                    listaModel2.addElement(a2);
                }

            }
            cadGrupo.liAtleta.setModel(listaModel2);//atualiza a lista
            cadGrupo.ckSelecionaAdd.setSelected(false);//desmarca o check
            cadGrupo.liTodosAtletas.clearSelection();//deseleciona
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar o atleta no grupo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cancelar() {
        limparTela();
        alterar = false;
    }

    private void del() {
        if (cadGrupo.liAtleta.getSelectedValuesList().isEmpty()) {//verifica se a seleção da lista está nula
            JOptionPane.showMessageDialog(null, "Selecione um atleta da lista!");
            return;
        }
        //remove o atleta da lista
        for (Object atletaR : cadGrupo.liAtleta.getSelectedValuesList()) {
            listaModel2.removeElement(atletaR);
            arrayNew.remove(atletaR);
            arrayEditar.remove(atletaR);
        }
        cadGrupo.ckSelecionaDel.setSelected(false);
        cadGrupo.liAtleta.clearSelection();//deseleciona
    }

    private void novoGrupo() {
        abilita(true);
        limparTela();
        alterar = false;
    }

    private void editar() {
        limparTela();
        alterar = true;
        int linha = cadGrupo.tbGrupo.getSelectedRow();
        if (linha != -1) {
            abilita(true);
            cadGrupo.tfNome.setText((String) cadGrupo.tbGrupo.getValueAt(linha, 1));
            cadGrupo.cbInstituicao.setSelectedIndex((int) cadGrupo.tbGrupo.getValueAt(linha, 2) - 1);
            cadGrupo.dcInicio.setDate(trocaDiaPorMes(cadGrupo.tbGrupo.getValueAt(linha, 3).toString()));
            cadGrupo.dcFinal.setDate(trocaDiaPorMes(cadGrupo.tbGrupo.getValueAt(linha, 4).toString()));
            idga = (int) cadGrupo.tbGrupo.getValueAt(linha, 5);
            ArrayList grupoAtleta = new ArrayList();
            int grupoId = (int) cadGrupo.tbGrupo.getValueAt(linha, 0);
            int max = array.size();
            try {
                grupoAtleta = (ArrayList) grupoAtletaId(grupoId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(grupoAtleta.size());
            atletaEditar = new modelo.Atleta();
            Periodo ga = new Periodo();
            ArrayList<modelo.Atleta> atletaArray = new ArrayList<modelo.Atleta>();
            for (int i = 0; i < grupoAtleta.size(); i++) {

                ga = (Periodo) grupoAtleta.get(i);
                int id = ga.getAtletaId();
                try {
                    atletaArray = (ArrayList<modelo.Atleta>) atletaId(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                atletaEditar = atletaArray.get(0);
                arrayEditar.add(atletaEditar);
                listaModel2.addElement(atletaEditar);
            }
            cadGrupo.liAtleta.setModel(listaModel2);

        } else {
            JOptionPane.showMessageDialog(mp, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void excluir() {
        int linha = cadGrupo.tbGrupo.getSelectedRow();
        if (linha != -1) {
            try {
                int[] row = cadGrupo.tbGrupo.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    int id = (int) cadGrupo.tbGrupo.getValueAt(row[i], 0);
                    periodDelete(id);
                }
                for (int i = 0; i < row.length; i++) {
                    int id = (int) cadGrupo.tbGrupo.getValueAt(row[i], 0);
                    delete(id);
                }
                GrupoTableModel model = new GrupoTableModel(grupo(), listaGrupoAtleta());
                tabela(model);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(mp, "Erro ao remover grupos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(mp, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }
        limparTela();
        abilita(false);
    }

    private void mostraTudo() {
        try {
            GrupoTableModel model = new GrupoTableModel(grupo(), listaGrupoAtleta());
            tabela(model);
            cadGrupo.btMostraTudo.setEnabled(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void pesquisar() {
        String nome = cadGrupo.tfNomePesquisa.getText();
        List<modelo.Grupo> grupo = new ArrayList<>();
        try {
            grupo = searchGroupByName(nome);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (grupo.isEmpty()) {
            try {
                GrupoTableModel model = new GrupoTableModel(grupo, listaGrupoAtleta());
                tabela(model);
                JOptionPane.showMessageDialog(mp, "Grupo não encontrado!");
                cadGrupo.btMostraTudo.setEnabled(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                GrupoTableModel model = new GrupoTableModel(grupo, listaGrupoAtleta());
                tabela(model);
                cadGrupo.btMostraTudo.setEnabled(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void salvar() {
        if (alterar == false) {
            if (validaCampos()) {

                modelo.Grupo grupo = new modelo.Grupo();
                grupo.setNome(cadGrupo.tfNome.getText());
                grupo.setInstituicaoID(inst.getId());
                grupo.setDataInicial(cadGrupo.dcInicio.getDate());
                grupo.setDataFinal(cadGrupo.dcFinal.getDate());
                int grupoMaxID;
                try {
                    // Gravar o grupo
                    save(grupo);
                    grupoMaxID = getID();

                    // Gravar os atletas do grupo na tabela período
                    for (int i = 0; i < listaModel2.size(); i++) {
                        modelo.Atleta a = (modelo.Atleta) listaModel2.get(i);
                        periodSave(a.getId(), grupoMaxID);
                    }

                    // Depois que salvou... atualizar tabelas da interface
                    GrupoTableModel model = new GrupoTableModel(grupo(), listaGrupoAtleta());
                    tabela(model);
                    cadGrupo.liAtleta.removeAll();
                    limparTela();
                    abilita(false);

                } catch (SQLException | ParseException e) {
                    JOptionPane.showMessageDialog(mp, "Erro ao gravar grupo e atletas do grupo: \n" + e.getMessage());
                    e.printStackTrace();
                }

            }
        } else {
            if (validaCampos()) {
                modelo.Grupo grupo = new modelo.Grupo();
                grupo.setNome(cadGrupo.tfNome.getText());
                int linha = cadGrupo.tbGrupo.getSelectedRow();
                int grupoID = (int) cadGrupo.tbGrupo.getValueAt(linha, 0);
                grupo.setId(grupoID);
                grupo.setInstituicaoID(inst.getId());
                grupo.setDataInicial(cadGrupo.dcInicio.getDate());
                grupo.setDataFinal(cadGrupo.dcFinal.getDate());
                try {
                    // altera os dados da tabela grupo
                    update(grupo);

                    // remove todos os atletas da tabela periodo de um determinado grupo
                    removeAll(grupoID);
                    for (int i = 0; i < listaModel2.size(); i++) {
                        modelo.Atleta a = (modelo.Atleta) listaModel2.get(i);
                        // salva os atletas na tabela Periodo
                        periodSave(a.getId(), grupoID);
                    }

                    // atualiza a tabela
                    GrupoTableModel model = new GrupoTableModel(grupo(), listaGrupoAtleta());
                    tabela(model);
                    cadGrupo.liAtleta.removeAll();
                    limparTela();
                    abilita(false);
                    alterar = true;
                } catch (SQLException | ParseException e) {
                    JOptionPane.showMessageDialog(mp, "Erro ao alterar os dados: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }

    private void comboInstituicao() {
        this.inst = (Instituicao) cadGrupo.cbInstituicao.getSelectedItem();
        try {
            setListaTodosAtleta(inst.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cadGrupo.btFiltrar.setEnabled(true);
    }

    private void checkAdd() {
        cadGrupo.liTodosAtletas.setSelectionInterval(0, listaModel.getSize() - 1);
        if (!cadGrupo.ckSelecionaAdd.isSelected()) {
            cadGrupo.liTodosAtletas.clearSelection();
        }
    }

    private void checkDel() {
        cadGrupo.liAtleta.setSelectionInterval(0, listaModel2.getSize() - 1); //seleciona todos as linhas da lista
        if (!cadGrupo.ckSelecionaDel.isSelected()) {//verifica se o check está selecionado
            cadGrupo.liAtleta.clearSelection();//limpa as linhas lista
        }
    }

    private void filtrar() {
        String genero = (String) cadGrupo.cbGenero.getSelectedItem();
        String nivel = (String) cadGrupo.cbNivel.getSelectedItem();
        int instituicao = inst.getId();
        setListaTodosAtletaFiltro(instituicao, genero, nivel);
        cadGrupo.btFiltrar.setEnabled(false);
    }

    private Date trocaDiaPorMes(String date) {// metodo para trocar o dia pelo mês e o mês pelo dia
        Date newDate = new Date();
        String d = date.substring(3, 6);
        d += date.substring(0, 3);
        d += date.substring(6, 10);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            newDate = sdf.parse(d);
        } catch (ParseException ex) {
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }
    // retorna o ultimo ID do grupo

    public int getID() throws SQLException {
        PeriodoDAO g = new PeriodoDAO();
        return g.getId();
    }

    //retorna o ultimo ID do GrupoAtleta
    public int getIDPeriodo() throws SQLException {
        PeriodoDAO g = new PeriodoDAO();
        return g.getId();
    }

    //retorna uma lista do GrupoAtleta(Periodo)
    public List<Periodo> grupoAtletaId(int grupoId) throws SQLException {
        PeriodoDAO dao = new PeriodoDAO();
        return dao.grupoAtletaId(grupoId);
    }

    //retorna o uma lista de um determinado atleta 
    public List<modelo.Atleta> atletaId(int atletaId) throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.findByID(atletaId);
    }

    //retorna uma lista contendo todos os dados da tabela grupoAtleta
    public List<Periodo> listaGrupoAtleta() throws SQLException {
        PeriodoDAO dao = new PeriodoDAO();
        return dao.findGrupoAtleta();
    }

    //método que retorna uma lista contendo todas as instituições cadastradas
    public List<Instituicao> instituicao() throws SQLException {
        InstituicaoDAO dao = new InstituicaoDAO();
        return dao.findByInstituicao();
    }

    //método que retorna uma lista contendo todos os atletas cadastrados
    public List<modelo.Atleta> atleta() throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.atletas();
    }

    //método que retorna uma lista contendo todos os atletas que pertencem a uma determinada instituição
    public List<modelo.Atleta> listInstituicao(int id) throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.athletesOfTheInstitution(id);
    }

    /*método que retorna uma lista contendo todos os atletas que pertencem a uma
     *determinada instituição, e possuem um certo gênero e um nível*/
    public List<modelo.Atleta> listInstituicaoGeneroNivel(int instituicao, String genero, String nivel) throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.listInstitutionGenderLevel(instituicao, genero, nivel);
    }

    //método para salvar os grupos no banco de dados
    public void save(modelo.Grupo grupo) throws SQLException, ParseException {
        new GrupoDAO().salvar(grupo);
    }

    //método para salvar o período no banco de dados
    public void periodSave(int atletaID, int grupoID) throws SQLException, ParseException {
        Periodo g = new Periodo();
        g.setAtletaId(atletaID);
        g.setGrupoId(grupoID);

        new PeriodoDAO().save(g);
    }

    //método para alterar o período no banco de dados
    public void updatePeriod(int grupoAtletaID, int atletaID, int grupoID) throws SQLException, ParseException {
        Periodo g = new Periodo();
        g.setPeriodoId(grupoAtletaID);
        g.setGrupoId(grupoID);
        g.setAtletaId(atletaID);

        new PeriodoDAO().update(g);
    }

    //método para alterar o grupo no banco de dados
    public void update(modelo.Grupo g) throws SQLException {
        new GrupoDAO().alterar(g);
    }

    //método que retorna uma lista contendo todos os grupos
    public List<modelo.Grupo> grupo() {
        GrupoDAO dao = new GrupoDAO();
        try {
            return dao.findGrupo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar grupo: " + e.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //método que apaga os dados do grupo do banco de dados
    public void delete(int codigo) throws SQLException {
        new GrupoDAO().excluir(codigo);
    }

    //método para excluir os dados da tabela GrupoAtleta
    public void periodDelete(int codigo) throws SQLException {
        new PeriodoDAO().excluir(codigo);
    }

    //método que procura os grupos por nome
    public List<modelo.Grupo> searchGroupByName(String name) throws SQLException {
        GrupoDAO dao = new GrupoDAO();
        return dao.findParcial(name);
    }

    //método que remove todos os períodos do banco de dados
    public void removeAll(int id) throws SQLException {
        PeriodoDAO dao = new PeriodoDAO();
        dao.removeAll(id);
    }
}
