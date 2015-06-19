//InstituicaoDAO.java
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Instituicao;

/**
 * @author 5102011212
 */
public class InstituicaoDAO extends DAO {

    public void salvar(Instituicao instituicao) throws SQLException {
        String insert = "INSERT INTO INSTITUICAO(instituicaoID, cnpj, nome, cidade, rua, telefone, email,uf,cep) VALUES(?,?,?,?,?,?,?,?,?)";
        save(insert, instituicao.getId(), instituicao.getCnpj(), instituicao.getNome(), instituicao.getCidade(), instituicao.getRua(), instituicao.getTelefone(), instituicao.getEmail(), instituicao.getUf(), instituicao.getCep());
    }

    public void alterar(Instituicao instituicao) throws SQLException {
        String update = "UPDATE INSTITUICAO "
                + "SET cnpj = ?, nome = ?, cidade = ?, rua = ?, telefone = ?, email = ?, uf = ?, cep = ? "
                + "WHERE instituicaoID = ?";
        update(update, instituicao.getId(), instituicao.getCnpj(), instituicao.getNome(), instituicao.getCidade(), instituicao.getRua(), instituicao.getTelefone(), instituicao.getEmail(), instituicao.getUf(), instituicao.getCep());
    }

    public void excluir(int id) throws SQLException {
        String delete = "DELETE FROM INSTITUICAO WHERE INSTITUICAOID = ?";
        delete(delete, id);
    }

    public List<Instituicao> findInstituicao() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        List<Instituicao> instituicao = new ArrayList<>();
        String select = "SELECT * FROM INSTITUICAO";
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Instituicao i = new Instituicao();
            i.setId(rs.getInt("instituicaoID"));
            i.setCnpj(rs.getString("cnpj"));
            i.setNome(rs.getString("nome"));
            i.setCidade(rs.getString("cidade"));
            i.setRua(rs.getString("rua"));
            i.setTelefone(rs.getString("telefone"));
            i.setEmail(rs.getString("email"));
            i.setUf(rs.getString("uf"));
            i.setCep(rs.getString("cep"));
            instituicao.add(i);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return instituicao;
    }

    public List<Instituicao> findByName(String nome) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT * FROM INSTITUICAO WHERE nome = ?";
        Instituicao instituicao = null;
        List<Instituicao> i = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            instituicao = new Instituicao();
            instituicao.setId(rs.getInt("instituicaoID"));
            instituicao.setCnpj(rs.getString("cnpj"));
            instituicao.setNome(rs.getString("nome"));
            instituicao.setCidade(rs.getString("cidade"));
            instituicao.setRua(rs.getString("rua"));
            instituicao.setTelefone(rs.getString("telefone"));
            instituicao.setEmail(rs.getString("email"));
            instituicao.setUf(rs.getString("uf"));
            instituicao.setCep(rs.getString("cep"));
            i.add(instituicao);
        }

        rs.close();
        stmt.close();
        //fecha a conenexão
        super.connection.close();
        return i;
    }

    public List<Instituicao> findById(int id) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT * FROM INSTITUICAO WHERE nome = " + id + "";
        Instituicao instituicao = null;
        List<Instituicao> i = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            instituicao = new Instituicao();
            instituicao.setId(rs.getInt("instituicaoID"));
            instituicao.setCnpj(rs.getString("cnpj"));
            instituicao.setNome(rs.getString("nome"));
            instituicao.setCidade(rs.getString("cidade"));
            instituicao.setRua(rs.getString("rua"));
            instituicao.setTelefone(rs.getString("telefone"));
            instituicao.setEmail(rs.getString("email"));
            instituicao.setUf(rs.getString("uf"));
            instituicao.setCep(rs.getString("cep"));
            i.add(instituicao);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return i;
    }

    public Instituicao instituicao(int id) throws SQLException {
        String select = "SELECT * FROM INSTITUICAO WHERE instituicaoID = " + id + "";
        Instituicao instituicao = null;
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            instituicao = new Instituicao();
            instituicao.setId(rs.getInt("instituicaoID"));
            instituicao.setCnpj(rs.getString("cnpj"));
            instituicao.setNome(rs.getString("nome"));
            instituicao.setCidade(rs.getString("cidade"));
            instituicao.setRua(rs.getString("rua"));
            instituicao.setTelefone(rs.getString("telefone"));
            instituicao.setEmail(rs.getString("email"));
            instituicao.setUf(rs.getString("uf"));
            instituicao.setCep(rs.getString("cep"));
        }

        rs.close();
        stmt.close();
        return instituicao;
    }

    public List<Instituicao> findParcial(String nome) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        String select = "SELECT * FROM INSTITUICAO WHERE nome like '%" + nome + "%'";
        Instituicao instituicao = null;
        List<Instituicao> i = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(select);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            instituicao = new Instituicao();
            instituicao.setId(rs.getInt("instituicaoID"));
            instituicao.setCnpj(rs.getString("cnpj"));
            instituicao.setNome(rs.getString("nome"));
            instituicao.setCidade(rs.getString("cidade"));
            instituicao.setRua(rs.getString("rua"));
            instituicao.setTelefone(rs.getString("telefone"));
            instituicao.setEmail(rs.getString("email"));
            instituicao.setUf(rs.getString("uf"));
            instituicao.setCep(rs.getString("cep"));
            i.add(instituicao);
        }

        rs.close();
        stmt.close();
        //fecha a conexão
        super.connection.close();
        return i;
    }

    public List<Instituicao> findByInstituicao() throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        List<Instituicao> instituicao = new ArrayList<>();
        String select = "SELECT * FROM INSTITUICAO";
        PreparedStatement stmtcb = getConnection().prepareStatement(select);
        ResultSet rs = stmtcb.executeQuery();

        while (rs.next()) {
            Instituicao i = new Instituicao();
            i.setId(rs.getInt("instituicaoID"));
            i.setCnpj(rs.getString("cnpj"));
            i.setNome(rs.getString("nome"));
            i.setCidade(rs.getString("cidade"));
            i.setRua(rs.getString("rua"));
            i.setTelefone(rs.getString("telefone"));
            i.setEmail(rs.getString("email"));
            i.setUf(rs.getString("uf"));
            i.setCep(rs.getString("cep"));

            instituicao.add(i);
        }

        rs.close();
        stmtcb.close();
        //fecha a conexão
        super.connection.close();
        return instituicao;
    }

    public Instituicao object(int id) throws SQLException {
        //se não houver conexão então é criada uma nova conexão
        if (super.connection == null) {
            super.getConnection();
        }
        Instituicao inst = new Instituicao();
        String select = "SELECT * FROM INSTITUICAO WHERE INSTITUICAOID = " + id + "";
        PreparedStatement stmtcb = getConnection().prepareStatement(select);
        ResultSet rs = stmtcb.executeQuery();

        while (rs.next()) {
            inst.setId(rs.getInt("instituicaoId"));
            inst.setCep(rs.getString("cep"));
            inst.setCidade(rs.getString("cidade"));
            inst.setCnpj(rs.getString("cnpj"));
            inst.setEmail(rs.getString("email"));
            inst.setNome(rs.getString("nome"));
            inst.setRua(rs.getString("rua"));
            inst.setTelefone(rs.getString("telefone"));
            inst.setUf(rs.getString("uf"));
        }
        rs.close();
        stmtcb.close();
        //fecha a conexão
        super.connection.close();
        return inst;
    }
}
