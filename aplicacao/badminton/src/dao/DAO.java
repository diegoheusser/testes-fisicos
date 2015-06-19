//DAO.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 5102011212
 */
public abstract class DAO {

    protected Connection connection;

    protected DAO() {
        this.connection = Conexao.getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void save(String insertSql, Object... parametros) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(insertSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        pstmt.execute();
        pstmt.close();

    }

    protected void update(String updateSql, Object id, Object... parametros) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(updateSql);
        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        pstmt.setObject(parametros.length + 1, id);
        pstmt.execute();
        pstmt.close();

    }

    protected void delete(String deleteSql, Object... parametros) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(deleteSql);
        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        pstmt.execute();
        pstmt.close();
    }

    protected void deleteAll(String deleteSql) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(deleteSql);
        pstmt.execute();
        pstmt.close();
    }
}
