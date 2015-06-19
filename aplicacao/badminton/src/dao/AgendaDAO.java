//AgendaDAO.java
package dao;

import modelo.Grupo;
import modelo.Agenda;
import modelo.Instituicao;
import modelo.Protocolo;
import modelo.Atleta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 5102011212
 */
public class AgendaDAO extends DAO {

    //método para salvar o agendamento no banco de dados
    public void save(Agenda scheduling) throws SQLException {
        //salva a parte da tabela agenda
        String insert = "INSERT INTO AGENDA (agendaID, data, local, situacao)"
                + "VALUES (?,?,?,?)";
        save(insert, scheduling.getId(), scheduling.getData(), scheduling.getLocal(), scheduling.getSituacao());
        //salva a parte da tabela atletaAgendado
        scheduling.setId(getId());//pega o maior id do banco
        String insertAthlete = "INSERT INTO ATLETAAGENDADO (atletaAgendadoId, atleta, ordem) VALUES (?,?,?)";
        int cont = 1;
        for (Atleta list : scheduling.getAtleta()) {
            save(insertAthlete, scheduling.getId(), list.getId(), cont);
            cont++;
        }
        //salva a parte da tabela protocoloAgendado
        String insertProtocol = "INSERT INTO PROTOCOLOAGENDADO (protocoloAgendadoId, protocolo, ordem)"
                + "VALUES (?,?,?)";
        cont = 1;
        for (Protocolo list : scheduling.getProtocolo()) {
            save(insertProtocol, scheduling.getId(), list.getId(), cont);
            cont++;
        }

    }

    //método para alterar o agendamento no banco de dados
    public void update(Agenda scheduling) throws SQLException {
        //altera a parte da tabela agenda
        String update = "UPDATE AGENDA SET data = ?, local = ?, situacao = ? "
                + "WHERE agendaID = ?";
        update(update, scheduling.getId(), scheduling.getData(), scheduling.getLocal(), scheduling.getSituacao());
        //altera a parte da tabela atletaAgendado
        String deleteAllAthletes = "DELETE FROM ATLETAAGENDADO WHERE ATLETAAGENDADOID = ?";
        delete(deleteAllAthletes, scheduling.getId());
        String insertAthlete = "INSERT INTO ATLETAAGENDADO (atletaAgendadoId, atleta, ordem) VALUES (?,?,?)";
        int cont = 1;
        for (Atleta list : scheduling.getAtleta()) {
            save(insertAthlete, scheduling.getId(), list.getId(), cont);
            cont++;
        }
        //altera a parte da tabela protocoloAgendado
        String deleteAllProtocols = "DELETE FROM PROTOCOLOAGENDADO WHERE PROTOCOLOAGENDADOID = ?";
        delete(deleteAllProtocols, scheduling.getId());
        String insertProtocol = "INSERT INTO PROTOCOLOAGENDADO (protocoloAgendadoId, protocolo, ordem)"
                + "VALUES (?,?,?)";
        cont = 1;
        for (Protocolo list : scheduling.getProtocolo()) {
            save(insertProtocol, scheduling.getId(), list.getId(), cont);
            cont++;
        }
    }

    //método para apagar o agendamento no banco de dados
    public void delete(int id) throws SQLException {
        String deleteAthlete = "DELETE FROM ATLETAAGENDADO WHERE atletaAgendadoId = ?";
        String deleteProtocol = "DELETE FROM PROTOCOLOAGENDADO WHERE protocoloAgendadoId = ?";
        String delete = "DELETE FROM AGENDA WHERE agendaId = ?";
        String deleteAE = "DELETE FROM AgendaExecucao WHERE agendaID = ?";
        delete(deleteAthlete, id);
        delete(deleteProtocol, id);
        delete(deleteAE, id);
        delete(delete, id);
    }

    //método que o maior ID da agenda
    public int getId() throws SQLException {
        String select = "SELECT MAX(agendaID) as maximo FROM AGENDA";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();
        //fecha a conexão
        return id;
    }

    //método que retorna um array com os ids dos atletas agendados
    public List<Atleta> atletasAgendados(int agenda) throws SQLException {
        AtletaDAO athleteDAO = new AtletaDAO();
        List<Atleta> list = new ArrayList<>();
        String select = "SELECT * FROM ATLETAAGENDADO WHERE atletaAgendadoId = '" + agenda + "' ORDER BY ordem ASC";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        int b = 0;
        while (rs.next()) {
            b = rs.getInt("atleta");
            Atleta a = (Atleta) athleteDAO.athleteObject(b);
            list.add(a);
        }
        return list;
    }

