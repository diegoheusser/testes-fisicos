// Relatorio.java
//Classe de controle dos relatórios
package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import modelo.Atleta;
import modelo.Instituicao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import visao.RelSelecionaAtletas;
import visao.RelSelecionaProtocolos;

/**
 * @author Diego Heusser
 */
public class Relatorio {

    private visao.Relatorio relatorio;
    private visao.MenuPrincipal mp;
    private visao.RelSelecionaAtletas selecionaAtletas;
    private DefaultListModel listaAtletas;
    private visao.RelSelecionaProtocolos selecionaProtocolos;
    private DefaultListModel listaProtocolos;

    public Relatorio(visao.MenuPrincipal mp) {
        this.mp = mp;
        relatorio = new visao.Relatorio();
        relatorio.rbAtleta.setSelected(true);
        relatorio.rbRAT.setSelected(true);
        listaAtletas = new DefaultListModel();
        listaProtocolos = new DefaultListModel();
        preencheInstituicao();
        ouvintes();
    }

    public void executar() {
        mp.jPanel.removeAll();
        mp.jPanel.add(relatorio);
        mp.jPanel.validate();
    }

    private void ouvintes() {
        relatorio.btAddProtocolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaProtocolos();
            }
        });
        relatorio.btAddAtleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaAtletas();
            }
        });
        relatorio.btDelAtleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(relatorio.liAtleta, listaAtletas, relatorio.liAtleta.getSelectedValuesList());
            }
        });
        relatorio.btDelProtocolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(relatorio.liProtocolo, listaProtocolos, relatorio.liProtocolo.getSelectedValuesList());
            }
        });
        relatorio.btGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    valida();
                    gerar();
                } catch (CampoExcecao | SQLException | JRException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mp,ex.getMessage() , "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void valida() throws CampoExcecao {
        Date inicio = relatorio.dcInicio.getDate();
        Date fim = relatorio.dcFim.getDate();
        if (inicio == null) {
            throw new CampoExcecao("Data inicial é nula!");
        } else if (fim == null) {
            throw new CampoExcecao("Data final é nula!");
        } else if (fim.before(inicio)) {
            throw new CampoExcecao("A data inicial é depois da final!");
        } else if (listaAtletas.isEmpty()) {
            throw new CampoExcecao("A lista de atletas está vazia!");
        } else if (listaProtocolos.isEmpty()){
            throw new CampoExcecao("A lista de protocolo está vazia!");
        }
    }

    private void gerar() throws JRException, SQLException {
        String atletas = "";
        String protocolos = "";
        String agrupar = "";
        String tipo = "";
        String titulo = "";
        for (int i = 0; i < listaAtletas.getSize(); i++) {
            atletas += ((modelo.Atleta) listaAtletas.getElementAt(i)).getId() + ",";
        }
        for (int i = 0; i < listaProtocolos.getSize(); i++) {
            protocolos += ((((modelo.Protocolo) listaProtocolos.getElementAt(i)).getId())) + ",";
        }
        String arquivoRelatorio = "relatorios\\";
        if (relatorio.rbAtleta.isSelected()) {
            agrupar = "atleta";
        } else  {
            agrupar = "protocolo";
        }
        if (relatorio.rbRAT.isSelected()) {
            titulo = "Relação Analítica de Testes";
            tipo = "A";
        } else if (relatorio.rbRET.isSelected()) {
            titulo = "Resumo de Evolução de Testes";
            tipo = "B";
        } else {
            titulo = "Histórico de Testes";
            tipo = "C";
        }
        File file = new  File("");
        arquivoRelatorio += tipo + "_" + agrupar + ".jasper";
        protocolos = protocolos.substring(0, protocolos.length() - 1);
        atletas = atletas.substring(0, atletas.length() - 1);
        Map parametros = new HashMap();
        parametros.put("PROTOCOLOS", protocolos);
        parametros.put("ATLETAS", atletas);
        parametros.put("DATA_INICIAL", relatorio.dcInicio.getDate());
        parametros.put("DATA_FINAL", relatorio.dcFim.getDate());
        parametros.put("TITULO", titulo);
        parametros.put("SUBREPORT_DIR",file.getAbsolutePath());
        parametros.put("NOME_INSTITUICAO", ((modelo.Instituicao) relatorio.cbInstituicao.getSelectedItem()).getNome());

        JasperPrint print = JasperFillManager.fillReport(arquivoRelatorio, parametros, dao.Conexao.getConnection());

        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setVisible(true);
        viewer.toFront();
    }

    private void preencheInstituicao() {
        try {
            List<modelo.Instituicao> lista = new dao.InstituicaoDAO().findByInstituicao();
            for (modelo.Instituicao i : lista) {
                relatorio.cbInstituicao.addItem(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(relatorio, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preencheLista(JList ji, DefaultListModel li, List<Object> objetos) {
        for (Object objeto : objetos) {
            if (!li.contains(objeto)) {
                li.addElement(objeto);
            }
        }
        ji.setModel(li);
    }

    private void remove(JList ji, DefaultListModel li, List<Object> objetos) {
        if (objetos != null) {
            for (Object objeto : objetos) {
                li.removeElement(objeto);
            }
        }
        ji.setModel(li);
    }

    //--------------------------------------------------------------------------
    private void selecionaAtletas() {
        selecionaAtletas = new RelSelecionaAtletas(mp, true);
        ouvintesSelecionaAtletas();
        preencheListaSelecionaAtleta(atletas());
        selecionaAtletas.setVisible(true);
    }

    private List<modelo.Atleta> atletas() {
        try {
            return new dao.AtletaDAO().atletas(
                    (Instituicao) relatorio.cbInstituicao.getSelectedItem());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void ouvintesSelecionaAtletas() {
        selecionaAtletas.btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaAtleta();
            }
        });
        selecionaAtletas.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCamposSelecionaAtletas();
                preencheListaSelecionaAtleta(atletas());
            }
        });
        selecionaAtletas.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencheListaSelecionaAtleta(pesquisqarAtletas(
                        selecionaAtletas.tfNome.getText(),
                        (String) selecionaAtletas.cbGenero.getSelectedItem(),
                        (String) selecionaAtletas.cbNivel.getSelectedItem(),
                        (String) selecionaAtletas.cbLateralidade.getSelectedItem()));
            }
        });
        selecionaAtletas.ckTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTodos(selecionaAtletas.ckTodos, selecionaAtletas.lista);
            }
        });
    }

    private void preencheListaSelecionaAtleta(List<Atleta> atletas) {
        DefaultListModel lista = new DefaultListModel();
        if (atletas != null) {
            for (modelo.Atleta atleta : atletas) {
                lista.addElement(atleta);
            }
        }
        selecionaAtletas.lista.setModel(lista);
    }

    private void adicionaAtleta() {
        List<Object> lista = selecionaAtletas.lista.getSelectedValuesList();
        preencheLista(relatorio.liAtleta, listaAtletas, lista);
        selecionaAtletas.setVisible(false);
    }

    private List<modelo.Atleta> pesquisqarAtletas(String nome, String genero,
            String nivel, String lateralidade) {
        try {
            return new dao.AtletaDAO().atletas(nome, genero, nivel, lateralidade,
                    (modelo.Instituicao) relatorio.cbInstituicao.getSelectedItem());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void limparCamposSelecionaAtletas() {
        selecionaAtletas.tfNome.setText(null);
        selecionaAtletas.cbGenero.setSelectedIndex(0);
        selecionaAtletas.cbLateralidade.setSelectedIndex(0);
        selecionaAtletas.cbNivel.setSelectedIndex(0);
    }

    //--------------------------------------------------------------------------
    private void selecionaProtocolos() {
        selecionaProtocolos = new RelSelecionaProtocolos(mp, true);
        ouvintesSelecionaProtocolos();
        preencheListaSelecionaProtocolo(protocolos());
        selecionaProtocolos.setVisible(true);
    }

    private void ouvintesSelecionaProtocolos() {
        selecionaProtocolos.btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaProtocolo();
            }
        });
        selecionaProtocolos.btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCamposSelecionaProtocolos();
                preencheListaSelecionaProtocolo(protocolos());
            }
        });
        selecionaProtocolos.btPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencheListaSelecionaProtocolo(pesquisqarProtocolos(
                        selecionaProtocolos.tfNome.getText(),
                        (String) selecionaProtocolos.cbTipo.getSelectedItem()));
            }
        });
        selecionaProtocolos.ckTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTodos(selecionaProtocolos.ckTodos, selecionaProtocolos.lista);
            }
        });
    }

    private void checkTodos(JCheckBox ck, JList ji) {
        if (ck.isSelected()) {
            ji.setSelectionInterval(0, ji.getModel().getSize() - 1);
        } else {
            ji.clearSelection();
        }
    }

    private List<modelo.Protocolo> protocolos() {
        try {
            return new dao.ProtocoloDAO().protocol();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void preencheListaSelecionaProtocolo(List<modelo.Protocolo> protocolos) {
        DefaultListModel lista = new DefaultListModel();
        if (protocolos != null) {
            for (modelo.Protocolo protocolo : protocolos) {
                lista.addElement(protocolo);
            }
        }
        selecionaProtocolos.lista.setModel(lista);
    }

    private void adicionaProtocolo() {
        List<Object> lista = selecionaProtocolos.lista.getSelectedValuesList();
        preencheLista(relatorio.liProtocolo, listaProtocolos, lista);
        selecionaProtocolos.setVisible(false);
    }

    private void limparCamposSelecionaProtocolos() {
        selecionaProtocolos.tfNome.setText(null);
        selecionaProtocolos.cbTipo.setSelectedIndex(0);
    }

    private List<modelo.Protocolo> pesquisqarProtocolos(String nome, String tipo) {
        try {
            return new dao.ProtocoloDAO().protocolo(nome, tipo);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
