//ShuttleRRunDAO.java
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Heusser
 */
public class ShuttleRunDAO extends dao.ExecucaoDAO {

    public void salvar(modelo.ShuttleRun novoShuttleRun) throws SQLException {
        String inserir = "INSERT INTO shuttleRun (shuttleRunID, nivel, tempo, execucaoID)"
                + " VALUES(?,?,?,?)";
        save(inserir, null, novoShuttleRun.getNivel(), novoShuttleRun.getTempo(), novoShuttleRun.getId());
    }

    public void alterar(modelo.ShuttleRun umShuttleRun) throws SQLException {
        String alterar = "UPDATE shuttleRun SET nivel = ?, tempo = ? "
                + "WHERE shuttleRunId = ?";
        update(alterar, umShuttleRun.getShuttleRunId(), umShuttleRun.getNivel(), umShuttleRun.getTempo());
    }

    public void remover(int id) throws SQLException {
        String remover = "DELETE FROM shuttleRun WHERE shuttleRunID = ?";
        delete(remover, id);
    }

    public List<modelo.ShuttleRun> listar(List<modelo.Atleta> atletas,
            modelo.Protocolo protocolo, modelo.Agenda agenda) throws SQLException, ParseException {

        List<modelo.ShuttleRun> lista = new ArrayList<>();

        for (modelo.Atleta umAtleta : atletas) {
            String select = "SELECT e.*, sr.* FROM "
                    + "Agenda a INNER JOIN AgendaExecucao ae "
                    + "ON a.agendaId = ae.agendaId "
                    + "INNER JOIN Execucao e "
                    + "ON ae.execucaoId = e.execucaoId "
                    + "INNER JOIN ShuttleRun sr "
                    + "ON e.execucaoId = sr.execucaoId "
                    + "WHERE a.agendaId = " + agenda.getId() + " "
                    + "AND e.protocoloId = " + protocolo.getId() + " "
                    + "AND e.atletaId = " + umAtleta.getId() + " ";

            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while (rs.next()) {
                modelo.ShuttleRun run = new modelo.ShuttleRun();
                run.setAtleta(umAtleta);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                run.setData(rs.getDate("data"));
                run.setHora(rs.getString("hora"));
                run.setId(rs.getInt("execucaoId"));
                run.setNivel(rs.getInt("nivel"));
                run.setProtocolo(protocolo);
                run.setShuttleRunId(rs.getInt("shuttleRunId"));
                run.setStatus(status(rs.getInt("status")));
                run.setSuperficie(rs.getString("tipoSuperficie"));
                run.setTemperatura(rs.getInt("temperatura"));
                run.setTempo(rs.getString("tempo"));

                lista.add(run);
            }
            rs.close();
            stmt.close();
        }
        return lista;
    }

    public modelo.ShuttleRun shuttleRun(modelo.Agenda umaAgenda, modelo.Execucao umaExecucao) throws SQLException, ParseException {
        String select = "SELECT sr.*, e.* FROM Agenda a "
                + "INNER JOIN AgendaExecucao ae "
                + "ON a.agendaId = ae.agendaId "
                + "INNER JOIN Execucao e "
                + "ON ae.execucaoId = e.execucaoId "
                + "INNER JOIN ShuttleRun sr "
                + "ON e.execucaoId = sr.execucaoId "
                + "WHERE a.agendaId = " + umaAgenda.getId() + " "
                + "AND ae.execucaoId = " + umaExecucao.getId() + " ";
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(select);
        modelo.ShuttleRun run = new modelo.ShuttleRun();
        while (rs.next()) {
            run.setAtleta(umaExecucao.getAtleta());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            run.setData(rs.getDate("data"));
            run.setHora(rs.getString("hora"));
            run.setId(rs.getInt("execucaoId"));
            run.setNivel(rs.getInt("nivel"));
            run.setProtocolo(umaExecucao.getProtocolo());
            run.setShuttleRunId(rs.getInt("shuttleRunId"));
            run.setStatus(umaExecucao.getStatus());
            run.setSuperficie(rs.getString("tipoSuperficie"));
            run.setTemperatura(rs.getInt("temperatura"));
            run.setTempo(rs.getString("tempo"));
        }
        return run;
    }
}