    //método que retorna uma lista com os atletas agendados
    public List<Atleta> athlete(int agenda) throws SQLException {
        AtletaDAO athleteDAO = new AtletaDAO();
        List<Atleta> list = new ArrayList<>();
        String select = "SELECT Atleta.* FROM ATLETAAGENDADO INNER JOIN Atleta "
                + "ON AtletaAgendado.atleta = Atleta.atletaID "
                + "WHERE atletaAgendadoId = '" + agenda + "' ORDER BY ordem ASC";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Atleta a = null;
        while (rs.next()) {
            a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            list.add(a);
        }
        rs.close();
        stmt.close();
        super.connection.close();
        return list;
    }

    //método que retorna um array com os ids dos protcolos agendados
    public List<Protocolo> protocolosAgendados(int agenda) throws SQLException {
        ProtocoloDAO protocolDAO = new ProtocoloDAO();
        List<Protocolo> list = new ArrayList<>();
        String select = "SELECT * FROM PROTOCOLOAGENDADO WHERE protocoloAgendadoId = '" + agenda + "' ORDER BY ordem ASC";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        int b = 0;
        while (rs.next()) {
            b = rs.getInt("protocolo");
            Protocolo p = protocolDAO.protocol(b);
            list.add(p);
        }

        return list;
    }

    //método que retorna uma lista dos protcolos agendados
    public List<Protocolo> protocol(int agenda) throws SQLException {
        super.getConnection();
        ProtocoloDAO protocolDAO = new ProtocoloDAO();
        List<Protocolo> list = new ArrayList<>();
        String select = "SELECT Protocolo.* FROM PROTOCOLOAGENDADO INNER JOIN Protocolo "
                + "ON ProtocoloAgendado.protocolo = Protocolo.protocoloId "
                + "WHERE protocoloAgendadoId = '" + agenda + "' ORDER BY ordem ASC";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        Protocolo p = null;
        while (rs.next()) {
            p = new Protocolo();
            p.setId(rs.getInt("protocoloId"));
            p.setNome(rs.getString("nome"));
            p.setTipo(rs.getString("tipo"));
            list.add(p);
        }
        rs.close();
        stmt.close();
        super.connection.close();
        return list;
    }

    //método que retorna uma lista de grupos conforme o id
    public List<Grupo> group(int id) throws SQLException {
        List<Grupo> group = new ArrayList<>();
        String select = "SELECT * FROM GRUPO WHERE instituicaoID = " + id + " ";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Grupo g = null;
        while (rs.next()) {
            g = new Grupo();
            g.setId(rs.getInt("GrupoID"));
            g.setNome(rs.getString("nome"));
            g.setInstituicaoID(rs.getInt("instituicaoID"));
            g.setDataInicial(rs.getDate("dataInicial"));
            g.setDataFinal(rs.getDate("dataFinal"));
            group.add(g);
        }

        rs.close();
        stmt.close();
        super.connection.close();
        return group;
    }

    //método que retorna um objeto do tipo Agenda
    public Agenda scheduling(int agendaId) throws SQLException {
        List<Agenda> scheduling = new ArrayList<>();
        String select = "SELECT * FROM AGENDA WHERE AGENDAID = '" + agendaId + "'";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Agenda sc = null;
        while (rs.next()) {
            sc = new Agenda();
            sc.setId(rs.getInt("agendaID"));
            sc.setData(rs.getTimestamp("data"));
            sc.setLocal(rs.getString("local"));
            sc.setAtleta(atletasAgendados(sc.getId()));
            sc.setProtocolo(protocolosAgendados(sc.getId()));
        }
        return sc;
    }

