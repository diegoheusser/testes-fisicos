//Instituicao.java
package controle;

import dao.InstituicaoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import visao.modelosComponentes.InstituicaoTableModel;

/**
 * @author 5102011212
 */
public class Instituicao {

    private visao.MenuPrincipal mp;
    private visao.Instituicao cadInstituicao;
    private boolean alterar;

    public Instituicao(visao.MenuPrincipal mp) {
        this.mp = mp;
        cadInstituicao = new visao.Instituicao();
        alterar = false;
        InstituicaoTableModel model = new InstituicaoTableModel(instituicao());
        tabela(model);
        //Ouvintes
        cadInstituicao.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        cadInstituicao.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        cadInstituicao.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        cadInstituicao.btMostraTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraTudo();
            }
        });
        cadInstituicao.btNovaInstituicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaInstituicao();
            }
        });
        cadInstituicao.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        cadInstituicao.btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });

        cadInstituicao.tbInstituicao.addMouseListener(new MouseAdapter() {
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
        mp.jPanel.add(cadInstituicao);
        mp.jPanel.validate();
    }

    private void abilita(boolean b) {
        cadInstituicao.tfCidade.setEnabled(b);
        cadInstituicao.tfEmail.setEnabled(b);
        cadInstituicao.tfNome.setEnabled(b);
        cadInstituicao.tfRua.setEnabled(b);
        cadInstituicao.ftTelefone.setEnabled(b);
        cadInstituicao.cbUf.setEnabled(b);
        cadInstituicao.ftCep.setEnabled(b);
        cadInstituicao.ftCnpj.setEnabled(b);
        cadInstituicao.btCancelar.setEnabled(b);
        cadInstituicao.btSalvar.setEnabled(b);
    }

    private void limparTela() {
        cadInstituicao.ftCep.setText("");
        cadInstituicao.ftCnpj.setText("");
        cadInstituicao.tfCidade.setText("");
        cadInstituicao.tfEmail.setText("");
        cadInstituicao.tfNome.setText("");
        cadInstituicao.tfRua.setText("");
        cadInstituicao.ftTelefone.setText("");
        cadInstituicao.cbUf.setSelectedIndex(0);
    }

    private void tabela(InstituicaoTableModel model) {
        cadInstituicao.tbInstituicao.setModel(model);

        cadInstituicao.tbInstituicao.getColumnModel().getColumn(0).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getColumnModel().getColumn(0).setMinWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        cadInstituicao.tbInstituicao.getColumnModel().getColumn(1).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getColumnModel().getColumn(1).setMinWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        cadInstituicao.tbInstituicao.getColumnModel().getColumn(4).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getColumnModel().getColumn(4).setMinWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

        cadInstituicao.tbInstituicao.getColumnModel().getColumn(7).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getColumnModel().getColumn(7).setMinWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);

        cadInstituicao.tbInstituicao.getColumnModel().getColumn(8).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getColumnModel().getColumn(8).setMinWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        cadInstituicao.tbInstituicao.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
    }

    private boolean validacaoDeCampos() {
        boolean a;
        String cnpj = (cadInstituicao.ftCnpj.getText());
        String nome = (cadInstituicao.tfNome.getText());
        String cidade = (cadInstituicao.tfCidade.getText());
        String rua = (cadInstituicao.tfRua.getText());
        String telefone = (cadInstituicao.ftTelefone.getText());
        String email = (cadInstituicao.tfEmail.getText());
        String uf = (String) (cadInstituicao.cbUf.getSelectedItem());
        String cep = (cadInstituicao.ftCep.getText());
        if (cnpj.isEmpty() || nome.isEmpty() || cidade.isEmpty() || rua.isEmpty() || telefone.isEmpty() || email.isEmpty() || uf.isEmpty() || cep.isEmpty()) {
            JOptionPane.showMessageDialog(mp, "Preencha todos os campo!");
            a = false;
        } else if (!isEmail()) {
            JOptionPane.showMessageDialog(mp, "E-mail inválido!");
            cadInstituicao.tfEmail.requestFocus();
            a = false;
        } else if (!isCNPJ()) {
            cadInstituicao.ftCnpj.requestFocus();
            JOptionPane.showMessageDialog(mp, "CNPJ inválido!");
            a = false;
        } else if (!isCidade()) {
            cadInstituicao.tfCidade.requestFocus();
            JOptionPane.showMessageDialog(mp, "Cidade Inválida!");
            a = false;
        } else {
            a = true;
        }
        return a;
    }

    private boolean isEmail() {
        if ((cadInstituicao.tfEmail.getText().contains("@")) && (cadInstituicao.tfEmail.getText().contains("."))) {
            //a parte do E-mail antes do @
            String usuario = new String(cadInstituicao.tfEmail.getText().substring(0, cadInstituicao.tfEmail.getText().lastIndexOf("@")));
            //a parte do E-mail depois do @
            String dominio = new String(
                    cadInstituicao.tfEmail.getText().substring(cadInstituicao.tfEmail.getText().lastIndexOf("@") + 1, cadInstituicao.tfEmail.getText().length()));
            return (usuario.length() >= 1) && (!usuario.contains("@"))
                    && (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".")
                    >= 1) && (dominio.lastIndexOf(".") < dominio.length() - 1);

        } else {

            return false;
        }
    }

    private boolean isCNPJ() {
        String CNPJ = new String((cadInstituicao.ftCnpj.getText().substring(0, 2))
                + cadInstituicao.ftCnpj.getText().substring(3, 6)
                + cadInstituicao.ftCnpj.getText().substring(7, 10)
                + cadInstituicao.ftCnpj.getText().substring(11, 15)
                + cadInstituicao.ftCnpj.getText().substring(16, cadInstituicao.ftCnpj.getText().length()));
//        //System.out.println(cnpj);
//        numero de cnpj valido
//        14572457000185
        //50450317404
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                return (true);
            } else {

                return (false);

            }
        } catch (InputMismatchException erro) {

            return (false);
//          
        }
    }

    private boolean isCidade() {
        return !cadInstituicao.tfCidade.getText().contains("1")
                && !cadInstituicao.tfCidade.getText().contains("2")
                && !cadInstituicao.tfCidade.getText().contains("3")
                && !cadInstituicao.tfCidade.getText().contains("4")
                && !cadInstituicao.tfCidade.getText().contains("5")
                && !cadInstituicao.tfCidade.getText().contains("6")
                && !cadInstituicao.tfCidade.getText().contains("7")
                && !cadInstituicao.tfCidade.getText().contains("8")
                && !cadInstituicao.tfCidade.getText().contains("9")
                && !cadInstituicao.tfCidade.getText().contains("0");
    }

    private void editar() {
        int linhaSelecionada = cadInstituicao.tbInstituicao.getSelectedRow();
        if (linhaSelecionada != -1) {
            alterar = true;
            cadInstituicao.tfNome.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 2));
            cadInstituicao.tfCidade.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 3));
            cadInstituicao.cbUf.setSelectedItem((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 7));
            cadInstituicao.tfRua.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 4));
            cadInstituicao.ftTelefone.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 5));
            cadInstituicao.tfEmail.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 6));
            cadInstituicao.ftCep.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 8));
            cadInstituicao.ftCnpj.setText((String) cadInstituicao.tbInstituicao.getValueAt(linhaSelecionada, 1));
            abilita(true);
        } else {
            JOptionPane.showMessageDialog(mp, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void excluir() {
        int linha = cadInstituicao.tbInstituicao.getSelectedRow();
        if (linha != -1) {
            int[] row = cadInstituicao.tbInstituicao.getSelectedRows();
            try {
                for (int i = 0; i < row.length; i++) {
                    int id = (int) cadInstituicao.tbInstituicao.getValueAt(row[i], 0);
                    delete(id);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(mp, "Não foi possível remover!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            InstituicaoTableModel model = new InstituicaoTableModel(instituicao());
            tabela(model);
            limparTela();
            abilita(false);
        } else {
            JOptionPane.showMessageDialog(mp, "Selecione uma linha tabela!", "Tabela", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostraTudo() {
        InstituicaoTableModel model = new InstituicaoTableModel(instituicao());
        cadInstituicao.tfNomePesquisa.setText("");
        tabela(model);
        cadInstituicao.btMostraTudo.setEnabled(false);
    }

    private void novaInstituicao() {
        limparTela();
        abilita(true);
        alterar = false;
    }

    private void pesquisar() {
        String nomeI = cadInstituicao.tfNomePesquisa.getText();
        List<modelo.Instituicao> instituicao = new ArrayList<modelo.Instituicao>();
        try {

            instituicao = (searchInstitutionByName(nomeI));
        } catch (SQLException ex) {
            Logger.getLogger(visao.Instituicao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (instituicao.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma Instituição com esse nome foi encontrada!");
            InstituicaoTableModel model = new InstituicaoTableModel(instituicao);
            tabela(model);
            cadInstituicao.btMostraTudo.setEnabled(true);
        } else {
            InstituicaoTableModel model = new InstituicaoTableModel(instituicao);
            tabela(model);
            cadInstituicao.btMostraTudo.setEnabled(true);
            //tfNomePesquisa.setText("");
        }
    }

    private void salvar() {
        visao.Instituicao i = new visao.Instituicao();
        if (validacaoDeCampos()) {
            if (alterar) {
                int linhaSeleciona = cadInstituicao.tbInstituicao.getSelectedRow();
                int id = (int) cadInstituicao.tbInstituicao.getValueAt(linhaSeleciona, 0);
                String cnpj = (cadInstituicao.ftCnpj.getText());
                String nome = (cadInstituicao.tfNome.getText());
                String cidade = (cadInstituicao.tfCidade.getText());
                String rua = (cadInstituicao.tfRua.getText());
                String telefone = (cadInstituicao.ftTelefone.getText());
                String email = (cadInstituicao.tfEmail.getText());
                String uf = (String) (cadInstituicao.cbUf.getSelectedItem());
                String cep = (cadInstituicao.ftCep.getText());
                try {
                    update(id, cnpj, nome, cidade, rua, telefone, email, uf, cep);
                } catch (SQLException ex) {
                    Logger.getLogger(visao.Instituicao.class.getName()).log(Level.SEVERE, null, ex);
                }
                InstituicaoTableModel model = new InstituicaoTableModel(instituicao());
                tabela(model);
            } else {

                String cnpj = (cadInstituicao.ftCnpj.getText());
                String nome = (cadInstituicao.tfNome.getText());
                String cidade = (cadInstituicao.tfCidade.getText());
                String rua = (cadInstituicao.tfRua.getText());
                String telefone = (cadInstituicao.ftTelefone.getText());
                String email = (cadInstituicao.tfEmail.getText());
                String uf = (String) (cadInstituicao.cbUf.getSelectedItem());
                String cep = (cadInstituicao.ftCep.getText());
                try {
                    save(cnpj, nome, cidade, rua, telefone, email, uf, cep);
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(visao.Instituicao.class.getName()).log(Level.SEVERE, null, ex);
                }

                InstituicaoTableModel model = new InstituicaoTableModel(instituicao());
                tabela(model);
            }
            limparTela();
            abilita(false);
        }

    }

    private void cancelar() {
        limparTela();
        abilita(false);
        alterar = false;
    }

    //método para salvar as instiuições no banco de dados
    public void save(String cnpj, String nome, String cidade, String rua,
            String telefone, String email, String uf, String cep) throws SQLException, ParseException {
        modelo.Instituicao i = new modelo.Instituicao();
        i.setCnpj(cnpj);
        i.setNome(nome);
        i.setCidade(cidade);
        i.setRua(rua);
        i.setTelefone(telefone);
        i.setEmail(email);
        i.setUf(uf);
        i.setCep(cep);

        new InstituicaoDAO().salvar(i);
    }

    //método para alterar as instituições no banco de dados
    public void update(int id, String cnpj, String nome, String cidade,
            String rua, String telefone, String email, String uf, String cep) throws SQLException {
        modelo.Instituicao i = new modelo.Instituicao();
        i.setId(id);
        i.setCnpj(cnpj);
        i.setNome(nome);
        i.setCidade(cidade);
        i.setRua(rua);
        i.setTelefone(telefone);
        i.setEmail(email);
        i.setUf(uf);
        i.setCep(cep);

        new InstituicaoDAO().alterar(i);
    }

    //método que retorna uma lista com todas as instituições cadastradas
    public List<modelo.Instituicao> instituicao() {
        InstituicaoDAO dao = new InstituicaoDAO();
        try {
            return dao.findInstituicao();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar instituicao\n" + e.getLocalizedMessage());
        }
        return null;
    }

    //método para apagar as instituições do banco de dados
    public void delete(int id) throws SQLException {
        new InstituicaoDAO().excluir(id);
    }

    //método que retorna uma lista de instituições que possuem um determinado nome
    public List<modelo.Instituicao> searchInstitutionByName(String nome) throws SQLException {
        InstituicaoDAO dao = new InstituicaoDAO();
        return dao.findParcial(nome);
    }
}
