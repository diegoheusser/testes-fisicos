//ProtocoloDAO.java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Protocolo;

/**
 * @author 5102011212
 */
public class ProtocoloDAO extends DAO {

    //método que retorna uma Protcolo conforme o id informado
    public Protocolo protocol(int id) throws SQLException {
        String select = "SELECT * FROM PROTOCOLO WHERE PROTOCOLOID = '" + id + "'";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Protocolo protocol = new Protocolo();
        while (rs.next()) {
            protocol.setId(rs.getInt("protocoloID"));
            protocol.setNome(rs.getString("nome"));
            protocol.setTipo(rs.getString("tipo"));
        }

        rs.close();
        stmt.close();
        return protocol;
    }

    //método que retorna uma lista com todos os protocolos cadastrados
    public List<Protocolo> protocol() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT * FROM PROTOCOLO";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        List<Protocolo> protocol = new ArrayList<>();

        while (rs.next()) {
            Protocolo p = new Protocolo();
            p.setId(rs.getInt("protocoloID"));
            p.setNome(rs.getString("nome"));
            p.setTipo(rs.getString("tipo"));
            protocol.add(p);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return protocol;
    }

    public List<Protocolo> protocolo(String nome, String tipo) throws SQLException {
        String seleciona = "SELECT * FROM Protocolo p ";

        if (!nome.isEmpty() && !tipo.isEmpty()) {
            seleciona += "WHERE p.nome LIKE '" + nome + "%' "
                    + "AND p.tipo = '" + tipo + "' ";
        } else if (nome.isEmpty() && !tipo.isEmpty()) {
            seleciona += "WHERE p.tipo = '" + tipo + "' ";
        } else if (!nome.isEmpty() && tipo.isEmpty()) {
            seleciona += "WHERE p.nome LIKE '" + nome + "%' ";
        }

        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(seleciona);

        List<Protocolo> lista = new ArrayList<>();

        while (rs.next()) {
            Protocolo p = new Protocolo();
            p.setId(rs.getInt("protocoloID"));
            p.setNome(rs.getString("nome"));
            p.setTipo(rs.getString("tipo"));
            lista.add(p);
        }
        
        rs.close();
        stmt.close();
        return lista;
    }
}
