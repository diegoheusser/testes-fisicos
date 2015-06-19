//TelaInicialDAO.java
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 5102011212
 */
public class TelaInicialDAO extends DAO {
    public void update(int laf) throws SQLException{
        String update = "Update laf set laf = ? "
                +"where lafId = ?";
        update(update, 1, laf);
    }
    
    public int laf() throws SQLException{
        String select = "Select * from laf";
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(select);
        int a = 1;
        while (rs.next()){
            a = rs.getInt("laf");
        }
        return a;
    }
}
