//AgilidadeEmQuadraDAO.java
package dao;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.AgilidadeEmQuadra;
import modelo.Atleta;
import modelo.Status;
import modelo.Valido;

/**
 *
 * @author 5102011212
 */
public class AgilidadeEmQuadraDAO extends ExecucaoDAO{

    public void update(int execucaoId, Status umStatus) throws SQLException {
        String update = "update execucao set status = ? where execucaoId = ?";
        update(update, execucaoId, umStatus.getValor());
    }

    public void save(AgilidadeEmQuadra aeq, int agendaID) throws SQLException {
        //salva a parte da tabela execucao
        String insert = "Insert into execucao (execucaoID, atletaID, protocoloID, hora, data, temperatura, tipoSuperficie, status) "
                + "values(?,?,?,?,?,?,?,?)";
        save(insert, null, aeq.getAtleta().getId(), aeq.getProtocolo().getId(), aeq.getHora(),
                aeq.getData(), aeq.getTemperatura(), aeq.getSuperficie(), aeq.getStatus().getValor());
        //salva a parte da tabela AgendaExecucao
        String insertAgendaExecucao = "Insert into AgendaExecucao (agendaID, execucaoID) values(?,?)";
        save(insertAgendaExecucao, agendaID, maxId());

    }

    public void save(AgilidadeEmQuadra aeq) throws SQLException {
        String insertAeq = "insert into agilidadeemquadra (agilidadeemquadraID, tempo, valido, parcial, motivo, execucaoID) "
                + "values(?,?,?,?,?,?)";
        save(insertAeq, null, aeq.getTempo(), aeq.getValido().getValor(), aeq.getParcial(), aeq.getMotivo(), aeq.getId());
    }

    public modelo.Execucao execucao(int agendaID, int atletaID, int protocoloID) throws SQLException, ParseException {
        String select =
                "SELECT execucao.* "
                + "FROM Agenda  INNER JOIN AgendaExecucao "
                + "ON Agenda.agendaID = AgendaExecucao.agendaID "
                + "INNER JOIN Execucao "
                + "ON Execucao.execucaoID = AgendaExecucao.execucaoID "
                + "WHERE Agenda.agendaID = " + agendaID + " "
                + "AND Execucao.atletaID = " + atletaID + " "
                + "AND Execucao.protocoloID = " + protocoloID + " ";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        modelo.Execucao umaExecucao = new modelo.Execucao();
        while(rs.next()){
            umaExecucao.setId(rs.getInt("execucaoID"));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = rs.getDate("data");
            umaExecucao.setData(data);
            umaExecucao.setHora(rs.getString("hora"));
            umaExecucao.setTemperatura(rs.getInt("temperatura"));
            umaExecucao.setSuperficie(rs.getString("tipoSuperficie"));
        }
        return umaExecucao;
    }

    public int maxExecucaoId(int atletaID) throws SQLException {
        String select = "SELECT MAX(ExecucaoID) as maximo "
                + "FROM Atleta inner join Execucao "
                + "on Atleta.atletaID = Execucao.atletaID "
                + "where execucao.atletaId = " + atletaID;
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

    public int ultimaRodada(Atleta athlete, int agendaID) throws SQLException {
        String select = "Select max(AgilidadeEmQuadra.parcial) as maximo from "
                + "Agenda inner join AgendaExecucao "
                + "on Agenda.agendaID = AgendaExecucao.agendaID "
                + "inner join Execucao "
                + "on AgendaExecucao.execucaoID = Execucao.execucaoID "
                + "inner join AgilidadeEmQuadra "
                + "on AgilidadeEmQuadra.execucaoID = Execucao.execucaoID "
                + "where Execucao.atletaID = " + athlete.getId() + " "
                + "and Agenda.agendaID = " + agendaID + " ";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();

        return id;
    }

    public List<AgilidadeEmQuadra> rodadas(int execucaoId, int protocoloId) throws SQLException, ParseException {
        String select = "Select AgilidadeEmQuadra.*, Execucao.* from "
                + "Agenda inner join AgendaExecucao "
                + "on Agenda.agendaID = AgendaExecucao.agendaID "
                + "inner join Execucao "
                + "on Execucao.execucaoID = AgendaExecucao.execucaoID "
                + "inner join AgilidadeEmQuadra "
                + "on AgilidadeEmQuadra.execucaoID = Execucao.execucaoID "
                + "where Execucao.execucaoID = " + execucaoId + " "
                + "and Execucao.protocoloId = " + protocoloId + " "
                + " order by AgilidadeEmQuadra.parcial";

        List<AgilidadeEmQuadra> list = new ArrayList<>();
        AgilidadeEmQuadra aeq;
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            aeq = new AgilidadeEmQuadra();
            aeq.setId(rs.getInt("execucaoID"));
            aeq.setData(rs.getDate("data"));
            aeq.setHora(rs.getString("hora"));
            aeq.setSuperficie(rs.getString("tipoSuperficie"));
            aeq.setTemperatura(rs.getInt("temperatura"));
            aeq.setTempo(rs.getString("tempo"));
            aeq.setValido(converte(rs.getInt("valido")));
            aeq.setMotivo(rs.getString("motivo"));
            aeq.setParcial(rs.getInt("parcial"));
            list.add(aeq);
        }
        return list;
    }

