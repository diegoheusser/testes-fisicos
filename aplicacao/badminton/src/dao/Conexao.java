//Conexao.java
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 5102011212
 */
public class Conexao {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/badminton";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "root";

    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            throw new RuntimeException(ex);
        }
        return null;
    }
}