    //metodo que retorna uma lista de agendamentos
    public List<Agenda> scheduling(String athlete, String local, Date date, Protocolo protocol, String situation) throws SQLException {
        List<Agenda> scheduling = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String select = "";
        if (!athlete.isEmpty() && date != null && !local.isEmpty() && protocol != null && !situation.isEmpty()) {//se todos os filtros estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%'  "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%'  "
                    + "AND Agenda.local LIKE '%" + local + "%'  "
                    + "AND Protocolo.protocoloId = " + protocol.getId() + " "
                    + "AND Agenda.situacao = '" + situation + "' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && date != null && !local.isEmpty() && protocol != null) {// se os filtros atleta, data, local e protocolo estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%'  "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%'  "
                    + "AND Agenda.local LIKE '%" + local + "%'  "
                    + "AND Protocolo.protocoloId = " + protocol.getId() + " "
                    + "ORDER BY Agenda.data DESC";
        } else if (date != null && !local.isEmpty() && protocol != null && !situation.isEmpty()) {//se os filtros data, local, protocolo e situação estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Agenda.data LIKE '" + sdf.format(date) + "%'  "
                    + "AND Agenda.local LIKE '%" + local + "%'  "
                    + "AND Protocolo.protocoloId = " + protocol.getId() + " "
                    + "AND Agenda.situacao = '" + situation + "' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && !local.isEmpty() && protocol != null && !situation.isEmpty()) {// se os filtros atleta, local, protocolo e situação estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%'  "
                    + "AND Agenda.local LIKE '%" + local + "%'  "
                    + "AND Protocolo.protocoloId = " + protocol.getId() + " "
                    + "AND Agenda.situacao = '" + situation + "' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && date != null && protocol != null && !situation.isEmpty()) {// se os filtros atleta, data, protocolo e situação estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%'  "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%'  "
                    + "AND Protocolo.protocoloId = " + protocol.getId() + " "
                    + "AND Agenda.situacao = '" + situation + "' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && date != null && !local.isEmpty() && !situation.isEmpty()) {// se os filtros atleta, data, local e situação estiverem preenchidos
            select = "SELECT DISTINCT Agenda.data, Agenda.local, Agenda.agendaId from Agenda "
                    + "join AtletaAgendado on (Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "join Atleta  on (Atleta.atletaId = AtletaAgendado.atleta) "
                    + "join ProtocoloAgendado on (Agenda.agendaId = ProtocoloAgendado.protocoloAgendadoId) "
                    + "join Protocolo on (Protocolo.protocoloId = ProtocoloAgendado.protocolo) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%'  "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%'  "
                    + "AND Agenda.local LIKE '%" + local + "%'  "
                    + "AND Agenda.situacao = '" + situation + "' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && date != null && !local.isEmpty()) {//se os filtros atleta, data, local estiverem preenchidos
            select = "SELECT DISTINCT Agenda.* "
                    + "FROM ((Agenda INNER JOIN AtletaAgendado "
                    + "ON Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "INNER JOIN Atleta "
                    + "ON AtletaAgendado.atleta = Atleta.atletaId) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%' "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%' "
                    + "AND Agenda.local LIKE '%" + local + "%'  ORDER BY Agenda.data DESC";
        } else if (date != null && !local.isEmpty() && protocol != null) {//se os filtros data, local e protocolo estiverem preenchidos
            select = "SELECT DISTINCT Agenda.* "
                    + "FROM Agenda INNER JOIN ProtocoloAgendado "
                    + "ON Agenda.AgendaId = ProtocoloAgendado.protocoloAgendadoId "
                    + "WHERE ProtocoloAgendado.protocolo = " + protocol.getId() + " "
                    + "AND Agenda.data LIKE '" + sdf.format(date) + "%' "
                    + "AND Agenda.local LIKE '%" + local + "%' ORDER BY Agenda.data DESC";
        } else if (date != null && !local.isEmpty()) {//se os filtros data e local estiverem preenchidos
            select = "SELECT * FROM Agenda "
                    + "WHERE local LIKE '%" + local + "%' AND data LIKE '" + sdf.format(date) + "%' ORDER BY Agenda.data DESC";
        } else if (!local.isEmpty() && protocol != null) {//se os filtros local e protocolo estiverem preenchidos
            select = "SELECT Agenda.*, ProtocoloAgendado.* "
                    + "FROM Agenda INNER JOIN ProtocoloAgendado "
                    + "ON Agenda.AgendaId = ProtocoloAgendado.protocoloAgendadoId "
                    + "WHERE ProtocoloAgendado.protocolo = " + protocol.getId() + " "
                    + "AND Agenda.local LIKE '%" + local + "%' ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty() && date != null) {//se os filtros atleta, data estiverem preechidos
            select = "SELECT DISTINCT Agenda.* "
                    + "FROM ((Agenda INNER JOIN AtletaAgendado "
                    + "ON Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "INNER JOIN Atleta "
                    + "ON AtletaAgendado.atleta = Atleta.atletaId) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%' AND Agenda.data LIKE '" + sdf.format(date) + "%' "
                    + "ORDER BY Agenda.data DESC";
        } else if (!athlete.isEmpty()) {//filtro somente por atleta
            select = "SELECT DISTINCT Agenda.* "
                    + "FROM ((Agenda INNER JOIN AtletaAgendado "
                    + "ON Agenda.agendaId = AtletaAgendado.atletaAgendadoId) "
                    + "INNER JOIN Atleta "
                    + "ON AtletaAgendado.atleta = Atleta.atletaId) "
                    + "WHERE Atleta.nome LIKE '%" + athlete + "%' ORDER BY Agenda.data DESC, AtletaAgendado.ordem ASC";
        } else if (!local.isEmpty()) {//filtro somente por local
            select = "SELECT * FROM AGENDA WHERE local LIKE '%" + local + "%' ORDER BY data DESC";
        } else if (protocol != null) {//filtro somente por protocolo
            select = "SELECT Agenda.*, ProtocoloAgendado.* "
                    + "FROM Agenda INNER JOIN ProtocoloAgendado "
                    + "ON Agenda.AgendaId = ProtocoloAgendado.protocoloAgendadoId "
                    + "WHERE ProtocoloAgendado.protocolo = " + protocol.getId() + " ORDER BY Agenda.data DESC";
        } else if (date != null) {//filtro somente por data
            select = "SELECT * FROM AGENDA WHERE data LIKE '" + sdf.format(date) + "%' ORDER BY data DESC";
        } else if (!situation.isEmpty()) {// se somente o filtro situação estiver preenchido
            select = "SELECT * FROM AGENDA WHERE situacao = '" + situation + "' ORDER BY data DESC";
        } else {//sem nenhum filtro mostra todos os cadastrados
            select = "SELECT * FROM AGENDA ORDER BY data DESC";
        }

        PreparedStatement pstmt = getConnection().prepareStatement(select);
        ResultSet rs = pstmt.executeQuery();
        Agenda sc = null;
        while (rs.next()) {
            sc = new Agenda();
            sc.setId(rs.getInt("agendaId"));
            sc.setData(rs.getTimestamp("data"));
            sc.setLocal(rs.getString("local"));
            sc.setSituacao(rs.getString("Situacao"));
            sc.setAtleta(atletasAgendados(sc.getId()));
            sc.setProtocolo(protocolosAgendados(sc.getId()));
            scheduling.add(sc);
        }
        pstmt.close();
        rs.close();
        super.connection.close();
        return scheduling;
    }

