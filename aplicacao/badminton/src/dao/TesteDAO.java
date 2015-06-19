// TesteDAO.java
//Classe de persistencia dos testes
package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.Agenda;
import modelo.Atleta;
import modelo.Protocolo;
import modelo.Teste;

/**
 * @author Diego Heusser
 */
public class TesteDAO extends ExecucaoDAO {

    private final String tabela, id, obj;
    private modelo.Teste teste;

    public TesteDAO(String tabela, String obj, modelo.Teste teste) {
        this.teste = teste;
        this.tabela = tabela;
        this.id = tabela + "_id";
        this.obj = obj;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public Teste teste(Agenda agenda, Atleta umAtleta, Protocolo protocolo) throws Exception {
        String seleciona = "SELECT e.*, t.* FROM "
                + "Agenda a INNER JOIN AgendaExecucao ae "
                + "ON a.agendaId = ae.agendaId "
                + "INNER JOIN Execucao e "
                + "ON ae.execucaoId = e.execucaoId "
                + "INNER JOIN " + tabela + " t "
                + "ON e.execucaoId = t." + id + " "
                + "WHERE a.agendaId = " + agenda.getId() + " "
                + "AND e.atletaId = " + umAtleta.getId() + " "
                + "AND e.protocoloId = " + protocolo.getId() + " ";
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(seleciona);

        //criando uma nova instância da classe
        modelo.Teste t = (modelo.Teste) Class.forName(teste.getClass().getName()).newInstance();

        while (rs.next()) {
            t.setAtleta(umAtleta);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = rs.getDate("data");
            t.setData(sdf.format(data));
            t.setHora(rs.getString("hora"));
            t.setId(rs.getInt(id));
            t.setObjeto(rs.getObject(obj));
            t.setProtocolo(protocolo);
            t.setStatus(status(rs.getInt("status")));
            t.setSuperficie(rs.getString("tipoSuperficie"));
            t.setTemperatura(rs.getInt("temperatura"));
        }
        rs.close();
        stmt.close();
        return t;
    }

    public void remove(Agenda agenda, Atleta a, Protocolo protocolo) throws Exception {
        modelo.Execucao umaExecucao = execucao(agenda, a, protocolo);
        String remover = "DELETE FROM " + tabela + " "
                + " WHERE " + id + " = ?";
        delete(remover, umaExecucao.getId());
        removerExecucao(a, protocolo, agenda);
    }

    public void altera(Teste t) throws Exception {
        alterarExecucao(t);
        String alterar = "UPDATE " + tabela + " "
                + "SET " + obj + " = ? "
                + "WHERE " + id + " = ?";
        update(alterar, t.getId(), t.getObjeto());
    }

    public void insere(Teste t, Agenda agenda) throws Exception {
        salvarExeucao(t, agenda);
        modelo.Execucao umaExecucao = execucao(agenda, t.getAtleta(), t.getProtocolo());
        String inserir = "INSERT INTO " + tabela + " (" + id + ", " + obj + ") "
                + "VALUES (?,?)";
        save(inserir, umaExecucao.getId(), t.getObjeto());
    }

}