    public int agilidadeEmQuadraId(int execucaoID, int parcial) throws SQLException {
        String select = "select max(AgilidadeEmQuadra.AgilidadeEmQuadraID) as maximo "
                + "from Execucao inner join AgilidadeEmQuadra "
                + "on Execucao.execucaoId = AgilidadeEmQuadra.execucaoID "
                + "where Execucao.ExecucaoID = " + execucaoID
                + " and AgilidadeEmQuadra.parcial = " + parcial;
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();

        return id;
    }

    public void invalida(AgilidadeEmQuadra aeq) throws SQLException {
        String update = "update AgilidadeEmQuadra "
                + "set valido = ?, motivo = ? "
                + "where agilidadeEmQuadraID = ? ";
        update(update, aeq.getAgilidadeEmQuadraID(), aeq.getValido().getValor(), aeq.getMotivo());
    }

    public List<modelo.Atleta> verificaCor(List<modelo.Atleta> list, int agendaId, int protocoloId) throws SQLException {
        List<modelo.Atleta> atleta = new ArrayList<>();
        for (Atleta umAtleta : list) {
            if (umAtleta != null) {
                String select = "select Execucao.status "
                        + "from Agenda inner join AgendaExecucao on Agenda.agendaId = AgendaExecucao.agendaId "
                        + "inner join Execucao on Execucao.execucaoId = AgendaExecucao.execucaoId "
                        + "where Execucao.atletaId = " + umAtleta.getId() + " "
                        + "and Execucao.protocoloId = " + protocoloId + " "
                        + "and Agenda.agendaId =  " + agendaId + " ";

                PreparedStatement stmt = getConnection().prepareStatement(select);
                ResultSet rs = stmt.executeQuery();
                int status = 0;
                if (rs.next()) {
                    status = rs.getInt("status");
                    if (status == 1) {
                        umAtleta.setCor(Color.green);
                    } else {
                        umAtleta.setCor(Color.white);
                    }
                } else {
                    umAtleta.setCor(Color.white);
                }
                atleta.add(umAtleta);
                rs.close();
                stmt.close();
            } else {
                atleta.add(umAtleta);
            }
        }
        return atleta;
    }

    public Object[] verificaFimProt(List<modelo.Protocolo> list, int agendaId, int qtd) throws SQLException {
        List<modelo.Protocolo> protocolos = new ArrayList<>();

        for (modelo.Protocolo umProtocolo : list) {
            String select = "select Execucao.status "
                    + "from Agenda inner join AgendaExecucao on Agenda.agendaId = AgendaExecucao.agendaId "
                    + "inner join Execucao on Execucao.execucaoId = AgendaExecucao.execucaoId "
                    + "where Execucao.protocoloId = " + umProtocolo.getId() + " "
                    + "and Agenda.agendaId =  " + agendaId + " ";

            PreparedStatement stmt = getConnection().prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            int status = 0;
            umProtocolo.setCor(Color.green);
            int cont = 0;
            while (rs.next()) {
                status = rs.getInt("status");
                if (status == 0) {
                    umProtocolo.setCor(Color.white);
                }
                cont++;
            }
            if (cont < qtd) {
                umProtocolo.setCor(Color.white);
            }

            protocolos.add(umProtocolo);
            rs.close();
            stmt.close();
            
        }
        Object[] objs = new Object[protocolos.size()];
        int i = 0;
        for (Object obj : protocolos) {
            objs[i] = obj;
            i++;
        }
        return objs;
    }

    public Valido converte(int v) {
        switch (v) {
            case 0:
                return Valido.INVALIDO;
            case 1:
                return Valido.VALIDO;
            default:
                return Valido.INVALIDO;
        }
    }
}