    //método que retorna uma lista de atletas conforme os filtros passados por parâmetro
    public List<Atleta> athlete(Instituicao institution, Grupo group, String gender, String level) throws SQLException {
        List<Atleta> athlete = new ArrayList<>();
        String select = "";
        if (institution != null && group != null && !gender.isEmpty() && !level.isEmpty()) {//com todos os filtros preenchidos
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (institution != null && group != null && !gender.isEmpty()) {//filtros instituição, grupo e genero
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' ";
        } else if (group != null && !gender.isEmpty() && !level.isEmpty()) {//filtros grupo, genero e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (institution != null && !gender.isEmpty() && !level.isEmpty()) {//filtros instituição, genero e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (institution != null && group != null && !level.isEmpty()) {//filtros instituição, grupo e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (institution != null && group != null) {//filtro instituição e grupo
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Grupo.grupoId = " + group.getId() + " ";
        } else if (institution != null && !gender.isEmpty()) {//filtro instituição e genero
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' ";
        } else if (institution != null && !level.isEmpty()) {//filtro instituição e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.instituicao = " + institution.getId() + " "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (group != null && !gender.isEmpty()) {//filtro grupo e genero
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.genero = '" + gender + "' ";
        } else if (group != null && !level.isEmpty()) {//filtro grupo e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Grupo.grupoId = " + group.getId() + " "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (!gender.isEmpty() && !level.isEmpty()) {//filtro genero e nivel
            select = "SELECT Atleta.* "
                    + "FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Atleta.genero = '" + gender + "' "
                    + "AND Atleta.nivel = '" + level + "' ";
        } else if (institution != null) {//filtro instituição
            select = "SELECT * FROM ATLETA WHERE instituicao = " + institution.getId() + " ";
        } else if (group != null) {//filtro grupo
            select = "SELECT Atleta.* FROM Atleta INNER JOIN Grupo "
                    + "ON Atleta.instituicao = Grupo.instituicaoId "
                    + "WHERE Grupo.grupoId = " + group.getId() + " ";
        } else if (!gender.isEmpty()) {//filtro genero
            select = "SELECT * FROM Atleta WHERE genero = '" + gender + "' ";
        } else if (!level.isEmpty()) {//filtro nivel
            select = "SELECT * FROM Atleta WHERE nivel = '" + level + "' ";
        } else {
            select = "SELECT * FROM Atleta ";
        }
        PreparedStatement pstmt = getConnection().prepareStatement(select);
        ResultSet rs = pstmt.executeQuery();
        Atleta a = null;
        while (rs.next()) {
            a = new Atleta();
            a.setId(rs.getInt("atletaID"));
            a.setNome(rs.getString("nome"));
            a.setInstituicao(new InstituicaoDAO().instituicao(rs.getInt("instituicao")));
            a.setEstatura(rs.getDouble("estatura"));
            a.setMassaCorporal(rs.getDouble("massaCorporal"));
            a.setPratica(rs.getString("tempoPratica"));
            a.setPraticaSemana(rs.getString("praticaSemana"));
            a.setNivel(rs.getString("nivel"));
            a.setDataNascimento(rs.getDate("dataNascimento"));
            a.setGenero(rs.getString("genero"));
            a.setLateralidade(rs.getString("lateralidade"));
            a.setEnvergadura(rs.getInt("envergadura"));
            a.setEmail(rs.getString("email"));
            athlete.add(a);
        }
        pstmt.close();
        rs.close();
        super.connection.close();
        return athlete;
    }
}
