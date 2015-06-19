//ExecucaoDAO.java
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Status;

/**
 * @author Diego Heusser
 */
public class ExecucaoDAO extends DAO {

    public void salvarExeucao(modelo.Execucao novaExecucao, modelo.Agenda umaScheduling) throws SQLException {
        //salva a parte da tabela execucao
        String insert = "INSERT INTO execucao (execucaoID, atletaID, protocoloID, hora, data, temperatura, tipoSuperficie, status) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        save(insert, null, novaExecucao.getAtleta().getId(), novaExecucao.getProtocolo().getId(), novaExecucao.getHora(),
                novaExecucao.getData(), novaExecucao.getTemperatura(), novaExecucao.getSuperficie(), novaExecucao.getStatus().getValor());
        //salva a parte da tabela AgendaExecucao
        String insertAgendaExecucao = "INSERT INTO AgendaExecucao (agendaID, execucaoID) VALUES(?,?)";
        save(insertAgendaExecucao, umaScheduling.getId(), maxId());
    }

    public void salvarExeucao(modelo.Teste teste, modelo.Agenda umaScheduling) throws SQLException {
        //salva a parte da tabela execucao
        String insert = "INSERT INTO execucao (execucaoID, atletaID, protocoloID, hora, data, temperatura, tipoSuperficie, status) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        save(insert, null, teste.getAtleta().getId(), teste.getProtocolo().getId(), teste.getHora(),
                teste.getData(), teste.getTemperatura(), teste.getSuperficie(), teste.getStatus().getValor());
        //salva a parte da tabela AgendaExecucao
        String insertAgendaExecucao = "INSERT INTO AgendaExecucao (agendaID, execucaoID) VALUES(?,?)";
        save(insertAgendaExecucao, umaScheduling.getId(), maxId());
    }

    public int maxId() throws SQLException {
        String select = "SELECT MAX(execucaoID) as maximo FROM EXECUCAO";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();

        return id;
    }

    public void alterarExecucao(modelo.Execucao umaExecucao) throws SQLException {
        String alterar = "UPDATE execucao "
                + "SET status = ?, tipoSuperficie = ?, temperatura = ?, data = ?, "
                + "hora = ?, protocoloID = ?, atletaID = ? "
                + "WHERE execucaoId = ?";
        update(alterar, umaExecucao.getId(), umaExecucao.getStatus().getValor(),
                umaExecucao.getSuperficie(), umaExecucao.getTemperatura(),
                umaExecucao.getData(), umaExecucao.getHora(),
                umaExecucao.getProtocolo().getId(), umaExecucao.getAtleta().getId());
    }

    public void alterarExecucao(modelo.Teste teste) throws SQLException {
        String alterar = "UPDATE execucao "
                + "SET status = ?, tipoSuperficie = ?, temperatura = ?, data = ?, "
                + "hora = ?, protocoloID = ?, atletaID = ? "
                + "WHERE execucaoId = ?";
        update(alterar, teste.getId(), teste.getStatus().getValor(),
                teste.getSuperficie(), teste.getTemperatura(),
                teste.getData(), teste.getHora(),
                teste.getProtocolo().getId(), teste.getAtleta().getId());
    }

    public void removerExecucao(int id) throws SQLException {
        String removeAE = "DELETE FROM AgendaExecucao WHERE execucaoID = ?";
        delete(removeAE, id);
        String remover = "DELETE FROM execucao WHERE execucaoID = ?";
        delete(remover, id);
    }

    public void removerExecucao(modelo.Atleta atleta, modelo.Protocolo protocolo,
            modelo.Agenda agenda) throws SQLException, ParseException {
        modelo.Execucao umaExecucao = execucao(agenda, atleta, protocolo);
        String remove = "DELETE FROM execucao WHERE execucaoID = " + umaExecucao.getId();
        String removeAE = "DELETE FROM AgendaExecucao WHERE execucaoID = " + umaExecucao.getId();
        String removeSR = "DELETE FROM shuttleRun WHERE execucaoID = " + umaExecucao.getId();
        Statement stmt = getConnection().createStatement();
        stmt.execute(removeSR);
        stmt.execute(removeAE);
        stmt.execute(remove);
    }

    public modelo.Execucao execucao(modelo.Agenda umScheduling, modelo.Atleta umAtleta, modelo.Protocolo umProtocol) throws SQLException, ParseException {
        String select
                = "SELECT execucao.* "
                + "FROM Agenda  INNER JOIN AgendaExecucao "
                + "ON Agenda.agendaID = AgendaExecucao.agendaID "
                + "INNER JOIN Execucao "
                + "ON Execucao.execucaoID = AgendaExecucao.execucaoID "
                + "WHERE Agenda.agendaID = " + umScheduling.getId() + " "
                + "AND Execucao.atletaID = " + umAtleta.getId() + " "
                + "AND Execucao.protocoloID = " + umProtocol.getId() + " ";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        modelo.Execucao umaExecucao = new modelo.Execucao();
        while (rs.next()) {
            umaExecucao.setId(rs.getInt("execucaoID"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = rs.getDate("data");
            umaExecucao.setData(data);
            umaExecucao.setHora(rs.getString("hora"));
            umaExecucao.setTemperatura(rs.getInt("temperatura"));
            umaExecucao.setSuperficie(rs.getString("tipoSuperficie"));
        }
        rs.close();
        stmt.close();
        return umaExecucao;
    }

    protected Status status(int s) {
        switch (s) {
            case 0:
                return Status.NÃO_CONCLUIDO;
            case 1:
                return Status.CONCLUIDO;
            default:
                return null;
        }
    }
}
