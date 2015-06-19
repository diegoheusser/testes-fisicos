//GrupoDAO.java
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Grupo;

/**
 * @author 5102011212
 */
public class GrupoDAO extends DAO {

    public void salvar(Grupo grupo) throws SQLException {
        String insert = "INSERT INTO GRUPO(grupoID, nome, instituicaoID, dataInicial, dataFinal) VALUES(?,?,?,?,?)";
        save(insert, grupo.getId(), grupo.getNome(), grupo.getInstituicaoID(), grupo.getDataInicial(), grupo.getDataInicial());
    }

    public void alterar(Grupo grupo) throws SQLException {
        String update = "UPDATE GRUPO "
                + "SET nome = ?, instituicaoID = ?, dataInicial = ?, dataFinal = ? "
                + "WHERE grupoID = ?";
        update(update, grupo.getId(), grupo.getNome(), grupo.getInstituicaoID(), grupo.getDataInicial(), grupo.getDataFinal());
    }

    public void excluir(int id) throws SQLException {
        String delete = "DELETE FROM GRUPO WHERE GRUPOID = ?";
        delete(delete, id);
    }

    public List<Grupo> findGrupo() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }        
        List<Grupo> grupo = new ArrayList<>();
        String select = "SELECT * FROM GRUPO";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Grupo g = new Grupo();
            g.setId(rs.getInt("GrupoID"));
            g.setNome(rs.getString("nome"));
            g.setInstituicaoID(rs.getInt("instituicaoID"));
            g.setDataInicial(rs.getDate("dataInicial"));
            g.setDataFinal(rs.getDate("dataFinal"));
            grupo.add(g);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return grupo;
    }

    public List<Grupo> findByName(String nome) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }        
        String select = "SELECT * FROM GRUPO WHERE nome = ?";
        Grupo grupo = null;
        List<Grupo> g = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            grupo = new Grupo();
            grupo.setId(rs.getInt("GrupoID"));
            grupo.setNome(rs.getString("nome"));
            grupo.setInstituicaoID(rs.getInt("instituicaoID"));
            grupo.setDataInicial(rs.getDate("dataInicial"));
            grupo.setDataFinal(rs.getDate("dataFinal"));
            g.add(grupo);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return g;
    }

    public List<Grupo> findParcial(String nome) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }        
        String select = "SELECT * FROM GRUPO WHERE nome like '%" + nome + "%'";
        Grupo grupo = null;
        List<Grupo> g = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            grupo = new Grupo();
            grupo.setId(rs.getInt("GrupoID"));
            grupo.setNome(rs.getString("nome"));
            grupo.setInstituicaoID(rs.getInt("instituicaoID"));
            grupo.setDataInicial(rs.getDate("dataInicial"));
            grupo.setDataFinal(rs.getDate("dataFinal"));
            g.add(grupo);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return g;
    }

}
