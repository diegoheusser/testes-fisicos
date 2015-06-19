//MenuPrincipal.java
package controle;

import dao.TelaInicialDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import visao.Protocolo;

/**
 * @author 5102011212
 */
public class MenuPrincipal {

    public visao.MenuPrincipal mp;
    private boolean isExecucao;

    public MenuPrincipal() {
        mp = new visao.MenuPrincipal();
        isExecucao = false;
        mp.setIconImage(new ImageIcon(visao.MenuPrincipal.class.getResource("imagens/a.png")).getImage());
        mp.setExtendedState(JFrame.MAXIMIZED_BOTH);
        atualiza();
        instituicao();
        mp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });
        mp.btAgenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agenda();
            }
        });
        mp.btAtleta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atleta();
            }
        });
        mp.btExecuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executa();
            }
        });
        mp.btGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grupo();
            }
        });
        mp.btInstituicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instituicao();
            }
        });
        mp.btPersonalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personalizar();
            }
        });
        mp.btProtocolos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                protocolos();
            }
        });
        mp.btRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                relatorios();
            }
        });
        mp.btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
    }

    public void executar() {
        mp.setVisible(true);
    }

    private void sair() {
        int reposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (reposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void relatorios() {
        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        Relatorio rel = new Relatorio(mp);
        rel.executar();
        mp.jPanel.repaint();
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(false);
        mp.btPersonalizar.setEnabled(true);
    }

    private void protocolos() {
        Protocolo pro = new Protocolo();
        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        controle.Protocolo crt = new controle.Protocolo(mp);
        crt.executar();
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(false);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    private void personalizar() {
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);

        //Dialog para trocar o look and feel(LAF) da aplicação
        ImageIcon icon = createImageIcon("imagens/personalizar.png");
        Object[] temas = {"Java", "Nimbus", "Windows"};
        String s = (String) JOptionPane.showInputDialog(mp, "Escolha o Tema Abaixo:",
                "Personalizar", JOptionPane.PLAIN_MESSAGE, icon, temas, "Java");
        UIManager.LookAndFeelInfo[] looks =
                UIManager.getInstalledLookAndFeels();
        if ((s != null) && s.length() > 0) {
            try {
                if (s.equals("Java")) {
                    try {
                        int i = 0;
                        UIManager.setLookAndFeel(looks[i].getClassName());
                        SwingUtilities.updateComponentTreeUI(mp);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    update(0);
                } else if (s.equals("Nimbus")) {
                    try {
                        int i = 1;
                        UIManager.setLookAndFeel(looks[i].getClassName());
                        SwingUtilities.updateComponentTreeUI(mp);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    update(1);
                } else if (s.equals("Windows")) {
                    try {
                        int i = 3;
                        UIManager.setLookAndFeel(looks[i].getClassName());
                        SwingUtilities.updateComponentTreeUI(mp);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    update(3);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void agenda() {
        visao.Agenda pa = new visao.Agenda();
        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        controle.Agenda crt = new Agenda(mp);
        crt.executar();
        mp.btAgenda.setEnabled(false);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    private void atleta() {
        visao.Atleta pa = new visao.Atleta();
        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        controle.Atleta crt = new Atleta(mp);
        crt.executar();
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(false);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    private void executa() {
        isExecucao = true;
        controle.Execucao crt = new Execucao(mp);
        crt.executar();
        //adiciona linhas em branco para preencher a tabela
        crt.spDate();
        //
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(false);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    private void instituicao() {
        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        controle.Instituicao crt = new controle.Instituicao(mp);
        crt.executar();
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(true);
        mp.btInstituicao.setEnabled(false);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    private void grupo() {

        if (isExecucao) {
            int option = JOptionPane.showConfirmDialog(mp, "Tem certeza que deseja sair da execução?"
                    + "\nVocê pode peder todos os dados!", "Atenção!", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.NO_OPTION) {
                return;
            }
            isExecucao = false;
        }
        controle.Grupo crt = new Grupo(mp);
        crt.executar();
        mp.btAgenda.setEnabled(true);
        mp.btAtleta.setEnabled(true);
        mp.btExecuta.setEnabled(true);
        mp.btGrupo.setEnabled(false);
        mp.btInstituicao.setEnabled(true);
        mp.btProtocolos.setEnabled(true);
        mp.btRelatorios.setEnabled(true);
        mp.btPersonalizar.setEnabled(true);
    }

    public void update(int laf) throws SQLException {
        TelaInicialDAO dao = new TelaInicialDAO();
        dao.update(laf);
    }

    public int laf() throws SQLException {
        TelaInicialDAO dao = new TelaInicialDAO();
        return dao.laf();
    }

    public void atualiza() {
        UIManager.LookAndFeelInfo[] looks =
                UIManager.getInstalledLookAndFeels();
        TelaInicialDAO dao = new TelaInicialDAO();

        try {
            int i = dao.laf();
            UIManager.setLookAndFeel(looks[i].getClassName());
            SwingUtilities.updateComponentTreeUI(mp);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = visao.MenuPrincipal.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            JOptionPane.showMessageDialog(null, "Erro imagem não existem: view/imagens/personalizar.png", "Erro", JOptionPane.ERROR);
            return null;
        }
    }
}
