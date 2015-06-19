/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AtletaDAO;
import dao.InstituicaoDAO;
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
import javax.swing.JOptionPane;
import modelo.Instituicao;
import visao.modelosComponentes.AtletaTableModel;

/**
 *
 * @author 5102011212
 */
public class Atleta {

    private visao.MenuPrincipal mp;
    private visao.Atleta cadAtleta;
    private boolean alterar;
    private modelo.Instituicao inst;

    public Atleta() {
    }

    public Atleta(visao.MenuPrincipal mp) {
        this.mp = mp;
        cadAtleta = new visao.Atleta();
        alterar = false;
        setCbInstituicao();
        AtletaTableModel model = new AtletaTableModel(athletes());
        tabela(model);
        cadAtleta.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });
        cadAtleta.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        cadAtleta.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        cadAtleta.btMostraTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraTudo();
            }
        });
        cadAtleta.btNovoAtleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoAtleta();
            }
        });
        cadAtleta.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        cadAtleta.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
        cadAtleta.cbInstituicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inst = (Instituicao) cadAtleta.cbInstituicao.getSelectedItem();
            }
        });

        cadAtleta.tbAtleta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editar();
                } else {
                    limparTela();
                    abilita(false);
                }
            }
        });
    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(cadAtleta);
        mp.jPanel.validate();
    }

    private void limparTela() {
        cadAtleta.taHistorico.setText(null);
        cadAtleta.tfEnvergadura.setText(null);
        cadAtleta.ftEstatura.setText(null);
        cadAtleta.ftMassaCorporal.setText(null);
        cadAtleta.dcNascimento.setDate(null);
        cadAtleta.ftTempoPratica.setText(null);
        cadAtleta.ftPraticaSemana.setText(null);
        cadAtleta.tfNome.setText(null);
        cadAtleta.tfEmail.setText(null);
        cadAtleta.cbGenero.setSelectedIndex(-1);
        cadAtleta.cbInstituicao.setSelectedIndex(-1);
        cadAtleta.cbLateralidade.setSelectedIndex(-1);
        cadAtleta.cbNivel.setSelectedIndex(-1);
    }

    private void abilita(boolean b) {
        cadAtleta.taHistorico.setEnabled(b);
        cadAtleta.tfEnvergadura.setEnabled(b);
        cadAtleta.ftEstatura.setEnabled(b);
        cadAtleta.ftMassaCorporal.setEnabled(b);
        cadAtleta.dcNascimento.setEnabled(b);
        cadAtleta.ftTempoPratica.setEnabled(b);
        cadAtleta.ftPraticaSemana.setEnabled(b);
        cadAtleta.tfNome.setEnabled(b);
        cadAtleta.tfEmail.setEnabled(b);
        cadAtleta.cbGenero.setEnabled(b);
        cadAtleta.cbLateralidade.setEnabled(b);
        cadAtleta.cbNivel.setEnabled(b);
        cadAtleta.btCancelar.setEnabled(b);
        cadAtleta.btSalvar.setEnabled(b);
        cadAtleta.cbInstituicao.setEnabled(b);

    }

    private void editar() {
        int linhaSelecionada = cadAtleta.tbAtleta.getSelectedRow();
        if (linhaSelecionada != -1) {
            alterar = true;
            String codigo = cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 0).toString();
            int cod = Integer.parseInt(codigo);
            cadAtleta.tfNome.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 1).toString());
            cadAtleta.dcNascimento.setDate(trocaDiaPorMes(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 7).toString()));
            String est = cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 2).toString();
            if (est.length() == 3) {
                est += "0";
            }
            cadAtleta.ftEstatura.setText(est);
            cadAtleta.ftMassaCorporal.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 3).toString());
            cadAtleta.tfEnvergadura.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 10).toString());
            cadAtleta.cbNivel.setSelectedItem(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 6).toString());
            cadAtleta.ftTempoPratica.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 4).toString());
            cadAtleta.ftPraticaSemana.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 5).toString());
            cadAtleta.cbGenero.setSelectedItem(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 8).toString());
            cadAtleta.cbLateralidade.setSelectedItem(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 9).toString());
            if (cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 13) != null) {
                cadAtleta.taHistorico.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 13).toString());
            }
            modelo.Instituicao instituicao = (modelo.Instituicao) cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 12);

            try {
                cadAtleta.cbInstituicao.setSelectedItem(institutionObject(instituicao.getId()));
            } catch (SQLException ex) {
                Logger.getLogger(visao.Atleta.class.getName()).log(Level.SEVERE, null, ex);
            }
            cadAtleta.tfEmail.setText(cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 11).toString());
            //            JBGravar.setText("Alterar");
            abilita(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }
    }

    private Date trocaDiaPorMes(String date) {
        Date newDate = new Date();
        String d = date.substring(3, 6);
        d += date.substring(0, 3);
        d += date.substring(6, 10);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            newDate = sdf.parse(d);
        } catch (ParseException ex) {
            Logger.getLogger(Atleta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }

    private void excluir() {
        int linha = cadAtleta.tbAtleta.getSelectedRow();
        if (linha != -1) {
            try {
                int[] row = cadAtleta.tbAtleta.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    int id = (int) cadAtleta.tbAtleta.getValueAt(row[i], 0);
                    delete(id);
                }
            } catch (SQLException ex) {
            }

            AtletaTableModel model = new AtletaTableModel(athletes());
            tabela(model);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void tabela(AtletaTableModel model) {
        cadAtleta.tbAtleta.setModel(model);

        cadAtleta.tbAtleta.getColumnModel().getColumn(0).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(0).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(2).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(2).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(3).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(3).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(4).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(4).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(5).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(5).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(10).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(10).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(9).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(9).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(8).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(8).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(12).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(12).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);

        cadAtleta.tbAtleta.getColumnModel().getColumn(13).setMaxWidth(0);
        cadAtleta.tbAtleta.getColumnModel().getColumn(13).setMinWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
        cadAtleta.tbAtleta.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
    }

    private void mostraTudo() {
        AtletaTableModel model = new AtletaTableModel(athletes());
        tabela(model);
        cadAtleta.btMostraTudo.setEnabled(false);
        cadAtleta.tfNomePesquisaAtleta.setText(null);
    }

    private void novoAtleta() {
        limparTela();
        abilita(true);
    }

    private void salvar() {
        modelo.Atleta atleta = new modelo.Atleta();
        if (validacaoDeCampos()) {
            if (alterar) {
                modelo.Instituicao instituicao = inst;
                int linhaSelecionada = cadAtleta.tbAtleta.getSelectedRow();
                String codigo = cadAtleta.tbAtleta.getValueAt(linhaSelecionada, 0).toString();
                int cod = Integer.parseInt(codigo);
                String Nome = (cadAtleta.tfNome.getText());
                String est = cadAtleta.ftEstatura.getText();
                if (est.length() < 3) {
                    est += 0;
                }
                double estatura;
                estatura = Double.parseDouble(est);
                double massa = (Double.parseDouble(cadAtleta.ftMassaCorporal.getText()));
                String pratica = (cadAtleta.ftTempoPratica.getText());
                String semana = (cadAtleta.ftPraticaSemana.getText());
                String nivel = (String) (cadAtleta.cbNivel.getSelectedItem());
                Date dataNascimento = (cadAtleta.dcNascimento.getDate());
                String genero = (String) (cadAtleta.cbGenero.getSelectedItem());
                String lateralidade = (String) (cadAtleta.cbLateralidade.getSelectedItem());
                String historico = cadAtleta.taHistorico.getText();
                int envergadura = Integer.parseInt(cadAtleta.tfEnvergadura.getText());
                String email = (cadAtleta.tfEmail.getText());
                try {
                    update(instituicao, cod, Nome, estatura, massa, pratica, semana, nivel, dataNascimento, genero, lateralidade, envergadura, email, historico);
                } catch (SQLException ex) {
                    Logger.getLogger(visao.Atleta.class.getName()).log(Level.SEVERE, null, ex);
                }

                AtletaTableModel model = new AtletaTableModel(athletes());
                tabela(model);

                alterar = false;
            } else {
                String Nome = (cadAtleta.tfNome.getText());
                modelo.Instituicao instituicao = inst;
                double estatura = (Double.parseDouble(cadAtleta.ftEstatura.getText()));
                double massa = (Double.parseDouble(cadAtleta.ftMassaCorporal.getText()));
                String pratica = (cadAtleta.ftTempoPratica.getText());
                String semana = (cadAtleta.ftPraticaSemana.getText());
                String nivel = (String) (cadAtleta.cbNivel.getSelectedItem());
                Date dataNascimento = (cadAtleta.dcNascimento.getDate());
                String genero = (String) (cadAtleta.cbGenero.getSelectedItem());
                String lateralidade = (String) (cadAtleta.cbLateralidade.getSelectedItem());
                String historico = cadAtleta.taHistorico.getText();
                int envergadura = Integer.parseInt(cadAtleta.tfEnvergadura.getText());
                String email = (cadAtleta.tfEmail.getText());
                try {
                    save(instituicao, Nome, estatura, massa, pratica, semana, nivel, dataNascimento, genero, lateralidade, envergadura, email, historico);
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(visao.Atleta.class.getName()).log(Level.SEVERE, null, ex);
                }

                AtletaTableModel model = new AtletaTableModel(athletes());
                tabela(model);
            }
            limparTela();
            abilita(false);
        }
    }

    public boolean validacaoDeCampos() {

        if (cadAtleta.tfEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.tfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.tfEnvergadura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.ftEstatura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.ftMassaCorporal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.dcNascimento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.ftTempoPratica.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (cadAtleta.ftPraticaSemana.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return false;
        }
        if (!isEmail()) {
            JOptionPane.showMessageDialog(null, "E-mail inválido!");
            return false;
        } else if (!isEstatura()) {
            return false;
        } else if (!isPratica()) {
            return false;
        } else return isSemana();
    }

    public boolean isSemana() {
        String semana = cadAtleta.ftPraticaSemana.getText();
        int b = (Integer.parseInt(semana.substring(3, 5)));
        if (b > 59) {
            cadAtleta.ftPraticaSemana.requestFocus();
            JOptionPane.showMessageDialog(null, "Minutos de Prática semanais inválido!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isPratica() {
        String pratica = cadAtleta.ftTempoPratica.getText();
        int a = (Integer.parseInt(pratica.substring(0, 2)));
        int b = (Integer.parseInt(pratica.substring(3, 5)));
        if (a > 70) {
            cadAtleta.ftTempoPratica.requestFocus();
            JOptionPane.showMessageDialog(null, "Ano de Prática inválido!");
            return false;
        } else if (b > 11) {
            cadAtleta.ftTempoPratica.requestFocus();
            JOptionPane.showMessageDialog(null, "Meses de Prática inválido!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isEstatura() {
        String estatura = cadAtleta.ftEstatura.getText();

        int a = (Integer.parseInt(estatura.substring(0, 1)));
        int b = (Integer.parseInt(estatura.substring(2, 4)));

        if (a > 2) {
            cadAtleta.ftEstatura.requestFocus();
            JOptionPane.showMessageDialog(null, "Estatura inválida!");
            return false;
        } else if (a == 2 && b > 40) {
            cadAtleta.ftEstatura.requestFocus();
            JOptionPane.showMessageDialog(null, "Estatura inválida!");
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmail() {
        if ((cadAtleta.tfEmail.getText().contains("@")) && (cadAtleta.tfEmail.getText().contains("."))) {
            //a parte do E-mail antes do @
            String usuario = new String(cadAtleta.tfEmail.getText().substring(0, cadAtleta.tfEmail.getText().lastIndexOf("@")));
            //a parte do E-mail depois do @
            String dominio = new String(cadAtleta.tfEmail.getText().substring(cadAtleta.tfEmail.getText().lastIndexOf("@") + 1, cadAtleta.tfEmail.getText().length()));
            return (usuario.length() >= 1) && (!usuario.contains("@"))
                    && (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".")
                    >= 1) && (dominio.lastIndexOf(".") < dominio.length() - 1);

        } else {

            return false;
        }
    }

    private void setCbInstituicao() {
        try {
            controle.Grupo gc = new controle.Grupo();
            ArrayList<Instituicao> array = (ArrayList<Instituicao>) (gc.instituicao());
            for (int i = 0; i < array.size(); i++) {
                inst = (Instituicao) array.get(i);
                cadAtleta.cbInstituicao.addItem(inst);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void pesquisar() {
        String nome = cadAtleta.tfNomePesquisaAtleta.getText();
        List<modelo.Atleta> atleta = new ArrayList<>();
        try {
            atleta = (searchAthletesByName(nome));
        } catch (SQLException ex) {
            Logger.getLogger(visao.Atleta.class.getName()).log(Level.SEVERE, null, ex);
        }

        AtletaTableModel model = new AtletaTableModel(atleta);
        if (atleta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum atleta com esse nome foi encontrado!");
            tabela(model);
            cadAtleta.btMostraTudo.setEnabled(true);
        } else {
            tabela(model);
            cadAtleta.btMostraTudo.setEnabled(true);
        }
    }
    //método para salvar os atletas no banco de dados

    public void save(modelo.Instituicao instituicao, String nome, double estatura, double massaCorporal,
            String pratica, String praticaSemana, String nivel, Date dataNascimento,
            String genero, String lateralidade, int envergadura, String email, String historico) throws SQLException, ParseException {
        modelo.Atleta a = new modelo.Atleta();
        a.setInstituicao(instituicao);
        a.setNome(nome);
        a.setEstatura(estatura);
        a.setMassaCorporal(massaCorporal);
        a.setPratica(pratica);
        a.setPraticaSemana(praticaSemana);
        a.setNivel(nivel);
        a.setDataNascimento(dataNascimento);
        a.setGenero(genero);
        a.setLateralidade(lateralidade);
        a.setEnvergadura(envergadura);
        a.setEmail(email);
        a.setHistorico(historico);

        new AtletaDAO().save(a);
    }

    //método para alterar os atletas no banco de dados
    public void update(modelo.Instituicao intituicao, int codigoAtleta, String nome, double estatura,
            double massaCorporal, String pratica, String praticaSemana, String nivel,
            Date data, String genero, String lateralidade, int envergadura, String email, String historico) throws SQLException {
        modelo.Atleta a = new modelo.Atleta();
        a.setInstituicao(intituicao);
        a.setId(codigoAtleta);
        a.setNome(nome);
        a.setEstatura(estatura);
        a.setMassaCorporal(massaCorporal);
        a.setPratica(pratica);
        a.setPraticaSemana(praticaSemana);
        a.setNivel(nivel);
        a.setDataNascimento(data);
        a.setGenero(genero);
        a.setLateralidade(lateralidade);
        a.setEnvergadura(envergadura);
        a.setEmail(email);
        a.setHistorico(historico);

        new AtletaDAO().update(a);
    }

    //metodo que retorna uma lista de todos atletas cadastrados 
    public List<modelo.Atleta> athletes() {
        AtletaDAO dao = new AtletaDAO();
        try {
            return dao.atletas();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar atleta\n" + e.getLocalizedMessage());
        }
        return null;
    }

    //método para apagar todos os atletas do banco de dados
    public void delete(int codigo) throws SQLException {
        new AtletaDAO().delete(codigo);
    }

    //método que retorna uma lista de atletas que possuem um determinado nome 
    public List<modelo.Atleta> searchAthletesByName(String name) throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.searchAthletesByName(name);
    }
    //método que retorna uma lista de atletas que possuem um determinado id

    public List<modelo.Atleta> searchAthletesById(int id) throws SQLException {
        AtletaDAO dao = new AtletaDAO();
        return dao.findByID(id);
    }

    //método que retorna um objeto do tipo instituição
    public Instituicao institutionObject(int id) throws SQLException {
        InstituicaoDAO dao = new InstituicaoDAO();
        return dao.object(id);
    }
}
