//PeriodoDAO.java
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Periodo;

/**
 * @author 5102011212
 */
public class PeriodoDAO extends DAO {

    //método para salvar o período
    public void save(Periodo period) throws SQLException {
        String insert = "INSERT INTO GRUPOATLETA(grupoAtletaID, grupoId, atletaId) VALUES(?,?,?)";
        save(insert, period.getPeriodoId(), period.getGrupoId(), period.getAtletaId());
    }

    //método para alterar o período
    public void update(Periodo period) throws SQLException {
        String update = "UPDATE GRUPOATLETA "
                + "SET atletaId = ?,grupoId = ?"
                + "WHERE grupoAtletaID = ?";
        update(update, period.getPeriodoId(), period.getAtletaId(), period.getGrupoId());
    }

    //método para excluir os períodos
    public void excluir(int id) throws SQLException {
        String delete = "DELETE FROM GRUPOATLETA WHERE GRUPOID = ?";
        delete(delete, id);
    }

    //método que retorna uma lista de períodos 
    public List<Periodo> findGrupoAtleta() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }        
        List<Periodo> periodo = new ArrayList<>();
        String select = "SELECT * FROM GRUPOATLETA";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Periodo g = new Periodo();
            g.setGrupoId(rs.getInt("grupoId"));
            g.setAtletaId(rs.getInt("atletaId"));
            g.setPeriodoId(rs.getInt("grupoAtletaID"));
            periodo.add(g);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return periodo;
    }

    //método que retorna uma lista de períodos de um determinado grupo
    public List<Periodo> grupoAtletaId(int grupoID) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        List<Periodo> periodo = new ArrayList<>();
        String select = "SELECT * FROM GRUPOATLETA where grupoID = " + grupoID + "";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Periodo g = new Periodo();
            g.setAtletaId(rs.getInt("atletaId"));
            g.setPeriodoId(rs.getInt("grupoAtletaID"));
            periodo.add(g);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return periodo;
    }

    //método que o maior ID do grupo
    public int getId() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT MAX(grupoID) as maximo FROM GRUPO";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return id;
    }

    //método que retorna o maior ID do período
    public int getIdPeriodo() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT MAX(grupoAtletaID) as maximo FROM GRUPOATLETA";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("maximo");
        }
        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return id;
    }

    //método que apaga todos os períodos
    public void removeAll(int id) throws SQLException {
        String select = "DELETE FROM GRUPOATLETA WHERE GRUPOID = " + id;
        deleteAll(select);
    }
}
